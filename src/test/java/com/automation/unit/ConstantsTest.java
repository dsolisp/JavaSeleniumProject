package com.automation.unit;

import com.automation.config.Constants;
import com.automation.config.Settings;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for Constants and Settings classes.
 */
@Epic("Framework Stability")
@Feature("Configuration Validation")
@DisplayName("Configuration Tests")
@Tag("unit")
class ConstantsTest {

    private final Settings settings = Settings.getInstance();

    // ═══════════════════════════════════════════════════════════════════
    // CONSTANTS: USER AGENTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("UserAgent Chrome should contain Chrome")
    void userAgentChromeShouldContainChrome() {
        assertThat(Constants.USER_AGENT_CHROME).contains("Chrome");
    }

    @Test
    @DisplayName("UserAgent Firefox should contain Firefox")
    void userAgentFirefoxShouldContainFirefox() {
        assertThat(Constants.USER_AGENT_FIREFOX).contains("Firefox");
    }

    @Test
    @DisplayName("UserAgent Edge should contain Edg")
    void userAgentEdgeShouldContainEdg() {
        assertThat(Constants.USER_AGENT_EDGE).contains("Edg");
    }

    @Test
    @DisplayName("Default UserAgent should be Chrome")
    void defaultUserAgentShouldBeChrome() {
        assertThat(Constants.USER_AGENT_DEFAULT).isEqualTo(Constants.USER_AGENT_CHROME);
    }

    // ═══════════════════════════════════════════════════════════════════
    // CONSTANTS: BROWSER NAMES
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Browser constants should be lowercase")
    void browserConstantsShouldBeLowercase() {
        assertThat(Constants.BROWSER_CHROME).isEqualTo("chrome");
        assertThat(Constants.BROWSER_FIREFOX).isEqualTo("firefox");
        assertThat(Constants.BROWSER_EDGE).isEqualTo("edge");
    }

    // ═══════════════════════════════════════════════════════════════════
    // SETTINGS: TIMEOUTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Settings explicit wait should be positive")
    void settingsExplicitWaitShouldBePositive() {
        assertThat(settings.getExplicitWait().toSeconds()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Settings page load timeout should be positive")
    void settingsPageLoadTimeoutShouldBePositive() {
        assertThat(settings.getPageLoadTimeout().toSeconds()).isGreaterThan(0);
    }

    // ═══════════════════════════════════════════════════════════════════
    // SETTINGS: URLS AND ENVIRONMENT
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Settings baseUrl should not be empty")
    void settingsBaseUrlShouldNotBeEmpty() {
        assertThat(settings.getBaseUrl()).isNotEmpty();
    }

    @Test
    @DisplayName("Settings browser should not be empty")
    void settingsBrowserShouldNotBeEmpty() {
        assertThat(settings.getBrowser()).isNotEmpty();
    }

    @Test
    @DisplayName("Settings screenshots dir should not be empty")
    void settingsScreenshotsDirShouldNotBeEmpty() {
        assertThat(settings.getScreenshotsDir()).isNotEmpty();
    }
}

