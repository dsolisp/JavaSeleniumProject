package com.automation.web;

import com.automation.config.Settings;
import com.automation.utils.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for web tests providing WebDriver setup and teardown.
 * Extend this class for any test that needs a browser.
 */
public abstract class BaseWebTest {

    protected WebDriver driver;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeEach
    void setUp(TestInfo testInfo) {
        Settings settings = Settings.getInstance();
        driver = WebDriverFactory.createDriver(settings.getBrowser(), settings.isHeadless());
        logger.info("Test started: {}", testInfo.getDisplayName());
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        WebDriverFactory.quitDriver(driver);
        logger.info("Test completed: {}", testInfo.getDisplayName());
    }
}

