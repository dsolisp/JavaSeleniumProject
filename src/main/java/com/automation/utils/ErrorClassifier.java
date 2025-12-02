package com.automation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Smart Error Classifier with pattern matching, severity levels, and recovery strategies.
 * Equivalent to Python's utils/error_handler.py ErrorClassifier
 */
public class ErrorClassifier {

    private static final Logger logger = LoggerFactory.getLogger(ErrorClassifier.class);

    private static final List<ErrorPattern> ERROR_PATTERNS = List.of(
        // Network errors
        new ErrorPattern(
            Pattern.compile("(?i)(connection|connect|network|timeout|socket|dns|host).*(refused|timeout|failed|error|exception)"),
            ErrorCategory.NETWORK,
            Severity.HIGH,
            "Check network connectivity and server availability",
            RecoveryStrategy.RETRY_WITH_BACKOFF
        ),
        new ErrorPattern(
            Pattern.compile("(?i)(ssl|tls|certificate|handshake).*(error|failed|invalid)"),
            ErrorCategory.NETWORK,
            Severity.HIGH,
            "SSL/TLS certificate issue - check certificate validity",
            RecoveryStrategy.FAIL_FAST
        ),
        
        // Element not found
        new ErrorPattern(
            Pattern.compile("(?i)(element|locator|selector).*(not found|not visible|not present|stale)"),
            ErrorCategory.ELEMENT_NOT_FOUND,
            Severity.MEDIUM,
            "Element not found - check locators and page load state",
            RecoveryStrategy.RETRY_WITH_WAIT
        ),
        new ErrorPattern(
            Pattern.compile("(?i)NoSuchElementException|ElementNotInteractableException"),
            ErrorCategory.ELEMENT_NOT_FOUND,
            Severity.MEDIUM,
            "Selenium element exception - verify element locator",
            RecoveryStrategy.RETRY_WITH_WAIT
        ),
        new ErrorPattern(
            Pattern.compile("(?i)StaleElementReferenceException"),
            ErrorCategory.STALE_ELEMENT,
            Severity.MEDIUM,
            "Element became stale - re-find element",
            RecoveryStrategy.RETRY_IMMEDIATE
        ),
        
        // Timeout errors
        new ErrorPattern(
            Pattern.compile("(?i)(timeout|timed out|waiting).*(exceeded|expired|element)"),
            ErrorCategory.TIMEOUT,
            Severity.MEDIUM,
            "Operation timed out - increase timeout or check performance",
            RecoveryStrategy.RETRY_WITH_INCREASED_TIMEOUT
        ),
        new ErrorPattern(
            Pattern.compile("(?i)TimeoutException|WebDriverWait"),
            ErrorCategory.TIMEOUT,
            Severity.MEDIUM,
            "WebDriver timeout - check page load or element visibility",
            RecoveryStrategy.RETRY_WITH_INCREASED_TIMEOUT
        ),
        
        // Authentication errors
        new ErrorPattern(
            Pattern.compile("(?i)(401|403|unauthorized|forbidden|access denied|authentication|login).*(failed|error|invalid)"),
            ErrorCategory.AUTHENTICATION,
            Severity.HIGH,
            "Authentication failed - verify credentials",
            RecoveryStrategy.FAIL_FAST
        ),
        
        // API errors
        new ErrorPattern(
            Pattern.compile("(?i)(api|rest|http).*(error|failed|500|502|503|504)"),
            ErrorCategory.API_ERROR,
            Severity.HIGH,
            "API error - check server status and request payload",
            RecoveryStrategy.RETRY_WITH_BACKOFF
        ),
        new ErrorPattern(
            Pattern.compile("(?i)(400|bad request|invalid.*request)"),
            ErrorCategory.API_ERROR,
            Severity.MEDIUM,
            "Bad request - verify request parameters",
            RecoveryStrategy.FAIL_FAST
        ),
        
        // Browser/WebDriver errors
        new ErrorPattern(
            Pattern.compile("(?i)(browser|webdriver|chrome|firefox|edge).*(crash|closed|quit|session)"),
            ErrorCategory.BROWSER_ERROR,
            Severity.CRITICAL,
            "Browser crashed or closed unexpectedly - restart driver",
            RecoveryStrategy.RESTART_SESSION
        ),
        new ErrorPattern(
            Pattern.compile("(?i)SessionNotFoundException|InvalidSessionIdException"),
            ErrorCategory.BROWSER_ERROR,
            Severity.CRITICAL,
            "WebDriver session invalid - create new session",
            RecoveryStrategy.RESTART_SESSION
        ),
        
        // Data/Assertion errors
        new ErrorPattern(
            Pattern.compile("(?i)(assertion|assert|expect|should|mismatch).*(failed|error)"),
            ErrorCategory.ASSERTION_ERROR,
            Severity.LOW,
            "Assertion failed - verify expected vs actual values",
            RecoveryStrategy.FAIL_FAST
        ),
        new ErrorPattern(
            Pattern.compile("(?i)(null|npe|nullpointer).*(exception|error)"),
            ErrorCategory.DATA_ERROR,
            Severity.HIGH,
            "Null pointer - check data initialization",
            RecoveryStrategy.FAIL_FAST
        ),
        
        // Environment errors
        new ErrorPattern(
            Pattern.compile("(?i)(environment|config|configuration|property).*(missing|invalid|not found)"),
            ErrorCategory.ENVIRONMENT,
            Severity.HIGH,
            "Configuration error - verify environment settings",
            RecoveryStrategy.FAIL_FAST
        )
    );

