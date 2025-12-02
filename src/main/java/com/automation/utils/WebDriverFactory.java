package com.automation.utils;

import com.automation.config.BrowserCapabilities;
import com.automation.config.Settings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Factory for creating configured WebDriver instances.
 * Equivalent to Python's utils/webdriver_factory.py
 * 
 * Design Pattern: Factory Pattern - encapsulates driver creation logic
 */
public class WebDriverFactory {

    private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);
    
    public static final Set<String> SUPPORTED_BROWSERS = Set.of("chrome", "firefox", "edge");

    private WebDriverFactory() {
        // Private constructor to prevent instantiation
    }

    /**
     * Create a WebDriver instance using settings from configuration.
     */
    public static WebDriver createDriver() {
        Settings settings = Settings.getInstance();
        return createDriver(settings.getBrowser(), settings.isHeadless());
    }

    /**
     * Create a WebDriver instance with specified browser and headless mode.
     *
     * @param browser  Browser name (chrome, firefox, edge)
     * @param headless Whether to run in headless mode
     * @return Configured WebDriver instance
     */
    public static WebDriver createDriver(String browser, boolean headless) {
        String browserLower = browser.toLowerCase();
        
        if (!SUPPORTED_BROWSERS.contains(browserLower)) {
            throw new IllegalArgumentException(
                String.format("Unsupported browser: %s. Supported: %s", browser, SUPPORTED_BROWSERS)
            );
        }

        logger.info("Creating {} driver (headless: {})", browserLower, headless);

        WebDriver driver = switch (browserLower) {
            case "chrome" -> createChromeDriver(headless);
            case "firefox" -> createFirefoxDriver(headless);
            case "edge" -> createEdgeDriver(headless);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };

        configureDriver(driver);
        logger.info("WebDriver created successfully: {}", driver.getClass().getSimpleName());
        
        return driver;
    }

    private static WebDriver createChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(BrowserCapabilities.getChromeOptions(headless));
    }

    private static WebDriver createFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(BrowserCapabilities.getFirefoxOptions(headless));
    }

    private static WebDriver createEdgeDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(BrowserCapabilities.getEdgeOptions(headless));
    }

    private static void configureDriver(WebDriver driver) {
        Settings settings = Settings.getInstance();
        
        driver.manage().timeouts().implicitlyWait(settings.getImplicitWait());
        driver.manage().timeouts().pageLoadTimeout(settings.getPageLoadTimeout());
        driver.manage().window().maximize();
    }

    /**
     * Safely quit a WebDriver instance.
     */
    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            try {
                driver.quit();
                logger.info("WebDriver quit successfully");
            } catch (Exception e) {
                logger.warn("Error quitting WebDriver: {}", e.getMessage());
            }
        }
    }
}

