package com.automation.utils;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.automation.config.Settings;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * <p>Uses Shutterbug for advanced screenshots (full page, scrolling).
 * Uses image-comparison (romankh3) for visual diff detection.
 *
 * <p>Configurable via environment variables:
 * <ul>
 *   <li>VISUAL_PIXEL_TOLERANCE - Pixel-level tolerance (default: 0.1)</li>
 * </ul>
 */
public class ScreenshotService {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotService.class);

	private static final Settings settings = Settings.getInstance();
	private final Path screenshotsDir;
	private final Path baselinesDir;
	private final Path diffsDir;

    private volatile boolean directoriesInitialized = false;

    public ScreenshotService() {
        // Default to the configured screenshots directory, while keeping
        // baselines/ and diffs/ at the project root for compatibility
        // with existing documentation and visual tests.
        this(Path.of(settings.getScreenshotsDir()), Path.of("baselines"), Path.of("diffs"));
    }

    public ScreenshotService(Path screenshotsDir, Path baselinesDir, Path diffsDir) {
        this.screenshotsDir = screenshotsDir;
        this.baselinesDir = baselinesDir;
        this.diffsDir = diffsDir;
        // No directory creation in constructor - lazy initialization instead
    }

    /**
     * Lazily creates directories when first needed.
     * Thread-safe using double-checked locking.
     */
    private void ensureDirectoriesExist() {
        if (!directoriesInitialized) {
            synchronized (this) {
                if (!directoriesInitialized) {
                    createDirectories();
                    directoriesInitialized = true;
                }
            }
        }
    }

    private void createDirectories() {
        try {
            Files.createDirectories(screenshotsDir);
            Files.createDirectories(baselinesDir);
            Files.createDirectories(diffsDir);
            logger.debug("Screenshot directories created: {}, {}, {}",
                    screenshotsDir, baselinesDir, diffsDir);
        } catch (IOException e) {
            logger.error("Failed to create screenshot directories", e);
            throw new ScreenshotException("Failed to create screenshot directories", e);
        }
    }

    /**
     * Capture a simple screenshot.
     */
    public Path captureScreenshot(WebDriver driver, String name) {
        ensureDirectoriesExist();

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
     * Capture a full page screenshot using Shutterbug.
     */
    public Path captureFullPageScreenshot(WebDriver driver, String name) {
        ensureDirectoriesExist();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = "%s_%s.png".formatted(name, timestamp);
        Path filePath = screenshotsDir.resolve(filename);

        try {
            BufferedImage image = Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .getImage();

            ImageIO.write(image, "PNG", filePath.toFile());
            logger.info("Full page screenshot saved: {}", filePath);
            return filePath;
        } catch (IOException e) {
            logger.error("Failed to save full page screenshot: {}", name, e);
            throw new ScreenshotException("Failed to capture full page screenshot", e);
        }
    }

    /**
     * Compare two images and return difference percentage.
     * Uses image-comparison library (romankh3) for professional visual diff.
     */
    public ComparisonResult compareImages(Path baseline, Path current) {
        try {
            BufferedImage baselineImg = ImageIO.read(baseline.toFile());
            BufferedImage currentImg = ImageIO.read(current.toFile());

            // Use image-comparison library for comparison
            ImageComparison imageComparison = new ImageComparison(baselineImg, currentImg);
            double pixelTolerance = settings.getVisualPixelTolerance();
            imageComparison.setPixelToleranceLevel(pixelTolerance);
            logger.debug("Image comparison using pixel tolerance: {}", pixelTolerance);

            ImageComparisonResult comparisonResult = imageComparison.compareImages();

            boolean hasDifference = comparisonResult.getImageComparisonState() != ImageComparisonState.MATCH;
            double diffPercent = comparisonResult.getDifferencePercent();

            // Save diff image if there are differences
            Path diffPath = null;
            if (hasDifference) {
                ensureDirectoriesExist();
                String diffFilename = "diff_" + current.getFileName();
                diffPath = diffsDir.resolve(diffFilename);

                // Save the result image with differences highlighted
                ImageComparisonUtil.saveImage(diffPath.toFile(), comparisonResult.getResult());
                logger.info("Diff image saved: {}", diffPath);
            }

            // Calculate approximate diff pixels from percentage
            int totalPixels = baselineImg.getWidth() * baselineImg.getHeight();
            int diffPixels = (int) (diffPercent / 100.0 * totalPixels);

            return new ComparisonResult(hasDifference, diffPercent, diffPixels, diffPath);
        } catch (IOException e) {
            logger.error("Failed to compare images", e);
            throw new ScreenshotException("Failed to compare images", e);
        }
    }

    /**
     * Save a baseline image.
     */
    public Path saveBaseline(WebDriver driver, String name) {
        ensureDirectoriesExist();

        String filename = name + ".png";
        Path filePath = baselinesDir.resolve(filename);

        try {
            BufferedImage image = Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .getImage();

            ImageIO.write(image, "PNG", filePath.toFile());
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

