package com.automation.ui.practice;

import com.automation.extensions.WebDriverExtension;
import com.automation.pages.practice.DropdownPage;
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
@Tag("smoke")
@ExtendWith(WebDriverExtension.class)
public class DropdownTest {

    @Nested
    @Story("ADV-E1: Static dropdown")
    @DisplayName("ADV-E1: Static dropdown")
    class StaticDropdownTests {

        @Test
        @Description("ADV-E1: static dropdown is present on page load")
        @DisplayName("Should display static dropdown")
        void staticDropdownIsVisible(WebDriver driver) {
            DropdownPage page = new DropdownPage(driver).open();
            assertThat(page.isStaticDropdownVisible()).isTrue();
        }

        @Test
        @Description("ADV-E1: selecting Option 1 shows it in the status")
        @DisplayName("Should update status when Option 1 is selected")
        void selectOption1UpdatesStatus(WebDriver driver) {
            DropdownPage page = new DropdownPage(driver).open();
            page.selectStatic("1");
            assertThat(page.getStaticStatus()).contains("Option 1");
        }

        @Test
        @Description("ADV-E1: selecting Option 2 shows it in the status")
        @DisplayName("Should update status when Option 2 is selected")
        void selectOption2UpdatesStatus(WebDriver driver) {
            DropdownPage page = new DropdownPage(driver).open();
            page.selectStatic("2");
            assertThat(page.getStaticStatus()).contains("Option 2");
        }

        @Test
        @Description("ADV-E1: selecting Option 3 shows it in the status")
        @DisplayName("Should update status when Option 3 is selected")
        void selectOption3UpdatesStatus(WebDriver driver) {
            DropdownPage page = new DropdownPage(driver).open();
            page.selectStatic("3");
            assertThat(page.getStaticStatus()).contains("Option 3");
        }
    }

    @Nested
    @Story("ADV-E2: Dynamic dropdown")
    @DisplayName("ADV-E2: Dynamic dropdown")
    class DynamicDropdownTests {

        @Test
        @Description("ADV-E2: the dynamic dropdown is disabled while options load")
        @DisplayName("Should be disabled initially")
        void dynamicDropdownStartsDisabled(WebDriver driver) {
            DropdownPage page = new DropdownPage(driver).open();
            assertThat(page.isDynamicDropdownDisabled()).isTrue();
            assertThat(page.getDynamicStatus()).contains("Fetching");
        }

        @Test
        @Description("ADV-E2: the dynamic dropdown becomes enabled after ~1.5 s")
        @DisplayName("Should become enabled after loading")
        void dynamicDropdownBecomesEnabled(WebDriver driver) {
            DropdownPage page = new DropdownPage(driver).open();
            assertThat(page.isDynamicDropdownEnabled()).isTrue();
            assertThat(page.getDynamicStatus()).contains("loaded");
        }

        @Test
        @Description("ADV-E2: selecting a dynamic option updates the status")
        @DisplayName("Should update status when a dynamic option is selected")
        void dynamicDropdownSelection(WebDriver driver) {
            DropdownPage page = new DropdownPage(driver).open();
            assertThat(page.isDynamicDropdownEnabled()).isTrue();
            page.selectDynamic("1");
            assertThat(page.getDynamicStatus()).doesNotContain("Fetching");
        }
    }
}
