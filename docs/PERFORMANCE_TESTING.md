# Performance Testing Guide

This guide covers performance testing and benchmarking in the Java Selenium project.

## Overview

The project includes performance testing capabilities for:
- WebDriver operation timing
- API response time measurement
- Concurrent operation benchmarks
- Memory usage monitoring
- Load testing with Gatling

## Timing Operations

### Simple Timing
```java
long start = System.currentTimeMillis();
driver.get(url);
long duration = System.currentTimeMillis() - start;

log.info("Page load took {}ms", duration);
assertThat(duration).isLessThan(5000);
```

### Timing with Logging
```java
@Test
void pageLoadShouldBeFast() {
    long start = System.currentTimeMillis();

    saucePage.navigateTo("https://www.saucedemo.com");

    long loadTime = System.currentTimeMillis() - start;
    log.debug("page_load: {}ms", loadTime);

    assertThat(loadTime)
        .as("Page should load in under 5 seconds")
        .isLessThan(5000);
}
```

## Performance Tests

### WebDriver Benchmarks

```java
@Test
@Tag("performance")
void webDriverCreationShouldBeFast() {
    List<Long> times = new ArrayList<>();
    
    for (int i = 0; i < 3; i++) {
        long start = System.currentTimeMillis();
        WebDriver driver = WebDriverFactory.createDriver("chrome", true);
        driver.quit();
        times.add(System.currentTimeMillis() - start);
    }
    
    double avg = times.stream().mapToLong(Long::longValue).average().orElse(0);
    assertThat(avg).isLessThan(10000); // < 10 seconds
}
```

### API Response Time

```java
@Test
@Tag("performance")
void apiResponseShouldBeFast() {
    long start = System.currentTimeMillis();
    
    given()
        .contentType(ContentType.JSON)
    .when()
        .get("/posts")
    .then()
        .statusCode(200);
    
    long responseTime = System.currentTimeMillis() - start;
    assertThat(responseTime).isLessThan(2000); // < 2 seconds
}
```

### Concurrent Operations

```java
@Test
void concurrentRequestsShouldComplete() throws Exception {
    int numThreads = 5;
    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    AtomicInteger successCount = new AtomicInteger(0);
    
    List<Future<Boolean>> futures = new ArrayList<>();
    for (int i = 0; i < numThreads; i++) {
        futures.add(executor.submit(() -> {
            // Make API request
            successCount.incrementAndGet();
            return true;
        }));
    }
    
    for (Future<Boolean> future : futures) {
        future.get();
    }
    
    assertThat(successCount.get()).isEqualTo(numThreads);
}
```

## Thresholds

### Default Thresholds

| Operation | Threshold |
|-----------|-----------|
| Page Load | < 8 seconds |
| API Response | < 2 seconds |
| Element Find | < 500ms |
| WebDriver Creation | < 10 seconds |

### Custom Thresholds

```java
long customThreshold = 3000; // 3 seconds

assertThat(responseTime)
    .as("Response should be under custom threshold")
    .isLessThan(customThreshold);
```

## Memory Monitoring

```java
@Test
void memoryUsageShouldBeReasonable() {
    Runtime runtime = Runtime.getRuntime();
    System.gc();
    
    long memBefore = runtime.totalMemory() - runtime.freeMemory();
    
    // Perform operations...
    
    long memAfter = runtime.totalMemory() - runtime.freeMemory();
    long memUsedMB = (memAfter - memBefore) / (1024 * 1024);
    
    assertThat(memUsedMB).isLessThan(100); // < 100MB
}
```

## Running Performance Tests

```bash
# Run all performance tests
mvn test -Dtest="**/performance/*Test"

# Run with specific tag
mvn test -Dgroups="performance"
```

## Test Locations

- Performance Tests: `src/test/java/com/automation/performance/`
- Gatling Simulations: `src/test/java/com/automation/performance/GatlingSimulation.java`

