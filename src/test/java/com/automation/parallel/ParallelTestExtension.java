package com.automation.parallel;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit 5 extension for parallel test execution with proper isolation.
 * 
 * Usage:
 * @ExtendWith(ParallelTestExtension.class)
 * class MyParallelTest { ... }
 */
public class ParallelTestExtension implements 
        BeforeEachCallback, 
        AfterEachCallback,
        BeforeAllCallback,
        AfterAllCallback,
        TestWatcher {

    private static final Logger logger = LoggerFactory.getLogger(ParallelTestExtension.class);

    @Override
    public void beforeAll(ExtensionContext context) {
        logger.info("Starting parallel test class: {}", context.getDisplayName());
    }

    @Override
    public void afterAll(ExtensionContext context) {
        logger.info("Finished parallel test class: {}", context.getDisplayName());
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        String testName = context.getDisplayName();
        long threadId = Thread.currentThread().getId();
        
        logger.info("Starting test '{}' on thread {}", testName, threadId);
        
        // Initialize test context
        TestIsolation.TestContext ctx = TestIsolation.getContext();
        ctx.setTestName(testName);
        ctx.set("startTime", System.currentTimeMillis());
        
        // Create driver if needed (lazy initialization)
        // Driver creation is deferred to test if needed
    }

    @Override
    public void afterEach(ExtensionContext context) {
        String testName = context.getDisplayName();
        long threadId = Thread.currentThread().getId();
        
        try {
            // Clean up driver
            if (ThreadSafeDriverManager.hasDriver()) {
                ThreadSafeDriverManager.removeDriver();
            }
        } catch (Exception e) {
            logger.warn("Error cleaning up driver: {}", e.getMessage());
        }
        
        // Calculate duration
        Long startTime = TestIsolation.get("startTime");
        long duration = startTime != null ? System.currentTimeMillis() - startTime : 0;
        
        logger.info("Finished test '{}' on thread {} ({}ms)", testName, threadId, duration);
        
        // Clear test context
        TestIsolation.clear();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.debug("Test passed: {}", context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("Test failed: {} - {}", context.getDisplayName(), cause.getMessage());
        
        // Take screenshot on failure if driver exists
        if (ThreadSafeDriverManager.hasDriver()) {
            try {
                var driver = ThreadSafeDriverManager.getDriver();
                // Screenshot logic would go here
            } catch (Exception e) {
                logger.warn("Could not capture failure screenshot: {}", e.getMessage());
            }
        }
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        logger.warn("Test aborted: {} - {}", context.getDisplayName(), cause.getMessage());
    }

    @Override
    public void testDisabled(ExtensionContext context, java.util.Optional<String> reason) {
        logger.info("Test disabled: {} - {}", context.getDisplayName(), 
            reason.orElse("No reason provided"));
    }
}

