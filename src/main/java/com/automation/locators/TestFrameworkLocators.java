package com.automation.locators;

import org.openqa.selenium.By;

/**
 * Generic test framework locators for common page patterns.
 * Equivalent to Python's locators/test_framework_locators.py
 */
public final class TestFrameworkLocators {

    private TestFrameworkLocators() {
        // Utility class - prevent instantiation
    }

    // ═══════════════════════════════════════════════════════════════════
    // FORM ELEMENT LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * All input fields.
     */
    public static final By ALL_INPUTS = By.cssSelector("input:not([type='hidden'])");

    /**
     * Text inputs.
     */
    public static final By TEXT_INPUTS = By.cssSelector("input[type='text']");

    /**
     * Password inputs.
     */
    public static final By PASSWORD_INPUTS = By.cssSelector("input[type='password']");

    /**
     * Email inputs.
     */
    public static final By EMAIL_INPUTS = By.cssSelector("input[type='email']");

    /**
     * All buttons.
     */
    public static final By ALL_BUTTONS = By.cssSelector("button, input[type='submit'], input[type='button']");

    /**
     * Submit buttons.
     */
    public static final By SUBMIT_BUTTONS = By.cssSelector("button[type='submit'], input[type='submit']");

    /**
     * Select dropdowns.
     */
    public static final By SELECT_DROPDOWNS = By.tagName("select");

    /**
     * Checkboxes.
     */
    public static final By CHECKBOXES = By.cssSelector("input[type='checkbox']");

    /**
     * Radio buttons.
     */
    public static final By RADIO_BUTTONS = By.cssSelector("input[type='radio']");

    /**
     * Textareas.
     */
    public static final By TEXTAREAS = By.tagName("textarea");

    // ═══════════════════════════════════════════════════════════════════
    // NAVIGATION LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * All links.
     */
    public static final By ALL_LINKS = By.tagName("a");

    /**
     * External links.
     */
    public static final By EXTERNAL_LINKS = By.cssSelector("a[target='_blank']");

    /**
     * Navigation menu.
     */
    public static final By NAV_MENU = By.cssSelector("nav, [role='navigation']");

    /**
     * Header element.
     */
    public static final By HEADER = By.cssSelector("header, [role='banner']");

    /**
     * Footer element.
     */
    public static final By FOOTER = By.cssSelector("footer, [role='contentinfo']");

    // ═══════════════════════════════════════════════════════════════════
    // MODAL & DIALOG LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Modal dialogs.
     */
    public static final By MODALS = By.cssSelector("[role='dialog'], .modal");

    /**
     * Modal close buttons.
     */
    public static final By MODAL_CLOSE = By.cssSelector("[role='dialog'] button[aria-label*='close'], .modal .close");

    /**
     * Alert messages.
     */
    public static final By ALERTS = By.cssSelector("[role='alert'], .alert");

    // ═══════════════════════════════════════════════════════════════════
    // LOADING & STATE LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Loading spinners.
     */
    public static final By LOADING_SPINNERS = By.cssSelector(".loading, .spinner, [aria-busy='true']");

    /**
     * Progress bars.
     */
    public static final By PROGRESS_BARS = By.cssSelector("progress, [role='progressbar']");

    /**
     * Error messages.
     */
    public static final By ERROR_MESSAGES = By.cssSelector(".error, [class*='error'], [role='alert']");

    /**
     * Success messages.
     */
    public static final By SUCCESS_MESSAGES = By.cssSelector(".success, [class*='success']");

    // ═══════════════════════════════════════════════════════════════════
    // TABLE LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Data tables.
     */
    public static final By TABLES = By.tagName("table");

    /**
     * Table rows.
     */
    public static final By TABLE_ROWS = By.cssSelector("table tr, table tbody tr");

    /**
     * Table headers.
     */
    public static final By TABLE_HEADERS = By.cssSelector("table th, table thead th");

    /**
     * Table cells.
     */
    public static final By TABLE_CELLS = By.cssSelector("table td");

    // ═══════════════════════════════════════════════════════════════════
    // ACCESSIBILITY LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Main content area.
     */
    public static final By MAIN_CONTENT = By.cssSelector("main, [role='main']");

    /**
     * Skip navigation link.
     */
    public static final By SKIP_NAV = By.cssSelector("[href='#main'], .skip-link");

    /**
     * Images without alt text.
     */
    public static final By IMAGES_WITHOUT_ALT = By.cssSelector("img:not([alt]), img[alt='']");
}

