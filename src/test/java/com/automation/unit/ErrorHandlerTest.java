package com.automation.unit;

import com.automation.utils.ErrorHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for ErrorHandler with Resilience4j retry.
 */
@DisplayName("ErrorHandler Tests")
class ErrorHandlerTest {

    private ErrorHandler errorHandler;

    @BeforeEach
    void setUp() {
        errorHandler = new ErrorHandler(3, Duration.ofMillis(100));
    }

    @Test
    @DisplayName("Should execute successful operation without retry")
    void shouldExecuteSuccessfulOperationWithoutRetry() {
        AtomicInteger callCount = new AtomicInteger(0);
        
        String result = errorHandler.executeWithRetry(() -> {
            callCount.incrementAndGet();
            return "success";
        });
        
        assertThat(result).isEqualTo("success");
        assertThat(callCount.get()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should retry on failure and eventually succeed")
    void shouldRetryOnFailureAndEventuallySucceed() {
        AtomicInteger callCount = new AtomicInteger(0);
        
        String result = errorHandler.executeWithRetry(() -> {
            int count = callCount.incrementAndGet();
            if (count < 3) {
                throw new org.openqa.selenium.StaleElementReferenceException("Stale element");
            }
            return "success after retries";
        });
        
        assertThat(result).isEqualTo("success after retries");
        assertThat(callCount.get()).isEqualTo(3);
    }

    @Test
    @DisplayName("Should throw exception after max retries")
    void shouldThrowExceptionAfterMaxRetries() {
        AtomicInteger callCount = new AtomicInteger(0);
        
        assertThatThrownBy(() -> errorHandler.executeWithRetry(() -> {
            callCount.incrementAndGet();
            throw new org.openqa.selenium.NoSuchElementException("Element not found");
        })).isInstanceOf(org.openqa.selenium.NoSuchElementException.class);
        
        assertThat(callCount.get()).isEqualTo(3);
    }

    @Test
    @DisplayName("Should not retry on ignored exceptions")
    void shouldNotRetryOnIgnoredExceptions() {
        AtomicInteger callCount = new AtomicInteger(0);
        
        assertThatThrownBy(() -> errorHandler.executeWithRetry(() -> {
            callCount.incrementAndGet();
            throw new org.openqa.selenium.InvalidSelectorException("Invalid selector");
        })).isInstanceOf(org.openqa.selenium.InvalidSelectorException.class);
        
        assertThat(callCount.get()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should execute void operation with retry")
    void shouldExecuteVoidOperationWithRetry() {
        AtomicInteger callCount = new AtomicInteger(0);
        
        errorHandler.executeWithRetry(() -> {
            int count = callCount.incrementAndGet();
            if (count < 2) {
                throw new org.openqa.selenium.ElementNotInteractableException("Not interactable");
            }
        });
        
        assertThat(callCount.get()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should create custom retry configuration")
    void shouldCreateCustomRetryConfiguration() {
        var customRetry = errorHandler.createCustomRetry("custom", 5, Duration.ofSeconds(1));
        
        assertThat(customRetry).isNotNull();
        assertThat(customRetry.getName()).isEqualTo("custom");
    }

    @Test
    @DisplayName("Should provide retry statistics")
    void shouldProvideRetryStatistics() {
        // Execute a few operations
        errorHandler.executeWithRetry(() -> "success");
        
        ErrorHandler.RetryStats stats = errorHandler.getStats();
        
        assertThat(stats).isNotNull();
        assertThat(stats.successWithoutRetry()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @DisplayName("Default constructor should create handler with default settings")
    void defaultConstructorShouldCreateHandlerWithDefaultSettings() {
        ErrorHandler defaultHandler = new ErrorHandler();
        
        assertThat(defaultHandler).isNotNull();
        String result = defaultHandler.executeWithRetry(() -> "test");
        assertThat(result).isEqualTo("test");
    }
}

