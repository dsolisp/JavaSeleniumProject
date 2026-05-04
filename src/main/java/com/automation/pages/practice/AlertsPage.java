package com.automation.pages.practice;

import com.automation.pages.BasePage;
import com.automation.locators.practice.AlertsLocators;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * AlertsPage — Practice App Alerts page (ADV-E7, ADV-E8, ADV-E9).
 * 
 * Equivalent to:
 * - Cypress: cypress/pages/practice/alerts.page.ts
 * - Playwright: pages/practice/alerts.page.ts
 * - Python: pages/practice/alerts_page.py
 * 
 * Responsibilities: navigate, trigger dialogs, surface result text.
 * No assertions — callers decide what to assert (Law 2).
 * Inherits BasePage only (max 1 level — Law 4).
 */
public class AlertsPage extends BasePage {

    private static final String PRACTICE_BASE_URL = "http://localhost:8080";

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    // ── Navigation ────────────────────────────────────────────────────────

    public AlertsPage open() {
        driver.get(PRACTICE_BASE_URL + "/alerts.html");
        return this;
    }

    // ── Actions ───────────────────────────────────────────────────────────

    public AlertsPage triggerAlert() {
        click(AlertsLocators.TRIGGER_ALERT);
        return this;
    }

    public AlertsPage triggerConfirm() {
        click(AlertsLocators.TRIGGER_CONFIRM);
        return this;
    }

    public AlertsPage triggerPrompt() {
        click(AlertsLocators.TRIGGER_PROMPT);
        return this;
    }

    public String acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public String dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.dismiss();
        return text;
    }

    public String respondToPrompt(String response) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.sendKeys(response);
        alert.accept();
        return text;
    }

    // ── Getters ───────────────────────────────────────────────────────────

    public String getResultText() {
        return getText(AlertsLocators.RESULT_TEXT);
    }
}
