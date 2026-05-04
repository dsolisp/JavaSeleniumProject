package com.automation.locators.practice;

import org.openqa.selenium.By;

/**
 * AlertsLocators — locators for /alerts.html (ADV-E7, ADV-E8, ADV-E9).
 * Pure locator definitions — zero logic, zero assertions (Law 1 & Law 2).
 * 
 * Equivalent to:
 * - Cypress: cypress/locators/practice/alerts.locators.ts
 * - Playwright: locators/practice/alerts.locators.ts
 * - Python: locators/practice/alerts_locators.py
 */
public final class AlertsLocators {

    private AlertsLocators() {
        // Utility class, no instantiation
    }

    public static final By TRIGGER_ALERT = By.cssSelector("[data-test='trigger-alert']");
    public static final By TRIGGER_CONFIRM = By.cssSelector("[data-test='trigger-confirm']");
    public static final By TRIGGER_PROMPT = By.cssSelector("[data-test='trigger-prompt']");
    public static final By RESULT_TEXT = By.cssSelector("[data-test='result-text']");
}
