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
| **Specialized Testing** | Visual regression, accessibility, BDD | Comprehensive quality mindset |

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
| **Browser Automation** | Selenium WebDriver 4.27 (with built-in Selenium Manager) | Primary UI testing |
| **Alternative Browser Tool** | Playwright | *Demonstration of modern alternative* |
| **Test Framework** | JUnit 5, AssertJ, Allure | Core testing infrastructure |
| **API Testing** | REST Assured | Complements UI testing |
| **Load Testing** | Gatling | *Demonstration of performance testing* |
| **Visual Testing** | Shutterbug | Screenshot comparison |
| **Accessibility** | Axe-core (WCAG 2.1) | *Demonstration of a11y testing* |
| **BDD** | Cucumber | *Demonstration of behavior-driven development* |
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
│   ├── pages/           # Page Objects (BasePage, sauce/)
│   ├── locators/        # Element Locators (shared components)
│   ├── utils/           # Utilities (WebDriverFactory, TestDataManager)
│   ├── playwright/      # Playwright alternative browser automation
│   ├── accessibility/   # Axe-core accessibility testing
│   └── parallel/        # Thread-safe execution support (JUnit 5 extensions)
├── src/test/java/com/automation/
│   ├── unit/            # Unit tests for framework components
│   ├── api/             # API tests (REST Assured)
│   ├── web/             # Web UI tests (Selenium + JUnit 5)
│   ├── visual/          # Visual regression tests (Shutterbug + image-comparison)
│   ├── performance/     # Load tests (Gatling)
│   ├── accessibility/   # Accessibility tests (Axe-core)
│   ├── bdd/             # Cucumber BDD tests with step definitions
│   └── integration/     # Integration tests
├── scripts/             # CI/CD scripts
├── Dockerfile           # Container support
└── docker-compose.yml   # Selenium Grid setup
```

### Core vs Optional Modules

The project intentionally includes more tools than a typical production framework to demonstrate breadth. A realistic **core** stack would be:

- Selenium WebDriver + JUnit 5 + AssertJ
- REST Assured for API tests
- Allure + SLF4J/Logback for reporting and logging
- `Settings`, `WebDriverFactory`, `BasePage`, and page objects under `pages/`

Additional folders such as `playwright/`, `performance/`, `bdd/`, and `accessibility/` represent **optional, advanced modules** that you might enable selectively depending on your team’s needs.

## 🚀 Quick Start

### Prerequisites
- Java 21 or higher
- Maven 3.8+
- Chrome, Firefox, or Edge browser

### Quick Setup

```bash
# macOS/Linux
./setup_env.sh

# Windows
setup_env.bat
```

This will verify your environment, download dependencies, compile the project, and run a quick test.

### Manual Installation

```bash
cd JavaSeleniumProject

# Install dependencies and compile
mvn clean compile

# Run all tests
mvn test

# Run with specific browser (macOS/Linux)
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
mvn test -Dtest="ConstantsTest#timeoutsShouldHaveReasonableValues"
```

## ⚙️ Configuration

Configuration via environment variables (all read from `Settings`):

| Variable | Default | Description |
|----------|---------|-------------|
| `BROWSER` | chrome | Browser to use (chrome, firefox, edge) |
| `HEADLESS` | false | Run in headless mode |
| `ENVIRONMENT` | dev | Logical environment name (dev, qa, prod) |
| `BASE_URL` | https://www.bing.com | Base URL for web tests |
| `API_BASE_URL` | https://jsonplaceholder.typicode.com | Base URL for API tests |
| `SAUCE_DEMO_URL` | https://www.saucedemo.com | Base URL for SauceDemo UI tests |
| `IMPLICIT_WAIT` | 10 | Implicit wait in seconds |
| `EXPLICIT_WAIT` | 10 | Explicit wait in seconds |
| `PAGE_LOAD_TIMEOUT` | 30 | Page load timeout in seconds |
| `PAGE_LOAD_THRESHOLD_MS` | 15000 | Perf budget: page load threshold (ms) |
| `API_RESPONSE_THRESHOLD_MS` | 30000 | Perf budget: API response threshold (ms) |
| `VISUAL_DIFF_THRESHOLD` | 5.0 | Max allowed visual diff percentage |
| `VISUAL_PIXEL_TOLERANCE` | 0.1 | Pixel-level tolerance for image comparison |
| `VISUAL_SAME_PAGE_TOLERANCE` | 15.0 | Tolerance for dynamic content on same page |

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
public class LoginPage extends BasePage {
    public InventoryPage loginAsStandardUser() {
        type(USERNAME_INPUT, "standard_user");
        type(PASSWORD_INPUT, "secret_sauce");
        click(LOGIN_BUTTON);
        return new InventoryPage(driver);
    }
}
```

