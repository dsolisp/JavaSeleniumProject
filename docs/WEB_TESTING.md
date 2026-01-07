# Web Testing Guide

This guide covers Selenium WebDriver testing in the Java Selenium project.

## Overview

The project uses Selenium WebDriver with the built-in Selenium Manager (available since Selenium 4.6+)
for automated browser testing, implementing the Page Object Model (POM) pattern.

> **Note**: Selenium Manager automatically handles browser driver downloads. No external
> WebDriverManager dependency is needed.

## Setup

Dependencies in `pom.xml`:

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.27.0</version>
</dependency>
<!-- Selenium 4.6+ includes Selenium Manager - no external driver manager needed -->
```

## Page Object Model

### Base Page

```java
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    
    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }
}
```

### Page Implementation

```java
public class SearchEnginePage extends BasePage {
    private static final By SEARCH_INPUT = By.name("q");
    private static final By SEARCH_BUTTON = By.xpath("//button[@type='submit']");

    public SearchEnginePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://duckduckgo.com");
    }

    public void search(String query) {
        type(SEARCH_INPUT, query);
        click(SEARCH_BUTTON);
    }
}
```

## Writing Tests

### Basic Test Structure

Tests use `@ExtendWith(WebDriverExtension.class)` for automatic WebDriver lifecycle management:

```java
@Tag("web")
@ExtendWith(WebDriverExtension.class)
class SearchEngineTest {

    @Test
    void shouldSearchSuccessfully(WebDriver driver) {
        SearchEnginePage searchPage = new SearchEnginePage(driver);
        searchPage.open();
        searchPage.search("Selenium WebDriver");

        assertThat(searchPage.getSearchResultCount()).isGreaterThan(0);
    }
}
```

The `WebDriverExtension` automatically:
- Creates a headless Chrome driver before each test
- Injects the driver as a test method parameter
- Quits the driver after each test (even on failure)

## Browser Configuration

### Headless Mode

```java
WebDriver driver = WebDriverFactory.createDriver("chrome", true);
```

### Browser Options

```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless=new");
options.addArguments("--window-size=1920,1080");
options.addArguments("--disable-gpu");
```

## Wait Strategies

### Explicit Waits

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("element")));
wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".button")));
wait.until(ExpectedConditions.textToBePresentInElement(element, "Expected Text"));
```

### Fluent Wait

```java
Wait<WebDriver> fluentWait = new FluentWait<>(driver)
    .withTimeout(Duration.ofSeconds(30))
    .pollingEvery(Duration.ofMillis(500))
    .ignoring(NoSuchElementException.class);
```

## Screenshots

```java
// Capture on failure
@AfterEach
void captureScreenshotOnFailure(TestInfo testInfo) {
    if (testFailed) {
        Path screenshot = screenshotService.captureScreenshot(driver, testInfo.getDisplayName());
        logger.info("Screenshot saved: {}", screenshot);
    }
}
```

## Running Web Tests

```bash
# Run all web tests
mvn test -Dtest="**/web/*Test"

# Run in headless mode
mvn test -Dtest="**/web/*Test" -Dheadless=true

# Run specific browser
mvn test -Dtest="**/web/*Test" -Dbrowser=firefox
```

## Handling Flaky Web Tests

Occasionally, UI tests that depend on real external sites (like Bing) can be flaky due to network hiccups or transient DOM changes.
This project includes a small JUnit 5 retry mechanism for such cases:

```java
@Tag("web")
@RetryOnFailure(maxRetries = 1)
@ExtendWith({WebDriverExtension.class, RetryExtension.class})
class SearchEngineTest {
    // ...
}
```

Use retries sparingly and only after making tests as stable as possible; they are meant to smooth over rare, non-deterministic failures rather than hide real bugs.

## Test Locations

- Page Objects: `src/main/java/com/automation/pages/`
- Web Tests: `src/test/java/com/automation/web/`

