package com.automation.utils;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * Smart error handler with retry mechanisms using Resilience4j.
 * Equivalent to Python's utils/error_handler.py with tenacity.
 * 
 * Design Pattern: Strategy Pattern - different recovery strategies
 */
public class ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    private final RetryRegistry retryRegistry;
    private final Retry defaultRetry;

    // Default configuration
    private static final int DEFAULT_MAX_ATTEMPTS = 3;
    private static final Duration DEFAULT_WAIT_DURATION = Duration.ofSeconds(2);

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
                        TimeoutException.class,
                        NoSuchElementException.class
                )
                .ignoreExceptions(
                        InvalidSelectorException.class
                )
                .build();

        this.retryRegistry = RetryRegistry.of(config);
        this.defaultRetry = retryRegistry.retry("default");
        
        // Add event listeners for logging
        defaultRetry.getEventPublisher()
                .onRetry(event -> logger.warn(
                        "Retry attempt {} for operation (error: {})",
                        event.getNumberOfRetryAttempts(),
                        event.getLastThrowable().getMessage()
                ))
                .onSuccess(event -> logger.debug(
                        "Operation succeeded after {} attempts",
                        event.getNumberOfRetryAttempts()
                ))
                .onError(event -> logger.error(
                        "Operation failed after {} attempts: {}",
                        event.getNumberOfRetryAttempts(),
                        event.getLastThrowable().getMessage()
                ));
    }

    /**
     * Execute an operation with automatic retry on failure.
     *
     * @param operation The operation to execute
     * @param <T>       Return type
     * @return Result of the operation
     */
    public <T> T executeWithRetry(Supplier<T> operation) {
        return Retry.decorateSupplier(defaultRetry, operation).get();
    }

    /**
     * Execute an operation with retry, allowing checked exceptions.
     */
    public <T> T executeWithRetryChecked(Callable<T> operation) throws Exception {
        return Retry.decorateCallable(defaultRetry, operation).call();
    }

    /**
     * Execute a void operation with retry.
     */
    public void executeWithRetry(Runnable operation) {
        Retry.decorateRunnable(defaultRetry, operation).run();
    }

    /**
     * Click an element with retry logic.
     */
    public void clickWithRetry(WebDriver driver, By locator) {
        executeWithRetry(() -> {
            WebElement element = driver.findElement(locator);
            element.click();
        });
        logger.debug("Clicked element: {}", locator);
    }

    /**
     * Type text into an element with retry logic.
     */
    public void typeWithRetry(WebDriver driver, By locator, String text) {
        executeWithRetry(() -> {
            WebElement element = driver.findElement(locator);
            element.clear();
            element.sendKeys(text);
        });
        logger.debug("Typed text into element: {}", locator);
    }

    /**
     * Find element with retry logic.
     */
    public WebElement findElementWithRetry(WebDriver driver, By locator) {
        return executeWithRetry(() -> driver.findElement(locator));
    }

    /**
     * Create a custom retry configuration.
     */
    public Retry createCustomRetry(String name, int maxAttempts, Duration waitDuration) {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(maxAttempts)
                .waitDuration(waitDuration)
                .build();
        return retryRegistry.retry(name, config);
    }

    /**
     * Recovery strategy: Refresh page and retry.
     */
    public <T> T executeWithPageRefresh(WebDriver driver, Supplier<T> operation) {
        try {
            return executeWithRetry(operation);
        } catch (Exception e) {
            logger.warn("Initial attempt failed, refreshing page and retrying: {}", e.getMessage());
            driver.navigate().refresh();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            return executeWithRetry(operation);
        }
    }

    /**
     * Get retry statistics.
     */
    public RetryStats getStats() {
        var metrics = defaultRetry.getMetrics();
        return new RetryStats(
                metrics.getNumberOfSuccessfulCallsWithoutRetryAttempt(),
                metrics.getNumberOfSuccessfulCallsWithRetryAttempt(),
                metrics.getNumberOfFailedCallsWithoutRetryAttempt(),
                metrics.getNumberOfFailedCallsWithRetryAttempt()
        );
    }

    public record RetryStats(
            long successWithoutRetry,
            long successWithRetry,
            long failedWithoutRetry,
            long failedWithRetry
    ) {}
}

