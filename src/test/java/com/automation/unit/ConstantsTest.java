package com.automation.unit;

import com.automation.config.Constants;
import com.automation.config.Settings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for Constants class.
 * Equivalent to C#'s ConstantsTests.cs - ensures framework constants are correctly defined.
 */
@Epic("Framework Stability")
@Feature("Constants Validation")
@DisplayName("Constants Tests")
@Tag("unit")
class ConstantsTest {

    // ═══════════════════════════════════════════════════════════════════
    // USER AGENT TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Description("Chrome user agent should contain 'Chrome'")
    @DisplayName("UserAgent Chrome should contain Chrome")
    void userAgentChromeShouldContainChrome() {
        assertThat(Constants.USER_AGENT_CHROME).contains("Chrome");
    }

    @Test
    @Description("Chrome user agent should contain WebKit")
    @DisplayName("UserAgent Chrome should contain WebKit")
    void userAgentChromeShouldContainWebKit() {
        assertThat(Constants.USER_AGENT_CHROME).contains("AppleWebKit");
    }

    @Test
    @Description("Firefox user agent should contain 'Firefox'")
    @DisplayName("UserAgent Firefox should contain Firefox")
    void userAgentFirefoxShouldContainFirefox() {
        assertThat(Constants.USER_AGENT_FIREFOX).contains("Firefox");
    }

    @Test
    @Description("Firefox user agent should contain 'Gecko'")
    @DisplayName("UserAgent Firefox should contain Gecko")
    void userAgentFirefoxShouldContainGecko() {
        assertThat(Constants.USER_AGENT_FIREFOX).contains("Gecko");
    }

    @Test
    @Description("Edge user agent should contain 'Edg'")
    @DisplayName("UserAgent Edge should contain Edg")
    void userAgentEdgeShouldContainEdg() {
        assertThat(Constants.USER_AGENT_EDGE).contains("Edg");
    }

    @Test
    @Description("Default user agent should be Chrome")
    @DisplayName("Default UserAgent should be Chrome")
    void defaultUserAgentShouldBeChrome() {
        assertThat(Constants.USER_AGENT_DEFAULT).isEqualTo(Constants.USER_AGENT_CHROME);
    }

    // ═══════════════════════════════════════════════════════════════════
    // TIMEOUT TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Description("Default explicit wait should be positive")
    @DisplayName("Timeouts Default should be positive")
    void timeoutsDefaultShouldBePositive() {
        assertThat(Constants.DEFAULT_EXPLICIT_WAIT).isGreaterThan(0);
    }

    @Test
    @Description("Short wait should be less than default")
    @DisplayName("Timeouts Short should be less than Default")
    void timeoutsShortShouldBeLessThanDefault() {
        assertThat(Constants.SHORT_WAIT).isLessThan(Constants.DEFAULT_EXPLICIT_WAIT);
    }

    @Test
    @Description("Long wait should be greater than default")
    @DisplayName("Timeouts Long should be greater than Default")
    void timeoutsLongShouldBeGreaterThanDefault() {
        assertThat(Constants.LONG_WAIT).isGreaterThan(Constants.DEFAULT_EXPLICIT_WAIT);
    }

    @Test
    @Description("Page load timeout should be positive")
    @DisplayName("PageLoad timeout should be positive")
    void pageLoadTimeoutShouldBePositive() {
        assertThat(Constants.DEFAULT_PAGE_LOAD_TIMEOUT).isGreaterThan(0);
    }

    // ═══════════════════════════════════════════════════════════════════
    // BROWSER TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Description("Browser Chrome constant should be 'chrome'")
    @DisplayName("Browser Chrome should be chrome")
    void browserChromeShouldBeChrome() {
        assertThat(Constants.BROWSER_CHROME).isEqualTo("chrome");
    }

    @Test
    @Description("Browser Firefox constant should be 'firefox'")
    @DisplayName("Browser Firefox should be firefox")
    void browserFirefoxShouldBeFirefox() {
        assertThat(Constants.BROWSER_FIREFOX).isEqualTo("firefox");
    }

    @Test
    @Description("Browser Edge constant should be 'edge'")
    @DisplayName("Browser Edge should be edge")
    void browserEdgeShouldBeEdge() {
        assertThat(Constants.BROWSER_EDGE).isEqualTo("edge");
    }

    // ═══════════════════════════════════════════════════════════════════
    // RETRY CONFIGURATION TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Description("Default retry attempts should be positive")
    @DisplayName("Retry attempts should be positive")
    void retryAttemptsShouldBePositive() {
        assertThat(Constants.DEFAULT_RETRY_ATTEMPTS).isGreaterThan(0);
    }

    @Test
    @Description("Retry delay should be positive")
    @DisplayName("Retry delay should be positive")
    void retryDelayShouldBePositive() {
        assertThat(Constants.DEFAULT_RETRY_DELAY_MS).isGreaterThan(0);
    }

    // ═══════════════════════════════════════════════════════════════════
    // SETTINGS TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Description("Settings base URL should not be empty")
    @DisplayName("Settings baseUrl should not be empty")
    void settingsBaseUrlShouldNotBeEmpty() {
        assertThat(Settings.getInstance().getBaseUrl()).isNotEmpty();
    }

    @Test
    @Description("Settings browser should not be empty")
    @DisplayName("Settings browser should not be empty")
    void settingsBrowserShouldNotBeEmpty() {
        assertThat(Settings.getInstance().getBrowser()).isNotEmpty();
    }
}

