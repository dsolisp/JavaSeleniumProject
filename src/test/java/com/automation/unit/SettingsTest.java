package com.automation.unit;

import com.automation.config.Settings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for Settings configuration class.
 */
@DisplayName("Settings Configuration Tests")
class SettingsTest {

    @Test
    @DisplayName("Settings should be singleton")
    void settingsShouldBeSingleton() {
        Settings settings1 = Settings.getInstance();
        Settings settings2 = Settings.getInstance();
        
        assertThat(settings1).isSameAs(settings2);
    }

    @Test
    @DisplayName("Settings should have default browser")
    void settingsShouldHaveDefaultBrowser() {
        Settings settings = Settings.getInstance();
        
        assertThat(settings.getBrowser()).isNotNull();
        assertThat(settings.getBrowser()).isIn("chrome", "firefox", "edge");
    }

    @Test
    @DisplayName("Settings should have default timeouts")
    void settingsShouldHaveDefaultTimeouts() {
        Settings settings = Settings.getInstance();
        
        assertThat(settings.getImplicitWait()).isNotNull();
        assertThat(settings.getExplicitWait()).isNotNull();
        assertThat(settings.getPageLoadTimeout()).isNotNull();
        assertThat(settings.getImplicitWait().getSeconds()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Settings should have environment config")
    void settingsShouldHaveEnvironmentConfig() {
        Settings settings = Settings.getInstance();
        
        assertThat(settings.getEnvironment()).isNotNull();
        assertThat(settings.getBaseUrl()).isNotNull();
        assertThat(settings.getApiBaseUrl()).isNotNull();
    }

    @Test
    @DisplayName("Settings should have reporting config")
    void settingsShouldHaveReportingConfig() {
        Settings settings = Settings.getInstance();
        
        assertThat(settings.getReportsDir()).isNotNull();
        assertThat(settings.getScreenshotsDir()).isNotNull();
    }

    @Test
    @DisplayName("Settings should have performance thresholds")
    void settingsShouldHavePerformanceThresholds() {
        Settings settings = Settings.getInstance();
        
        assertThat(settings.getPageLoadThresholdMs()).isGreaterThan(0);
        assertThat(settings.getApiResponseThresholdMs()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Settings toString should include key values")
    void settingsToStringShouldIncludeKeyValues() {
        Settings settings = Settings.getInstance();
        String toString = settings.toString();
        
        assertThat(toString).contains("browser");
        assertThat(toString).contains("headless");
        assertThat(toString).contains("environment");
    }
}

