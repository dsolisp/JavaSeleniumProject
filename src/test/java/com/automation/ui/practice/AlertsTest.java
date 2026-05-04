package com.automation.ui.practice;

import com.automation.extensions.WebDriverExtension;
import com.automation.pages.practice.AlertsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * QA Practice App — Alerts Tests (Java)
 *
 * Equivalent to:
 * - Cypress: cypress/ui/practice/alerts.cy.ts
 * - Playwright: tests/ui/practice/alerts.spec.ts
 * - Python: tests/ui/practice/test_alerts.py
 *
 * ADV-E7, ADV-E8, ADV-E9 — JS dialog interactions.
 * Law 1 (1:1 Mirroring): Same test logic across all 4 stacks.
 */
@Epic("UI Testing")
@Feature("QA Practice App")
@DisplayName("Practice App — Alerts Tests")
@Tag("ui")
@Tag("practice")
@Tag("smoke")
@ExtendWith(WebDriverExtension.class)
public class AlertsTest {

    // ══════════════════════════════════════════════════════════════════════
    // ADV-E7: Simple Alert
    // ══════════════════════════════════════════════════════════════════════

    @Nested
    @Story("ADV-E7: Simple alert")
    @DisplayName("ADV-E7: Simple alert")
    class SimpleAlertTests {

        @Test
        @Description("ADV-E7: clicking trigger fires an alert with the expected message")
        @DisplayName("Should display expected alert text and update result")
        void simpleAlertTextAndResult(WebDriver driver) {
            AlertsPage page = new AlertsPage(driver);
            page.open();

            page.triggerAlert();
            String alertText = page.acceptAlert();

            assertThat(alertText)
                    .as("Alert text should match expected message")
                    .isEqualTo("This is a simple alert!");

            assertThat(page.getResultText())
                    .as("Result text should be updated after alert acceptance")
                    .isEqualTo("Alert accepted.");
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // ADV-E8: Confirm Dialog
    // ══════════════════════════════════════════════════════════════════════

    @Nested
    @Story("ADV-E8: Confirm dialog")
    @DisplayName("ADV-E8: Confirm dialog")
    class ConfirmDialogTests {

        @Test
        @Description("ADV-E8: accepting a confirm dialog sets the expected result text")
        @DisplayName("Should update result when confirm is accepted")
        void confirmAcceptedUpdatesResult(WebDriver driver) {
            AlertsPage page = new AlertsPage(driver);
            page.open();

            page.triggerConfirm();
            page.acceptAlert();

            assertThat(page.getResultText())
                    .as("Result should show confirm was accepted")
                    .isEqualTo("Confirm accepted.");
        }

        @Test
        @Description("ADV-E8: dismissing a confirm dialog sets the expected result text")
        @DisplayName("Should update result when confirm is dismissed")
        void confirmDismissedUpdatesResult(WebDriver driver) {
            AlertsPage page = new AlertsPage(driver);
            page.open();

            page.triggerConfirm();
            page.dismissAlert();

            assertThat(page.getResultText())
                    .as("Result should show confirm was dismissed")
                    .isEqualTo("Confirm dismissed.");
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // ADV-E9: Prompt Dialog
    // ══════════════════════════════════════════════════════════════════════

    @Nested
    @Story("ADV-E9: Prompt dialog")
    @DisplayName("ADV-E9: Prompt dialog")
    class PromptDialogTests {

        @Test
        @Description("ADV-E9: responding to a prompt echoes the input in the result")
        @DisplayName("Should echo entered text in result")
        void promptEchoesEnteredText(WebDriver driver) {
            AlertsPage page = new AlertsPage(driver);
            page.open();

            page.triggerPrompt();
            page.respondToPrompt("Daniel");

            assertThat(page.getResultText())
                    .as("Result should contain the entered text")
                    .contains("Daniel");
        }

        @Test
        @Description("ADV-E9: dismissing a prompt shows the dismissed message")
        @DisplayName("Should show dismissed message when prompt is cancelled")
        void promptDismissedShowsDismissedMessage(WebDriver driver) {
            AlertsPage page = new AlertsPage(driver);
            page.open();

            page.triggerPrompt();
            page.dismissAlert();

            assertThat(page.getResultText())
                    .as("Result should show prompt was dismissed")
                    .isEqualTo("Prompt dismissed.");
        }
    }
}
