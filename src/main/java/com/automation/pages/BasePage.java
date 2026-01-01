package com.automation.pages;

import com.automation.config.Settings;
import com.automation.utils.ScreenshotService;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * Base page class providing common functionality for all page objects.
 *
 * Uses standard Selenium WebDriverWait for synchronization.
 * Design Pattern: Template Method - defines skeleton, subclasses implement specifics.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;
    protected final Logger log;
    protected final Settings settings;

    private final ScreenshotService screenshotService;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.settings = Settings.getInstance();
        this.wait = new WebDriverWait(driver, settings.getExplicitWait());
        this.actions = new Actions(driver);
        this.screenshotService = new ScreenshotService();
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    // ═══════════════════════════════════════════════════════════════════
    // NAVIGATION
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Navigate to a URL.
     */
    public void navigateTo(String url) {
        log.info("Navigating to: {}", url);
        driver.get(url);
    }

    /**
     * Get current URL.
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get page title.
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Refresh the page.
     */
    public void refresh() {
        driver.navigate().refresh();
    }

    // ═══════════════════════════════════════════════════════════════════
    // ELEMENT FINDING
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Find element with explicit wait.
     */
    protected WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Find multiple elements.
     */
    protected List<WebElement> findElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * Check if element is present (without waiting).
     */
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Check if element is visible.
     */
    protected boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // ELEMENT INTERACTIONS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Click an element (waits for clickable).
     */
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /**
     * Type text into an element.
     */
    protected void type(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Clear an input field.
     */
    protected void clear(By locator) {
        findElement(locator).clear();
    }

    /**
     * Submit a form.
     */
    protected void submit(By locator) {
        findElement(locator).submit();
    }

    /**
     * Press a key.
     */
    protected void pressKey(By locator, Keys key) {
        findElement(locator).sendKeys(key);
    }

    /**
     * Select option by visible text.
     */
    protected void selectByText(By locator, String text) {
        new Select(findElement(locator)).selectByVisibleText(text);
    }

    /**
     * Get text from an element.
     */
    protected String getText(By locator) {
        return findElement(locator).getText();
    }

    /**
     * Get attribute value.
     * Uses getDomAttribute for Selenium 4+ compatibility.
     */
    protected String getAttribute(By locator, String attribute) {
        return findElement(locator).getDomAttribute(attribute);
    }

    // ═══════════════════════════════════════════════════════════════════
    // ADVANCED INTERACTIONS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Hover over an element.
     */
    protected void hover(By locator) {
        actions.moveToElement(findElement(locator)).perform();
    }

    /**
     * Double-click an element.
     */
    protected void doubleClick(By locator) {
        actions.doubleClick(findElement(locator)).perform();
    }

    /**
     * Right-click an element.
     */
    protected void rightClick(By locator) {
        actions.contextClick(findElement(locator)).perform();
    }

    /**
     * Drag and drop.
     */
    protected void dragAndDrop(By source, By target) {
        actions.dragAndDrop(findElement(source), findElement(target)).perform();
    }

    /**
     * Scroll to element.
     */
    protected void scrollToElement(By locator) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                findElement(locator)
        );
    }

    /**
     * Execute JavaScript.
     */
    protected Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    // ═══════════════════════════════════════════════════════════════════
    // WAITS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Wait for element to be visible.
     */
    protected WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable.
     */
    protected WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to disappear.
     */
    protected boolean waitForInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Wait for text to be present in element.
     */
    protected boolean waitForTextPresent(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Wait with custom timeout.
     */
    protected WebElement waitFor(By locator, Duration timeout) {
        return new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ═══════════════════════════════════════════════════════════════════
    // SCREENSHOTS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Capture screenshot.
     */
    public java.nio.file.Path captureScreenshot(String name) {
        return screenshotService.captureScreenshot(driver, name);
    }

    /**
     * Capture full page screenshot.
     */
    public java.nio.file.Path captureFullPageScreenshot(String name) {
        return screenshotService.captureFullPageScreenshot(driver, name);
    }

    // ═══════════════════════════════════════════════════════════════════
    // UTILITY
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Get the WebDriver instance.
     */
    public WebDriver getDriver() {
        return driver;
    }
}

