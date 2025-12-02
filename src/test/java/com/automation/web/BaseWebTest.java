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
 */
public abstract class BaseWebTest {

    protected WebDriver driver;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private long testStartTime;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        testStartTime = System.currentTimeMillis();

        Settings settings = Settings.getInstance();
        driver = WebDriverFactory.createDriver(
                settings.getBrowser(),
                settings.isHeadless()
        );

        logger.info("Test started: {} (browser: {})",
                testInfo.getDisplayName(),
                settings.getBrowser());
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        long duration = System.currentTimeMillis() - testStartTime;

        WebDriverFactory.quitDriver(driver);

        logger.info("Test completed: {} ({}ms)",
                testInfo.getDisplayName(),
                duration);
    }
}

