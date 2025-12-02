# Java Selenium Test Automation Framework

## 🎯 Portfolio Showcase Project

> **Note**: This is a **portfolio demonstration project** designed to showcase proficiency across multiple test automation technologies and approaches. It intentionally includes diverse tools to demonstrate breadth of knowledge—not all features would be used together in a typical production environment.

### What This Project Demonstrates

| Skill Area | What You'll Find | Why It Matters |
|------------|------------------|----------------|
| **Core Automation** | Selenium WebDriver, Page Object Model | Foundation of UI test automation |
| **Modern Alternatives** | Playwright integration | Awareness of emerging tools |
| **API Testing** | REST Assured with fluent assertions | Full-stack testing capability |
| **Design Patterns** | Factory, Singleton, Strategy, Template | Software engineering principles |
| **Error Resilience** | Retry mechanisms, smart error handling | Production-ready thinking |
| **CI/CD Ready** | Docker, headless execution, Maven profiles | DevOps integration skills |
| **Reporting** | Allure, structured logging | Test observability |
| **Specialized Testing** | Visual regression, accessibility, contracts | Comprehensive quality mindset |

### 📖 For Hiring Managers & Reviewers

This project answers the question: *"Can this candidate work with the tools and patterns we use?"*

- **Looking for Selenium skills?** → See `src/main/java/com/automation/pages/` and `src/test/java/com/automation/web/`
- **Looking for API testing?** → See `src/test/java/com/automation/api/ApiTest.java`
- **Looking for design patterns?** → See [Design Patterns Used](#-design-patterns-used)
- **Looking for CI/CD experience?** → See `Dockerfile`, `docker-compose.yml`, `.github/workflows/`

👉 **See [FEATURE_MATRIX.md](docs/FEATURE_MATRIX.md)** for a complete guide on which technologies are alternatives vs. complementary.

---

## 🧰 Technology Stack

| Category | Technologies | Notes |
|----------|-------------|-------|
| **Browser Automation** | Selenium WebDriver 4.27, WebDriverManager | Primary UI testing |
| **Alternative Browser Tool** | Playwright | *Demonstration of modern alternative* |
| **Test Framework** | JUnit 5, AssertJ, Allure | Core testing infrastructure |
| **API Testing** | REST Assured | Complements UI testing |
| **Load Testing** | Gatling | *Demonstration of performance testing* |
| **Visual Testing** | AShot | Screenshot comparison |
| **Accessibility** | Axe-core (WCAG 2.1) | *Demonstration of a11y testing* |
| **Contract Testing** | Pact | *Demonstration of consumer-driven contracts* |
| **BDD** | Cucumber | *Demonstration of behavior-driven development* |
| **Error Handling** | Resilience4j | Retry patterns |
| **Logging** | SLF4J + Logback | Structured JSON output |
| **Build & CI** | Maven, Docker, GitHub Actions | Full pipeline support |
| **Code Quality** | Checkstyle, SpotBugs, JaCoCo | Static analysis & coverage |

*Italicized items are demonstrations of capability—alternatives or specialized tools not typically combined.*

---

## 📁 Project Structure

```
JavaSeleniumProject/
├── src/main/java/com/automation/
│   ├── config/          # Settings, Constants
│   ├── pages/           # Page Objects (BasePage, SearchEnginePage)
│   ├── locators/        # Element Locators (separated)
│   ├── utils/           # Utilities (WebDriverFactory, DataManager)
│   ├── playwright/      # Playwright support
│   ├── accessibility/   # Axe-core testing
│   └── parallel/        # Thread-safe execution
├── src/test/java/com/automation/
│   ├── unit/            # Unit tests
│   ├── api/             # API tests (RestAssured)
│   ├── web/             # Web UI tests (Selenium)
│   ├── visual/          # Visual regression tests
│   ├── performance/     # Load tests (Gatling)
│   ├── accessibility/   # Accessibility tests
│   ├── contract/        # Contract tests (Pact)
│   └── integration/     # Integration tests
├── scripts/             # CI/CD scripts
├── Dockerfile           # Container support
└── docker-compose.yml   # Selenium Grid setup
```

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.8+
- Chrome, Firefox, or Edge browser

### Installation

```bash
cd JavaSeleniumProject

# Install dependencies and compile
mvn clean compile

# Run all tests
mvn test

# Run with specific browser
BROWSER=firefox mvn test

# Run in headless mode
HEADLESS=true mvn test
```

### Running Specific Tests

```bash
# Unit tests only
mvn test -Dtest="**/unit/*Test"

# API tests only
mvn test -Dtest="**/api/*Test"

# Web tests only
mvn test -Dtest="**/web/*Test"

# Single test class
mvn test -Dtest=SettingsTest

# Single test method
mvn test -Dtest="ErrorHandlerTest#shouldRetryOnFailure*"
```

## ⚙️ Configuration

Configuration via environment variables:

| Variable | Default | Description |
|----------|---------|-------------|
| `BROWSER` | chrome | Browser to use (chrome, firefox, edge) |
| `HEADLESS` | false | Run in headless mode |
| `BASE_URL` | https://www.google.com | Base URL for web tests |
| `API_BASE_URL` | https://jsonplaceholder.typicode.com | Base URL for API tests |
| `IMPLICIT_WAIT` | 10 | Implicit wait in seconds |
| `EXPLICIT_WAIT` | 10 | Explicit wait in seconds |

## 📊 Test Reports

### JUnit Reports
```bash
mvn test
# Reports: target/surefire-reports/
```

### Allure Reports
```bash
mvn test
mvn allure:serve
# Opens browser with interactive report
```

### Code Coverage (JaCoCo)
```bash
mvn test jacoco:report
# Report: target/site/jacoco/index.html
```

## 🔧 Key Components

### Page Object Model
```java
public class SearchEnginePage extends BasePage {
    public SearchEnginePage search(String query) {
        type(SEARCH_INPUT, query);
        pressKey(SEARCH_INPUT, Keys.ENTER);
        return this;
    }
}
```

### Error Handler with Retry
```java
ErrorHandler handler = new ErrorHandler();
handler.executeWithRetry(() -> {
    element.click();
    return null;
});
```

### Performance Monitoring
```java
PerformanceMonitor monitor = new PerformanceMonitor("Tests");
var result = monitor.timeOperation("page_load", () -> {
    driver.get(url);
    return driver.getTitle();
});
```

### Structured Logging
```java
StructuredLogger logger = new StructuredLogger(MyTest.class);
logger.testStarted("testName", "unit", "chrome");
logger.apiRequest("GET", "/users", 200, 150);
```

## 🧪 Test Categories

| Type | Location | Command |
|------|----------|---------|
| Unit Tests | `src/test/java/.../unit/` | `mvn test -Dtest="**/unit/*"` |
| API Tests | `src/test/java/.../api/` | `mvn test -Dtest="**/api/*"` |
| Web Tests | `src/test/java/.../web/` | `mvn test -Dtest="**/web/*"` |
| Load Tests | `src/test/java/.../performance/` | `mvn gatling:test` |
| Accessibility | `src/test/java/.../accessibility/` | `mvn test -Dgroups="accessibility"` |
| Contract Tests | `src/test/java/.../contract/` | `mvn test -Dgroups="contract"` |

## 🐳 Docker Support

```bash
# Start Selenium Grid
docker-compose up -d selenium-hub chrome firefox

# Run tests in container
docker-compose run test-runner

# View Selenium Grid: http://localhost:4444
# View Allure Reports: http://localhost:5050
```

## 🔄 CI/CD Integration

### Run CI Checks
```bash
./scripts/run_ci_checks.sh
```

### GitHub Actions (example)
```yaml
- name: Run Tests
  run: |
    cd JavaSeleniumProject
    mvn test -DHEADLESS=true
```

## 📈 Code Quality

```bash
# Checkstyle
mvn checkstyle:check

# SpotBugs (security)
mvn spotbugs:check

# Full quality check
mvn verify
```

## 🎓 Design Patterns Used

| Pattern | Implementation |
|---------|---------------|
| **Page Object Model** | `BasePage`, `SearchEnginePage` |
| **Factory** | `WebDriverFactory` |
| **Singleton** | `Settings` |
| **Strategy** | Recovery strategies in `ErrorHandler` |
| **Template Method** | `BasePage` abstract methods |

## 📚 Documentation

### 🎓 Learning Resources
- **[Zero to Hero Tutorial](docs/ZERO_TO_HERO_TUTORIAL.md)** - Complete guide to building a Selenium framework from scratch
- [Feature Matrix](docs/FEATURE_MATRIX.md) - When to use each technology (alternatives vs. complementary)

### 📖 Reference Guides
- [API Testing Guide](docs/API_TESTING.md)
- [Web Testing Guide](docs/WEB_TESTING.md)
- [Visual Testing Guide](docs/VISUAL_TESTING.md)
- [Configuration Reference](docs/CONFIGURATION.md)

## 🆚 Python Equivalent

This project mirrors the Python Selenium project with equivalent implementations:

| Python | Java |
|--------|------|
| pytest | JUnit 5 |
| requests | REST Assured |
| tenacity | Resilience4j |
| structlog | SLF4J + Logback |
| psutil | OSHI |
| Pillow | AShot |
| webdriver-manager | WebDriverManager |

---

## 📬 Contact

This project was created as a portfolio demonstration. For questions or discussions about the implementation choices, please open an issue.

---

**Java 17** | **Maven** | **Selenium 4** | **JUnit 5** | **REST Assured**

*Built with ❤️ to demonstrate modern test automation practices*

