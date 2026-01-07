package com.automation.extensions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark tests that should be retried on failure.
 * Useful for flaky UI tests that may fail due to timing issues.
 *
 * <p>Usage:
 * <pre>
 * {@code
 * @ExtendWith(RetryExtension.class)
 * class MyFlakyTest {
 *
 *     @Test
 *     @RetryOnFailure(maxRetries = 3)
 *     void flakyTest() {
 *         // Test that might fail intermittently
 *     }
 *
 *     @Test
 *     @RetryOnFailure(maxRetries = 2, retryOn = {TimeoutException.class})
 *     void timeoutSensitiveTest() {
 *         // Only retry on TimeoutException
 *     }
 * }
 * }
 * </pre>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RetryOnFailure {

    /**
     * Maximum number of retry attempts (default: 3).
     */
    int maxRetries() default 3;

    /**
     * Exception types that should trigger a retry.
     * If empty, all exceptions trigger a retry.
     */
    Class<? extends Throwable>[] retryOn() default {};

    /**
     * Delay in milliseconds between retry attempts (default: 1000ms).
     */
    long delayMs() default 1000;
}

