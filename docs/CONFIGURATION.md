# Configuration Guide

This document describes all configuration options and environment variables that control test execution in the Java Selenium Test Framework.

## Table of Contents
- [Environment Variables](#environment-variables)
- [JUnit Platform Properties](#junit-platform-properties)
- [Browser Capabilities](#browser-capabilities)
- [Maven Configuration](#maven-configuration)
- [Docker Configuration](#docker-configuration)
- [Logging Configuration](#logging-configuration)
- [Usage Examples](#usage-examples)

---

## Environment Variables

All environment variables are read by the `Settings` class (`src/main/java/com/automation/config/Settings.java`) with sensible defaults.

### Browser Settings

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `BROWSER` | String | `chrome` | Browser to use: `chrome`, `firefox`, `edge`, `safari` |
| `HEADLESS` | Boolean | `false` | Run browser in headless mode (`true`/`false` or `1`/`0`) |

### Timeout Settings

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `IMPLICIT_WAIT` | Integer | `10` | Implicit wait timeout in seconds |
| `EXPLICIT_WAIT` | Integer | `10` | Explicit wait timeout in seconds |
| `PAGE_LOAD_TIMEOUT` | Integer | `30` | Page load timeout in seconds |

### Environment & URLs

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `ENVIRONMENT` | String | `dev` | Environment name (`dev`, `staging`, `prod`) |
| `ENV` | String | `dev` | Alternative environment variable (used by TestDataManager) |
| `BASE_URL` | String | `https://www.bing.com` | Base URL for web tests |
| `API_BASE_URL` | String | `https://jsonplaceholder.typicode.com` | Base URL for API tests |

### Reporting Settings

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `ENABLE_ALLURE` | Boolean | `true` | Enable Allure reporting |
| `REPORTS_DIR` | String | `reports` | Directory for test reports |
| `SCREENSHOTS_DIR` | String | `screenshots` | Directory for screenshots |

### Performance Thresholds

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `PAGE_LOAD_THRESHOLD_MS` | Long | `3000` | Maximum acceptable page load time (ms) |
| `API_RESPONSE_THRESHOLD_MS` | Long | `2000` | Maximum acceptable API response time (ms) |

### Docker/Grid Settings

| Variable | Type | Default | Description |
|----------|------|---------|-------------|
| `SELENIUM_GRID_URL` | String | - | Selenium Grid hub URL (e.g., `http://selenium-hub:4444`) |

---

## JUnit Platform Properties

Configuration file: `src/test/resources/junit-platform.properties`

### Parallel Execution

```properties
# Enable parallel test execution
junit.jupiter.execution.parallel.enabled=true

# Execution modes
junit.jupiter.execution.parallel.mode.default=same_thread
junit.jupiter.execution.parallel.mode.classes.default=concurrent
```

### Parallelism Strategy

**Dynamic (CPU-based):**
```properties
junit.jupiter.execution.parallel.config.strategy=dynamic
junit.jupiter.execution.parallel.config.dynamic.factor=0.5
```

**Fixed thread count:**
```properties
junit.jupiter.execution.parallel.config.strategy=fixed
junit.jupiter.execution.parallel.config.fixed.parallelism=4
```

### Timeouts

```properties
# Default timeout for all tests
junit.jupiter.execution.timeout.default=5m

# Timeout for individual test methods
junit.jupiter.execution.timeout.testable.method.default=2m
```

### Test Instance Lifecycle

```properties
# per_method = new instance for each test (isolation)
# per_class = shared instance (faster, shared state)
junit.jupiter.testinstance.lifecycle.default=per_method
```

---

## Browser Capabilities

Configuration file: `src/main/resources/config/capabilities.json`

### Chrome Options

```json
{
  "chrome": {
    "browserName": "chrome",
    "chromeOptions": {
      "args": [
        "--no-sandbox",
        "--disable-dev-shm-usage",
        "--disable-gpu",
        "--disable-extensions",
        "--disable-infobars",
        "--window-size=1920,1080"
      ],
      "prefs": {
        "credentials_enable_service": false,
        "profile.password_manager_enabled": false,
        "profile.default_content_setting_values.notifications": 2
      }
    }
  }
}
```

### Firefox Options

```json
{
  "firefox": {
    "browserName": "firefox",
    "firefoxOptions": {
      "args": ["--width=1920", "--height=1080"],
      "prefs": {
        "dom.webnotifications.enabled": false,
        "media.volume_scale": "0.0"
      }
    }
  }
}
```

### Edge & Safari

Edge and Safari configurations are also available in `capabilities.json`.

---

## Maven Configuration

Configuration file: `pom.xml`

### System Properties

Pass configuration via Maven command line:

```bash
# Browser selection
mvn test -Dbrowser=firefox

# Headless mode
mvn test -Dheadless=true

# Specific test
mvn test -Dtest=SearchEngineTest

# Test pattern
mvn test -Dtest="**/web/*Test"
```

### Surefire Plugin (Unit Tests)

```xml
<plugin>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <includes>
            <include>**/*Test.java</include>
        </includes>
    </configuration>
</plugin>
```

### Parallel Execution Profile

```xml
<profile>
    <id>parallel</id>
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-surefire-plugin</artifactId>
                <configuration>
                    <parallel>methods</parallel>
                    <threadCount>4</threadCount>
                </configuration>
            </plugin>
        </plugins>
    </build>
</profile>
```

Activate with: `mvn test -Pparallel`

---

## Docker Configuration

### Docker Compose Environment Variables

File: `docker-compose.yml`

#### Selenium Grid Hub

```yaml
environment:
  - GRID_MAX_SESSION=10          # Max concurrent sessions
  - GRID_BROWSER_TIMEOUT=60      # Browser timeout (seconds)
  - GRID_TIMEOUT=60              # Grid timeout (seconds)
```

#### Chrome/Firefox/Edge Nodes

```yaml
environment:
  - SE_EVENT_BUS_HOST=selenium-hub
  - SE_EVENT_BUS_PUBLISH_PORT=4442
  - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
  - SE_NODE_MAX_SESSIONS=4       # Max sessions per node
  - SE_NODE_SESSION_TIMEOUT=60   # Session timeout (seconds)
```

#### Test Runner Container

```yaml
environment:
  - SELENIUM_GRID_URL=http://selenium-hub:4444
  - HEADLESS=true
  - BROWSER=chrome
  - ENV=docker
```

### Dockerfile Environment Variables

```dockerfile
ENV HEADLESS=true
ENV BROWSER=chrome
ENV MAVEN_OPTS="-Xmx1024m"
```

---

## Logging Configuration

Configuration file: `src/main/resources/logback.xml`

### Log Levels

```xml
<!-- Root logger -->
<root level="INFO">
    <appender-ref ref="CONSOLE"/>
    <appender-ref ref="FILE"/>
</root>

<!-- Framework logs (more verbose) -->
<logger name="com.automation" level="DEBUG"/>

<!-- Third-party libraries (less verbose) -->
<logger name="org.seleniumhq" level="WARN"/>
<logger name="io.github.bonigarcia" level="WARN"/>
```

### Log File Configuration

```xml
<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
    <file>logs/automation.log</file>
    <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
        <fileNamePattern>logs/automation.%d{yyyy-MM-dd}.log</fileNamePattern>
        <maxHistory>30</maxHistory>  <!-- Keep 30 days of logs -->
    </rollingPolicy>
</appender>
```

### JSON Logging (Production)

For structured logging in production environments, switch to JSON appender:

```xml
<appender name="CONSOLE_JSON" class="ch.qos.logback.core.ConsoleAppender">
    <encoder class="net.logstash.logback.encoder.LogstashEncoder">
        <includeMdc>true</includeMdc>
    </encoder>
</appender>
```

---

## Constants

File: `src/main/java/com/automation/config/Constants.java`

Hard-coded constants that don't change per environment:

### Timeouts (seconds)
- `DEFAULT_EXPLICIT_WAIT = 10`
- `DEFAULT_IMPLICIT_WAIT = 0`
- `DEFAULT_PAGE_LOAD_TIMEOUT = 30`
- `SHORT_WAIT = 3`
- `LONG_WAIT = 30`

### Performance Thresholds (milliseconds)
- `MAX_PAGE_LOAD_TIME_MS = 3000`
- `MAX_API_RESPONSE_TIME_MS = 1000`
- `MAX_ELEMENT_INTERACTION_TIME_MS = 500`
- `SLOW_TEST_THRESHOLD_MS = 5000`

### Browser Names
- `BROWSER_CHROME = "chrome"`
- `BROWSER_FIREFOX = "firefox"`
- `BROWSER_EDGE = "edge"`
- `BROWSER_SAFARI = "safari"`

---

## Usage Examples

### 1. Run Tests with Custom Browser

```bash
# Using environment variable
export BROWSER=firefox
mvn test

# Using Maven property
mvn test -Dbrowser=firefox

# Using script
BROWSER=firefox ./scripts/run_full_workflow.sh
```

### 2. Run Tests in Headless Mode

```bash
# Environment variable
export HEADLESS=true
mvn test

# Maven property
mvn test -Dheadless=true

# Script
HEADLESS=true ./scripts/run_full_workflow.sh
```

### 3. Run Tests Against Different Environment

```bash
# Set environment and base URL
export ENVIRONMENT=staging
export BASE_URL=https://staging.example.com
export API_BASE_URL=https://api.staging.example.com
mvn test
```

### 4. Run Tests with Custom Timeouts

```bash
export IMPLICIT_WAIT=15
export EXPLICIT_WAIT=20
export PAGE_LOAD_TIMEOUT=60
mvn test
```

### 5. Run Tests with Selenium Grid

```bash
# Start Selenium Grid
docker-compose up -d selenium-hub chrome firefox

# Run tests pointing to grid
export SELENIUM_GRID_URL=http://localhost:4444
mvn test

# Or use Docker Compose
docker-compose run test-runner
```

### 6. Run Specific Test Categories

```bash
# Unit tests only
mvn test -Dtest="**/unit/*Test"

# Web tests only
mvn test -Dtest="**/web/*Test"

# API tests only
mvn test -Dtest="**/api/*Test"

# Performance tests
mvn test -Dtest="**/performance/*Test"

# Visual tests
mvn test -Dtest="**/visual/*Test"
```

### 7. Run Tests with Parallel Execution

```bash
# Using Maven profile
mvn test -Pparallel

# Or modify junit-platform.properties
# Set: junit.jupiter.execution.parallel.enabled=true
```

### 8. Run Tests with Custom Performance Thresholds

```bash
export PAGE_LOAD_THRESHOLD_MS=5000
export API_RESPONSE_THRESHOLD_MS=3000
mvn test -Dtest="PerformanceMonitorTest"
```

### 9. Disable Allure Reporting

```bash
export ENABLE_ALLURE=false
mvn test
```

### 10. Run BDD Tests with Tags

```bash
# Run all BDD tests
mvn test -Dtest=CucumberTestRunner

# Run specific tags (modify CucumberTestRunner)
# @ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@smoke")
```

---

## Configuration Priority

When the same setting is defined in multiple places, the priority order is:

1. **Environment Variables** (highest priority)
2. **Maven System Properties** (`-Dproperty=value`)
3. **JUnit Platform Properties** (`junit-platform.properties`)
4. **Settings Class Defaults** (lowest priority)

Example:
```bash
# This will use firefox (env var overrides default)
export BROWSER=firefox
mvn test -Dbrowser=chrome  # BROWSER env var takes precedence
```

---

## Best Practices

### 1. Local Development
```bash
# Use defaults or minimal config
mvn test
```

### 2. CI/CD Pipeline
```bash
# Headless, fast, parallel
export HEADLESS=true
export BROWSER=chrome
mvn test -Pparallel
```

### 3. Cross-Browser Testing
```bash
# Test on multiple browsers
for browser in chrome firefox edge; do
    BROWSER=$browser mvn test -Dtest="**/web/*Test"
done
```

### 4. Performance Testing
```bash
# Strict thresholds for performance tests
export PAGE_LOAD_THRESHOLD_MS=2000
export API_RESPONSE_THRESHOLD_MS=1000
mvn test -Dtest="**/performance/*Test"
```

### 5. Docker/Grid Testing
```bash
# Use Docker Compose for isolated environment
docker-compose up -d selenium-hub chrome
docker-compose run test-runner mvn test
```

---

## Troubleshooting

### Issue: Tests timing out

**Solution:** Increase timeout values
```bash
export PAGE_LOAD_TIMEOUT=60
export EXPLICIT_WAIT=20
```

### Issue: Browser not found

**Solution:** Ensure WebDriverManager can download drivers or set driver path
```bash
# WebDriverManager handles this automatically
# Or use Docker for consistent environment
```

### Issue: Parallel tests failing

**Solution:** Ensure thread-safe driver management
```bash
# Check ThreadLocal usage in WebDriverFactory
# Reduce parallelism
# Edit junit-platform.properties: dynamic.factor=0.25
```

### Issue: Out of memory errors

**Solution:** Increase Maven memory
```bash
export MAVEN_OPTS="-Xmx2048m -XX:MaxPermSize=512m"
```

---

## Summary

This framework provides flexible configuration through:
- ✅ **Environment variables** for runtime configuration
- ✅ **JUnit properties** for test execution behavior
- ✅ **Browser capabilities** for browser-specific settings
- ✅ **Maven profiles** for different execution modes
- ✅ **Docker** for containerized testing
- ✅ **Logging** for debugging and monitoring

All settings have sensible defaults, making it easy to get started while providing full control when needed.

