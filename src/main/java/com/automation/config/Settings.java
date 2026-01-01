package com.automation.config;

import java.time.Duration;
import java.util.Optional;

/**
 * Application settings loaded from environment variables with sensible defaults.
 * Equivalent to Python's config/settings.py
 *
 * Thread-safe singleton using Bill Pugh's Initialization-on-demand holder idiom.
 * This ensures lazy initialization without synchronization overhead.
 */
public class Settings {

    // Bill Pugh Singleton - thread-safe lazy initialization without synchronization
    private static class SettingsHolder {
        private static final Settings INSTANCE = new Settings();
    }

    // Browser Settings
    private final String browser;
    private final boolean headless;
    private final Duration implicitWait;
    private final Duration explicitWait;
    private final Duration pageLoadTimeout;

    // Environment
    private final String environment;
    private final String baseUrl;
    private final String apiBaseUrl;
    private final String sauceDemoUrl;
    private final String searchEngineUrl;

    // Reporting
    private final boolean enableAllure;
    private final String reportsDir;
    private final String screenshotsDir;

    // Performance Thresholds (milliseconds)
    private final long pageLoadThresholdMs;
    private final long apiResponseThresholdMs;

    private Settings() {
        this.browser = getEnv("BROWSER", "chrome");
        this.headless = getBoolEnv("HEADLESS", false);
        this.implicitWait = Duration.ofSeconds(getLongEnv("IMPLICIT_WAIT", 10));
        this.explicitWait = Duration.ofSeconds(getLongEnv("EXPLICIT_WAIT", 10));
        this.pageLoadTimeout = Duration.ofSeconds(getLongEnv("PAGE_LOAD_TIMEOUT", 30));

        this.environment = getEnv("ENVIRONMENT", "dev");
        this.baseUrl = getEnv("BASE_URL", "https://www.bing.com");
        this.apiBaseUrl = getEnv("API_BASE_URL", "https://jsonplaceholder.typicode.com");
        this.sauceDemoUrl = getEnv("SAUCE_DEMO_URL", "https://www.saucedemo.com");
        this.searchEngineUrl = getEnv("SEARCH_ENGINE_URL", "https://www.bing.com");

        this.enableAllure = getBoolEnv("ENABLE_ALLURE", true);
        this.reportsDir = getEnv("REPORTS_DIR", "reports");
        this.screenshotsDir = getEnv("SCREENSHOTS_DIR", "screenshots");

        this.pageLoadThresholdMs = getLongEnv("PAGE_LOAD_THRESHOLD_MS", 3000);
        this.apiResponseThresholdMs = getLongEnv("API_RESPONSE_THRESHOLD_MS", 2000);
    }

    /**
     * Returns the singleton instance of Settings.
     * Thread-safe due to class loading mechanism (Bill Pugh pattern).
     *
     * @return the Settings singleton instance
     */
    public static Settings getInstance() {
        return SettingsHolder.INSTANCE;
    }

    // Helper methods for environment variables
    private String getEnv(String key, String defaultValue) {
        return Optional.ofNullable(System.getenv(key)).orElse(defaultValue);
    }

    private boolean getBoolEnv(String key, boolean defaultValue) {
        String value = System.getenv(key);
        if (value == null) return defaultValue;
        return value.equalsIgnoreCase("true") || value.equals("1");
    }

    private long getLongEnv(String key, long defaultValue) {
        String value = System.getenv(key);
        if (value == null) return defaultValue;
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // Getters
    public String getBrowser() { return browser; }
    public boolean isHeadless() { return headless; }
    public Duration getImplicitWait() { return implicitWait; }
    public Duration getExplicitWait() { return explicitWait; }
    public Duration getPageLoadTimeout() { return pageLoadTimeout; }
    public String getEnvironment() { return environment; }
    public String getBaseUrl() { return baseUrl; }
    public String getApiBaseUrl() { return apiBaseUrl; }
    public String getSauceDemoUrl() { return sauceDemoUrl; }
    public String getSearchEngineUrl() { return searchEngineUrl; }
    public boolean isEnableAllure() { return enableAllure; }
    public String getReportsDir() { return reportsDir; }
    public String getScreenshotsDir() { return screenshotsDir; }
    public long getPageLoadThresholdMs() { return pageLoadThresholdMs; }
    public long getApiResponseThresholdMs() { return apiResponseThresholdMs; }

    @Override
    public String toString() {
        return "Settings{browser='%s', headless=%s, environment='%s', baseUrl='%s'}".formatted(
                browser, headless, environment, baseUrl
        );
    }
}

