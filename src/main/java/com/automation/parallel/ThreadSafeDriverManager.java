package com.automation.parallel;

import com.automation.config.Settings;
import com.automation.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread-safe WebDriver manager for parallel test execution.
 * Each thread gets its own isolated WebDriver instance.
 */
public class ThreadSafeDriverManager {

    private static final Logger logger = LoggerFactory.getLogger(ThreadSafeDriverManager.class);
    
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final Map<Long, WebDriver> activeDrivers = new ConcurrentHashMap<>();
    private static final AtomicInteger driverCount = new AtomicInteger(0);

    // ═══════════════════════════════════════════════════════════════════
    // DRIVER LIFECYCLE
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
     * Create new driver for current thread.
     */
    public static WebDriver createDriver() {
        Settings settings = Settings.getInstance();
        return createDriver(settings.getBrowser(), settings.isHeadless());
    }

    /**
     * Create driver with specific configuration.
     */
    public static WebDriver createDriver(String browser, boolean headless) {
        long threadId = Thread.currentThread().getId();
        
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
        long threadId = Thread.currentThread().getId();
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
     * Get count of active drivers.
     */
    public static int getActiveDriverCount() {
        return driverCount.get();
    }

    /**
     * Check if current thread has a driver.
     */
    public static boolean hasDriver() {
        return driverThreadLocal.get() != null;
    }
}