    /**
     * Classify an error and return detailed context.
     */
    public static ErrorContext classify(Throwable error) {
        return classify(error.getClass().getName() + ": " + error.getMessage(), error);
    }

    /**
     * Classify an error message and return detailed context.
     */
    public static ErrorContext classify(String errorMessage) {
        return classify(errorMessage, null);
    }

    /**
     * Classify with both message and throwable.
     */
    public static ErrorContext classify(String errorMessage, Throwable error) {
        if (errorMessage == null) {
            errorMessage = error != null ? error.toString() : "Unknown error";
        }
        
        for (ErrorPattern pattern : ERROR_PATTERNS) {
            if (pattern.pattern.matcher(errorMessage).find()) {
                logger.debug("Classified error as {}: {}", pattern.category, errorMessage);
                return new ErrorContext(
                    pattern.category,
                    pattern.severity,
                    errorMessage,
                    pattern.suggestion,
                    pattern.recoveryStrategy,
                    error,
                    LocalDateTime.now()
                );
            }
        }
        
        // Default classification
        return new ErrorContext(
            ErrorCategory.UNKNOWN,
            Severity.MEDIUM,
            errorMessage,
            "Unknown error - check logs for details",
            RecoveryStrategy.RETRY_WITH_BACKOFF,
            error,
            LocalDateTime.now()
        );
    }

    /**
     * Check if error is retryable.
     */
    public static boolean isRetryable(ErrorContext context) {
        return context.recoveryStrategy != RecoveryStrategy.FAIL_FAST;
    }

    /**
     * Get recommended wait time before retry.
     */
    public static long getRetryDelayMs(ErrorContext context, int attemptNumber) {
        return switch (context.recoveryStrategy) {
            case RETRY_IMMEDIATE -> 0;
            case RETRY_WITH_WAIT -> 1000L * attemptNumber;
            case RETRY_WITH_BACKOFF -> (long) (1000 * Math.pow(2, attemptNumber - 1));
            case RETRY_WITH_INCREASED_TIMEOUT -> 2000L * attemptNumber;
            case RESTART_SESSION, FAIL_FAST -> -1;
        };
    }

    // ═══════════════════════════════════════════════════════════════════
    // ENUMS AND RECORDS
    // ═══════════════════════════════════════════════════════════════════

    public enum ErrorCategory {
        NETWORK,
        ELEMENT_NOT_FOUND,
        STALE_ELEMENT,
        TIMEOUT,
        AUTHENTICATION,
        API_ERROR,
        BROWSER_ERROR,
        ASSERTION_ERROR,
        DATA_ERROR,
        ENVIRONMENT,
        UNKNOWN
    }

    public enum Severity {
        LOW(1),
        MEDIUM(2),
        HIGH(3),
        CRITICAL(4);

        private final int level;

        Severity(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }

        public boolean isHigherThan(Severity other) {
            return this.level > other.level;
        }
    }

    public enum RecoveryStrategy {
        FAIL_FAST,
        RETRY_IMMEDIATE,
        RETRY_WITH_WAIT,
        RETRY_WITH_BACKOFF,
        RETRY_WITH_INCREASED_TIMEOUT,
        RESTART_SESSION
    }

    private record ErrorPattern(
        Pattern pattern,
        ErrorCategory category,
        Severity severity,
        String suggestion,
        RecoveryStrategy recoveryStrategy
    ) {}

    public record ErrorContext(
        ErrorCategory category,
        Severity severity,
        String message,
        String suggestion,
        RecoveryStrategy recoveryStrategy,
        Throwable originalError,
        LocalDateTime timestamp
    ) {
        public boolean isRetryable() {
            return ErrorClassifier.isRetryable(this);
        }

        public String toLogString() {
            return String.format("[%s/%s] %s - %s",
                category, severity, message, suggestion);
        }
    }
}