### Test Data Management
```java
TestDataManager dataManager = new TestDataManager();

// Load JSON/YAML test data
Map<String, Object> data = dataManager.load("test_data");

// Get SauceDemo credentials
Map<String, String> creds = dataManager.getStandardUserCredentials();

// Generate random test data with Datafaker
Map<String, Object> user = dataManager.generate()
    .withName()
    .withEmail()
    .withAddress()
    .build();
```

### Logging (Standard SLF4J)
```java
private static final Logger log = LoggerFactory.getLogger(MyTest.class);
log.info("Test started: {}", testName);
log.debug("Element found: {}", element.getText());
```

### Error Resilience & Retries

Some UI tests that hit external systems (for example Bing search or live visual comparisons) can be intermittently flaky.
To demonstrate production-ready resilience, this project includes a focused JUnit 5 retry mechanism:

- `RetryExtension` – a `TestExecutionExceptionHandler` that re-runs failing tests a limited number of times
- `RetryOnFailure` – an annotation to opt specific tests or classes into retry and configure `maxRetries`, `retryOn`, and `delayMs`

The extension is wired into representative tests such as:

- `SearchEngineTest` (web UI tests)
- `VisualRegressionTest` (visual regression tests)

Retries are intentionally conservative (`maxRetries = 1`) and should be used sparingly, only for known flaky scenarios.

### Locator Strategy

This project demonstrates two valid locator strategies:

- **Page-owned locators** – most page objects declare `By` fields privately inside the page class. This keeps locators close to the behavior that uses them and is preferred for small/medium test suites.
- **Shared locator classes** under `com.automation.locators` – used for cross-cutting UI pieces or when multiple pages/tests share the same widgets.

In a real-world project you would usually pick **one primary style** and use the other sparingly for truly shared components.

## 🧪 Test Categories

| Type | Location | Command |
|------|----------|---------|
| Unit Tests | `src/test/java/.../unit/` | `mvn test -Dtest="**/unit/*"` |
| API Tests | `src/test/java/.../api/` | `mvn test -Dtest="**/api/*"` |
| Web Tests | `src/test/java/.../web/` | `mvn test -Dtest="**/web/*"` |
| BDD Tests | `src/test/java/.../bdd/` | `mvn test -Dtest="CucumberTestRunner"` |
| Load Tests | `src/test/java/.../performance/` | `mvn gatling:test` |
| Accessibility | `src/test/java/.../accessibility/` | `mvn test -Dgroups="accessibility"` |

## 🎓 Recommended Learning Path

If you are new to automation, you don’t need to understand every folder on day one. A suggested path:

1. **Core Selenium & JUnit 5** – explore `src/main/java/com/automation/pages/` and `src/test/java/com/automation/web/`.
2. **Configuration & Test Data** – read `Settings`, `TestDataManager`, and the examples in `unit/` and `api/` tests.
3. **Visual & Accessibility Testing** – look at `visual/`, `accessibility/`, and `docs/VISUAL_TESTING.md`.
4. **Performance & Load** – review `performance/` (Gatling).
5. **Playwright Alternative** – compare Selenium vs Playwright under `playwright/` and `src/test/java/com/automation/playwright/`.
6. **BDD with Cucumber** – finally, explore the `bdd/` package and feature files.

For a step-by-step tutorial that builds the framework from scratch, see `docs/ZERO_TO_HERO_TUTORIAL.md`.

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
| **Factory** | `WebDriverFactory`, `PlaywrightFactory` |
| **Singleton** | `Settings` |
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
| Selenium | Selenium + Playwright |
| structlog | SLF4J + Logback |
| Faker | Datafaker |
| Pillow | Shutterbug |
| webdriver-manager | Selenium Manager (built-in) |

---

## 📬 Contact

This project was created as a portfolio demonstration. For questions or discussions about the implementation choices, please open an issue.

---

**Java 21** | **Maven** | **Selenium 4** | **JUnit 5** | **REST Assured**

*Built with ❤️ to demonstrate modern test automation practices*

