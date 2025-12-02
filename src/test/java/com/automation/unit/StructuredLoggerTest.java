package com.automation.unit;

import com.automation.utils.StructuredLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for StructuredLogger.
 */
@DisplayName("StructuredLogger Tests")
class StructuredLoggerTest {

    private StructuredLogger logger;

    @BeforeEach
    void setUp() {
        logger = new StructuredLogger("TestComponent");
    }

    @Test
    @DisplayName("Should create logger with component name")
    void shouldCreateLoggerWithComponentName() {
        StructuredLogger componentLogger = new StructuredLogger("MyComponent");
        assertThat(componentLogger).isNotNull();
    }

    @Test
    @DisplayName("Should create logger with class")
    void shouldCreateLoggerWithClass() {
        StructuredLogger classLogger = new StructuredLogger(StructuredLoggerTest.class);
        assertThat(classLogger).isNotNull();
    }

    @Test
    @DisplayName("Should log test started event")
    void shouldLogTestStartedEvent() {
        // Should not throw exception
        logger.testStarted("testExample", "unit", "chrome");
    }

    @Test
    @DisplayName("Should log test completed event - passed")
    void shouldLogTestCompletedEventPassed() {
        // Should not throw exception
        logger.testCompleted("testExample", "PASSED", 1500);
    }

    @Test
    @DisplayName("Should log test completed event - failed")
    void shouldLogTestCompletedEventFailed() {
        // Should not throw exception
        logger.testCompleted("testExample", "FAILED", 3000);
    }

    @Test
    @DisplayName("Should log API request")
    void shouldLogApiRequest() {
        // Should not throw exception
        logger.apiRequest("GET", "https://api.example.com/users", 200, 150);
    }

    @Test
    @DisplayName("Should log page action - success")
    void shouldLogPageActionSuccess() {
        // Should not throw exception
        logger.pageAction("CLICK", "submitButton", true);
    }

    @Test
    @DisplayName("Should log page action - failure")
    void shouldLogPageActionFailure() {
        // Should not throw exception
        logger.pageAction("CLICK", "submitButton", false);
    }

    @Test
    @DisplayName("Should log performance metric")
    void shouldLogPerformanceMetric() {
        // Should not throw exception
        logger.performanceMetric("page_load_time", 1250.5, "ms");
    }

    @Test
    @DisplayName("Should log with custom context")
    void shouldLogWithCustomContext() {
        Map<String, String> context = new HashMap<>();
        context.put("user_id", "12345");
        context.put("session_id", "abc-123");
        
        // Should not throw exception
        logger.logWithContext("INFO", "User performed action", context);
    }

    @Test
    @DisplayName("Should support debug logging")
    void shouldSupportDebugLogging() {
        // Should not throw exception
        logger.debug("Debug message with param: {}", "value");
    }

    @Test
    @DisplayName("Should support info logging")
    void shouldSupportInfoLogging() {
        // Should not throw exception
        logger.info("Info message with params: {} and {}", "value1", "value2");
    }

    @Test
    @DisplayName("Should support warn logging")
    void shouldSupportWarnLogging() {
        // Should not throw exception
        logger.warn("Warning message");
    }

    @Test
    @DisplayName("Should support error logging")
    void shouldSupportErrorLogging() {
        // Should not throw exception
        logger.error("Error message");
    }

    @Test
    @DisplayName("Should support error logging with throwable")
    void shouldSupportErrorLoggingWithThrowable() {
        Exception testException = new RuntimeException("Test exception");
        // Should not throw exception
        logger.error("Error occurred", testException);
    }

    @Test
    @DisplayName("Should handle different log levels in logWithContext")
    void shouldHandleDifferentLogLevels() {
        Map<String, String> context = Map.of("key", "value");
        
        // Should not throw exception for any level
        logger.logWithContext("DEBUG", "Debug message", context);
        logger.logWithContext("INFO", "Info message", context);
        logger.logWithContext("WARN", "Warn message", context);
        logger.logWithContext("ERROR", "Error message", context);
        logger.logWithContext("UNKNOWN", "Unknown level defaults to info", context);
    }
}

