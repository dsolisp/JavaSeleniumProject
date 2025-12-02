package com.automation.unit;

import com.automation.utils.WebDriverFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for WebDriverFactory.
 */
@DisplayName("WebDriverFactory Tests")
class WebDriverFactoryTest {

    @Test
    @DisplayName("Should have supported browsers defined")
    void shouldHaveSupportedBrowsersDefined() {
        assertThat(WebDriverFactory.SUPPORTED_BROWSERS)
                .containsExactlyInAnyOrder("chrome", "firefox", "edge");
    }

    @ParameterizedTest
    @ValueSource(strings = {"chrome", "CHROME", "Chrome"})
    @DisplayName("Should support Chrome in any case")
    void shouldSupportChromeInAnyCase(String browser) {
        assertThat(WebDriverFactory.SUPPORTED_BROWSERS)
                .contains(browser.toLowerCase());
    }

    @ParameterizedTest
    @ValueSource(strings = {"firefox", "FIREFOX", "Firefox"})
    @DisplayName("Should support Firefox in any case")
    void shouldSupportFirefoxInAnyCase(String browser) {
        assertThat(WebDriverFactory.SUPPORTED_BROWSERS)
                .contains(browser.toLowerCase());
    }

    @ParameterizedTest
    @ValueSource(strings = {"edge", "EDGE", "Edge"})
    @DisplayName("Should support Edge in any case")
    void shouldSupportEdgeInAnyCase(String browser) {
        assertThat(WebDriverFactory.SUPPORTED_BROWSERS)
                .contains(browser.toLowerCase());
    }

    @Test
    @DisplayName("Should throw exception for unsupported browser")
    void shouldThrowExceptionForUnsupportedBrowser() {
        assertThatThrownBy(() -> WebDriverFactory.createDriver("safari", false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unsupported browser");
    }

    @Test
    @DisplayName("Should throw exception for invalid browser name")
    void shouldThrowExceptionForInvalidBrowserName() {
        assertThatThrownBy(() -> WebDriverFactory.createDriver("invalid", false))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("quitDriver should handle null driver gracefully")
    void quitDriverShouldHandleNullDriverGracefully() {
        // Should not throw exception
        WebDriverFactory.quitDriver(null);
    }
}

