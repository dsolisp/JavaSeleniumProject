# Test Analytics Guide

This guide covers test analytics, flaky test detection, and reporting in the Java Selenium project.

## Overview

Test Analytics provides:
- Automatic test result collection
- Flaky test detection (< 90% pass rate)
- Slow test identification (> 5000ms)
- Comprehensive test reports

## TestAnalyticsExtension

### Usage

Add the extension to your test class:

```java
@ExtendWith(TestAnalyticsExtension.class)
class MyTest {
    @Test
    void testSomething() {
        // Test code
    }
}
```

### Automatic Collection

The extension automatically:
- Records test start/end times
- Captures pass/fail status
- Stores error messages
- Saves results to JSON

## Flaky Test Detection

### Algorithm

A test is considered "flaky" if its pass rate is below the threshold (default: 90%):

```java
passRate = passedRuns / totalRuns

if (passRate < 0.90) {
    // Test is flaky
}
```

### Detection Example

```java
TestAnalytics analytics = new TestAnalytics("test_results");

// Get flaky tests with 90% threshold
List<FlakyTest> flakyTests = analytics.getFlakyTests(0.90);

for (FlakyTest test : flakyTests) {
    System.out.printf("Flaky: %s (%.1f%% pass rate, %d runs)%n",
        test.testName(),
        test.passRate() * 100,
        test.totalRuns());
}
```

## Slow Test Detection

### Threshold

Tests are slow if average duration exceeds 5000ms:

```java
List<SlowTest> slowTests = analytics.getSlowTests(5000);

for (SlowTest test : slowTests) {
    System.out.printf("Slow: %s (avg %.0fms)%n",
        test.testName(),
        test.avgDurationMs());
}
```

## Test Result Format

### JSON Structure

```json
{
    "suite": "MyTestSuite",
    "timestamp": "2024-01-15T10:30:00",
    "totalDurationMs": 15000,
    "results": [
        {
            "testName": "MyTest.testSomething",
            "passed": true,
            "durationMs": 1234,
            "errorMessage": null,
            "timestamp": "2024-01-15T10:30:00"
        }
    ]
}
```

## Generating Reports

### Console Report

```java
TestAnalytics analytics = new TestAnalytics("test_results");
analytics.generateReport();
```

Output:
```
============================================================
TEST ANALYTICS REPORT
============================================================

Summary:
  Total Tests: 100
  Passed: 95
  Failed: 5
  Pass Rate: 95.0%

Flaky Tests (< 90% pass rate):
  - loginTest: 75.0% (12 runs)

Slow Tests (> 5000ms):
  - performanceTest: avg 6500ms

============================================================
```

## Statistics

### Getting Current Stats

```java
Map<String, Object> stats = TestAnalyticsExtension.getStatistics();

int total = (Integer) stats.get("total");
int passed = (Integer) stats.get("passed");
int failed = (Integer) stats.get("failed");
double passRate = (Double) stats.get("passRate");
```

## Configuration

### Custom Thresholds

```java
// Stricter flaky detection (95% pass rate)
var strictFlakyTests = analytics.getFlakyTests(0.95);

// Stricter slow test detection (3 seconds)
var strictSlowTests = analytics.getSlowTests(3000);
```

### Custom Results Directory

```java
TestAnalytics analytics = new TestAnalytics("/path/to/results");
```

## CI Integration

### Running with Analytics

```bash
# Run tests with analytics collection
mvn test -Dtest="*Test"

# Results saved to test_results/
ls test_results/*.json
```

### Jenkins Pipeline

```groovy
stage('Test') {
    steps {
        sh 'mvn test'
    }
    post {
        always {
            archiveArtifacts 'test_results/*.json'
        }
    }
}
```

## Test Locations

- TestAnalytics: `src/main/java/com/automation/utils/TestAnalytics.java`
- Extension: `src/main/java/com/automation/extensions/TestAnalyticsExtension.java`
- Tests: `src/test/java/com/automation/unit/TestAnalyticsTest.java`

