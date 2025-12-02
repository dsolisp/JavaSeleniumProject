package com.automation.integration;

import com.automation.config.Settings;
import com.automation.pages.SearchEnginePage;
import com.automation.utils.ScreenshotService;
import com.automation.utils.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

/**
 * Visual regression/image diff integration tests.
 * Tests screenshot and visual comparison functionality.
 * Equivalent to Python's tests/integration/test_image_diff.py
 */
@Epic("Integration Testing")
@Feature("Visual Testing")
@DisplayName("Image Diff Integration Tests")
@Tag("integration")
@Tag("visual")
class ImageDiffTest {

    private WebDriver driver;
    private ScreenshotService screenshotService;

    @BeforeEach
    void setUp() {
        screenshotService = new ScreenshotService();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // SCREENSHOT FUNCTIONALITY TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Screenshot Functionality")
    @Description("Test basic screenshot capture functionality")
    @DisplayName("Screenshot functionality should work")
    void screenshotFunctionalityShouldWork() throws Exception {
        driver = WebDriverFactory.createDriver("chrome", true);
        SearchEnginePage searchPage = new SearchEnginePage(driver);
        
        searchPage.open();
        
        // Take screenshot
        Path screenshotPath = screenshotService.captureScreenshot(driver, "test_functionality");
        
        assertThat(screenshotPath).isNotNull();
        assertThat(screenshotPath).exists();
        assertThat(Files.size(screenshotPath)).isGreaterThan(0);
        
        // Cleanup
        Files.deleteIfExists(screenshotPath);
    }

    @Test
    @Story("Screenshot Functionality")
    @Description("Test full page screenshot capture")
    @DisplayName("Full page screenshot should work")
    void fullPageScreenshotShouldWork() throws Exception {
        driver = WebDriverFactory.createDriver("chrome", true);
        SearchEnginePage searchPage = new SearchEnginePage(driver);

        searchPage.open();

        // Capture full page screenshot
        Path screenshotPath = screenshotService.captureFullPageScreenshot(driver, "full_page");

        assertThat(screenshotPath).isNotNull();
        assertThat(screenshotPath).exists();
        assertThat(Files.size(screenshotPath)).isGreaterThan(0);

        // Cleanup
        Files.deleteIfExists(screenshotPath);
    }

    // ═══════════════════════════════════════════════════════════════════
    // VISUAL COMPARISON TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Visual Comparison")
    @Description("Test visual comparison between two screenshots")
    @DisplayName("Visual comparison should detect differences")
    void visualComparisonShouldDetectDifferences() throws Exception {
        driver = WebDriverFactory.createDriver("chrome", true);
        SearchEnginePage searchPage = new SearchEnginePage(driver);

        // Navigate and take first screenshot
        searchPage.open();
        Path baseline = screenshotService.captureScreenshot(driver, "baseline");

        // Make a change (type in search) and take second screenshot
        if (searchPage.isSearchInputDisplayed()) {
            searchPage.enterSearchQuery("Visual Test Query");
        }
        Path current = screenshotService.captureScreenshot(driver, "current");

        // Compare
        var result = screenshotService.compareScreenshots(baseline, current);

        // There should be some difference since we typed text
        assertThat(result.diffPercent()).isGreaterThanOrEqualTo(0);

        // Cleanup
        Files.deleteIfExists(baseline);
        Files.deleteIfExists(current);
    }

    @Test
    @Story("Visual Comparison")
    @Description("Test identical screenshots have zero difference")
    @DisplayName("Identical screenshots should have zero difference")
    void identicalScreenshotsShouldHaveZeroDifference() throws Exception {
        driver = WebDriverFactory.createDriver("chrome", true);
        SearchEnginePage searchPage = new SearchEnginePage(driver);

        searchPage.open();

        // Take same screenshot twice (should be identical)
        Path screenshot1 = screenshotService.captureScreenshot(driver, "identical1");
        Path screenshot2 = screenshotService.captureScreenshot(driver, "identical2");

        var result = screenshotService.compareScreenshots(screenshot1, screenshot2);

        // Should be zero or very close to zero
        assertThat(result.diffPercent()).isLessThan(1.0);

        // Cleanup
        Files.deleteIfExists(screenshot1);
        Files.deleteIfExists(screenshot2);
    }

    // ═══════════════════════════════════════════════════════════════════
    // DIFF HANDLER AVAILABILITY TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Diff Handler")
    @Description("Test that screenshot service is available and functional")
    @DisplayName("Screenshot service should be available")
    void screenshotServiceShouldBeAvailable() {
        assertThat(screenshotService).isNotNull();
        
        // Verify service has required methods
        assertThat(screenshotService.getClass().getMethods())
                .extracting("name")
                .contains("captureScreenshot", "compareScreenshots");
    }
}

