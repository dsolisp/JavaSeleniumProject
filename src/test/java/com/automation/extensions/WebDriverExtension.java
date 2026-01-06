package com.automation.extensions;

import com.automation.config.Settings;
import com.automation.utils.ScreenshotService;
import com.automation.utils.WebDriverFactory;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.util.Optional;

/**
 * JUnit 5 Extension for WebDriver lifecycle management.
 * 
 * <p>Features:
 * <ul>
 *   <li>Thread-safe WebDriver creation/cleanup</li>
 *   <li>Automatic screenshot on test failure</li>
 *   <li>Allure report integration</li>
 *   <li>Test timing and logging</li>
 * </ul>
 * 
 * <p>Usage with parameter injection:
 * <pre>{@code
 * @ExtendWith(WebDriverExtension.class)
 * class MyTest {
 *     @Test
 *     void myTest(WebDriver driver) {
 *         driver.get("https://example.com");
 *     }
 * }
 * }</pre>
 * 
 * <p>Usage with static access:
 * <pre>{@code
 * @ExtendWith(WebDriverExtension.class)
 * class MyTest {
 *     @Test
 *     void myTest() {
 *         WebDriver driver = WebDriverExtension.getDriver();
 *     }
 * }
 * }</pre>
 */
public class WebDriverExtension implements 
        BeforeEachCallback, 
        AfterEachCallback, 
        ParameterResolver,
        TestWatcher {

    private static final Logger logger = LoggerFactory.getLogger(WebDriverExtension.class);
    private static final ThreadLocal<WebDriver> DRIVER_HOLDER = new ThreadLocal<>();
    private static final ThreadLocal<Long> TEST_START_TIME = new ThreadLocal<>();
    private static final String DRIVER_KEY = "webdriver";

    // ═══════════════════════════════════════════════════════════════════
    // LIFECYCLE CALLBACKS
    // ═══════════════════════════════════════════════════════════════════

    @Override
    public void beforeEach(ExtensionContext context) {
        TEST_START_TIME.set(System.currentTimeMillis());
        
        Settings settings = Settings.getInstance();
        WebDriver driver = WebDriverFactory.createDriver(settings.getBrowser(), settings.isHeadless());
        
        DRIVER_HOLDER.set(driver);
        getStore(context).put(DRIVER_KEY, driver);
        
        logger.info("[{}] Test started: {} (Thread: {})",
                getTestId(context),
                context.getDisplayName(),
                Thread.currentThread().getName());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        long duration = System.currentTimeMillis() - TEST_START_TIME.get();
        
        try {
            WebDriver driver = getDriverFromStore(context);
            WebDriverFactory.quitDriver(driver);
        } finally {
            DRIVER_HOLDER.remove();
            TEST_START_TIME.remove();
            getStore(context).remove(DRIVER_KEY);
        }
        
        logger.info("[{}] Test completed: {} (Duration: {}ms)",
                getTestId(context),
                context.getDisplayName(),
                duration);
    }

    // ═══════════════════════════════════════════════════════════════════
    // TEST WATCHER - SCREENSHOT ON FAILURE
    // ═══════════════════════════════════════════════════════════════════

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = getDriverFromStore(context);
        if (driver == null) return;

        String testName = context.getDisplayName().replaceAll("[^a-zA-Z0-9]", "_");
        
        try {
            // Capture screenshot to file
            ScreenshotService screenshotService = new ScreenshotService();
            Path screenshotPath = screenshotService.captureScreenshot(driver, "FAILED_" + testName);
            logger.error("Test failed - Screenshot saved: {}", screenshotPath);

            // Attach to Allure report
            if (driver instanceof TakesScreenshot ts) {
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Screenshot on Failure", 
                        "image/png", 
                        new ByteArrayInputStream(screenshot), 
                        ".png");
            }
        } catch (Exception e) {
            logger.warn("Could not capture failure screenshot: {}", e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // PARAMETER RESOLVER - INJECT WEBDRIVER
    // ═══════════════════════════════════════════════════════════════════

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType().equals(WebDriver.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return getDriverFromStore(extensionContext);
    }

    // ═══════════════════════════════════════════════════════════════════
    // STATIC ACCESS
    // ═══════════════════════════════════════════════════════════════════

    /** Get the WebDriver for the current thread. */
    public static WebDriver getDriver() {
        return DRIVER_HOLDER.get();
    }

    /** Get the WebDriver for the current thread as Optional. */
    public static Optional<WebDriver> getDriverOptional() {
        return Optional.ofNullable(DRIVER_HOLDER.get());
    }

    // ═══════════════════════════════════════════════════════════════════
    // HELPERS
    // ═══════════════════════════════════════════════════════════════════

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }

    private WebDriver getDriverFromStore(ExtensionContext context) {
        return getStore(context).get(DRIVER_KEY, WebDriver.class);
    }

    private String getTestId(ExtensionContext context) {
        return context.getTestClass().map(Class::getSimpleName).orElse("Unknown");
    }
}

