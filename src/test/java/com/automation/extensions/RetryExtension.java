package com.automation.extensions;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExecutableInvoker;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * JUnit 5 extension that retries failed tests.
 * Works with {@link RetryOnFailure} annotation to configure retry behavior.
 *
 * <p>Features:
 * <ul>
 *   <li>Configurable max retry attempts</li>
 *   <li>Optional filtering by exception type</li>
 *   <li>Configurable delay between retries</li>
 *   <li>Allure reporting integration</li>
 *   <li>Detailed logging</li>
 * </ul>
 *
 * <p>Usage:
 * <pre>
 * {@code
 * @ExtendWith(RetryExtension.class)
 * class MyTest {
 *     @Test
 *     @RetryOnFailure(maxRetries = 3)
 *     void flakyTest() { ... }
 * }
 * }
 * </pre>
 */
public class RetryExtension implements TestExecutionExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RetryExtension.class);

    private static final String RETRY_COUNT_KEY = "retryCount";

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) 
            throws Throwable {
        
        Method testMethod = context.getRequiredTestMethod();
        RetryOnFailure annotation = getRetryAnnotation(testMethod, context.getRequiredTestClass());

        if (annotation == null) {
            // No retry annotation - rethrow immediately
            throw throwable;
        }

        int maxRetries = annotation.maxRetries();
        Class<? extends Throwable>[] retryOn = annotation.retryOn();
        long delayMs = annotation.delayMs();

        // Check if this exception type should trigger retry
        if (!shouldRetry(throwable, retryOn)) {
            logger.debug("Exception {} not in retry list, failing immediately", 
                    throwable.getClass().getSimpleName());
            throw throwable;
        }

        // Get current retry count from store
        ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(
                RetryExtension.class, testMethod));
        int currentRetry = store.getOrDefault(RETRY_COUNT_KEY, Integer.class, 0);

        if (currentRetry < maxRetries) {
            currentRetry++;
            store.put(RETRY_COUNT_KEY, currentRetry);

            String testName = context.getDisplayName();
            logger.warn("Test '{}' failed (attempt {}/{}). Retrying in {}ms...",
                    testName, currentRetry, maxRetries + 1, delayMs);
            logger.debug("Failure reason: {}", throwable.getMessage());

            // Log to Allure
            Allure.step(String.format("Retry %d/%d - Previous failure: %s", 
                    currentRetry, maxRetries, throwable.getMessage()));

            // Wait before retry
            if (delayMs > 0) {
                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw throwable;
                }
            }

            // Re-execute the test using JUnit's executable invoker so that
            // parameter resolution and extensions (e.g., WebDriver injection)
            // continue to work correctly.
            Object testInstance = context.getRequiredTestInstance();
            ExecutableInvoker invoker = context.getExecutableInvoker();
            try {
                invoker.invoke(testMethod, testInstance);
                logger.info("Test '{}' passed on retry attempt {}", testName, currentRetry);
            } catch (Throwable t) {
                handleTestExecutionException(context, t);
            }
        } else {
            logger.error("Test '{}' failed after {} retries", 
                    context.getDisplayName(), maxRetries);
            Allure.step("Test failed after " + maxRetries + " retries");
            throw throwable;
        }
    }

    private RetryOnFailure getRetryAnnotation(Method method, Class<?> testClass) {
        // Check method first, then class
        RetryOnFailure annotation = method.getAnnotation(RetryOnFailure.class);
        if (annotation == null) {
            annotation = testClass.getAnnotation(RetryOnFailure.class);
        }
        return annotation;
    }

    private boolean shouldRetry(Throwable throwable, Class<? extends Throwable>[] retryOn) {
        if (retryOn == null || retryOn.length == 0) {
            return true; // Retry on all exceptions
        }
        return Arrays.stream(retryOn)
                .anyMatch(exceptionClass -> exceptionClass.isInstance(throwable));
    }
}

