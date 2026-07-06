package com.automation.ui.practice;

import com.automation.extensions.PageObjectExtension;
import com.automation.extensions.SharedDriver;
import com.automation.extensions.WebDriverExtension;
import com.automation.pages.practice.SelectorsPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * QA Practice App — Selector Playground Tests (Java)
 */
@Epic("UI Testing")
@Feature("QA Practice App")
@DisplayName("Practice App — Selector Playground")
@Tag("ui")
@Tag("practice")
@Tag("selectors")
@SharedDriver
@ExtendWith({WebDriverExtension.class, PageObjectExtension.class})
public class SelectorsTest {

    @Test
    @DisplayName("S1-S3: Basic selectors (ID, Name, Class, Link Text, Aria-Label)")
    void testBasicSelectors(SelectorsPage page) {
        page.open();
        Allure.step("Verify basic selectors work as expected", () ->
                SoftAssertions.assertSoftly(softly -> {
                    // S1: ID and Name
                    softly.assertThat(page.getUsernameInputAttribute("id")).isEqualTo("username-field");
                    softly.assertThat(page.getPasswordInputAttribute("name")).isEqualTo("password");

                    // S2: CSS Classes
                    softly.assertThat(page.getPrimaryButtonClass()).contains("btn-primary");
                    softly.assertThat(page.getSecondaryButtonClass()).contains("btn-secondary");
                    softly.assertThat(page.getSuccessBadgeText()).isEqualTo("Active");
                    softly.assertThat(page.getWarningBadgeText()).isEqualTo("Pending");
                    softly.assertThat(page.getErrorBadgeText()).isEqualTo("Inactive");

                    // S3: Link Text and Aria-Label
                    softly.assertThat(page.getExactLinkText()).contains("Download Report");
                    softly.assertThat(page.getPartialLinkText()).contains("Annual Summary");
                    softly.assertThat(page.getAriaLinkAttribute("aria-label")).isEqualTo("Download the PDF document");
                }));
    }

    @Test
    @DisplayName("S4: Accessibility selectors (Role, Aria-Label, Live Region)")
    void testAccessibilitySelectors(SelectorsPage page) {
        page.open();
        Allure.step("Verify accessibility-focused selectors", () ->
                SoftAssertions.assertSoftly(softly -> {
                    // S4: Role and Aria-Label
                    softly.assertThat(page.getEmailInputAttribute("role")).isEqualTo("textbox");
                    softly.assertThat(page.getEmailInputAttribute("aria-label")).isEqualTo("Work email address");

                    // S4: Live Region
                    page.triggerLiveRegion();
                    softly.assertThat(page.getLiveRegionText()).contains("Updated at");
                }));
    }

    @Test
    @DisplayName("S5: Form element states (Disabled, Radios, Dropdown)")
    void testFormElementSelectors(SelectorsPage page) {
        page.open();
        Allure.step("Verify form element interactions and states", () ->
                SoftAssertions.assertSoftly(softly -> {
                    // S5: States
                    softly.assertThat(page.isInputDisabled()).isTrue();
                    softly.assertThat(page.isRadioProChecked()).isTrue();
                    softly.assertThat(page.isRadioBasicChecked()).isFalse();

                    // S5: Dropdown interaction
                    page.selectCountry("us");
                    softly.assertThat(page.isInputDisabled()).isTrue(); // remains disabled
                }));
    }

    @Test
    @DisplayName("S6-S8: Attribute selectors (Data-*, Images, Titles)")
    void testAttributeSelectors(SelectorsPage page) {
        page.open();
        Allure.step("Verify attribute-based selectors", () ->
                SoftAssertions.assertSoftly(softly -> {
                    // S6: Data Attributes
                    softly.assertThat(page.getProductItemsCount()).isEqualTo(3);
                    softly.assertThat(page.getElectronicsItemsCount()).isEqualTo(2);

                    // S7: Image attributes
                    softly.assertThat(page.getLogoAttribute("alt")).isEqualTo("QA Practice Lab logo");
                    softly.assertThat(page.getLogoAttribute("title")).isEqualTo("QA Practice Lab");

                    // S8: Title attributes
                    softly.assertThat(page.getSaveButtonTitle()).isEqualTo("Save your current progress");
                    softly.assertThat(page.getDeleteButtonTitle()).isEqualTo("Delete this record permanently");
                    softly.assertThat(page.getAbbrQaTitle()).isEqualTo("Quality Assurance");
                }));
    }

    @Test
    @DisplayName("S9-S10: Structural selectors (Tables, Lists, XPath Text)")
    void testStructuralSelectors(SelectorsPage page) {
        page.open();
        Allure.step("Verify structural and text-content selectors", () ->
                SoftAssertions.assertSoftly(softly -> {
                    // S9: Tables
                    softly.assertThat(page.getTableRowsCount()).isEqualTo(3);
                    softly.assertThat(page.getTableRowNameCellText("2")).isEqualTo("Bob");

                    // S10: Lists and Text Content
                    softly.assertThat(page.getFruitItemsCount()).isEqualTo(3);
                    softly.assertThat(page.getFruitItemText(1)).isEqualTo("Banana");
                    softly.assertThat(page.getXpathText()).contains("quick brown fox");
                    softly.assertThat(page.getXpathPartialText()).contains("partial text");
                }));
    }

}
