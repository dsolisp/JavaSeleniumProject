package com.automation.integration;

import com.automation.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Concrete implementation of BasePage for integration testing.
 * Exposes protected methods for testing purposes.
 */
public class TestPage extends BasePage {

    public TestPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to URL.
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Get page title.
     */
    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Find element by locator (exposed for testing).
     */
    @Override
    public WebElement findElement(By locator) {
        return super.findElement(locator);
    }

    /**
     * Find multiple elements (exposed for testing).
     */
    public List<WebElement> findAllElements(By locator) {
        return findElements(locator);
    }

    /**
     * Type text into element (exposed for testing).
     */
    public void typeText(By locator, String text) {
        type(locator, text);
    }

    /**
     * Click element (exposed for testing).
     */
    public void clickElement(By locator) {
        click(locator);
    }

    /**
     * Check if element is visible (exposed for testing).
     */
    public boolean checkVisible(By locator) {
        return isElementVisible(locator);
    }

    /**
     * Take screenshot (exposed for testing).
     */
    public String takeScreenshotAsString(String name) {
        return captureScreenshot(name).toString();
    }

    /**
     * Get current URL.
     */
    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }
}

