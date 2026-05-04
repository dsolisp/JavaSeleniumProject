package com.automation.ui.practice;

import com.automation.extensions.WebDriverExtension;
import com.automation.pages.practice.SelectorsPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

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
@ExtendWith(WebDriverExtension.class)
public class SelectorsTest {

    @Test
    @DisplayName("S1: locate inputs by data-test (mirrors id/name attributes)")
    void testS1IdAndNameInputs(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getUsernameInputAttribute("id")).isEqualTo("username-field");
        assertThat(page.getPasswordInputAttribute("name")).isEqualTo("password");
    }

    @Test
    @DisplayName("S2: locate primary and secondary buttons by class")
    void testS2CssClassButtons(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getPrimaryButtonClass()).contains("btn-primary");
        assertThat(page.getSecondaryButtonClass()).contains("btn-secondary");
    }

    @Test
    @DisplayName("S2: locate status badges by data-test variant")
    void testS2CssClassBadges(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getSuccessBadgeText()).isEqualTo("Active");
        assertThat(page.getWarningBadgeText()).isEqualTo("Pending");
        assertThat(page.getErrorBadgeText()).isEqualTo("Inactive");
    }

    @Test
    @DisplayName("S3: locate links by exact and partial text content")
    void testS3LinkTextExactAndPartial(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getExactLinkText()).contains("Download Report");
        assertThat(page.getPartialLinkText()).contains("Annual Summary");
    }

    @Test
    @DisplayName("S3: locate link by aria-label attribute")
    void testS3AriaLabelLink(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getAriaLinkAttribute("aria-label")).isEqualTo("Download the PDF document");
    }

    @Test
    @DisplayName("S4: locate input by role and aria-label")
    void testS4AriaEmailInput(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getEmailInputAttribute("role")).isEqualTo("textbox");
        assertThat(page.getEmailInputAttribute("aria-label")).isEqualTo("Work email address");
    }

    @Test
    @DisplayName("S4: live region updates when its trigger button is clicked")
    void testS4LiveRegionUpdatesOnTrigger(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        page.triggerLiveRegion();
        assertThat(page.getLiveRegionText()).contains("Updated at");
    }

    @Test
    @DisplayName("S5: disabled input is not editable")
    void testS5DisabledInput(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.isInputDisabled()).isTrue();
    }

    @Test
    @DisplayName("S5: radio Pro is pre-checked; Basic is not")
    void testS5RadioProPreChecked(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.isRadioProChecked()).isTrue();
        assertThat(page.isRadioBasicChecked()).isFalse();
    }

    @Test
    @DisplayName("S5: a country can be selected from the dropdown")
    void testS5CountryDropdown(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        page.selectCountry("us");
        assertThat(page.isInputDisabled()).isTrue();
    }

    @Test
    @DisplayName("S6: locate products by data-test + data-category")
    void testS6DataAttributes(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getProductItemsCount()).isEqualTo(3);
        assertThat(page.getElectronicsItemsCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("S7: locate logo image by alt and title attributes")
    void testS7ImageLogo(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getLogoAttribute("alt")).isEqualTo("QA Practice Lab logo");
        assertThat(page.getLogoAttribute("title")).isEqualTo("QA Practice Lab");
    }

    @Test
    @DisplayName("S8: locate buttons by title attribute")
    void testS8TitleAttributeButtons(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getSaveButtonTitle()).isEqualTo("Save your current progress");
        assertThat(page.getDeleteButtonTitle()).isEqualTo("Delete this record permanently");
    }

    @Test
    @DisplayName("S8: locate abbr element by title attribute")
    void testS8AbbrTitle(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getAbbrQaTitle()).isEqualTo("Quality Assurance");
    }

    @Test
    @DisplayName("S9: locate table rows and verify name cell content")
    void testS9TableRows(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getTableRowsCount()).isEqualTo(3);
        assertThat(page.getTableRowNameCellText("2")).isEqualTo("Bob");
    }

    @Test
    @DisplayName("S10: locate fruit list items by data-test")
    void testS10FruitItems(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getFruitItemsCount()).isEqualTo(3);
        assertThat(page.getFruitItemText(1)).isEqualTo("Banana");
    }

    @Test
    @DisplayName("S10: locate elements by text content")
    void testS10XpathTextTargets(WebDriver driver) {
        SelectorsPage page = new SelectorsPage(driver).open();
        assertThat(page.getXpathText()).contains("quick brown fox");
        assertThat(page.getXpathPartialText()).contains("partial text");
    }
}
