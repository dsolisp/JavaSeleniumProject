package com.automation.utils;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Error handler with retry mechanisms using Resilience4j.
 *
 * Provides generic retry functionality for any operation that may fail transiently.
 * For Selenium-specific waits, prefer using WebDriverWait with ExpectedConditions
 * or FluentWait for more control.
 */
public class ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    private final Retry retry;

    // Default configuration
    private static final int DEFAULT_MAX_ATTEMPTS = 3;
    private static final Duration DEFAULT_WAIT_DURATION = Duration.ofSeconds(1);

    public ErrorHandler() {
        this(DEFAULT_MAX_ATTEMPTS, DEFAULT_WAIT_DURATION);
    }

    public ErrorHandler(int maxAttempts, Duration waitDuration) {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(maxAttempts)
                .waitDuration(waitDuration)
                .retryExceptions(
                        StaleElementReferenceException.class,
                        ElementNotInteractableException.class,
                        TimeoutException.class
                )
                .ignoreExceptions(
                        InvalidSelectorException.class,
                        NoSuchElementException.class  // Let this fail fast
                )
                .build();

        this.retry = Retry.of("errorHandler", config);

        // Log retry attempts
        retry.getEventPublisher()
                .onRetry(event -> logger.warn(
                        "Retry attempt {} (error: {})",
                        event.getNumberOfRetryAttempts(),
                        event.getLastThrowable().getMessage()
                ));
    }

    /**
     * Execute an operation with automatic retry on transient failures.
     *
     * @param operation The operation to execute
     * @param <T>       Return type
     * @return Result of the operation
     */
    public <T> T executeWithRetry(Supplier<T> operation) {
        return Retry.decorateSupplier(retry, operation).get();
    }

    /**
     * Execute a void operation with retry.
     */
    public void executeWithRetry(Runnable operation) {
        Retry.decorateRunnable(retry, operation).run();
    }

    /**
     * Create a one-off retry with custom configuration.
     */
    public static <T> T withRetry(int maxAttempts, Duration waitDuration, Supplier<T> operation) {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(maxAttempts)
                .waitDuration(waitDuration)
                .build();
        return Retry.of("custom", config).executeSupplier(operation);
    }
}

