# Web Testing Guide

This guide covers Selenium WebDriver testing in the Java Selenium project.

## Overview

The project uses Selenium WebDriver with WebDriverManager for automated browser testing,
implementing the Page Object Model (POM) pattern.

## Setup

Dependencies in `pom.xml`:

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.19.0</version>
</dependency>
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.7.0</version>
</dependency>
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

```java
@Tag("web")
class SearchEngineTest extends BaseWebTest {
    private SearchEnginePage searchPage;
    
    @BeforeEach
    void setUpPage() {
        searchPage = new SearchEnginePage(driver);
    }
    
    @Test
    void shouldSearchSuccessfully() {
        searchPage.open();
        searchPage.search("Selenium WebDriver");
        
        assertThat(searchPage.getSearchResultCount()).isGreaterThan(0);
    }
}
```

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

## Test Locations

- Page Objects: `src/main/java/com/automation/pages/`
- Web Tests: `src/test/java/com/automation/web/`

