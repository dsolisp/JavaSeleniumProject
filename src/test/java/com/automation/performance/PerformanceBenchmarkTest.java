package com.automation.performance;

import com.automation.config.Settings;
import com.automation.utils.PerformanceMonitor;
import com.automation.utils.SqlConnection;
import com.automation.utils.StructuredLogger;
import com.automation.utils.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Performance benchmark tests.
 * Equivalent to Python's tests/performance/test_benchmarks.py
 */
@Epic("Performance Testing")
@Feature("Performance Benchmarks")
@DisplayName("Performance Benchmark Tests")
@Tag("performance")
class PerformanceBenchmarkTest {

    private static final StructuredLogger logger = new StructuredLogger(PerformanceBenchmarkTest.class);
    private static PerformanceMonitor monitor;

    @BeforeAll
    static void setup() {
        monitor = new PerformanceMonitor("BenchmarkTests");
        RestAssured.baseURI = Settings.getInstance().getApiBaseUrl();
        logger.info("Performance benchmark test suite started");
    }

    @AfterAll
    static void teardown() {
        var report = monitor.generateReport();
        logger.info("Performance benchmark suite completed with {} metrics", report.size());
    }

    // ═══════════════════════════════════════════════════════════════════
    // WEBDRIVER BENCHMARKS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("WebDriver Performance")
    @Description("Benchmark WebDriver creation time")
    @DisplayName("WebDriver creation should be fast")
    void webDriverCreationShouldBeFast() {
        List<Long> times = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            long startTime = System.currentTimeMillis();
            
            WebDriver driver = WebDriverFactory.createDriver("chrome", true);
            driver.quit();
            
            long duration = System.currentTimeMillis() - startTime;
            times.add(duration);
            monitor.recordMetric("webdriver_creation", duration);
        }
        
        double avgTime = times.stream().mapToLong(Long::longValue).average().orElse(0);
        logger.performanceMetric("avg_webdriver_creation", avgTime, "ms");
        
