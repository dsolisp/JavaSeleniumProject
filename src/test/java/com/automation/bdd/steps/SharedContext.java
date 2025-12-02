package com.automation.bdd.steps;

import com.automation.pages.SauceDemoPage;
import org.openqa.selenium.WebDriver;

/**
 * Shared test context for Cucumber step definitions.
 * Uses Cucumber's built-in dependency injection (PicoContainer).
 */
public class SharedContext {
    
    private WebDriver driver;
    private SauceDemoPage sauceDemoPage;
    
    public WebDriver getDriver() {
        return driver;
    }
    
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    
    public SauceDemoPage getSauceDemoPage() {
        return sauceDemoPage;
    }
    
    public void setSauceDemoPage(SauceDemoPage sauceDemoPage) {
        this.sauceDemoPage = sauceDemoPage;
    }
    
    public void cleanup() {
        if (driver != null) {
            driver.quit();
            driver = null;
            sauceDemoPage = null;
        }
    }
}

