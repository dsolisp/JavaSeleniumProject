package com.automation.locators.practice;

import org.openqa.selenium.By;

/**
 * DropdownLocators — locators for /dropdown.html (ADV-E1, ADV-E2).
 * Pure locator definitions — zero logic, zero assertions (Law 1 & Law 2).
 */
public final class DropdownLocators {

    private DropdownLocators() {
        // Utility class, no instantiation
    }

    public static final By STATIC_DROPDOWN = By.cssSelector("[data-test='static-dropdown']");
    public static final By STATIC_STATUS = By.cssSelector("[data-test='static-status']");
    public static final By DYNAMIC_DROPDOWN = By.cssSelector("[data-test='dynamic-dropdown']");
    public static final By DYNAMIC_STATUS = By.cssSelector("[data-test='dynamic-status']");
}
