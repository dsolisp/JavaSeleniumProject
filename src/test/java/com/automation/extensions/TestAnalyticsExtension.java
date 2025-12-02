package com.automation.extensions;

import com.automation.utils.TestAnalytics;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JUnit 5 extension for automatic test result collection.
 * Integrates with TestAnalytics for flaky test detection and reporting.
 * 
 * Usage: @ExtendWith(TestAnalyticsExtension.class) on test class
 */
public class TestAnalyticsExtension implements 
        BeforeAllCallback, AfterAllCallback, 
        BeforeEachCallback, AfterEachCallback,
        TestExecutionExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(TestAnalyticsExtension.class);
    private static final String RESULTS_DIR = "test_results";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    // Store test execution data
    private static final Map<String, Long> testStartTimes = new ConcurrentHashMap<>();
    private static final List<Map<String, Object>> testResults = Collections.synchronizedList(new ArrayList<>());
    private static String suiteName;
    private static long suiteStartTime;

    @Override
    public void beforeAll(ExtensionContext context) {
        suiteName = context.getDisplayName();
        suiteStartTime = System.currentTimeMillis();
        logger.info("Test suite started: {}", suiteName);
        
        // Ensure results directory exists
        try {
            Files.createDirectories(Path.of(RESULTS_DIR));
        } catch (IOException e) {
            logger.error("Failed to create results directory", e);
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        long suiteDuration = System.currentTimeMillis() - suiteStartTime;
        logger.info("Test suite completed: {} ({} tests in {}ms)", 
                suiteName, testResults.size(), suiteDuration);
        
        // Save results to JSON file
        saveTestResults();
        
        // Generate analytics report
        try {
            TestAnalytics analytics = new TestAnalytics(RESULTS_DIR);
            analytics.generateReport();
        } catch (Exception e) {
            logger.error("Failed to generate analytics report", e);
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        String testName = getTestName(context);
        testStartTimes.put(testName, System.currentTimeMillis());
        logger.debug("Test started: {}", testName);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        String testName = getTestName(context);
        Long startTime = testStartTimes.remove(testName);
        long duration = startTime != null ? System.currentTimeMillis() - startTime : 0;
        
        boolean passed = context.getExecutionException().isEmpty();
        String errorMessage = context.getExecutionException()
                .map(Throwable::getMessage)
                .orElse(null);
        
        recordTestResult(testName, passed, duration, errorMessage);
        
        logger.debug("Test completed: {} - {} ({}ms)", 
                testName, passed ? "PASSED" : "FAILED", duration);
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        // Record the exception but rethrow it
        String testName = getTestName(context);
        logger.error("Test failed with exception: {}", testName, throwable);
        throw throwable;
    }

    private String getTestName(ExtensionContext context) {
        return context.getTestClass()
                .map(Class::getSimpleName)
                .orElse("Unknown") + "." + context.getDisplayName();
    }

    private void recordTestResult(String testName, boolean passed, long durationMs, String errorMessage) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("testName", testName);
        result.put("passed", passed);
        result.put("durationMs", durationMs);
        result.put("errorMessage", errorMessage);
        result.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        testResults.add(result);
    }

    private void saveTestResults() {
        if (testResults.isEmpty()) {
            return;
        }
        
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = String.format("results_%s_%s.json", 
                suiteName.replaceAll("[^a-zA-Z0-9]", "_"), timestamp);
        Path filePath = Path.of(RESULTS_DIR, filename);
        
        try {
            Map<String, Object> output = new LinkedHashMap<>();
            output.put("suite", suiteName);
            output.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            output.put("totalDurationMs", System.currentTimeMillis() - suiteStartTime);
            output.put("results", new ArrayList<>(testResults));
            
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(output);
            Files.writeString(filePath, json);
            
            logger.info("Test results saved: {}", filePath);
            
            // Clear results for next suite
            testResults.clear();
        } catch (IOException e) {
            logger.error("Failed to save test results", e);
        }
    }

    /**
     * Get current test statistics.
     */
    public static Map<String, Object> getStatistics() {
        long passed = testResults.stream().filter(r -> (Boolean) r.get("passed")).count();
        long failed = testResults.size() - passed;
        
        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("total", testResults.size());
        stats.put("passed", passed);
        stats.put("failed", failed);
        stats.put("passRate", testResults.isEmpty() ? 100.0 : 
                (double) passed / testResults.size() * 100);
        
        return stats;
    }
}

