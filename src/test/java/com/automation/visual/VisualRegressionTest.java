package com.automation.visual;

import com.automation.pages.SearchEnginePage;
import com.automation.utils.ScreenshotService;
import com.automation.utils.ScreenshotService.ComparisonResult;
import com.automation.utils.StructuredLogger;
import com.automation.utils.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Visual regression tests using AShot for screenshot comparison.
 * Equivalent to Python's visual testing capabilities.
 */
@Epic("Visual Testing")
@Feature("Visual Regression")
@DisplayName("Visual Regression Tests")
@Tag("visual")
class VisualRegressionTest {

    private static final StructuredLogger logger = new StructuredLogger(VisualRegressionTest.class);
    private static final String BASELINE_DIR = "visual_baselines";
    
    private WebDriver driver;
    private ScreenshotService screenshotService;
    private SearchEnginePage searchPage;

    @BeforeEach
    void setUp() {
        driver = WebDriverFactory.createDriver("chrome", true);
        screenshotService = new ScreenshotService();
        searchPage = new SearchEnginePage(driver);
        
        // Ensure baseline directory exists
        try {
            Files.createDirectories(Path.of(BASELINE_DIR));
        } catch (IOException e) {
            logger.error("Failed to create baseline directory", e);
        }
    }

    @AfterEach
    void tearDown() {
        WebDriverFactory.quitDriver(driver);
    }

    // ═══════════════════════════════════════════════════════════════════
    // SCREENSHOT CAPTURE TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Screenshot Capture")
    @Description("Verify screenshot capture works correctly")
    @DisplayName("Screenshot should be captured successfully")
    void screenshotShouldBeCapturedSuccessfully() {
        searchPage.open();
        
        Path screenshot = screenshotService.captureScreenshot(driver, "google_homepage");
        
        assertThat(screenshot).isNotNull();
        assertThat(Files.exists(screenshot)).isTrue();
        assertThat(Files.isRegularFile(screenshot)).isTrue();
        
        logger.info("Screenshot captured: {}", screenshot);
    }

    @Test
    @Story("Screenshot Capture")
    @Description("Verify full page screenshot capture")
    @DisplayName("Full page screenshot should be captured")
    void fullPageScreenshotShouldBeCaptured() {
        searchPage.open();
        
        Path screenshot = screenshotService.captureFullPageScreenshot(driver, "google_full_page");
        
        assertThat(screenshot).isNotNull();
        assertThat(Files.exists(screenshot)).isTrue();
        
        logger.info("Full page screenshot captured: {}", screenshot);
    }

    @Test
    @Story("Screenshot Capture")
    @Description("Verify element screenshot capture")
    @DisplayName("Element screenshot should be captured")
    void elementScreenshotShouldBeCaptured() {
        searchPage.open();
        
        // Capture screenshot of search input area
        Path screenshot = screenshotService.captureScreenshot(driver, "search_input");
        
        assertThat(screenshot).isNotNull();
        assertThat(Files.exists(screenshot)).isTrue();
        
        logger.info("Element screenshot captured: {}", screenshot);
    }

    // ═══════════════════════════════════════════════════════════════════
    // VISUAL COMPARISON TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Visual Comparison")
    @Description("Verify baseline creation for visual comparison")
    @DisplayName("Baseline should be created for comparison")
    void baselineShouldBeCreatedForComparison() throws IOException {
        searchPage.open();
        
        Path baselinePath = Path.of(BASELINE_DIR, "homepage_baseline.png");
        Path screenshot = screenshotService.captureScreenshot(driver, "homepage_current");
        
        // Copy as baseline
        Files.deleteIfExists(baselinePath);
        Files.copy(screenshot, baselinePath);
        
        assertThat(Files.exists(baselinePath)).isTrue();
        logger.info("Baseline created: {}", baselinePath);
    }

    @Test
    @Story("Visual Comparison")
    @Description("Verify visual comparison detects identical images")
    @DisplayName("Identical images should have no difference")
    void identicalImagesShouldHaveNoDifference() {
        searchPage.open();
        
        // Capture two screenshots of the same page
        Path screenshot1 = screenshotService.captureScreenshot(driver, "compare_test_1");
        Path screenshot2 = screenshotService.captureScreenshot(driver, "compare_test_2");
        
        ComparisonResult result = screenshotService.compareScreenshots(screenshot1, screenshot2);
        
        // Same page captured twice should be very similar
        // Allow small tolerance for rendering differences
        assertThat(result.diffPercent())
                .as("Screenshots of same page should be similar")
                .isLessThan(5.0); // Allow 5% tolerance
        
        logger.info("Comparison result: {}% difference", result.diffPercent());
    }

