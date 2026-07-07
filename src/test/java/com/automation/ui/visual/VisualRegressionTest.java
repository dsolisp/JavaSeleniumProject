package com.automation.ui.visual;

import com.automation.config.Settings;
import com.automation.extensions.PageObjectExtension;
import com.automation.extensions.RetryExtension;
import com.automation.extensions.RetryOnFailure;
import com.automation.extensions.SharedDriver;
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
@SharedDriver
@ExtendWith({WebDriverExtension.class, PageObjectExtension.class, RetryExtension.class})
class VisualRegressionTest {

    private static final Logger logger = LoggerFactory.getLogger(VisualRegressionTest.class);
    private static final String BASELINE_DIR = "baselines";
    private static final String DIFFS_DIR = "diffs";

    private final Settings settings = Settings.getInstance();
    private ScreenshotService screenshotService;
    private LoginPage loginPage;

    @BeforeEach
    void setUp(WebDriver driver, LoginPage loginPage) {
        // Shared driver: reset to the canonical viewport so responsive resize tests don't leak dimensions.
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1280, 720));

        screenshotService = new ScreenshotService(Path.of(settings.getScreenshotsDir()), Path.of(BASELINE_DIR), Path.of(DIFFS_DIR));
        this.loginPage = loginPage;

        // Ensure baseline directory exists
        try {
            Files.createDirectories(Path.of(BASELINE_DIR));
        } catch (IOException e) {
            logger.error("Failed to create baseline directory", e);
        }

        logger.debug("Visual thresholds - Diff: {}%, Same page: {}%",
                settings.getVisualDiffThreshold(), settings.getVisualSamePageTolerance());
    }

    private void assertVisualSnapshot(WebDriver driver, String snapshotName) {
        assertVisualSnapshot(driver, snapshotName, settings.getVisualDiffThreshold());
    }

    private void assertVisualSnapshot(WebDriver driver, String snapshotName, double customThreshold) {
        Path baseline = Path.of(BASELINE_DIR).resolve(snapshotName + ".png");
        if (!Files.exists(baseline)) {
            screenshotService.saveBaseline(driver, snapshotName);
            logger.info("Created baseline for {}", snapshotName);
            return;
        }

        ComparisonResult result = screenshotService.compareWithBaseline(driver, snapshotName);
        assertThat(result.diffPercent())
                .as("Visual difference for %s", snapshotName)
                .isLessThanOrEqualTo(customThreshold);
    }

    // ── Login Page visual states ────────────────────────────────────────

    @Test
    @Story("Login Page")
    @DisplayName("Should match baseline for default login page")
    void shouldMatchBaselineForDefaultLoginPage(WebDriver driver) {
        loginPage.open();
        assertVisualSnapshot(driver, "login-page-default-state");
    }

    @Test
    @Story("Login Page")
    @DisplayName("Should match baseline for login error state")
    void shouldMatchBaselineForLoginErrorState(WebDriver driver) {
        loginPage.open();
        loginPage.login("invalid", "invalid");
        assertVisualSnapshot(driver, "login-page-error-state");
    }

    @Test
    @Story("Login Page")
    @DisplayName("Should match baseline for login form component only")
    void shouldMatchBaselineForLoginFormComponentOnly(WebDriver driver) {
        loginPage.open();
        assertVisualSnapshot(driver, "login-form-component");
    }

    // ── Inventory Page visual states ────────────────────────────────────

    @Test
    @Story("Inventory Page")
    @DisplayName("Should match baseline for inventory page full scroll")
    void shouldMatchBaselineForInventoryPageFullScroll(WebDriver driver) {
        loginPage.open();
        loginPage.loginAsStandardUser();
        assertVisualSnapshot(driver, "inventory-page-full");
    }

    @Test
    @Story("Inventory Page")
    @DisplayName("Should match baseline ignoring cart badge")
    void shouldMatchBaselineIgnoringCartBadge(WebDriver driver) {
        loginPage.open();
        loginPage.loginAsStandardUser();
        assertVisualSnapshot(driver, "inventory-page-clean");
    }

    // ── Responsive Layout — Cross-Device ────────────────────────────────

    @Test
    @Story("Responsive Layout")
    @DisplayName("Should match baseline for mobile view")
    void shouldMatchBaselineForMobileView(WebDriver driver) {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 812));
        loginPage.open();
        assertVisualSnapshot(driver, "login-page-mobile");
    }

    @Test
    @Story("Responsive Layout")
    @DisplayName("Should match baseline for tablet view")
    void shouldMatchBaselineForTabletView(WebDriver driver) {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(768, 1024));
        loginPage.open();
        assertVisualSnapshot(driver, "login-page-tablet");
    }

    @Test
    @Story("Responsive Layout")
    @DisplayName("Should match baseline for desktop view")
    void shouldMatchBaselineForDesktopView(WebDriver driver) {
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
        loginPage.open();
        assertVisualSnapshot(driver, "login-page-desktop");
    }

    // ── Advanced snapshot comparisons ───────────────────────────────────

    @Test
    @Story("Advanced Comparisons")
    @DisplayName("Should allow minor differences with 10% threshold")
    void shouldAllowMinorDifferences(WebDriver driver) {
        loginPage.open();
        loginPage.loginAsStandardUser();
        assertVisualSnapshot(driver, "inventory-flexible-comparison", 10.0);
    }

    @Test
    @Story("Advanced Comparisons")
    @DisplayName("Should detect even tiny differences with strict 1% threshold")
    void shouldDetectTinyDifferences(WebDriver driver) {
        loginPage.open();
        loginPage.loginAsStandardUser();
        assertVisualSnapshot(driver, "inventory-strict-comparison", 1.0);
    }

    // ── Component visual snapshots ───────────────────────────────────────

    @Test
    @Story("Component Snapshots")
    @DisplayName("Should match baseline for login button component")
    void shouldMatchBaselineForLoginButtonComponent(WebDriver driver) {
        loginPage.open();
        assertVisualSnapshot(driver, "login-button-component");
    }

    @Test
    @Story("Component Snapshots")
    @DisplayName("Should match baseline for username input field")
    void shouldMatchBaselineForUsernameInputField(WebDriver driver) {
        loginPage.open();
        assertVisualSnapshot(driver, "username-input-component");
    }

    @Test
    @Story("Component Snapshots")
    @DisplayName("Should match baseline for login logo")
    void shouldMatchBaselineForLoginLogo(WebDriver driver) {
        loginPage.open();
        assertVisualSnapshot(driver, "login-logo-component");
    }
}

