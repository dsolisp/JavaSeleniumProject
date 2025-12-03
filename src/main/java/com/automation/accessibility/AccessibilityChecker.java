package com.automation.accessibility;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Accessibility checker using Axe-core for WCAG compliance testing.
 * Equivalent to Python's accessibility testing capabilities.
 */
public class AccessibilityChecker {

    private static final Logger logger = LoggerFactory.getLogger(AccessibilityChecker.class);

    private final WebDriver driver;
    private final Set<String> rulesToInclude;
    private final Set<String> rulesToExclude;

    public AccessibilityChecker(WebDriver driver) {
        this.driver = driver;
        this.rulesToInclude = new HashSet<>();
        this.rulesToExclude = new HashSet<>();
    }

    // ═══════════════════════════════════════════════════════════════════
    // CONFIGURATION
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Configure to check only WCAG 2.1 Level AA rules.
     */
    public AccessibilityChecker withWcag21AA() {
        rulesToInclude.addAll(List.of(
            "wcag2a", "wcag2aa", "wcag21a", "wcag21aa"
        ));
        return this;
    }

    /**
     * Configure to check all WCAG 2.1 rules (A, AA, AAA).
     */
    public AccessibilityChecker withWcag21All() {
        rulesToInclude.addAll(List.of(
            "wcag2a", "wcag2aa", "wcag2aaa",
            "wcag21a", "wcag21aa", "wcag21aaa"
        ));
        return this;
    }

    /**
     * Exclude specific rules.
     */
    public AccessibilityChecker excludeRules(String... rules) {
        rulesToExclude.addAll(Arrays.asList(rules));
        return this;
    }

    /**
     * Include specific rules.
     */
    public AccessibilityChecker includeRules(String... rules) {
        rulesToInclude.addAll(Arrays.asList(rules));
        return this;
    }

    // ═══════════════════════════════════════════════════════════════════
    // ANALYSIS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Run accessibility analysis on the current page.
     */
    public AccessibilityReport analyze() {
        return analyze(null);
    }

    /**
     * Run accessibility analysis on a specific element.
     */
    public AccessibilityReport analyze(String cssSelector) {
        logger.info("Running accessibility analysis...");
        
        AxeBuilder axeBuilder = new AxeBuilder();
        
        if (!rulesToInclude.isEmpty()) {
            axeBuilder.withTags(new ArrayList<>(rulesToInclude));
        }
        
        if (!rulesToExclude.isEmpty()) {
            axeBuilder.disableRules(new ArrayList<>(rulesToExclude));
        }
        
        if (cssSelector != null) {
            axeBuilder.include(cssSelector);
        }
        
        Results results = axeBuilder.analyze(driver);
        
        AccessibilityReport report = new AccessibilityReport(results);
        
        logger.info("Accessibility analysis complete: {} violations, {} passes",
            report.getViolationsCount(), report.getPassesCount());
        
        return report;
    }

    /**
     * Quick check for critical violations only.
     */
    public AccessibilityReport analyzeCritical() {
        return new AccessibilityChecker(driver)
            .includeRules("critical", "serious")
            .analyze();
    }

    // ═══════════════════════════════════════════════════════════════════
    // REPORT CLASS
    // ═══════════════════════════════════════════════════════════════════

    public static class AccessibilityReport {
        private final Results results;
        private final List<Violation> violations;
        private final int passesCount;

        public AccessibilityReport(Results results) {
            this.results = results;
            this.violations = results.getViolations().stream()
                .map(Violation::fromRule)
                .collect(Collectors.toList());
            this.passesCount = results.getPasses().size();
        }

        public boolean isAccessible() {
            return violations.isEmpty();
        }

        public boolean hasViolations() {
            return !violations.isEmpty();
        }

        public int getViolationsCount() {
            return violations.size();
        }

        public int getPassesCount() {
            return passesCount;
        }

        public List<Violation> getViolations() {
            return Collections.unmodifiableList(violations);
        }

        public List<Violation> getCriticalViolations() {
            return violations.stream()
                .filter(v -> "critical".equals(v.impact()) || "serious".equals(v.impact()))
                .collect(Collectors.toList());
        }

        public String getSummary() {
            StringBuilder sb = new StringBuilder();
            sb.append("Accessibility Report\n");
            sb.append("====================\n");
            sb.append("Passes: %d\n".formatted(passesCount));
            sb.append("Violations: %d\n".formatted(violations.size()));
            
            if (!violations.isEmpty()) {
                sb.append("\nViolations:\n");
                for (Violation v : violations) {
                    sb.append("  [%s] %s - %s\n".formatted(
                            v.impact(), v.id(), v.description()));
                }
            }
            
            return sb.toString();
        }
    }

    public record Violation(
        String id,
        String impact,
        String description,
        String help,
        String helpUrl,
        int nodeCount
    ) {
        public static Violation fromRule(Rule rule) {
            return new Violation(
                rule.getId(),
                rule.getImpact(),
                rule.getDescription(),
                rule.getHelp(),
                rule.getHelpUrl(),
                rule.getNodes().size()
            );
        }

        public boolean isCritical() {
            return "critical".equals(impact) || "serious".equals(impact);
        }
    }
}

