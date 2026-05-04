package com.automation.pages.practice;

import com.automation.pages.BasePage;
import com.automation.locators.practice.DropdownLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * DropdownPage — Practice App Dropdown page (ADV-E1, ADV-E2).
 * 
 * Responsibilities: navigate, select dropdown options, surface status text.
 * No assertions — callers decide what to assert (Law 2).
 * Inherits BasePage only (max 1 level — Law 4).
 */
public class DropdownPage extends BasePage {

    private static final String PRACTICE_BASE_URL = "http://localhost:8080";

    public DropdownPage(WebDriver driver) {
        super(driver);
    }

    public DropdownPage open() {
        driver.get(PRACTICE_BASE_URL + "/dropdown.html");
        return this;
    }

    // ── ADV-E1: Static dropdown ───────────────────────────────────────────

    public DropdownPage selectStatic(String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(DropdownLocators.STATIC_DROPDOWN));
        new Select(element).selectByValue(value);
        return this;
    }

    public String getStaticStatus() {
        return getText(DropdownLocators.STATIC_STATUS);
    }

    public boolean isStaticDropdownVisible() {
        return driver.findElements(DropdownLocators.STATIC_DROPDOWN).size() > 0 && 
               driver.findElement(DropdownLocators.STATIC_DROPDOWN).isDisplayed();
    }

    // ── ADV-E2: Dynamic dropdown ──────────────────────────────────────────

    public boolean isDynamicDropdownDisabled() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(DropdownLocators.DYNAMIC_DROPDOWN));
        return !element.isEnabled();
    }

    public boolean isDynamicDropdownEnabled() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.elementToBeClickable(DropdownLocators.DYNAMIC_DROPDOWN));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public DropdownPage selectDynamic(String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(DropdownLocators.DYNAMIC_DROPDOWN));
        new Select(element).selectByValue(value);
        return this;
    }

    public String getDynamicStatus() {
        return getText(DropdownLocators.DYNAMIC_STATUS);
    }
}
