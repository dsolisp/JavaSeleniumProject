package com.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Screenshot and visual comparison service.
 * Equivalent to Python's visual testing with Pillow + pixelmatch.
 * 
 * Uses AShot for advanced screenshot and image comparison.
 */
public class ScreenshotService {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotService.class);

    private final Path screenshotsDir;
    private final Path baselinesDir;
    private final Path diffsDir;

    public ScreenshotService() {
        this(Path.of("screenshots"), Path.of("baselines"), Path.of("diffs"));
    }

    public ScreenshotService(Path screenshotsDir, Path baselinesDir, Path diffsDir) {
        this.screenshotsDir = screenshotsDir;
        this.baselinesDir = baselinesDir;
        this.diffsDir = diffsDir;
        
        createDirectories();
    }

    private void createDirectories() {
        try {
            Files.createDirectories(screenshotsDir);
            Files.createDirectories(baselinesDir);
            Files.createDirectories(diffsDir);
        } catch (IOException e) {
            logger.error("Failed to create screenshot directories", e);
        }
    }

    /**
     * Capture a simple screenshot.
     */
    public Path captureScreenshot(WebDriver driver, String name) {
        if (!(driver instanceof TakesScreenshot)) {
            throw new IllegalArgumentException("Driver does not support screenshots");
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS"));
        String filename = "%s_%s.png".formatted(name, timestamp);
        Path filePath = screenshotsDir.resolve(filename);

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Use REPLACE_EXISTING to handle rare timestamp collisions
            Files.copy(screenshot.toPath(), filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            logger.info("Screenshot saved: {}", filePath);
            return filePath;
        } catch (IOException e) {
            logger.error("Failed to save screenshot: {}", name, e);
            throw new ScreenshotException("Failed to capture screenshot", e);
        }
    }

    /**
     * Capture a full page screenshot using AShot.
     */
    public Path captureFullPageScreenshot(WebDriver driver, String name) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = "%s_%s.png".formatted(name, timestamp);
        Path filePath = screenshotsDir.resolve(filename);

        try {
            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(driver);
            
            ImageIO.write(screenshot.getImage(), "PNG", filePath.toFile());
            logger.info("Full page screenshot saved: {}", filePath);
            return filePath;
        } catch (IOException e) {
            logger.error("Failed to save full page screenshot: {}", name, e);
            throw new ScreenshotException("Failed to capture full page screenshot", e);
        }
    }

    /**
     * Compare two images and return difference percentage.
     */
    public ComparisonResult compareImages(Path baseline, Path current) {
        try {
            BufferedImage baselineImg = ImageIO.read(baseline.toFile());
            BufferedImage currentImg = ImageIO.read(current.toFile());

            ImageDiffer differ = new ImageDiffer();
            ImageDiff diff = differ.makeDiff(baselineImg, currentImg);

            boolean hasDifference = diff.hasDiff();
            int diffSize = diff.getDiffSize();
            int totalPixels = baselineImg.getWidth() * baselineImg.getHeight();
            double diffPercent = (double) diffSize / totalPixels * 100;

            // Save diff image if there are differences
            Path diffPath = null;
            if (hasDifference) {
                String diffFilename = "diff_" + current.getFileName();
                diffPath = diffsDir.resolve(diffFilename);
                ImageIO.write(diff.getMarkedImage(), "PNG", diffPath.toFile());
                logger.info("Diff image saved: {}", diffPath);
            }

            return new ComparisonResult(hasDifference, diffPercent, diffSize, diffPath);
        } catch (IOException e) {
            logger.error("Failed to compare images", e);
            throw new ScreenshotException("Failed to compare images", e);
        }
    }

    /**
     * Save a baseline image.
     */
    public Path saveBaseline(WebDriver driver, String name) {
        String filename = name + ".png";
        Path filePath = baselinesDir.resolve(filename);

        try {
            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(driver);
            
            ImageIO.write(screenshot.getImage(), "PNG", filePath.toFile());
            logger.info("Baseline saved: {}", filePath);
            return filePath;
        } catch (IOException e) {
            throw new ScreenshotException("Failed to save baseline", e);
        }
    }

    /**
     * Compare current screenshot against baseline.
     */
    public ComparisonResult compareWithBaseline(WebDriver driver, String baselineName) {
        Path baseline = baselinesDir.resolve(baselineName + ".png");
        if (!Files.exists(baseline)) {
            throw new ScreenshotException("Baseline not found: " + baseline);
        }

        Path current = captureFullPageScreenshot(driver, "current_" + baselineName);
        return compareImages(baseline, current);
    }

    /**
     * Compare two screenshots (alias for compareImages).
     */
    public ComparisonResult compareScreenshots(Path screenshot1, Path screenshot2) {
        return compareImages(screenshot1, screenshot2);
    }

    // Record for comparison results
    public record ComparisonResult(
            boolean hasDifference,
            double diffPercent,
            int diffPixels,
            Path diffImagePath
    ) {}

    public static class ScreenshotException extends RuntimeException {
        public ScreenshotException(String message) {
            super(message);
        }
        public ScreenshotException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

