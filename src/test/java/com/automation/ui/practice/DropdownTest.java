package com.automation.ui.practice;

import com.automation.extensions.PageObjectExtension;
import com.automation.extensions.SharedDriver;
import com.automation.extensions.WebDriverExtension;
import com.automation.pages.practice.DropdownPage;
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
 * QA Practice App — Dropdown Tests (Java)
 * 
 * Equivalent to:
 * - Cypress: cypress/ui/practice/dropdown.cy.ts
 * - Playwright: tests/ui/practice/dropdown.spec.ts
 * - Python: tests/ui/practice/test_dropdown.py
 */
@Epic("UI Testing")
@Feature("QA Practice App")
@DisplayName("Practice App — Dropdown Tests")
@Tag("ui")
@Tag("practice")
@SharedDriver
@ExtendWith({WebDriverExtension.class, PageObjectExtension.class})
public class DropdownTest {

    @Test
    @Tag("smoke")
    @Tag("sanity")
    @Story("ADV-E1: Static dropdown")
    @Description("ADV-E1: the static dropdown is visible and selecting each option updates the status")
    @DisplayName("Should display static dropdown and reflect every selected option")
    void staticDropdownReflectsSelections(DropdownPage page) {
        page.open();

        boolean visible = Allure.step("Read static dropdown visibility", page::isStaticDropdownVisible);

        String option1Status = Allure.step("Select Option 1", () -> {
            page.selectStatic("1");
            return page.getStaticStatus();
        });
        String option2Status = Allure.step("Select Option 2", () -> {
            page.selectStatic("2");
            return page.getStaticStatus();
        });
        String option3Status = Allure.step("Select Option 3", () -> {
            page.selectStatic("3");
            return page.getStaticStatus();
        });

        Allure.step("Verify visibility and each selected option's status", () ->
                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(visible).as("static dropdown visible").isTrue();
                    softly.assertThat(option1Status).as("Option 1 status").contains("Option 1");
                    softly.assertThat(option2Status).as("Option 2 status").contains("Option 2");
                    softly.assertThat(option3Status).as("Option 3 status").contains("Option 3");
                }));
    }

    @Test
    @Tag("sanity")
    @Tag("regression")
    @Story("ADV-E2: Dynamic dropdown")
    @Description("ADV-E2: the dynamic dropdown starts disabled, becomes enabled, and accepts a selection")
    @DisplayName("Should transition dynamic dropdown from disabled to enabled and accept a selection")
    void dynamicDropdownLoadsAndAcceptsSelection(DropdownPage page) {
        page.open();

        boolean disabledInitially = Allure.step("Read initial disabled state", page::isDynamicDropdownDisabled);
        String fetchingStatus = page.getDynamicStatus();

        boolean enabledAfterLoad = Allure.step("Wait for the dropdown to become enabled",
                page::isDynamicDropdownEnabled);
        String loadedStatus = page.getDynamicStatus();

        String selectedStatus = Allure.step("Select a dynamic option", () -> {
            page.selectDynamic("1");
            return page.getDynamicStatus();
        });

        Allure.step("Verify the dynamic dropdown lifecycle", () ->
                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(disabledInitially).as("disabled while fetching").isTrue();
                    softly.assertThat(fetchingStatus).as("fetching status").contains("Fetching");
                    softly.assertThat(enabledAfterLoad).as("enabled after load").isTrue();
                    softly.assertThat(loadedStatus).as("loaded status").contains("loaded");
                    softly.assertThat(selectedStatus).as("status after selection").doesNotContain("Fetching");
                }));
    }
}
