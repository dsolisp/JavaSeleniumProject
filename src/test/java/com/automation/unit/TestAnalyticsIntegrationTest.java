package com.automation.unit;

import com.automation.extensions.TestAnalyticsExtension;
import com.automation.utils.TestAnalytics;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for TestAnalytics integration and flaky test detection.
 */
@Epic("Test Analytics")
@Feature("Analytics Integration")
@DisplayName("Test Analytics Integration Tests")
@Tag("unit")
@ExtendWith(TestAnalyticsExtension.class)
class TestAnalyticsIntegrationTest {

    private static Path tempDir;

    @BeforeAll
    static void setUp() throws IOException {
        tempDir = Files.createTempDirectory("analytics_test");
    }

    @AfterAll
    static void tearDown() throws IOException {
        // Cleanup temp files
        if (tempDir != null && Files.exists(tempDir)) {
            Files.walk(tempDir)
                    .sorted((a, b) -> -a.compareTo(b))
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException ignored) {}
                    });
        }
    }

    @Test
    @Story("Statistics Collection")
    @Description("Verify statistics are collected correctly")
    @DisplayName("Statistics should be collected")
    void statisticsShouldBeCollected() {
        Map<String, Object> stats = TestAnalyticsExtension.getStatistics();

        assertThat(stats).containsKeys("total", "passed", "failed", "passRate");
        assertThat(((Number) stats.get("total")).longValue()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Story("Flaky Detection")
    @Description("Verify flaky test detection algorithm")
    @DisplayName("Flaky tests should be detected with < 90% pass rate")
    void flakyTestsShouldBeDetectedWithLowPassRate() throws Exception {
        // Create test data simulating flaky test
        String json = """
                {
                    "results": [
                        {"testName": "flakyTest", "passed": true, "durationMs": 100},
                        {"testName": "flakyTest", "passed": true, "durationMs": 100},
                        {"testName": "flakyTest", "passed": false, "durationMs": 100},
                        {"testName": "flakyTest", "passed": true, "durationMs": 100}
                    ]
                }
                """;
        Files.writeString(tempDir.resolve("flaky_results.json"), json);
        
        TestAnalytics analytics = new TestAnalytics(tempDir.toString());
        var flakyTests = analytics.getFlakyTests(0.90); // 90% threshold
        
        // With 75% pass rate (3/4), this should be detected as flaky
        assertThat(flakyTests)
                .as("Tests with < 90% pass rate should be detected as flaky")
                .hasSize(1);
        
        assertThat(flakyTests.get(0).testName()).isEqualTo("flakyTest");
        assertThat(flakyTests.get(0).passRate()).isLessThan(0.90);
    }

    @Test
    @Story("Flaky Detection")
    @Description("Verify stable tests are not marked as flaky")
    @DisplayName("Stable tests should not be marked as flaky")
    void stableTestsShouldNotBeMarkedAsFlaky() throws Exception {
        // Use a separate directory for this test to ensure isolation
        Path isolatedDir = Files.createTempDirectory("stable_test");

        String json = """
                {
                    "results": [
                        {"testName": "stableTest", "passed": true, "durationMs": 100},
                        {"testName": "stableTest", "passed": true, "durationMs": 100},
                        {"testName": "stableTest", "passed": true, "durationMs": 100},
                        {"testName": "stableTest", "passed": true, "durationMs": 100},
                        {"testName": "stableTest", "passed": true, "durationMs": 100}
                    ]
                }
                """;
        Files.writeString(isolatedDir.resolve("stable_results.json"), json);

        TestAnalytics analytics = new TestAnalytics(isolatedDir.toString());
        var flakyTests = analytics.getFlakyTests(0.90);

        // 100% pass rate should not be detected as flaky
        assertThat(flakyTests)
                .as("Tests with 100% pass rate should not be flaky")
                .isEmpty();

        // Cleanup
        Files.deleteIfExists(isolatedDir.resolve("stable_results.json"));
        Files.deleteIfExists(isolatedDir);
    }

    @Test
    @Story("Slow Test Detection")
    @Description("Verify slow tests are detected with > 5000ms threshold")
    @DisplayName("Slow tests should be detected")
    void slowTestsShouldBeDetected() throws Exception {
        String json = """
                {
                    "results": [
                        {"testName": "slowTest", "passed": true, "durationMs": 6000},
                        {"testName": "slowTest", "passed": true, "durationMs": 7000},
                        {"testName": "fastTest", "passed": true, "durationMs": 100}
                    ]
                }
                """;
        Files.writeString(tempDir.resolve("slow_results.json"), json);
        
        TestAnalytics analytics = new TestAnalytics(tempDir.toString());
        var slowTests = analytics.getSlowTests(5000); // 5 second threshold
        
        assertThat(slowTests)
                .as("Tests with > 5000ms avg duration should be slow")
                .hasSize(1);
        
        assertThat(slowTests.get(0).testName()).isEqualTo("slowTest");
        assertThat(slowTests.get(0).avgDurationMs()).isGreaterThan(5000);
    }

    @Test
    @Story("Report Generation")
    @Description("Verify report generation works")
    @DisplayName("Analytics report should be generated")
    void analyticsReportShouldBeGenerated() throws Exception {
        String json = """
                {
                    "results": [
                        {"testName": "test1", "passed": true, "durationMs": 100},
                        {"testName": "test2", "passed": false, "durationMs": 200}
                    ]
                }
                """;
        Files.writeString(tempDir.resolve("report_test.json"), json);
        
        TestAnalytics analytics = new TestAnalytics(tempDir.toString());
        
        // Should not throw exception
        Assertions.assertDoesNotThrow(analytics::generateReport);
    }
}

