package com.automation.web;

import com.automation.config.Settings;
import com.automation.utils.StructuredLogger;
import com.automation.utils.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;

/**
 * Base class for web tests providing WebDriver setup and teardown.
 */
public abstract class BaseWebTest {

    protected WebDriver driver;
    protected final StructuredLogger logger = new StructuredLogger(this.getClass());
    private long testStartTime;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        testStartTime = System.currentTimeMillis();
        
        Settings settings = Settings.getInstance();
        driver = WebDriverFactory.createDriver(
                settings.getBrowser(),
                settings.isHeadless()
        );
        
        logger.testStarted(
                testInfo.getDisplayName(),
                "web",
                settings.getBrowser()
        );
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        long duration = System.currentTimeMillis() - testStartTime;
        
        WebDriverFactory.quitDriver(driver);
        
        logger.testCompleted(
                testInfo.getDisplayName(),
                "COMPLETED",
                duration
        );
    }
}

