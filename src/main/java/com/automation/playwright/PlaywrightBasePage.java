package com.automation.playwright;

import com.automation.config.Constants;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

/**
 * Base page object for Playwright-based tests.
 * Equivalent to Python's pages/playwright_base_page.py
 */
public abstract class PlaywrightBasePage {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    protected final Page page;

    public PlaywrightBasePage(Page page) {
        this.page = page;
    }

    // ═══════════════════════════════════════════════════════════════════
    // NAVIGATION
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Navigate to URL and wait for load.
     */
    public void navigateTo(String url) {
        logger.info("Navigating to: {}", url);
        page.navigate(url);
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    /**
     * Get current page URL.
     */
    public String getCurrentUrl() {
        return page.url();
    }

    /**
     * Get page title.
     */
    public String getTitle() {
        return page.title();
    }

    /**
     * Reload the page.
     */
    public void reload() {
        page.reload();
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
    }

    // ═══════════════════════════════════════════════════════════════════
    // ELEMENT INTERACTION
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Click on element.
     */
    protected void click(String selector) {
        logger.debug("Clicking: {}", selector);
        page.locator(selector).click();
    }

    /**
     * Type text into element.
     */
    protected void fill(String selector, String text) {
        logger.debug("Filling {} with text", selector);
        page.locator(selector).fill(text);
    }

    /**
     * Clear and type text.
     */
    protected void clearAndFill(String selector, String text) {
        Locator locator = page.locator(selector);
        locator.clear();
        locator.fill(text);
    }

    /**
     * Get text content of element.
     */
    protected String getText(String selector) {
        return page.locator(selector).textContent();
    }

    /**
     * Get attribute value.
     */
    protected String getAttribute(String selector, String attribute) {
        return page.locator(selector).getAttribute(attribute);
    }

    /**
     * Check if element is visible.
     */
    protected boolean isVisible(String selector) {
        return page.locator(selector).isVisible();
    }

    /**
     * Wait for element to be visible.
     */
    protected Locator waitForVisible(String selector) {
        return waitForVisible(selector, Constants.DEFAULT_EXPLICIT_WAIT * 1000.0);
    }

    /**
     * Wait for element to be visible with custom timeout.
     */
    protected Locator waitForVisible(String selector, double timeoutMs) {
        Locator locator = page.locator(selector);
        locator.waitFor(new Locator.WaitForOptions()
            .setState(WaitForSelectorState.VISIBLE)
            .setTimeout(timeoutMs));
        return locator;
    }

    /**
     * Get all elements matching selector.
     */
    protected List<Locator> getAll(String selector) {
        return page.locator(selector).all();
    }

    /**
     * Get count of elements.
     */
    protected int count(String selector) {
        return page.locator(selector).count();
    }

    // ═══════════════════════════════════════════════════════════════════
    // SCREENSHOTS & DEBUGGING
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Take screenshot.
     */
    public Path takeScreenshot(String name) {
        Path path = Path.of(Constants.SCREENSHOTS_DIR, name + ".png");
        page.screenshot(new Page.ScreenshotOptions().setPath(path).setFullPage(true));
        logger.info("Screenshot saved: {}", path);
        return path;
    }

    /**
     * Take element screenshot.
     */
    public Path takeElementScreenshot(String selector, String name) {
        Path path = Path.of(Constants.SCREENSHOTS_DIR, name + ".png");
        page.locator(selector).screenshot(new Locator.ScreenshotOptions().setPath(path));
        return path;
    }

    // ═══════════════════════════════════════════════════════════════════
    // KEYBOARD & MOUSE
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Press keyboard key.
     */
    protected void pressKey(String key) {
        page.keyboard().press(key);
    }

    /**
     * Hover over element.
     */
    protected void hover(String selector) {
        page.locator(selector).hover();
    }

    /**
     * Double click element.
     */
    protected void doubleClick(String selector) {
        page.locator(selector).dblclick();
    }

    // ═══════════════════════════════════════════════════════════════════
    // SELECTS & FORMS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Select option from dropdown.
     */
    protected void selectOption(String selector, String value) {
        page.locator(selector).selectOption(value);
    }

    /**
     * Check checkbox.
     */
    protected void check(String selector) {
        page.locator(selector).check();
    }

    /**
     * Uncheck checkbox.
     */
    protected void uncheck(String selector) {
        page.locator(selector).uncheck();
    }
}

