package com.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Structured logging utility with JSON output support.
 * Equivalent to Python's utils/structured_logger.py
 * 
 * Uses SLF4J with Logback + Logstash encoder for JSON output.
 */
public class StructuredLogger {

    private final Logger logger;
    private final String component;

    public StructuredLogger(String component) {
        this.component = component;
        this.logger = LoggerFactory.getLogger(component);
    }

    public StructuredLogger(Class<?> clazz) {
        this.component = clazz.getSimpleName();
        this.logger = LoggerFactory.getLogger(clazz);
    }

    /**
     * Log test start event.
     */
    public void testStarted(String testName, String testType, String browser) {
        try {
            MDC.put("event_type", "test_started");
            MDC.put("test_name", testName);
            MDC.put("test_type", testType);
            MDC.put("browser", browser);
            MDC.put("timestamp", Instant.now().toString());
            
            logger.info("Test started: {} [{}] on {}", testName, testType, browser);
        } finally {
            MDC.clear();
        }
    }

    /**
     * Log test completion event.
     */
    public void testCompleted(String testName, String status, long durationMs) {
        try {
            MDC.put("event_type", "test_completed");
            MDC.put("test_name", testName);
            MDC.put("status", status);
            MDC.put("duration_ms", String.valueOf(durationMs));
            MDC.put("timestamp", Instant.now().toString());
            
            if ("PASSED".equals(status)) {
                logger.info("Test passed: {} ({}ms)", testName, durationMs);
            } else {
                logger.error("Test failed: {} ({}ms)", testName, durationMs);
            }
        } finally {
            MDC.clear();
        }
    }

    /**
     * Log API request.
     */
    public void apiRequest(String method, String url, int statusCode, long responseTimeMs) {
        try {
            MDC.put("event_type", "api_request");
            MDC.put("method", method);
            MDC.put("url", url);
            MDC.put("status_code", String.valueOf(statusCode));
            MDC.put("response_time_ms", String.valueOf(responseTimeMs));
            
            logger.info("{} {} -> {} ({}ms)", method, url, statusCode, responseTimeMs);
        } finally {
            MDC.clear();
        }
    }

    /**
     * Log page action.
     */
    public void pageAction(String action, String element, boolean success) {
        try {
            MDC.put("event_type", "page_action");
            MDC.put("action", action);
            MDC.put("element", element);
            MDC.put("success", String.valueOf(success));
            
            if (success) {
                logger.debug("{} on {} - success", action, element);
            } else {
                logger.warn("{} on {} - failed", action, element);
            }
        } finally {
            MDC.clear();
        }
    }

    /**
     * Log performance metric.
     */
    public void performanceMetric(String metricName, double value, String unit) {
        try {
            MDC.put("event_type", "performance_metric");
            MDC.put("metric_name", metricName);
            MDC.put("value", String.valueOf(value));
            MDC.put("unit", unit);
            
            logger.info("Performance: {} = {}{}", metricName, value, unit);
        } finally {
            MDC.clear();
        }
    }

    /**
     * Log with custom context.
     */
    public void logWithContext(String level, String message, Map<String, String> context) {
        try {
            context.forEach(MDC::put);
            MDC.put("component", component);
            
            switch (level.toUpperCase()) {
                case "DEBUG" -> logger.debug(message);
                case "INFO" -> logger.info(message);
                case "WARN" -> logger.warn(message);
                case "ERROR" -> logger.error(message);
                default -> logger.info(message);
            }
        } finally {
            MDC.clear();
        }
    }

    // Standard logging methods
    public void debug(String message, Object... args) { logger.debug(message, args); }
    public void info(String message, Object... args) { logger.info(message, args); }
    public void warn(String message, Object... args) { logger.warn(message, args); }
    public void error(String message, Object... args) { logger.error(message, args); }
    public void error(String message, Throwable t) { logger.error(message, t); }
}

