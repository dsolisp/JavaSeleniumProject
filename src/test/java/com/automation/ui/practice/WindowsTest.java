package com.automation.ui.practice;

import com.automation.extensions.PageObjectExtension;
import com.automation.extensions.SharedDriver;
import com.automation.extensions.WebDriverExtension;
import com.automation.pages.practice.WindowsPage;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
@SharedDriver
@ExtendWith({WebDriverExtension.class, PageObjectExtension.class})
public class WindowsTest {

    @Test
    @Story("ADV-E5: target=\"_blank\" link")
    @DisplayName("Should verify link and open new window")
    void tabLinkOpensNewWindow(WindowsPage page) {
        page.open();
        String href = Allure.step("Read the new-tab link href", page::getTabLinkHref);

        String originalHandle = Allure.step("Click the link and switch to the new window", () -> {
            String handle = page.clickTabLink();
            page.switchToNewWindow(handle);
            return handle;
        });

        Allure.step("Verify link, heading and body", () ->
                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(href).contains("/windows/new");
                    softly.assertThat(page.getNewWindowHeading()).isEqualTo("New Window");
                    softly.assertThat(page.getNewWindowBody()).contains("opened in a new tab");
                }));

        Allure.step("Close the new window and return to the original", () -> {
            page.closeCurrentWindow();
            page.switchToWindow(originalHandle);
        });
    }

    @Test
    @Story("ADV-E6: window.open()")
    @DisplayName("Should verify JS button and open new window")
    void tabButtonOpensNewWindow(WindowsPage page) {
        page.open();

        boolean isVisible = Allure.step("Verify button visibility", page::isTabButtonVisible);
        String buttonText = Allure.step("Get button text", page::getTabButtonText);

        String originalHandle = Allure.step("Click the JS button and switch to the new window", () -> {
            String handle = page.clickTabButton();
            page.switchToNewWindow(handle);
            return handle;
        });

        Allure.step("Verify button properties and new window heading", () ->
                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(isVisible).isTrue();
                    softly.assertThat(buttonText).contains("Open a New Window");
                    softly.assertThat(page.getNewWindowHeading()).isEqualTo("New Window");
                }));

        Allure.step("Close the new window and return to the original", () -> {
            page.closeCurrentWindow();
            page.switchToWindow(originalHandle);
        });
    }

}
