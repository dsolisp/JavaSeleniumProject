package com.automation.ui.practice;

import com.automation.extensions.WebDriverExtension;
import com.automation.pages.practice.WindowsPage;
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
 * QA Practice App — Windows / Tabs Tests (Java)
 * 
 * Equivalent to:
 * - Cypress: cypress/ui/practice/windows.cy.ts
 * - Playwright: tests/ui/practice/windows.spec.ts
 * - Python: tests/ui/practice/test_windows.py
 */
@Epic("UI Testing")
@Feature("QA Practice App")
@DisplayName("Practice App — Windows / Tabs Tests")
@Tag("ui")
@Tag("practice")
@Tag("smoke")
@ExtendWith(WebDriverExtension.class)
public class WindowsTest {

    @Nested
    @Story("ADV-E5: target=\"_blank\" link")
    @DisplayName("ADV-E5: target=\"_blank\" link")
    class TargetBlankLinkTests {

        @Test
        @Description("ADV-E5: the new-tab link href points to /windows/new")
        @DisplayName("Should have correct href for new tab link")
        void tabLinkHasCorrectHref(WebDriver driver) {
            WindowsPage page = new WindowsPage(driver).open();
            String href = page.getTabLinkHref();
            assertThat(href).contains("/windows/new");
        }

        @Test
        @Description("ADV-E5: clicking the link opens a new window with the expected heading")
        @DisplayName("Should open new window with expected content")
        void tabLinkOpensNewWindowWithCorrectContent(WebDriver driver) {
            WindowsPage page = new WindowsPage(driver).open();
            String originalHandle = page.clickTabLink();
            
            page.switchToNewWindow(originalHandle);
            assertThat(page.getNewWindowHeading()).isEqualTo("New Window");
            assertThat(page.getNewWindowBody()).contains("opened in a new tab");
            
            page.closeCurrentWindow();
            page.switchToWindow(originalHandle);
        }
    }

    @Nested
    @Story("ADV-E6: window.open()")
    @DisplayName("ADV-E6: window.open()")
    class WindowOpenTests {

        @Test
        @Description("ADV-E6: the JS open button is visible with correct text")
        @DisplayName("Should display JS open button with correct text")
        void tabButtonIsVisible(WebDriver driver) {
            WindowsPage page = new WindowsPage(driver).open();
            assertThat(page.isTabButtonVisible()).isTrue();
            assertThat(page.getTabButtonText()).contains("Open a New Window");
        }

        @Test
        @Description("ADV-E6: clicking the JS button opens a new window")
        @DisplayName("Should open new window when clicking JS button")
        void tabButtonOpensNewWindow(WebDriver driver) {
            WindowsPage page = new WindowsPage(driver).open();
            String originalHandle = page.clickTabButton();
            
            page.switchToNewWindow(originalHandle);
            assertThat(page.getNewWindowHeading()).isEqualTo("New Window");
            
            page.closeCurrentWindow();
            page.switchToWindow(originalHandle);
        }
    }
}
