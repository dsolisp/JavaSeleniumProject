package com.automation.config;

/**
 * Centralized constants for the test automation framework.
 * Equivalent to Python's config/constants.py
 * 
 * Maintains DRY principles by centralizing commonly used values.
 */
public final class Constants {

    private Constants() {
        // Utility class - prevent instantiation
    }

    // ═══════════════════════════════════════════════════════════════════
    // USER AGENTS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Chrome user agent string to mimic real browser and avoid bot detection.
     */
    public static final String USER_AGENT_CHROME = 
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/119.0.0.0 Safari/537.36";

    /**
     * Firefox user agent string.
     */
    public static final String USER_AGENT_FIREFOX = 
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7; rv:119.0) " +
            "Gecko/20100101 Firefox/119.0";

    /**
     * Edge user agent string.
     */
    public static final String USER_AGENT_EDGE = 
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) " +
            "Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0";

    /**
     * Default user agent (Chrome) for general use.
     */
    public static final String USER_AGENT_DEFAULT = USER_AGENT_CHROME;

    // ═══════════════════════════════════════════════════════════════════
    // TIMEOUTS (in seconds)
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Default explicit wait timeout.
     */
    public static final int DEFAULT_EXPLICIT_WAIT = 10;

    /**
     * Default implicit wait timeout.
     */
    public static final int DEFAULT_IMPLICIT_WAIT = 0;

    /**
     * Default page load timeout.
     */
    public static final int DEFAULT_PAGE_LOAD_TIMEOUT = 30;

    /**
     * Default script execution timeout.
     */
    public static final int DEFAULT_SCRIPT_TIMEOUT = 30;

    /**
     * Short wait for quick operations.
     */
    public static final int SHORT_WAIT = 3;

    /**
     * Long wait for slow operations.
     */
    public static final int LONG_WAIT = 30;

    // ═══════════════════════════════════════════════════════════════════
    // RETRY CONFIGURATION
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Default number of retry attempts.
     */
    public static final int DEFAULT_RETRY_ATTEMPTS = 3;

    /**
     * Default delay between retries in milliseconds.
     */
    public static final long DEFAULT_RETRY_DELAY_MS = 1000;

    /**
     * Maximum delay between retries in milliseconds.
     */
    public static final long MAX_RETRY_DELAY_MS = 10000;

    /**
     * Exponential backoff multiplier.
     */
    public static final double RETRY_BACKOFF_MULTIPLIER = 2.0;

    // ═══════════════════════════════════════════════════════════════════
    // PERFORMANCE THRESHOLDS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Maximum acceptable page load time in milliseconds.
     */
    public static final long MAX_PAGE_LOAD_TIME_MS = 3000;

    /**
     * Maximum acceptable API response time in milliseconds.
     */
    public static final long MAX_API_RESPONSE_TIME_MS = 1000;

    /**
     * Maximum acceptable element interaction time in milliseconds.
     */
    public static final long MAX_ELEMENT_INTERACTION_TIME_MS = 500;

    /**
     * Slow test threshold in milliseconds.
     */
    public static final long SLOW_TEST_THRESHOLD_MS = 5000;

    // ═══════════════════════════════════════════════════════════════════
    // FILE PATHS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Screenshots directory.
     */
    public static final String SCREENSHOTS_DIR = "screenshots";

    /**
     * Test results directory.
     */
    public static final String TEST_RESULTS_DIR = "test_results";

    /**
     * Test data directory.
     */
    public static final String TEST_DATA_DIR = "src/main/resources/data";

    /**
     * Reports directory.
     */
    public static final String REPORTS_DIR = "reports";

    // ═══════════════════════════════════════════════════════════════════
    // BROWSER NAMES
    // ═══════════════════════════════════════════════════════════════════

    public static final String BROWSER_CHROME = "chrome";
    public static final String BROWSER_FIREFOX = "firefox";
    public static final String BROWSER_EDGE = "edge";
    public static final String BROWSER_SAFARI = "safari";

    // ═══════════════════════════════════════════════════════════════════
    // CONTENT TYPES
    // ═══════════════════════════════════════════════════════════════════

    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_XML = "application/xml";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
}