        // WebDriver should create within 10 seconds
        assertThat(avgTime)
                .as("Average WebDriver creation time")
                .isLessThan(10000);
    }

    @Test
    @Story("WebDriver Performance")
    @Description("Benchmark page load performance")
    @DisplayName("Page load should be fast")
    void pageLoadShouldBeFast() {
        WebDriver driver = WebDriverFactory.createDriver("chrome", true);
        
        try {
            long startTime = System.currentTimeMillis();
            
            driver.get("https://www.google.com");
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
            
            long loadTime = System.currentTimeMillis() - startTime;
            monitor.recordMetric("page_load", loadTime);
            logger.performanceMetric("page_load_time", loadTime, "ms");
            
            // Page should load within 8 seconds
            assertThat(loadTime)
                    .as("Page load time")
                    .isLessThan(8000);
        } finally {
            driver.quit();
        }
    }

    @Test
    @Story("WebDriver Performance")
    @Description("Benchmark element finding operations")
    @DisplayName("Element finding should be fast")
    void elementFindingShouldBeFast() {
        WebDriver driver = WebDriverFactory.createDriver("chrome", true);
        
        try {
            driver.get("https://www.google.com");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
            
            List<Long> times = new ArrayList<>();
            
            for (int i = 0; i < 5; i++) {
                long startTime = System.currentTimeMillis();
                
                driver.findElement(By.name("q"));
                driver.findElements(By.tagName("a"));
                driver.findElements(By.tagName("input"));
                
                long duration = System.currentTimeMillis() - startTime;
                times.add(duration);
            }
            
            double avgTime = times.stream().mapToLong(Long::longValue).average().orElse(0);
            monitor.recordMetric("element_finding", (long) avgTime);
            logger.performanceMetric("avg_element_find_time", avgTime, "ms");
            
            // Element finding should be fast (under 500ms average)
            assertThat(avgTime).isLessThan(500);
        } finally {
            driver.quit();
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // API BENCHMARKS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("API Performance")
    @Description("Benchmark API response times")
    @DisplayName("API responses should be fast")
    void apiResponsesShouldBeFast() {
        List<Long> times = new ArrayList<>();
        
        for (int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            
            given()
                    .contentType(ContentType.JSON)
            .when()
                    .get("/posts/" + (i + 1))
            .then()
                    .statusCode(200);
            
            long duration = System.currentTimeMillis() - startTime;
            times.add(duration);
            monitor.recordMetric("api_request", duration);
        }
        
        double avgTime = times.stream().mapToLong(Long::longValue).average().orElse(0);
        long maxTime = times.stream().mapToLong(Long::longValue).max().orElse(0);
        long minTime = times.stream().mapToLong(Long::longValue).min().orElse(0);
        
        logger.info("API Performance - Avg: {}ms, Min: {}ms, Max: {}ms",
                avgTime, minTime, maxTime);

        // Average API response should be under 2 seconds
        assertThat(avgTime).isLessThan(2000);
    }

    // ═══════════════════════════════════════════════════════════════════
    // CONCURRENT OPERATIONS BENCHMARKS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Concurrent Performance")
    @Description("Benchmark concurrent API requests")
    @DisplayName("Concurrent API requests should complete")
    void concurrentApiRequestsShouldComplete() throws InterruptedException, ExecutionException {
        int numThreads = 5;
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        AtomicInteger successCount = new AtomicInteger(0);

        long startTime = System.currentTimeMillis();

        List<Future<Boolean>> futures = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            final int postId = i + 1;
            futures.add(executor.submit(() -> {
                try {
                    given()
                            .contentType(ContentType.JSON)
                    .when()
                            .get("/posts/" + postId)
                    .then()
                            .statusCode(200);
                    successCount.incrementAndGet();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }));
        }

        // Wait for all to complete
        for (Future<Boolean> future : futures) {
            future.get();
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        long totalTime = System.currentTimeMillis() - startTime;
        monitor.recordMetric("concurrent_requests", totalTime);

        logger.info("Concurrent requests - Total: {}ms, Success: {}/{}",
                totalTime, successCount.get(), numThreads);

        assertThat(successCount.get()).isEqualTo(numThreads);
        // All requests should complete within 10 seconds
        assertThat(totalTime).isLessThan(10000);
    }

    @Test
    @Story("Concurrent Performance")
    @Description("Benchmark thread pool performance")
    @DisplayName("Thread pool should handle workload")
    void threadPoolShouldHandleWorkload() throws InterruptedException {
        int numTasks = 10;
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CountDownLatch latch = new CountDownLatch(numTasks);
        AtomicInteger completedTasks = new AtomicInteger(0);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numTasks; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try {
                    // Simulate work
                    Thread.sleep(50);
                    completedTasks.incrementAndGet();
                    logger.debug("Task {} completed", taskId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    latch.countDown();
                }
            });
        }

        // Wait for all tasks
        boolean completed = latch.await(30, TimeUnit.SECONDS);
        executor.shutdown();

        long totalTime = System.currentTimeMillis() - startTime;
        monitor.recordMetric("thread_pool_workload", totalTime);

        assertThat(completed).isTrue();
        assertThat(completedTasks.get()).isEqualTo(numTasks);
    }

    // ═══════════════════════════════════════════════════════════════════
    // MEMORY BENCHMARKS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Memory Performance")
    @Description("Benchmark memory usage during operations")
    @DisplayName("Memory usage should be reasonable")
    void memoryUsageShouldBeReasonable() {
        Runtime runtime = Runtime.getRuntime();

        // Force garbage collection
        System.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        // Create some data structures
        List<String> largeList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            largeList.add("Item " + i + " with some additional data");
        }

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        long memoryUsed = (memoryAfter - memoryBefore) / (1024 * 1024); // MB

        monitor.recordMetric("memory_allocation", memoryUsed);
        logger.performanceMetric("memory_usage", memoryUsed, "MB");

        // Cleanup
        largeList.clear();
        System.gc();

        // Memory usage should be reasonable (under 100MB for this test)
        assertThat(memoryUsed).isLessThan(100);
    }

    // ═══════════════════════════════════════════════════════════════════
    // DATABASE BENCHMARKS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Database Performance")
    @Description("Benchmark database query operations")
    @DisplayName("Database queries should be fast")
    void databaseQueriesShouldBeFast() throws Exception {
        String dbPath = "src/main/resources/data/chinook.db";
        List<Long> times = new ArrayList<>();

        try (Connection conn = SqlConnection.getConnection(dbPath)) {
            for (int i = 0; i < 10; i++) {
                long startTime = System.currentTimeMillis();

                // Simple query
                SqlConnection.fetchAll(conn, "SELECT * FROM artists LIMIT 50");
                // Join query
                SqlConnection.fetchAll(conn,
                        "SELECT t.Name, a.Title FROM tracks t JOIN albums a ON t.AlbumId = a.AlbumId LIMIT 100");
                // Aggregation
                SqlConnection.fetchOne(conn, "SELECT COUNT(*) as count FROM tracks");

                long duration = System.currentTimeMillis() - startTime;
                times.add(duration);
                monitor.recordMetric("database_query", duration);
            }
        }

        double avgTime = times.stream().mapToLong(Long::longValue).average().orElse(0);
        logger.performanceMetric("avg_database_query", avgTime, "ms");

        // Database queries should be fast (under 100ms average)
        assertThat(avgTime)
                .as("Average database query time")
                .isLessThan(100);
    }

    @Test
    @Story("Database Performance")
    @Description("Benchmark database write operations")
    @DisplayName("Database writes should be fast")
    void databaseWritesShouldBeFast() throws Exception {
        // Use a temp in-memory database to avoid modifying chinook.db
        try (Connection conn = java.sql.DriverManager.getConnection("jdbc:sqlite::memory:")) {
            // Create test table
            try (var stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE test_perf (id INTEGER PRIMARY KEY, name TEXT, value INTEGER)");
            }

            List<Long> times = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                long startTime = System.currentTimeMillis();

                // Insert
                SqlConnection.insert(conn, "test_perf", Map.of("name", "test" + i, "value", i * 10));
                // Update
                SqlConnection.update(conn, "test_perf", Map.of("value", i * 20), "name = ?", "test" + i);
                // Delete
                SqlConnection.delete(conn, "test_perf", "name = ?", "test" + i);

                long duration = System.currentTimeMillis() - startTime;
                times.add(duration);
                monitor.recordMetric("database_write", duration);
            }

            double avgTime = times.stream().mapToLong(Long::longValue).average().orElse(0);
            logger.performanceMetric("avg_database_write", avgTime, "ms");

            // Database writes should be fast (under 50ms average)
            assertThat(avgTime)
                    .as("Average database write time")
                    .isLessThan(50);
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // THRESHOLD VALIDATION
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Threshold Validation")
    @Description("Validate performance thresholds are met")
    @DisplayName("All performance thresholds should pass")
    void allPerformanceThresholdsShouldPass() {
        // Define thresholds
        long pageLoadThreshold = 8000; // 8 seconds
        long apiResponseThreshold = 2000; // 2 seconds
        long elementFindThreshold = 500; // 500ms

        var report = monitor.generateReport();

        // Log all metrics
        report.forEach((metric, stats) -> {
            logger.info("Metric {}: mean={}ms, count={}",
                    metric, stats.mean(), stats.count());
        });

        // Validate thresholds if metrics exist
        if (report.containsKey("page_load")) {
            assertThat(report.get("page_load").mean())
                    .as("Page load threshold")
                    .isLessThan(pageLoadThreshold);
        }

        if (report.containsKey("api_request")) {
            assertThat(report.get("api_request").mean())
                    .as("API response threshold")
                    .isLessThan(apiResponseThreshold);
        }

        if (report.containsKey("element_finding")) {
            assertThat(report.get("element_finding").mean())
                    .as("Element finding threshold")
                    .isLessThan(elementFindThreshold);
        }
    }
}

