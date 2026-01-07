# Visual Testing Guide

This guide covers visual regression testing using Shutterbug in the Java Selenium project.

## Overview

Visual testing captures screenshots and compares them against baselines to detect
unintended visual changes.

## Setup

Add Shutterbug dependency to `pom.xml`:

```xml
<dependency>
    <groupId>com.assertthat</groupId>
    <artifactId>selenium-shutterbug</artifactId>
    <version>1.6</version>
</dependency>
```

> **Note**: Shutterbug is actively maintained and supports Selenium 4+. It provides
> full-page screenshots with scrolling and image comparison capabilities.

## ScreenshotService

### Capturing Screenshots

```java
ScreenshotService screenshotService = new ScreenshotService();

// Simple screenshot
Path screenshot = screenshotService.captureScreenshot(driver, "homepage");

// Full page screenshot (with scrolling)
Path fullPage = screenshotService.captureFullPageScreenshot(driver, "full_page");
```

### Creating Baselines

```java
// Save baseline for comparison
Path baseline = screenshotService.saveBaseline(driver, "homepage");
```

### Comparing Images

```java
ComparisonResult result = screenshotService.compareImages(baseline, current);

if (result.hasDifference()) {
    System.out.println("Difference: " + result.diffPercent() + "%");
    System.out.println("Diff pixels: " + result.diffPixels());
    System.out.println("Diff image: " + result.diffImagePath());
}
```

## Visual Test Example

```java
@Test
@Tag("visual")
void homepageShouldMatchBaseline() {
    searchPage.open();
    
    // First run: create baseline
    Path baseline = screenshotService.saveBaseline(driver, "homepage");
    
    // Subsequent runs: compare against baseline
    ComparisonResult result = screenshotService.compareWithBaseline(driver, "homepage");
    
    assertThat(result.diffPercent())
        .as("Visual difference should be minimal")
        .isLessThan(1.0); // 1% threshold
}
```

## Comparison Thresholds

### Tolerance Levels

| Level | Threshold | Use Case |
|-------|-----------|----------|
| Strict | < 0.1% | Pixel-perfect validation |
| Normal | < 1% | Minor rendering differences |
| Relaxed | < 5% | Cross-browser testing |
| Loose | < 10% | Layout validation only |

### Setting Thresholds

You can control thresholds either **in code** or via **environment variables** consumed by `Settings`:

- `VISUAL_DIFF_THRESHOLD` 0d default `5.0`
- `VISUAL_PIXEL_TOLERANCE` dd default `0.1`
- `VISUAL_SAME_PAGE_TOLERANCE` fd default `15.0`

Example (using the value from configuration):

```java
Settings settings = Settings.getInstance();
double threshold = settings.getVisualDiffThreshold();

ComparisonResult result = screenshotService.compareImages(baseline, current);

assertThat(result.diffPercent())
    .isLessThanOrEqualTo(threshold);
```

Environment variables can be set, for example:

```bash
export VISUAL_DIFF_THRESHOLD=3.0
export VISUAL_PIXEL_TOLERANCE=0.05
export VISUAL_SAME_PAGE_TOLERANCE=20.0
```

## Diff Images

When differences are detected, a diff image is generated highlighting the changes:

```java
if (result.hasDifference()) {
    Path diffImage = result.diffImagePath();
    // Diff image shows changed pixels in red
    logger.info("See diff at: {}", diffImage);
}
```

## Directory Structure

```
project/
├── screenshots/          # Current screenshots
├── baselines/           # Baseline images
└── diffs/               # Difference images
```

## Best Practices

### 1. Consistent Environment

- Use headless mode for CI
- Set fixed viewport size
- Disable animations

```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless=new");
options.addArguments("--window-size=1920,1080");
options.addArguments("--disable-animations");
```

### 2. Element Screenshots

For testing specific components:

```java
WebElement element = driver.findElement(By.id("header"));
Path screenshot = screenshotService.captureElementScreenshot(driver, element, "header");
```

### 3. Handling Dynamic Content

Mask or ignore dynamic elements:

```java
// Wait for page to stabilize
Thread.sleep(500); // Wait for animations

// Or hide dynamic elements
((JavascriptExecutor) driver).executeScript(
    "document.querySelector('.timestamp').style.visibility='hidden'"
);
```

### 4. Handling Occasional Flakiness

Visual tests that talk to real environments can still be affected by minor network or rendering hiccups.
For a small number of known flaky scenarios you can use the same JUnit 5 retry extension used by web tests:

```java
@Tag("visual")
@RetryOnFailure(maxRetries = 1)
@ExtendWith({WebDriverExtension.class, RetryExtension.class})
class VisualRegressionTest {
    // ...
}
```

Keep retries conservative (for example a single retry) and prefer fixing the underlying cause where possible.

## Running Visual Tests

```bash
# Run all visual tests
mvn test -Dtest="**/visual/*Test"

# Generate new baselines
mvn test -Dtest="**/visual/*Test" -DupdateBaselines=true
```

## Test Locations

- Visual Tests: `src/test/java/com/automation/visual/`
- Screenshot Service: `src/main/java/com/automation/utils/ScreenshotService.java`

