package com.automation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Test analytics engine for flaky test detection and reliability scoring.
 * Equivalent to Python's utils/test_analytics.py
 */
public class TestAnalytics {

    private static final Logger logger = LoggerFactory.getLogger(TestAnalytics.class);

    private final Path resultsDir;
    private final ObjectMapper objectMapper;
    private List<Map<String, Object>> testExecutions;

    // Thresholds
    private static final double FLAKY_THRESHOLD = 0.9;  // Tests with <90% pass rate
    private static final int MIN_EXECUTIONS_FOR_FLAKY = 3;
    private static final long SLOW_TEST_THRESHOLD_MS = 5000;

    public TestAnalytics() {
        this(Paths.get("src/test/resources/data/results"));
    }

    public TestAnalytics(String resultsDirPath) {
        this(Paths.get(resultsDirPath));
    }

    public TestAnalytics(Path resultsDir) {
        this.resultsDir = resultsDir;
        this.objectMapper = new ObjectMapper();
        this.testExecutions = new ArrayList<>();
    }

    /**
     * Load test results from JSON files.
     */
    public int loadTestResults() {
        testExecutions.clear();
        
        if (!Files.exists(resultsDir)) {
            logger.warn("Results directory not found: {}", resultsDir);
            return 0;
        }

        try (Stream<Path> paths = Files.walk(resultsDir)) {
            List<Path> jsonFiles = paths
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".json"))
                    .collect(Collectors.toList());

            for (Path file : jsonFiles) {
                try {
                    Map<String, Object> data = objectMapper.readValue(
                            file.toFile(), new TypeReference<>() {}
                    );
                    
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> results = 
                            (List<Map<String, Object>>) data.get("results");
                    
                    if (results != null) {
                        testExecutions.addAll(results);
                    }
                } catch (IOException e) {
                    logger.warn("Failed to parse {}: {}", file, e.getMessage());
                }
            }
        } catch (IOException e) {
            logger.error("Failed to load test results", e);
        }

        logger.info("Loaded {} test executions", testExecutions.size());
        return testExecutions.size();
    }

    /**
     * Detect flaky tests based on pass rate.
     */
    public List<FlakyTest> detectFlakyTests() {
        return getFlakyTests(FLAKY_THRESHOLD);
    }

    /**
     * Get flaky tests with custom threshold.
     */
    public List<FlakyTest> getFlakyTests(double threshold) {
        loadTestResults();

        Map<String, List<Boolean>> testResults = new HashMap<>();

        for (Map<String, Object> execution : testExecutions) {
            String testName = (String) execution.get("testName");
            Boolean passed = (Boolean) execution.get("passed");

            if (testName != null && passed != null) {
                testResults.computeIfAbsent(testName, k -> new ArrayList<>()).add(passed);
            }
        }

        List<FlakyTest> flakyTests = new ArrayList<>();

        for (Map.Entry<String, List<Boolean>> entry : testResults.entrySet()) {
            List<Boolean> results = entry.getValue();

            if (results.size() >= MIN_EXECUTIONS_FOR_FLAKY) {
                long passed = results.stream().filter(b -> b).count();
                double passRate = (double) passed / results.size();

                if (passRate < threshold && passRate > 0) {
                    flakyTests.add(new FlakyTest(
                            entry.getKey(),
                            passRate,
                            results.size(),
                            (int) passed
                    ));
                }
            }
        }

        flakyTests.sort(Comparator.comparingDouble(FlakyTest::passRate));
        logger.info("Detected {} flaky tests", flakyTests.size());
        return flakyTests;
    }

    /**
     * Identify slow tests.
     */
    public List<SlowTest> detectSlowTests() {
        return getSlowTests(SLOW_TEST_THRESHOLD_MS);
    }

    /**
     * Get slow tests with custom threshold.
     */
    public List<SlowTest> getSlowTests(long thresholdMs) {
        loadTestResults();

        Map<String, List<Long>> testDurations = new HashMap<>();

        for (Map<String, Object> execution : testExecutions) {
            String testName = (String) execution.get("testName");
            Object duration = execution.get("durationMs");

            if (testName != null && duration != null) {
                long durationMs = duration instanceof Number ?
                        ((Number) duration).longValue() : 0L;
                testDurations.computeIfAbsent(testName, k -> new ArrayList<>()).add(durationMs);
            }
        }

        List<SlowTest> slowTests = new ArrayList<>();

        for (Map.Entry<String, List<Long>> entry : testDurations.entrySet()) {
            double avgDuration = entry.getValue().stream()
                    .mapToLong(Long::longValue)
                    .average()
                    .orElse(0);

            if (avgDuration > thresholdMs) {
                slowTests.add(new SlowTest(entry.getKey(), avgDuration));
            }
        }

        slowTests.sort((a, b) -> Double.compare(b.avgDurationMs(), a.avgDurationMs()));
        logger.info("Detected {} slow tests", slowTests.size());
        return slowTests;
    }

    /**
     * Generate test reliability report.
     */
    public void generateReport() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("TEST ANALYTICS REPORT");
        System.out.println("=".repeat(60));
        System.out.printf("Total test executions: %d%n", testExecutions.size());

        List<FlakyTest> flakyTests = detectFlakyTests();
        System.out.printf("%n⚠️  Flaky Tests (%d):%n", flakyTests.size());
        flakyTests.forEach(t -> 
            System.out.printf("   • %s: %.0f%% pass rate (%d/%d passed)%n",
                    t.testName(), t.passRate() * 100, t.passedCount(), t.totalRuns())
        );

        List<SlowTest> slowTests = detectSlowTests();
        System.out.printf("%n🐢 Slow Tests (%d):%n", slowTests.size());
        slowTests.forEach(t ->
            System.out.printf("   • %s: %.2fs avg%n", t.testName(), t.avgDurationMs() / 1000.0)
        );

        System.out.println("\n" + "=".repeat(60));
    }

    // Record classes
    public record FlakyTest(String testName, double passRate, int totalRuns, int passedCount) {}
    public record SlowTest(String testName, double avgDurationMs) {}
}

