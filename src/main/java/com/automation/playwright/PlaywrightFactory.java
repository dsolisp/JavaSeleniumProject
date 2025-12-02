package com.automation.playwright;

import com.automation.config.Constants;
import com.automation.config.Settings;
import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Factory for creating Playwright browser instances.
 * Equivalent to Python's utils/playwright_factory.py
 */
public class PlaywrightFactory {

    private static final Logger logger = LoggerFactory.getLogger(PlaywrightFactory.class);
    
    private static final ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> contextThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    // ═══════════════════════════════════════════════════════════════════
    // BROWSER CREATION
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Create and return a new Page instance with default settings.
     */
    public static Page createPage() {
        Settings settings = Settings.getInstance();
        return createPage(settings.getBrowser(), settings.isHeadless());
    }

    /**
     * Create and return a new Page instance.
     */
    public static Page createPage(String browserType, boolean headless) {
        logger.info("Creating Playwright {} browser (headless: {})", browserType, headless);
        
        Playwright playwright = Playwright.create();
        playwrightThreadLocal.set(playwright);
        
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
            .setHeadless(headless)
            .setSlowMo(headless ? 0 : 50);
        
        Browser browser = switch (browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(launchOptions);
            case "webkit", "safari" -> playwright.webkit().launch(launchOptions);
            default -> playwright.chromium().launch(launchOptions);
        };
        browserThreadLocal.set(browser);
        
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
            .setViewportSize(1920, 1080)
            .setUserAgent(Constants.USER_AGENT_CHROME)
            .setLocale("en-US");
        
        BrowserContext context = browser.newContext(contextOptions);
        contextThreadLocal.set(context);
        
        // Enable tracing for debugging
        context.tracing().start(new Tracing.StartOptions()
            .setScreenshots(true)
            .setSnapshots(true));
        
        Page page = context.newPage();
        pageThreadLocal.set(page);
        
        // Set default timeout
        page.setDefaultTimeout(Constants.DEFAULT_EXPLICIT_WAIT * 1000.0);
        
        logger.info("Playwright browser created successfully");
        return page;
    }

    /**
     * Create page with video recording enabled.
     */
    public static Page createPageWithRecording(String videoDir) {
        Settings settings = Settings.getInstance();
        return createPageWithRecording(settings.getBrowser(), settings.isHeadless(), videoDir);
    }

    /**
     * Create page with video recording.
     */
    public static Page createPageWithRecording(String browserType, boolean headless, String videoDir) {
        logger.info("Creating Playwright browser with video recording");
        
        Playwright playwright = Playwright.create();
        playwrightThreadLocal.set(playwright);
        
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
            .setHeadless(headless);
        
        Browser browser = switch (browserType.toLowerCase()) {
            case "firefox" -> playwright.firefox().launch(launchOptions);
            case "webkit" -> playwright.webkit().launch(launchOptions);
            default -> playwright.chromium().launch(launchOptions);
        };
        browserThreadLocal.set(browser);
        
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
            .setViewportSize(1920, 1080)
            .setRecordVideoDir(Paths.get(videoDir))
            .setRecordVideoSize(1280, 720);
        
        BrowserContext context = browser.newContext(contextOptions);
        contextThreadLocal.set(context);
        
        Page page = context.newPage();
        pageThreadLocal.set(page);
        
        return page;
    }

    // ═══════════════════════════════════════════════════════════════════
    // RESOURCE MANAGEMENT
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Get current page for the thread.
     */
    public static Page getPage() {
        return pageThreadLocal.get();
    }

    /**
     * Get current browser context.
     */
    public static BrowserContext getContext() {
        return contextThreadLocal.get();
    }

    /**
     * Close all resources for current thread.
     */
    public static void close() {
        logger.info("Closing Playwright resources");
        
        try {
            BrowserContext context = contextThreadLocal.get();
            if (context != null) {
                context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("test_results/trace.zip")));
                context.close();
            }
        } catch (Exception e) {
            logger.warn("Error closing context: {}", e.getMessage());
        }
        
        try {
            Browser browser = browserThreadLocal.get();
            if (browser != null) {
                browser.close();
            }
        } catch (Exception e) {
            logger.warn("Error closing browser: {}", e.getMessage());
        }
        
        try {
            Playwright playwright = playwrightThreadLocal.get();
            if (playwright != null) {
                playwright.close();
            }
        } catch (Exception e) {
            logger.warn("Error closing playwright: {}", e.getMessage());
        }
        
        pageThreadLocal.remove();
        contextThreadLocal.remove();
        browserThreadLocal.remove();
        playwrightThreadLocal.remove();
    }
}

