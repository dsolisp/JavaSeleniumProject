package com.automation.ui.practice;

import com.automation.extensions.PageObjectExtension;
import com.automation.extensions.SharedDriver;
import com.automation.extensions.WebDriverExtension;
import com.automation.pages.practice.AlertsPage;
import com.automation.utils.TestDataManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
@SharedDriver
@ExtendWith({WebDriverExtension.class, PageObjectExtension.class})
public class AlertsTest {

    private final TestDataManager testData = new TestDataManager();

    @Test
    @Tag("smoke")
    @Tag("regression")
    @Story("ADV-E7/E8/E9: JS dialogs")
    @Description("ADV-E7/E8/E9: simple alert, confirm (accept/dismiss) and prompt (respond/dismiss) "
            + "each update the result text")
    @DisplayName("Should handle alert, confirm, and prompt dialogs and update result text")
    void allDialogTypesUpdateResultText(AlertsPage page) {
        page.open();
        String name = testData.getFaker().name().firstName();

        String alertText = Allure.step("Trigger and accept the simple alert", () -> {
            page.triggerAlert();
            return page.acceptAlert();
        });
        String alertResult = page.getResultText();

        Allure.step("Trigger and accept a confirm dialog", () -> {
            page.triggerConfirm();
            page.acceptAlert();
        });
        String confirmAcceptedResult = page.getResultText();

        Allure.step("Trigger and dismiss a confirm dialog", () -> {
            page.triggerConfirm();
            page.dismissAlert();
        });
        String confirmDismissedResult = page.getResultText();

        Allure.step("Trigger a prompt dialog and respond with text", () -> {
            page.triggerPrompt();
            page.respondToPrompt(name);
        });
        String promptRespondedResult = page.getResultText();

        Allure.step("Trigger and dismiss a prompt dialog", () -> {
            page.triggerPrompt();
            page.dismissAlert();
        });
        String promptDismissedResult = page.getResultText();

        Allure.step("Verify every dialog produced the expected result text", () ->
                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(alertText).as("simple alert text")
                            .isEqualTo("This is a simple alert!");
                    softly.assertThat(alertResult).as("simple alert result")
                            .isEqualTo("Alert accepted.");
                    softly.assertThat(confirmAcceptedResult).as("confirm accepted result")
                            .isEqualTo("Confirm accepted.");
                    softly.assertThat(confirmDismissedResult).as("confirm dismissed result")
                            .isEqualTo("Confirm dismissed.");
                    softly.assertThat(promptRespondedResult).as("prompt responded result")
                            .contains(name);
                    softly.assertThat(promptDismissedResult).as("prompt dismissed result")
                            .isEqualTo("Prompt dismissed.");
                }));
    }
}
