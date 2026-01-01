package com.automation.parallel;

import com.automation.config.Settings;
import com.automation.utils.WebDriverFactory;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JUnit 5 extension for parallel test execution with proper isolation.
 *
 * Consolidates thread-safe driver management and test context isolation
 * into a single, cohesive extension. Replaces the need for separate
 * ThreadSafeDriverManager and TestIsolation classes.
 *
 * Features:
 * - Thread-local WebDriver management
 * - Test context isolation for parallel execution
 * - Automatic screenshot capture on test failure
 * - Test timing and logging
 *
 * Usage:
 * {@code @ExtendWith(ParallelTestExtension.class)}
 * class MyParallelTest { ... }
 */
public class ParallelTestExtension implements
        BeforeEachCallback,
        AfterEachCallback,
        BeforeAllCallback,
        AfterAllCallback,
        TestWatcher {

    private static final Logger logger = LoggerFactory.getLogger(ParallelTestExtension.class);

    // ═══════════════════════════════════════════════════════════════════
    // THREAD-SAFE DRIVER MANAGEMENT
    // ═══════════════════════════════════════════════════════════════════

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Map<Long, WebDriver> activeDrivers = new ConcurrentHashMap<>();
    private static final AtomicInteger driverCount = new AtomicInteger(0);

    // ═══════════════════════════════════════════════════════════════════
    // TEST CONTEXT ISOLATION
    // ═══════════════════════════════════════════════════════════════════

    private static final ThreadLocal<TestContext> contextThreadLocal =
        ThreadLocal.withInitial(TestContext::new);

    // ═══════════════════════════════════════════════════════════════════
    // LIFECYCLE CALLBACKS
    // ═══════════════════════════════════════════════════════════════════

    @Override
    public void beforeAll(ExtensionContext context) {
        logger.info("Starting parallel test class: {}", context.getDisplayName());
    }

    @Override
    public void afterAll(ExtensionContext context) {
        logger.info("Finished parallel test class: {}", context.getDisplayName());
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        String testName = context.getDisplayName();
        long threadId = Thread.currentThread().threadId();

        logger.info("Starting test '{}' on thread {}", testName, threadId);

        // Initialize test context
        TestContext ctx = getContext();
        ctx.setTestName(testName);
        ctx.set("startTime", System.currentTimeMillis());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        String testName = context.getDisplayName();
        long threadId = Thread.currentThread().threadId();

        try {
            // Clean up driver
            if (hasDriver()) {
                removeDriver();
            }
        } catch (Exception e) {
            logger.warn("Error cleaning up driver: {}", e.getMessage());
        }

        // Calculate duration
        Long startTime = get("startTime");
        long duration = startTime != null ? System.currentTimeMillis() - startTime : 0;

        logger.info("Finished test '{}' on thread {} ({}ms)", testName, threadId, duration);

        // Clear test context
        clearContext();
    }

    // ═══════════════════════════════════════════════════════════════════
    // TEST WATCHER CALLBACKS
    // ═══════════════════════════════════════════════════════════════════

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.debug("Test passed: {}", context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("Test failed: {} - {}", context.getDisplayName(), cause.getMessage());

        // Take screenshot on failure if driver exists
        if (hasDriver()) {
            captureFailureScreenshot(context.getDisplayName());
        }
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger.warn("Test aborted: {} - {}", context.getDisplayName(), cause.getMessage());
    }

    @Override
    public void testDisabled(ExtensionContext context, java.util.Optional<String> reason) {
        logger.info("Test disabled: {} - {}", context.getDisplayName(),
            reason.orElse("No reason provided"));
    }

    // ═══════════════════════════════════════════════════════════════════
    // DRIVER MANAGEMENT (Static methods for test access)
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Get or create WebDriver for current thread.
     */
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    /**
     * Create new driver for current thread using default settings.
     */
    public static WebDriver createDriver() {
        Settings settings = Settings.getInstance();
        return createDriver(settings.getBrowser(), settings.isHeadless());
    }

    /**
     * Create driver with specific configuration.
     */
    public static WebDriver createDriver(String browser, boolean headless) {
        long threadId = Thread.currentThread().threadId();

        // Clean up any existing driver for this thread
        removeDriver();

        WebDriver driver = WebDriverFactory.createDriver(browser, headless);
        driverThreadLocal.set(driver);
        activeDrivers.put(threadId, driver);

        int count = driverCount.incrementAndGet();
        logger.info("Created driver for thread {} (total active: {})", threadId, count);

        return driver;
    }

    /**
     * Remove and quit driver for current thread.
     */
    public static void removeDriver() {
        long threadId = Thread.currentThread().threadId();
        WebDriver driver = driverThreadLocal.get();

        if (driver != null) {
            try {
                driver.quit();
                driverCount.decrementAndGet();
                logger.info("Removed driver for thread {}", threadId);
            } catch (Exception e) {
                logger.warn("Error quitting driver for thread {}: {}", threadId, e.getMessage());
            }
            driverThreadLocal.remove();
            activeDrivers.remove(threadId);
        }
    }

    /**
     * Quit all active drivers (for cleanup).
     */
    public static void quitAllDrivers() {
        logger.info("Quitting all {} active drivers", activeDrivers.size());

        activeDrivers.forEach((threadId, driver) -> {
            try {
                driver.quit();
            } catch (Exception e) {
                logger.warn("Error quitting driver for thread {}: {}", threadId, e.getMessage());
            }
        });

        activeDrivers.clear();
        driverCount.set(0);
    }

    /**
     * Check if current thread has a driver.
     */
    public static boolean hasDriver() {
        return driverThreadLocal.get() != null;
    }

    /**
     * Get count of active drivers.
     */
    public static int getActiveDriverCount() {
        return driverCount.get();
    }

    // ═══════════════════════════════════════════════════════════════════
    // CONTEXT MANAGEMENT (Static methods for test access)
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Get test context for current thread.
     */
    public static TestContext getContext() {
        return contextThreadLocal.get();
    }

    /**
     * Set value in current test context.
     */
    public static void set(String key, Object value) {
        getContext().set(key, value);
    }

    /**
     * Get value from current test context.
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) getContext().get(key);
    }

    /**
     * Get value with default.
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key, T defaultValue) {
        T value = (T) getContext().get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * Clear test context for current thread.
     */
    public static void clearContext() {
        getContext().clear();
        logger.debug("Cleared test context for thread {}", Thread.currentThread().threadId());
    }

    /**
     * Remove context entirely.
     */
    public static void removeContext() {
        contextThreadLocal.remove();
    }

    // ═══════════════════════════════════════════════════════════════════
    // SCREENSHOT ON FAILURE
    // ═══════════════════════════════════════════════════════════════════

    private void captureFailureScreenshot(String testName) {
        try {
            WebDriver driver = driverThreadLocal.get();
            if (driver instanceof TakesScreenshot) {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String sanitizedName = testName.replaceAll("[^a-zA-Z0-9]", "_");
                Path targetPath = Path.of("screenshots", "failures",
                    "FAILED_%s_%s.png".formatted(sanitizedName, timestamp));
                Files.createDirectories(targetPath.getParent());
                Files.copy(screenshot.toPath(), targetPath);
                logger.info("Failure screenshot saved: {}", targetPath);
            }
        } catch (IOException e) {
            logger.warn("Could not capture failure screenshot: {}", e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // TEST CONTEXT CLASS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Thread-isolated test context for storing test-specific data.
     */
    public static class TestContext {
        private final Map<String, Object> data = new ConcurrentHashMap<>();
        private final long threadId;
        private final long createdAt;
        private String testName;

        public TestContext() {
            this.threadId = Thread.currentThread().threadId();
            this.createdAt = System.currentTimeMillis();
        }

        public void set(String key, Object value) {
            data.put(key, value);
        }

        public Object get(String key) {
            return data.get(key);
        }

        public boolean has(String key) {
            return data.containsKey(key);
        }

        public void clear() {
            data.clear();
            testName = null;
        }

        public long getThreadId() {
            return threadId;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setTestName(String name) {
            this.testName = name;
        }

        public String getTestName() {
            return testName;
        }

        /**
         * Generate unique ID for current thread context.
         */
        public String getUniqueId() {
            return "%d_%d".formatted(threadId, System.currentTimeMillis());
        }

        /**
         * Generate unique email for test isolation.
         */
        public String getUniqueEmail(String prefix) {
            return "%s_%s@test.com".formatted(prefix, getUniqueId());
        }

        /**
         * Generate unique username for test isolation.
         */
        public String getUniqueUsername(String prefix) {
            return "%s_%s".formatted(prefix, getUniqueId());
        }
    }
}

