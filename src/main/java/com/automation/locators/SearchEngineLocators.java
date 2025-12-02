package com.automation.locators;

import org.openqa.selenium.By;

/**
 * Locators for Bing search engine pages.
 * Equivalent to Python's locators/search_engine_locators.py
 *
 * Centralizes all element locators for better maintainability.
 */
public final class SearchEngineLocators {

    private SearchEngineLocators() {
        // Utility class - prevent instantiation
    }

    // ═══════════════════════════════════════════════════════════════════
    // SEARCH INPUT LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Main search input field (Bing uses 'q').
     */
    public static final By SEARCH_INPUT = By.name("q");

    /**
     * Alternative search input by ID.
     */
    public static final By SEARCH_INPUT_ID = By.id("sb_form_q");

    /**
     * Search input by CSS selector.
     */
    public static final By SEARCH_INPUT_CSS = By.cssSelector("input[name='q']");

    /**
     * Search input by XPath.
     */
    public static final By SEARCH_INPUT_XPATH = By.xpath("//input[@name='q']");

    // ═══════════════════════════════════════════════════════════════════
    // SEARCH BUTTON LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Bing search button.
     */
    public static final By SEARCH_BUTTON = By.id("search_icon");

    /**
     * Alternative search button by CSS.
     */
    public static final By SEARCH_BUTTON_CSS = By.cssSelector("label[for='sb_form_go']");

    // ═══════════════════════════════════════════════════════════════════
    // SUGGESTIONS & AUTOCOMPLETE
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Bing search suggestions container.
     */
    public static final By SUGGESTIONS_CONTAINER = By.cssSelector("#sa_ul, .sa_sg");

    /**
     * Individual suggestion items.
     */
    public static final By SUGGESTION_ITEMS = By.cssSelector("#sa_ul li, .sa_sg li");

    // ═══════════════════════════════════════════════════════════════════
    // PAGE ELEMENTS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Bing logo.
     */
    public static final By LOGO = By.id("bLogo");

    /**
     * Page body element.
     */
    public static final By BODY = By.tagName("body");
}

