package com.automation.parallel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Test isolation utilities for parallel execution.
 * Provides thread-local context and data isolation.
 */
public class TestIsolation {

    private static final Logger logger = LoggerFactory.getLogger(TestIsolation.class);
    
    // Thread-local test context
    private static final ThreadLocal<TestContext> contextThreadLocal = 
        ThreadLocal.withInitial(TestContext::new);

    // ═══════════════════════════════════════════════════════════════════
    // CONTEXT MANAGEMENT
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Get test context for current thread.
     */
    public static TestContext getContext() {
        return contextThreadLocal.get();
    }

    /**
     * Set value in current test context.
     */
    public static void set(String key, Object value) {
        getContext().set(key, value);
    }

    /**
     * Get value from current test context.
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) getContext().get(key);
    }

    /**
     * Get value with default.
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String key, T defaultValue) {
        T value = (T) getContext().get(key);
        return value != null ? value : defaultValue;
    }

    /**
     * Clear test context for current thread.
     */
    public static void clear() {
        getContext().clear();
        logger.debug("Cleared test context for thread {}", Thread.currentThread().getId());
    }

    /**
     * Remove context entirely.
     */
    public static void remove() {
        contextThreadLocal.remove();
    }

    // ═══════════════════════════════════════════════════════════════════
    // TEST CONTEXT CLASS
    // ═══════════════════════════════════════════════════════════════════

    public static class TestContext {
        private final Map<String, Object> data = new ConcurrentHashMap<>();
        private final long threadId;
        private final long createdAt;
        private String testName;

        public TestContext() {
            this.threadId = Thread.currentThread().getId();
            this.createdAt = System.currentTimeMillis();
        }

        public void set(String key, Object value) {
            data.put(key, value);
        }

        public Object get(String key) {
            return data.get(key);
        }

        public boolean has(String key) {
            return data.containsKey(key);
        }

        public void clear() {
            data.clear();
            testName = null;
        }

        public long getThreadId() {
            return threadId;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setTestName(String name) {
            this.testName = name;
        }

        public String getTestName() {
            return testName;
        }

        /**
         * Generate unique ID for current thread context.
         */
        public String getUniqueId() {
            return "%d_%d".formatted(threadId, System.currentTimeMillis());
        }

        /**
         * Generate unique email for test isolation.
         */
        public String getUniqueEmail(String prefix) {
            return "%s_%s@test.com".formatted(prefix, getUniqueId());
        }

        /**
         * Generate unique username for test isolation.
         */
        public String getUniqueUsername(String prefix) {
            return "%s_%s".formatted(prefix, getUniqueId());
        }
    }
}

