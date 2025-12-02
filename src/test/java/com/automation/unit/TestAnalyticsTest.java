package com.automation.unit;

import com.automation.utils.TestAnalytics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for TestAnalytics.
 */
@DisplayName("TestAnalytics Tests")
class TestAnalyticsTest {

    @TempDir
    Path tempDir;

    private TestAnalytics analytics;

    @BeforeEach
    void setUp() {
        analytics = new TestAnalytics(tempDir);
    }

    @Test
    @DisplayName("Should load test results from JSON files")
    void shouldLoadTestResultsFromJsonFiles() throws Exception {
        String json = """
                {
                    "results": [
                        {"testName": "test1", "passed": true, "durationMs": 100},
                        {"testName": "test2", "passed": false, "durationMs": 200}
                    ]
                }
                """;
        Files.writeString(tempDir.resolve("results1.json"), json);
        
        int loaded = analytics.loadTestResults();
        
        assertThat(loaded).isEqualTo(2);
    }

    @Test
    @DisplayName("Should detect flaky tests")
    void shouldDetectFlakyTests() throws Exception {
        String json = """
                {
                    "results": [
                        {"testName": "flakyTest", "passed": true, "durationMs": 100},
                        {"testName": "flakyTest", "passed": false, "durationMs": 100},
                        {"testName": "flakyTest", "passed": true, "durationMs": 100},
                        {"testName": "stableTest", "passed": true, "durationMs": 100},
                        {"testName": "stableTest", "passed": true, "durationMs": 100},
                        {"testName": "stableTest", "passed": true, "durationMs": 100}
                    ]
                }
                """;
        Files.writeString(tempDir.resolve("results.json"), json);
        analytics.loadTestResults();
        
        List<TestAnalytics.FlakyTest> flakyTests = analytics.detectFlakyTests();
        
        assertThat(flakyTests).hasSize(1);
        assertThat(flakyTests.get(0).testName()).isEqualTo("flakyTest");
        assertThat(flakyTests.get(0).passRate()).isCloseTo(0.666, org.assertj.core.data.Offset.offset(0.01));
    }

    @Test
    @DisplayName("Should detect slow tests")
    void shouldDetectSlowTests() throws Exception {
        String json = """
                {
                    "results": [
                        {"testName": "slowTest", "passed": true, "durationMs": 6000},
                        {"testName": "slowTest", "passed": true, "durationMs": 7000},
                        {"testName": "fastTest", "passed": true, "durationMs": 100},
                        {"testName": "fastTest", "passed": true, "durationMs": 150}
                    ]
                }
                """;
        Files.writeString(tempDir.resolve("results.json"), json);
        analytics.loadTestResults();
        
        List<TestAnalytics.SlowTest> slowTests = analytics.detectSlowTests();
        
        assertThat(slowTests).hasSize(1);
        assertThat(slowTests.get(0).testName()).isEqualTo("slowTest");
        assertThat(slowTests.get(0).avgDurationMs()).isEqualTo(6500.0);
    }

    @Test
    @DisplayName("Should return empty list when no results loaded")
    void shouldReturnEmptyListWhenNoResultsLoaded() {
        List<TestAnalytics.FlakyTest> flakyTests = analytics.detectFlakyTests();
        List<TestAnalytics.SlowTest> slowTests = analytics.detectSlowTests();
        
        assertThat(flakyTests).isEmpty();
        assertThat(slowTests).isEmpty();
    }

    @Test
    @DisplayName("Should handle missing results directory")
    void shouldHandleMissingResultsDirectory() {
        TestAnalytics missingDirAnalytics = new TestAnalytics(Path.of("/nonexistent/path"));
        
        int loaded = missingDirAnalytics.loadTestResults();
        
        assertThat(loaded).isEqualTo(0);
    }

    @Test
    @DisplayName("Should generate report without error")
    void shouldGenerateReportWithoutError() throws Exception {
        String json = """
                {
                    "results": [
                        {"testName": "test1", "passed": true, "durationMs": 100}
                    ]
                }
                """;
        Files.writeString(tempDir.resolve("results.json"), json);
        analytics.loadTestResults();
        
        // Should not throw exception
        analytics.generateReport();
    }

    @Test
    @DisplayName("FlakyTest record should work correctly")
    void flakyTestRecordShouldWorkCorrectly() {
        TestAnalytics.FlakyTest flakyTest = new TestAnalytics.FlakyTest(
                "testName", 0.75, 4, 3
        );
        
        assertThat(flakyTest.testName()).isEqualTo("testName");
        assertThat(flakyTest.passRate()).isEqualTo(0.75);
        assertThat(flakyTest.totalRuns()).isEqualTo(4);
        assertThat(flakyTest.passedCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("SlowTest record should work correctly")
    void slowTestRecordShouldWorkCorrectly() {
        TestAnalytics.SlowTest slowTest = new TestAnalytics.SlowTest(
                "testName", 5500.0
        );
        
        assertThat(slowTest.testName()).isEqualTo("testName");
        assertThat(slowTest.avgDurationMs()).isEqualTo(5500.0);
    }

    @Test
    @DisplayName("Default constructor should use default results directory")
    void defaultConstructorShouldUseDefaultResultsDirectory() {
        TestAnalytics defaultAnalytics = new TestAnalytics();
        assertThat(defaultAnalytics).isNotNull();
    }
}

