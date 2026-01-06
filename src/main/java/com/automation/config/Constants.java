package com.automation.config;

/**
 * Static constants that never change.
 * For configurable values, use Settings (reads from env vars).
 */
public final class Constants {

    private Constants() {}

    // User Agents (for bot detection avoidance)
    public static final String USER_AGENT_CHROME =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36";

    public static final String USER_AGENT_FIREFOX =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7; rv:119.0) Gecko/20100101 Firefox/119.0";

    public static final String USER_AGENT_EDGE =
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) " +
            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0";

    public static final String USER_AGENT_DEFAULT = USER_AGENT_CHROME;

    // Browser names
    public static final String BROWSER_CHROME = "chrome";
    public static final String BROWSER_FIREFOX = "firefox";
    public static final String BROWSER_EDGE = "edge";
}

