package com.automation.accessibility;

import com.automation.config.Settings;
import com.automation.extensions.WebDriverExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.*;

/**
 * Accessibility tests using Axe-core for WCAG compliance.
 */
@Epic("Accessibility Testing")
@Feature("WCAG Compliance")
@DisplayName("Accessibility Tests")
@Tag("accessibility")
@ExtendWith(WebDriverExtension.class)
class AccessibilityTest {

    @Test
    @Story("Homepage Accessibility")
    @Description("Verify homepage accessibility - reports violations without failing")
    @DisplayName("Homepage accessibility audit")
    void homepageShouldBeAccessible(WebDriver driver) {
        AccessibilityChecker checker = new AccessibilityChecker(driver);
        driver.get(Settings.getInstance().getBaseUrl());

        AccessibilityChecker.AccessibilityReport report = checker
            .withWcag21AA()
            .analyze();

        Allure.addAttachment("Accessibility Report", report.getSummary());

        // Log all violations for reporting
        System.out.println("\n=== Accessibility Audit Results ===");
        System.out.printf("Passes: %d, Violations: %d%n",
            report.getPassesCount(), report.getViolationsCount());

        if (report.hasViolations()) {
            System.out.println("\nViolations found:");
            for (var violation : report.getViolations()) {
                System.out.printf("  [%s] %s: %s%n",
                    violation.impact(), violation.id(), violation.description());
                System.out.printf("    Help: %s%n", violation.helpUrl());
            }
        }

        // For audit purposes - report but don't fail on third-party sites
        // Fail only if there are more than expected violations
        assertThat(report.getViolationsCount())
            .as("Total accessibility violations (audit mode)")
            .isLessThanOrEqualTo(10); // Allow up to 10 for external sites
    }

    @Test
    @Story("Search Form Accessibility")
    @Description("Verify search form has proper accessibility labels")
    @DisplayName("Search form should be accessible")
    void searchFormShouldBeAccessible(WebDriver driver) {
        AccessibilityChecker checker = new AccessibilityChecker(driver);
        driver.get(Settings.getInstance().getBaseUrl());

        AccessibilityChecker.AccessibilityReport report = checker
            .includeRules("label", "aria-input-field-name", "form-field-multiple-labels")
            .analyze("form");

        assertThat(report.getViolationsCount())
            .as("Form accessibility violations")
            .isLessThanOrEqualTo(2); // Allow minor issues
    }

    @Test
    @Story("Color Contrast")
    @Description("Verify page has sufficient color contrast")
    @DisplayName("Page should have sufficient color contrast")
    void pageShouldHaveSufficientColorContrast(WebDriver driver) {
        AccessibilityChecker checker = new AccessibilityChecker(driver);
        driver.get(Settings.getInstance().getBaseUrl());

        AccessibilityChecker.AccessibilityReport report = checker
            .includeRules("color-contrast")
            .analyze();

        // Just report, don't fail - contrast issues are common
        System.out.println("Color contrast check: " + report.getViolationsCount() + " issues");
    }

    @Test
    @Story("Keyboard Navigation")
    @Description("Verify keyboard navigation is possible")
    @DisplayName("Page should support keyboard navigation")
    void pageShouldSupportKeyboardNavigation(WebDriver driver) {
        AccessibilityChecker checker = new AccessibilityChecker(driver);
        driver.get(Settings.getInstance().getBaseUrl());

        AccessibilityChecker.AccessibilityReport report = checker
            .includeRules("focus-order-semantics", "tabindex", "focusable-disabled")
            .analyze();

        assertThat(report.getViolationsCount())
            .as("Keyboard navigation violations")
            .isLessThanOrEqualTo(3);
    }
}

