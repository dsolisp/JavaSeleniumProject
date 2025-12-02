# Java Selenium Framework Tutorial

A step-by-step guide to using the Java Selenium Test Automation Framework.

## Prerequisites

- **Java 17+**: `java -version`
- **Maven 3.8+**: `mvn -version`
- **IDE**: IntelliJ IDEA or VS Code with Java extensions

## Step 1: Project Setup

```bash
cd JavaSeleniumProject

# Install dependencies
mvn clean compile

# Verify setup
mvn test -Dtest=SettingsTest
```

## Step 2: Understanding the Structure

```
src/main/java/com/automation/
├── config/Settings.java       # Environment configuration
├── pages/                     # Page Object Model
│   ├── BasePage.java         # Common page functionality
│   └── SearchEnginePage.java # Google search page
└── utils/                     # Utility classes
    ├── WebDriverFactory.java # Browser driver creation
    ├── ErrorHandler.java     # Retry mechanisms
    ├── PerformanceMonitor.java
    ├── StructuredLogger.java
    └── DataManager.java
```

## Step 3: Creating a Page Object

```java
public class MyPage extends BasePage {
    // Define locators
    private static final By SUBMIT_BTN = By.id("submit");
    private static final By EMAIL_INPUT = By.name("email");

    public MyPage(WebDriver driver) {
        super(driver);
    }

    // Page actions
    public MyPage enterEmail(String email) {
        type(EMAIL_INPUT, email);
        return this;
    }

    public void submit() {
        click(SUBMIT_BTN);
    }
}
```

## Step 4: Writing Tests

### Unit Test
```java
@DisplayName("My Component Tests")
class MyComponentTest {

    @Test
    @DisplayName("Should do something")
    void shouldDoSomething() {
        // Arrange
        var component = new MyComponent();
        
        // Act
        var result = component.doSomething();
        
        // Assert
        assertThat(result).isNotNull();
    }
}
```

### Web Test
```java
@DisplayName("My Page Tests")
class MyPageTest extends BaseWebTest {

    @Test
    @DisplayName("Should submit form")
    void shouldSubmitForm() {
        MyPage page = new MyPage(driver);
        page.navigateTo("https://example.com/form");
        page.enterEmail("test@example.com");
        page.submit();
        
        assertThat(page.getTitle()).contains("Success");
    }
}
```

### API Test
```java
@DisplayName("API Tests")
class MyApiTest {

    @Test
    void shouldGetUsers() {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .body("$", hasSize(greaterThan(0)));
    }
}
```

## Step 5: Running Tests

```bash
# All tests
mvn test

# Specific test class
mvn test -Dtest=SearchEngineTest

# By category
mvn test -Dtest="**/unit/*"
mvn test -Dtest="**/api/*"
mvn test -Dtest="**/web/*"

# With browser option
BROWSER=firefox HEADLESS=true mvn test
```

## Step 6: Using Utilities

### Error Handler
```java
ErrorHandler handler = new ErrorHandler();

// Retry on failure
handler.executeWithRetry(() -> {
    driver.findElement(By.id("btn")).click();
});
```

### Performance Monitor
```java
PerformanceMonitor monitor = new PerformanceMonitor("MyTest");

var result = monitor.timeOperation("page_load", () -> {
    driver.get(url);
    return driver.getTitle();
});

System.out.println("Duration: " + result.durationMs() + "ms");
```

### Data Manager
```java
DataManager manager = new DataManager();

// Load test data
Map<String, Object> data = manager.loadJson("test_data");

// Save results
manager.saveTestResults(results, "dev");
```

## Step 7: Generating Reports

```bash
# Coverage report
mvn test jacoco:report
open target/site/jacoco/index.html

# Allure report
mvn allure:serve
```

## Best Practices

1. **Use Page Object Model** - All page interactions through page classes
2. **Explicit waits** - Use `waitForVisible()`, `waitForClickable()`
3. **Retry mechanisms** - Use `ErrorHandler` for flaky operations
4. **Structured logging** - Use `StructuredLogger` for debugging
5. **Assertions** - Use AssertJ for readable assertions

## Common Issues

| Issue | Solution |
|-------|----------|
| Driver not found | Ensure browser is installed |
| Timeout errors | Increase wait times in Settings |
| Stale element | Use `findElementWithRetry()` |

---

For more details, see the [README](../README.md).

