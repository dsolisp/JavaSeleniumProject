package com.automation.ui.practice;

import com.automation.extensions.PageObjectExtension;
import com.automation.extensions.SharedDriver;
import com.automation.extensions.WebDriverExtension;
import com.automation.pages.practice.IframesPage;
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
 * QA Practice App — Iframes Tests (Java)
 * 
 * Equivalent to:
 * - Cypress: cypress/ui/practice/iframes.cy.ts
 * - Playwright: tests/ui/practice/iframes.spec.ts
 * - Python: tests/ui/practice/test_iframes.py
 */
@Epic("UI Testing")
@Feature("QA Practice App")
@DisplayName("Practice App — Iframes Tests")
@Tag("ui")
@Tag("practice")
@SharedDriver
@ExtendWith({WebDriverExtension.class, PageObjectExtension.class})
public class IframesTest {

    @Test
    @Tag("sanity")
    @Story("ADV-E3: Simple iframe")
    @Description("ADV-E3: the parent iframe is visible and the editor persists typed, cleared, and retyped text")
    @DisplayName("Should display the parent iframe and persist typed, cleared, and retyped text")
    void simpleIframeEditorPersistsText(IframesPage page) {
        page.open();

        boolean parentVisible = Allure.step("Read parent iframe visibility", page::isParentFrameVisible);

        String typedText = Allure.step("Type text into the editor", () -> {
            page.typeInEditor("Hello from Java!");
            return page.getEditorText();
        });

        String retypedText = Allure.step("Clear and retype editor text", () -> {
            page.clearEditor();
            page.typeInEditor("Replaced text");
            return page.getEditorText();
        });

        Allure.step("Verify iframe visibility and editor text lifecycle", () ->
                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(parentVisible).as("parent iframe visible").isTrue();
                    softly.assertThat(typedText).as("typed text").contains("Hello from Java!");
                    softly.assertThat(retypedText).as("retyped text")
                            .contains("Replaced text")
                            .doesNotContain("Hello from Java!");
                }));
    }

    @Test
    @Tag("regression")
    @Tag("integration")
    @Story("ADV-E4: Nested iframes")
    @Description("ADV-E4: the outer iframe is visible and the nested form submits with and without a name")
    @DisplayName("Should display the outer iframe and submit the nested form with and without a name")
    void nestedIframeFormSubmissions(IframesPage page) {
        page.open();

        boolean outerVisible = Allure.step("Read outer iframe visibility", page::isOuterFrameVisible);

        String withNameResult = Allure.step("Submit the inner form with name and email", () -> {
            page.submitInnerForm("Alice", "alice@example.com");
            return page.getInnerResult();
        });

        String noNameResult = Allure.step("Re-open and submit the inner form without a name", () -> {
            page.open();
            page.submitInnerForm("", "test@example.com");
            return page.getInnerResult();
        });

        Allure.step("Verify the outer iframe and both submissions", () ->
                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(outerVisible).as("outer iframe visible").isTrue();
                    softly.assertThat(withNameResult).as("with-name result")
                            .contains("Alice").contains("alice@example.com");
                    softly.assertThat(noNameResult).as("no-name result").contains("(no name)");
                }));
    }
}
