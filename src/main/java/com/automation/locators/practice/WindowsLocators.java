package com.automation.locators.practice;

import org.openqa.selenium.By;

/**
 * WindowsLocators — locators for /windows.html (ADV-E5, ADV-E6).
 * Pure locator definitions — zero logic, zero assertions (Law 1 & Law 2).
 */
public final class WindowsLocators {

    private WindowsLocators() {
        // Utility class, no instantiation
    }

    // ── /windows.html ──────────────────────────────────────────────────────
    public static final By OPEN_TAB_LINK = By.cssSelector("[data-test='open-new-tab-link']");
    public static final By OPEN_TAB_JS = By.cssSelector("[data-test='open-new-tab-js']");

    // ── /windows/new.html (in new window/tab) ─────────────────────────────
    public static final By NEW_WINDOW_HEADING = By.cssSelector("[data-test='new-window-heading']");
    public static final By NEW_WINDOW_BODY = By.cssSelector("[data-test='new-window-body']");
}
