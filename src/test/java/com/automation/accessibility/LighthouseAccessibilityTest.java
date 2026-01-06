package com.automation.accessibility;

import com.automation.config.Settings;
import com.automation.extensions.WebDriverExtension;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Lighthouse-Style Accessibility Audits.
 * Uses Axe-core with Lighthouse-like scoring methodology.
 */
@Epic("Accessibility")
@Feature("Lighthouse-Style Audits")
@DisplayName("Lighthouse Accessibility Tests")
@Tag("accessibility")
@ExtendWith(WebDriverExtension.class)
class LighthouseAccessibilityTest {

    private final Settings settings = Settings.getInstance();

    record AccessibilityScore(
        int score,
        int passes,
        int violations,
        int incomplete,
        int inapplicable,
        int criticalViolations,
        int seriousViolations
    ) {}

    /**
     * Calculate a Lighthouse-style accessibility score from Axe results.
     * Lighthouse uses a weighted scoring system based on impact.
     */
    private AccessibilityScore calculateAccessibilityScore(Results results) {
        List<Rule> violations = results.getViolations();
        
        int critical = (int) violations.stream()
            .filter(v -> "critical".equals(v.getImpact())).count();
        int serious = (int) violations.stream()
            .filter(v -> "serious".equals(v.getImpact())).count();
        int moderate = (int) violations.stream()
            .filter(v -> "moderate".equals(v.getImpact())).count();
        int minor = (int) violations.stream()
            .filter(v -> "minor".equals(v.getImpact())).count();

        // Weighted penalty: critical=10, serious=5, moderate=2, minor=1
        int penalty = (critical * 10) + (serious * 5) + (moderate * 2) + minor;
        int maxPenalty = 50; // Cap penalty at 50 points
        int score = Math.max(0, 100 - Math.min(penalty, maxPenalty));

        return new AccessibilityScore(
            score,
            results.getPasses().size(),
            violations.size(),
            results.getIncomplete().size(),
            results.getInapplicable().size(),
            critical,
            serious
        );
    }

    @Test
    @Description("Should have good accessibility score on Bing homepage")
    @DisplayName("Bing homepage accessibility score")
    void bingHomepageAccessibilityScore(WebDriver driver) {
        driver.get(Settings.getInstance().getBaseUrl());

        Results results = new AxeBuilder().analyze(driver);
        AccessibilityScore scoreData = calculateAccessibilityScore(results);

        System.out.println("\n=== Lighthouse-Style Accessibility Audit ===");
        System.out.printf("Bing Homepage Score: %d%%%n", scoreData.score());
        System.out.printf("Passes: %d, Violations: %d%n", scoreData.passes(), scoreData.violations());
        System.out.printf("Critical: %d, Serious: %d%n", scoreData.criticalViolations(), scoreData.seriousViolations());

        // Expect at least 70% accessibility score
        assertThat(scoreData.score())
            .as("Accessibility score should be at least 70%")
            .isGreaterThanOrEqualTo(70);
    }

    @Test
    @Description("Should have good accessibility score on SauceDemo login")
    @DisplayName("SauceDemo login accessibility score")
    void sauceDemoLoginAccessibilityScore(WebDriver driver) {
        driver.get(settings.getSauceDemoUrl());

        Results results = new AxeBuilder().analyze(driver);
        AccessibilityScore scoreData = calculateAccessibilityScore(results);

        System.out.println("\n=== SauceDemo Lighthouse-Style Accessibility ===");
        System.out.printf("Login Page Score: %d%%%n", scoreData.score());
        System.out.printf("Passes: %d, Violations: %d%n", scoreData.passes(), scoreData.violations());

        // SauceDemo is a demo site, expect at least 60%
        assertThat(scoreData.score())
            .as("Accessibility score should be at least 60%")
            .isGreaterThanOrEqualTo(60);
    }

    @Test
    @Description("Should report accessibility issues in detail")
    @DisplayName("Detailed accessibility report")
    void detailedAccessibilityReport(WebDriver driver) {
        driver.get(Settings.getInstance().getBaseUrl());

        Results results = new AxeBuilder().analyze(driver);
        AccessibilityScore scoreData = calculateAccessibilityScore(results);
        List<Rule> violations = results.getViolations();

        long ariaViolations = violations.stream()
            .filter(v -> v.getId().startsWith("aria")).count();
        long colorViolations = violations.stream()
            .filter(v -> v.getId().contains("color")).count();
        long imageViolations = violations.stream()
            .filter(v -> v.getId().contains("image") || v.getId().contains("alt")).count();

        System.out.println("\n=== Detailed Accessibility Report ===");
        System.out.printf("Overall Score: %d%%%n", scoreData.score());
        System.out.printf("ARIA issues: %d%n", ariaViolations);
        System.out.printf("Color contrast issues: %d%n", colorViolations);
        System.out.printf("Image/alt issues: %d%n", imageViolations);

        // Verify we got audit results
        int totalChecks = scoreData.passes() + scoreData.violations() + scoreData.incomplete();
        assertThat(totalChecks)
            .as("Should have performed accessibility checks")
            .isGreaterThan(0);
    }

    @Test
    @Description("Should have no critical accessibility violations")
    @DisplayName("No critical violations")
    void noCriticalAccessibilityViolations(WebDriver driver) {
        driver.get(Settings.getInstance().getBaseUrl());

        Results results = new AxeBuilder().analyze(driver);
        AccessibilityScore scoreData = calculateAccessibilityScore(results);

        System.out.println("\n=== Critical Violations Check ===");
        System.out.printf("Critical: %d, Serious: %d%n",
            scoreData.criticalViolations(), scoreData.seriousViolations());

        // No critical violations allowed
        assertThat(scoreData.criticalViolations())
            .as("Should have no critical violations")
            .isZero();
    }
}

