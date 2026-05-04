package com.automation.ui.practice;

import com.automation.extensions.WebDriverExtension;
import com.automation.pages.practice.IframesPage;
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
@ExtendWith(WebDriverExtension.class)
public class IframesTest {

    @Nested
    @Story("ADV-E3: Simple iframe")
    @DisplayName("ADV-E3: Simple iframe")
    class SimpleIframeTests {

        @Test
        @Description("ADV-E3: the parent iframe element is visible on the host page")
        @DisplayName("Should display parent iframe")
        void parentFrameIsVisible(WebDriver driver) {
            IframesPage page = new IframesPage(driver).open();
            assertThat(page.isParentFrameVisible()).isTrue();
        }

        @Test
        @Description("ADV-E3: text typed into the editor is persisted in the iframe")
        @DisplayName("Should persist typed text in the editor")
        void typeInEditor(WebDriver driver) {
            IframesPage page = new IframesPage(driver).open();
            page.typeInEditor("Hello from Java!");
            assertThat(page.getEditorText()).contains("Hello from Java!");
        }

        @Test
        @Description("ADV-E3: the editor can be cleared and accept new text")
        @DisplayName("Should clear editor and accept new text")
        void clearAndRetypeEditor(WebDriver driver) {
            IframesPage page = new IframesPage(driver).open();
            page.typeInEditor("First text");
            page.clearEditor();
            page.typeInEditor("Replaced text");
            assertThat(page.getEditorText()).contains("Replaced text");
        }
    }

    @Nested
    @Story("ADV-E4: Nested iframes")
    @DisplayName("ADV-E4: Nested iframes")
    class NestedIframesTests {

        @Test
        @Description("ADV-E4: the outer iframe element is visible on the host page")
        @DisplayName("Should display outer iframe")
        void outerFrameIsVisible(WebDriver driver) {
            IframesPage page = new IframesPage(driver).open();
            assertThat(page.isOuterFrameVisible()).isTrue();
        }

        @Test
        @Description("ADV-E4: submitting the inner form shows the submitted values")
        @DisplayName("Should show submitted values in the result")
        void submitInnerFormShowsResult(WebDriver driver) {
            IframesPage page = new IframesPage(driver).open();
            page.submitInnerForm("Alice", "alice@example.com");
            String result = page.getInnerResult();
            assertThat(result).contains("Alice").contains("alice@example.com");
        }

        @Test
        @Description("ADV-E4: submitting without a name shows '(no name)' in result")
        @DisplayName("Should show '(no name)' when submitted without name")
        void submitWithoutNameShowsNoName(WebDriver driver) {
            IframesPage page = new IframesPage(driver).open();
            page.submitInnerForm("", "test@example.com");
            assertThat(page.getInnerResult()).contains("(no name)");
        }
    }
}
