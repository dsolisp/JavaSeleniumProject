package com.automation.config;

/**
 * Static constants that never change.
 * For configurable values, use Settings (reads from env vars).
 */
public final class Constants {

    private Constants() {}

    public static final class Timeouts {
        public static final int DEFAULT = 30000;
        public static final int NAVIGATION = 30000;
        public static final int ACTION = 10000;
        public static final int EXPECT = 5000;
        public static final int API = 10000;
        public static final int SHORT = 3000;
        public static final int LONG = 60000;
        public static final int ANIMATION = 500;
    }

    public static final class Urls {
        public static final String SAUCE_DEMO = "https://www.saucedemo.com";
        public static final String JSON_PLACEHOLDER = "https://jsonplaceholder.typicode.com";
        public static final String SWAPI = "https://swapi.dev/api";
        public static final String PRACTICE_APP = "http://localhost:8080";
    }

    public static final class HttpStatus {
        public static final int OK = 200;
        public static final int CREATED = 201;
        public static final int NO_CONTENT = 204;
        public static final int BAD_REQUEST = 400;
        public static final int UNAUTHORIZED = 401;
        public static final int FORBIDDEN = 403;
        public static final int NOT_FOUND = 404;
        public static final int INTERNAL_SERVER_ERROR = 500;
    }

    public static final class Browsers {
        public static final String CHROME = "chrome";
        public static final String FIREFOX = "firefox";
        public static final String EDGE = "edge";
    }

    public static final class UserAgents {
        public static final String CHROME = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
        public static final String FIREFOX = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:121.0) Gecko/20100101 Firefox/121.0";
        public static final String EDGE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0";
    }

    public static final class Viewports {
        public static final int[] DESKTOP = { 1920, 1080 };
        public static final int[] LAPTOP = { 1366, 768 };
        public static final int[] TABLET = { 768, 1024 };
        public static final int[] MOBILE = { 375, 667 };
    }

    public static final class Paths {
        public static final String DB = "app.db";
    }
}