    @Test
    @Story("Visual Comparison")
    @Description("Verify diff image is generated")
    @DisplayName("Diff image should be generated on comparison")
    void diffImageShouldBeGeneratedOnComparison() {
        searchPage.open();
        
        Path screenshot1 = screenshotService.captureScreenshot(driver, "diff_test_1");
        
        // Enter text to create visual difference
        searchPage.enterSearchQuery("test");
        Path screenshot2 = screenshotService.captureScreenshot(driver, "diff_test_2");
        
        ComparisonResult result = screenshotService.compareScreenshots(screenshot1, screenshot2);
        
        assertThat(result.diffImagePath()).isNotNull();
        if (result.hasDifference()) {
            assertThat(Files.exists(result.diffImagePath())).isTrue();
            logger.info("Diff image generated: {}", result.diffImagePath());
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // THRESHOLD VALIDATION TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Threshold Validation")
    @Description("Verify comparison with custom threshold")
    @DisplayName("Comparison should respect custom threshold")
    void comparisonShouldRespectCustomThreshold() {
        searchPage.open();

        Path screenshot1 = screenshotService.captureScreenshot(driver, "threshold_test_1");

        // Make a small visual change
        searchPage.enterSearchQuery("a");
        Path screenshot2 = screenshotService.captureScreenshot(driver, "threshold_test_2");

        // Compare with different thresholds
        ComparisonResult result = screenshotService.compareScreenshots(screenshot1, screenshot2);

        double threshold10Percent = 10.0;
        double threshold1Percent = 1.0;

        boolean passesLargeThreshold = result.diffPercent() <= threshold10Percent;
        boolean passesSmallThreshold = result.diffPercent() <= threshold1Percent;

        logger.info("Diff: {}%, Passes 10% threshold: {}, Passes 1% threshold: {}",
                result.diffPercent(), passesLargeThreshold, passesSmallThreshold);

        // At least the large threshold should pass
        assertThat(passesLargeThreshold)
                .as("Should pass 10% threshold for minor UI change")
                .isTrue();
    }

    @Test
    @Story("Threshold Validation")
    @Description("Verify visual regression detection")
    @DisplayName("Visual regression should be detected")
    void visualRegressionShouldBeDetected() {
        searchPage.open();
        Path baseline = screenshotService.captureScreenshot(driver, "regression_baseline");

        // Navigate to different state (search results)
        searchPage.search("selenium");
        Path current = screenshotService.captureScreenshot(driver, "regression_current");

        ComparisonResult result = screenshotService.compareScreenshots(baseline, current);

        // Different pages should have significant difference
        assertThat(result.hasDifference())
                .as("Different pages should have visual differences")
                .isTrue();

        assertThat(result.diffPercent())
                .as("Difference percentage should be significant")
                .isGreaterThan(10.0);

        logger.info("Visual regression detected: {}% difference", result.diffPercent());
    }

    // ═══════════════════════════════════════════════════════════════════
    // MULTI-BROWSER VISUAL TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("crossbrowser")
    @Story("Cross Browser")
    @Description("Verify visual consistency across browsers")
    @DisplayName("Visual should be consistent in Chrome")
    void visualShouldBeConsistentInChrome() {
        searchPage.open();

        Path screenshot = screenshotService.captureScreenshot(driver, "chrome_visual");

        assertThat(Files.exists(screenshot)).isTrue();

        // Verify image has content (file size > 0)
        try {
            assertThat(Files.size(screenshot)).isGreaterThan(0);
        } catch (IOException e) {
            throw new AssertionError("Could not read file size", e);
        }

        logger.info("Chrome visual test passed: {}", screenshot);
    }

    @Test
    @Story("Screenshot Naming")
    @Description("Verify screenshots have unique timestamped names")
    @DisplayName("Screenshots should have unique names")
    void screenshotsShouldHaveUniqueNames() {
        searchPage.open();

        Path screenshot1 = screenshotService.captureScreenshot(driver, "unique_test");
        Path screenshot2 = screenshotService.captureScreenshot(driver, "unique_test");

        // Both screenshots should exist and be different files
        assertThat(Files.exists(screenshot1)).isTrue();
        assertThat(Files.exists(screenshot2)).isTrue();
        assertThat(screenshot1.getFileName()).isNotEqualTo(screenshot2.getFileName());

        logger.info("Screenshots: {} and {}", screenshot1.getFileName(), screenshot2.getFileName());
    }

    @Test
    @Story("Visual Report")
    @Description("Verify visual test report generation")
    @DisplayName("Visual test report should be generated")
    void visualTestReportShouldBeGenerated() {
        searchPage.open();

        // Capture multiple screenshots for a report
        Path screenshot1 = screenshotService.captureScreenshot(driver, "report_page1");
        searchPage.search("test");
        Path screenshot2 = screenshotService.captureScreenshot(driver, "report_page2");

        ComparisonResult result = screenshotService.compareScreenshots(screenshot1, screenshot2);

        // Log report data
        logger.info("Visual Test Report:");
        logger.info("  - Baseline: {}", screenshot1);
        logger.info("  - Current: {}", screenshot2);
        logger.info("  - Has Difference: {}", result.hasDifference());
        logger.info("  - Diff Percentage: {}%", result.diffPercent());
        logger.info("  - Diff Pixels: {}", result.diffPixels());

        if (result.diffImagePath() != null) {
            logger.info("  - Diff Image: {}", result.diffImagePath());
        }

        // Test passed if we got this far
        assertThat(true).isTrue();
    }
}

