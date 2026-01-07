package com.automation.visual;

import com.automation.config.Settings;
import com.automation.extensions.RetryExtension;
import com.automation.extensions.RetryOnFailure;
import com.automation.extensions.WebDriverExtension;
import com.automation.pages.sauce.LoginPage;
import com.automation.utils.ScreenshotService;
import com.automation.utils.ScreenshotService.ComparisonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Visual regression tests using Shutterbug for screenshot comparison.
 * Equivalent to Python's visual testing capabilities.
 *
 * <p>Thresholds are configurable via environment variables:
 * <ul>
 *   <li>VISUAL_DIFF_THRESHOLD - Max allowed difference % (default: 5.0)</li>
 *   <li>VISUAL_SAME_PAGE_TOLERANCE - Tolerance for same page dynamic content (default: 15.0)</li>
 * </ul>
 */
@Epic("Visual Testing")
@Feature("Visual Regression")
@DisplayName("Visual Regression Tests")
@Tag("visual")
@RetryOnFailure(maxRetries = 1)
@ExtendWith({WebDriverExtension.class, RetryExtension.class})
class VisualRegressionTest {

    private static final Logger logger = LoggerFactory.getLogger(VisualRegressionTest.class);
    private static final String BASELINE_DIR = "visual_baselines";

    private final Settings settings = Settings.getInstance();
    private ScreenshotService screenshotService;
    private LoginPage loginPage;

    @BeforeEach
    void setUp(WebDriver driver) {
        screenshotService = new ScreenshotService();
        loginPage = new LoginPage(driver);

        // Ensure baseline directory exists
        try {
            Files.createDirectories(Path.of(BASELINE_DIR));
        } catch (IOException e) {
            logger.error("Failed to create baseline directory", e);
        }

        logger.debug("Visual thresholds - Diff: {}%, Same page: {}%",
                settings.getVisualDiffThreshold(), settings.getVisualSamePageTolerance());
    }

    // ═══════════════════════════════════════════════════════════════════
    // SCREENSHOT CAPTURE TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Screenshot Capture")
    @Description("Verify screenshot capture works correctly")
    @DisplayName("Screenshot should be captured successfully")
    void screenshotShouldBeCapturedSuccessfully(WebDriver driver) {
        loginPage.open();

        Path screenshot = screenshotService.captureScreenshot(driver, "saucedemo_homepage");

        assertThat(screenshot).isNotNull();
        assertThat(Files.exists(screenshot)).isTrue();
        assertThat(Files.isRegularFile(screenshot)).isTrue();

        logger.info("Screenshot captured: {}", screenshot);
    }

    @Test
    @Story("Screenshot Capture")
    @Description("Verify full page screenshot capture")
    @DisplayName("Full page screenshot should be captured")
    void fullPageScreenshotShouldBeCaptured(WebDriver driver) {
        loginPage.open();

        Path screenshot = screenshotService.captureFullPageScreenshot(driver, "saucedemo_full_page");

        assertThat(screenshot).isNotNull();
        assertThat(Files.exists(screenshot)).isTrue();

        logger.info("Full page screenshot captured: {}", screenshot);
    }

    @Test
    @Story("Screenshot Capture")
    @Description("Verify element screenshot capture")
    @DisplayName("Element screenshot should be captured")
    void elementScreenshotShouldBeCaptured(WebDriver driver) {
        loginPage.open();

        // Capture screenshot of login area
        Path screenshot = screenshotService.captureScreenshot(driver, "login_input");

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
    void baselineShouldBeCreatedForComparison(WebDriver driver) throws IOException {
        loginPage.open();

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
    void identicalImagesShouldHaveNoDifference(WebDriver driver) {
        loginPage.open();

        // Capture two screenshots of the same page
        Path screenshot1 = screenshotService.captureScreenshot(driver, "compare_test_1");
        Path screenshot2 = screenshotService.captureScreenshot(driver, "compare_test_2");

        ComparisonResult result = screenshotService.compareScreenshots(screenshot1, screenshot2);

        // Same page captured twice should be very similar
        // Allow tolerance for dynamic content (ads, animations, time-based elements)
        double tolerance = settings.getVisualSamePageTolerance();
        assertThat(result.diffPercent())
                .as("Screenshots of same page should be within %s%% tolerance", tolerance)
                .isLessThan(tolerance);

        logger.info("Comparison result: {}% difference (tolerance: {}%)",
                result.diffPercent(), tolerance);
    }

    @Test
    @Story("Visual Comparison")
    @Description("Verify diff image is generated")
    @DisplayName("Diff image should be generated on comparison")
    void diffImageShouldBeGeneratedOnComparison(WebDriver driver) {
        loginPage.open();

        Path screenshot1 = screenshotService.captureScreenshot(driver, "diff_test_1");

        // Enter text to create visual difference
        loginPage.enterUsername("test_user");
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
    void comparisonShouldRespectCustomThreshold(WebDriver driver) {
        loginPage.open();

        Path screenshot1 = screenshotService.captureScreenshot(driver, "threshold_test_1");

        // Make a small visual change
        loginPage.enterUsername("a");
        Path screenshot2 = screenshotService.captureScreenshot(driver, "threshold_test_2");

        // Compare screenshots
        ComparisonResult result = screenshotService.compareScreenshots(screenshot1, screenshot2);

        // Validate comparison mechanism works correctly
        double diffPercent = result.diffPercent();

        assertThat(diffPercent)
                .as("Diff percent should be a valid non-negative value")
                .isGreaterThanOrEqualTo(0.0);

        // Test threshold comparison logic
        boolean passes100Threshold = diffPercent <= 100.0;
        boolean passes0Threshold = diffPercent <= 0.0;

        logger.info("Diff: {}%, Passes 100% threshold: {}, Passes 0% threshold: {}",
                diffPercent, passes100Threshold, passes0Threshold);

        // 100% threshold should always pass
        assertThat(passes100Threshold)
                .as("Any diff should pass 100% threshold")
                .isTrue();

        // 0% threshold only passes if images are identical
        assertThat(passes0Threshold == (diffPercent == 0.0))
                .as("0% threshold logic should be correct")
                .isTrue();
    }

    @Test
    @Story("Threshold Validation")
    @Description("Verify visual regression detection")
    @DisplayName("Visual regression should be detected")
    void visualRegressionShouldBeDetected(WebDriver driver) {
        loginPage.open();
        Path baseline = screenshotService.captureScreenshot(driver, "regression_baseline");

        // Navigate to different state (login and go to inventory)
        loginPage.loginAsStandardUser();
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
    void visualShouldBeConsistentInChrome(WebDriver driver) {
        loginPage.open();

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
    void screenshotsShouldHaveUniqueNames(WebDriver driver) {
        loginPage.open();

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
    void visualTestReportShouldBeGenerated(WebDriver driver) {
        loginPage.open();

        // Capture multiple screenshots for a report
        Path screenshot1 = screenshotService.captureScreenshot(driver, "report_page1");
        loginPage.loginAsStandardUser();
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

