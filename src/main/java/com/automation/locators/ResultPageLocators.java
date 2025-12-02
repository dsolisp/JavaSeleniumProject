package com.automation.locators;

import org.openqa.selenium.By;

/**
 * Locators for Bing search results pages.
 * Equivalent to Python's locators/result_page_locators.py
 */
public final class ResultPageLocators {

    private ResultPageLocators() {
        // Utility class - prevent instantiation
    }

    // ═══════════════════════════════════════════════════════════════════
    // RESULT CONTAINER LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Bing main results container.
     */
    public static final By RESULTS_CONTAINER = By.id("b_results");

    /**
     * Bing individual result items.
     */
    public static final By RESULT_ITEMS = By.cssSelector("#b_results .b_algo");

    /**
     * Alternative result items selector.
     */
    public static final By RESULT_ITEMS_ALT = By.cssSelector(".b_algo");

    // ═══════════════════════════════════════════════════════════════════
    // RESULT ELEMENT LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Bing result title links.
     */
    public static final By RESULT_TITLES = By.cssSelector("#b_results h2 a");

    /**
     * Bing result title text.
     */
    public static final By RESULT_TITLE_TEXT = By.cssSelector("#b_results h2");

    /**
     * Bing result snippets/descriptions.
     */
    public static final By RESULT_SNIPPETS = By.cssSelector("#b_results .b_caption p");

    /**
     * Bing result URLs.
     */
    public static final By RESULT_URLS = By.cssSelector("#b_results cite");

    // ═══════════════════════════════════════════════════════════════════
    // NAVIGATION LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Bing next page button.
     */
    public static final By NEXT_PAGE_BUTTON = By.cssSelector("a.sb_pagN");

    /**
     * Bing previous page button.
     */
    public static final By PREVIOUS_PAGE_BUTTON = By.cssSelector("a.sb_pagP");

    // ═══════════════════════════════════════════════════════════════════
    // FILTER LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Bing Images tab filter.
     */
    public static final By IMAGES_TAB = By.cssSelector("a[href*='/images/']");

    /**
     * Bing Videos tab filter.
     */
    public static final By VIDEOS_TAB = By.cssSelector("a[href*='/videos/']");

    /**
     * Bing News tab filter.
     */
    public static final By NEWS_TAB = By.cssSelector("a[href*='/news/']");

    // ═══════════════════════════════════════════════════════════════════
    // SPECIAL RESULT LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Bing knowledge panel / answer box.
     */
    public static final By INSTANT_ANSWER = By.cssSelector(".b_entityTP");

    /**
     * Bing related searches section.
     */
    public static final By RELATED_SEARCHES = By.cssSelector(".b_rs");

    /**
     * No results message.
     */
    public static final By NO_RESULTS = By.cssSelector(".b_no");
}

