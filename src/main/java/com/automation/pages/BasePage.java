package com.automation.pages;

import com.automation.config.Settings;
import com.automation.utils.ErrorHandler;
import com.automation.utils.PerformanceMonitor;
import com.automation.utils.ScreenshotService;
import com.automation.utils.StructuredLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Base page class providing common functionality for all page objects.
 * Equivalent to Python's pages/base_page.py
 * 
 * Design Pattern: Template Method - defines skeleton, subclasses implement specifics
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;
    protected final ErrorHandler errorHandler;
    protected final PerformanceMonitor performanceMonitor;
    protected final ScreenshotService screenshotService;
    protected final StructuredLogger logger;

    // Interaction history for debugging
    private static final int MAX_INTERACTION_HISTORY = 100;
    private final List<String> interactionHistory = new ArrayList<>();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        Settings settings = Settings.getInstance();
        this.wait = new WebDriverWait(driver, settings.getExplicitWait());
        this.actions = new Actions(driver);
        this.errorHandler = new ErrorHandler();
        this.performanceMonitor = new PerformanceMonitor(this.getClass().getSimpleName());
        this.screenshotService = new ScreenshotService();
        this.logger = new StructuredLogger(this.getClass());
    }

    // ═══════════════════════════════════════════════════════════════════
    // NAVIGATION
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Navigate to a URL.
     */
    public void navigateTo(String url) {
        long duration = performanceMonitor.timeOperation("navigate", () -> driver.get(url));
        logger.info("Navigated to {} in {}ms", url, duration);
        recordInteraction("NAVIGATE", url);
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
        recordInteraction("REFRESH", getCurrentUrl());
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
     * Find element with retry.
     */
    protected WebElement findElementWithRetry(By locator) {
        return errorHandler.findElementWithRetry(driver, locator);
    }

    /**
     * Find multiple elements.
     */
    protected List<WebElement> findElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * Check if element is present.
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
            return findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // ELEMENT INTERACTIONS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Click an element.
     */
    protected void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
        recordInteraction("CLICK", locator.toString());
    }

    /**
     * Click with retry.
     */
    protected void clickWithRetry(By locator) {
        errorHandler.clickWithRetry(driver, locator);
        recordInteraction("CLICK_RETRY", locator.toString());
    }

    /**
     * Type text into an element.
     */
    protected void type(By locator, String text) {
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
        recordInteraction("TYPE", locator.toString() + " -> " + text);
    }

    /**
     * Type with retry.
     */
    protected void typeWithRetry(By locator, String text) {
        errorHandler.typeWithRetry(driver, locator, text);
        recordInteraction("TYPE_RETRY", locator.toString() + " -> " + text);
    }

    /**
     * Clear an input field.
     */
    protected void clear(By locator) {
        findElement(locator).clear();
        recordInteraction("CLEAR", locator.toString());
    }

    /**
     * Submit a form.
     */
    protected void submit(By locator) {
        findElement(locator).submit();
        recordInteraction("SUBMIT", locator.toString());
    }

    /**
     * Press a key.
     */
    protected void pressKey(By locator, Keys key) {
        findElement(locator).sendKeys(key);
        recordInteraction("KEY_PRESS", locator.toString() + " -> " + key.name());
    }

    /**
     * Select option by visible text.
     */
    protected void selectByText(By locator, String text) {
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(text);
        recordInteraction("SELECT", locator.toString() + " -> " + text);
    }

    /**
     * Get text from an element.
     */
    protected String getText(By locator) {
        return findElement(locator).getText();
    }

    /**
     * Get attribute value.
     */
    protected String getAttribute(By locator, String attribute) {
        return findElement(locator).getAttribute(attribute);
    }

    // ═══════════════════════════════════════════════════════════════════
    // ADVANCED INTERACTIONS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Hover over an element.
     */
    protected void hover(By locator) {
        WebElement element = findElement(locator);
        actions.moveToElement(element).perform();
        recordInteraction("HOVER", locator.toString());
    }

    /**
     * Double-click an element.
     */
    protected void doubleClick(By locator) {
        WebElement element = findElement(locator);
        actions.doubleClick(element).perform();
        recordInteraction("DOUBLE_CLICK", locator.toString());
    }

    /**
     * Right-click an element.
     */
    protected void rightClick(By locator) {
        WebElement element = findElement(locator);
        actions.contextClick(element).perform();
        recordInteraction("RIGHT_CLICK", locator.toString());
    }

    /**
     * Drag and drop.
     */
    protected void dragAndDrop(By source, By target) {
        WebElement sourceElement = findElement(source);
        WebElement targetElement = findElement(target);
        actions.dragAndDrop(sourceElement, targetElement).perform();
        recordInteraction("DRAG_DROP", source.toString() + " -> " + target.toString());
    }

    /**
     * Scroll to element.
     */
    protected void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                element
        );
        recordInteraction("SCROLL_TO", locator.toString());
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
     * Wait for text to be present.
     */
    protected boolean waitForTextPresent(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Wait with custom timeout.
     */
    protected WebElement waitFor(By locator, Duration timeout) {
        WebDriverWait customWait = new WebDriverWait(driver, timeout);
        return customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
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
     * Record an interaction for debugging.
     */
    private void recordInteraction(String action, String details) {
        String interaction = String.format("[%s] %s: %s",
                java.time.Instant.now(), action, details);
        interactionHistory.add(interaction);

        if (interactionHistory.size() > MAX_INTERACTION_HISTORY) {
            interactionHistory.remove(0);
        }

        logger.debug("{}: {}", action, details);
    }

    /**
     * Get interaction history.
     */
    public List<String> getInteractionHistory() {
        return new ArrayList<>(interactionHistory);
    }

    /**
     * Get performance report.
     */
    public java.util.Map<String, PerformanceMonitor.MetricStats> getPerformanceReport() {
        return performanceMonitor.generateReport();
    }

    /**
     * Get the WebDriver instance.
     */
    public WebDriver getDriver() {
        return driver;
    }
}

