package com.automation.locators.practice;

import org.openqa.selenium.By;

/**
 * IframesLocators — locators for /iframes.html (ADV-E3, ADV-E4).
 * Pure locator definitions — zero logic, zero assertions (Law 1 & Law 2).
 */
public final class IframesLocators {

    private IframesLocators() {
        // Utility class, no instantiation
    }

    // ── /iframes.html host-page iframe elements ───────────────────────────
    public static final By PARENT_FRAME = By.cssSelector("[data-test='parent-frame']");
    public static final By OUTER_FRAME = By.cssSelector("[data-test='outer-frame']");

    // ── Inside parentFrame (editor.html) ──────────────────────────────────
    public static final By EDITOR = By.cssSelector("[data-test='editor']");

    // ── Inside outerFrame → childFrame (inner-form.html) ──────────────────
    public static final By CHILD_FRAME = By.cssSelector("[data-test='child-frame']");
    public static final By INNER_NAME = By.cssSelector("[data-test='inner-name']");
    public static final By INNER_EMAIL = By.cssSelector("[data-test='inner-email']");
    public static final By INNER_SUBMIT = By.cssSelector("[data-test='inner-submit']");
    public static final By INNER_RESULT = By.cssSelector("[data-test='inner-result']");
}
