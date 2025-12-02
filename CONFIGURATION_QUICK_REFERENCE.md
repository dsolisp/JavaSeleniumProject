# Configuration Quick Reference

> **📖 For detailed documentation, see [docs/CONFIGURATION.md](docs/CONFIGURATION.md)**

## Environment Variables Quick Reference

### Essential Variables

```bash
# Browser & Mode
export BROWSER=chrome              # chrome, firefox, edge, safari
export HEADLESS=true               # true/false or 1/0

# URLs
export BASE_URL=https://www.bing.com
export API_BASE_URL=https://jsonplaceholder.typicode.com

# Environment
export ENVIRONMENT=dev             # dev, staging, prod
```

### All Available Variables

| Variable | Default | Description |
|----------|---------|-------------|
| **Browser Settings** |||
| `BROWSER` | `chrome` | Browser: chrome, firefox, edge, safari |
| `HEADLESS` | `false` | Headless mode: true/false |
| **Timeouts (seconds)** |||
| `IMPLICIT_WAIT` | `10` | Implicit wait timeout |
| `EXPLICIT_WAIT` | `10` | Explicit wait timeout |
| `PAGE_LOAD_TIMEOUT` | `30` | Page load timeout |
| **Environment** |||
| `ENVIRONMENT` | `dev` | Environment name |
| `ENV` | `dev` | Alternative env variable |
| `BASE_URL` | `https://www.bing.com` | Web test base URL |
| `API_BASE_URL` | `https://jsonplaceholder.typicode.com` | API test base URL |
| **Reporting** |||
| `ENABLE_ALLURE` | `true` | Enable Allure reports |
| `REPORTS_DIR` | `reports` | Reports directory |
| `SCREENSHOTS_DIR` | `screenshots` | Screenshots directory |
| **Performance (milliseconds)** |||
| `PAGE_LOAD_THRESHOLD_MS` | `3000` | Max page load time |
| `API_RESPONSE_THRESHOLD_MS` | `2000` | Max API response time |
| **Docker/Grid** |||
| `SELENIUM_GRID_URL` | - | Grid hub URL |

---

## Common Usage Patterns

### Run Tests Locally
```bash
mvn test
```

### Run Headless
```bash
HEADLESS=true mvn test
# or
mvn test -Dheadless=true
```

### Change Browser
```bash
BROWSER=firefox mvn test
# or
mvn test -Dbrowser=firefox
```

### Run Specific Tests
```bash
# Single test class
mvn test -Dtest=SearchEngineTest

# Test pattern
mvn test -Dtest="**/web/*Test"

# Unit tests only
mvn test -Dtest="**/unit/*Test"

# API tests only
mvn test -Dtest="**/api/*Test"
```

### Run with Selenium Grid
```bash
# Start grid
docker-compose up -d selenium-hub chrome firefox

# Run tests
docker-compose run test-runner

# Or manually
export SELENIUM_GRID_URL=http://localhost:4444
mvn test
```

### Run Full Workflow
```bash
./scripts/run_full_workflow.sh

# With custom settings
BROWSER=firefox HEADLESS=true ./scripts/run_full_workflow.sh
```

### Parallel Execution
```bash
mvn test -Pparallel
```

---

## Configuration Files

| File | Purpose |
|------|---------|
| `src/test/resources/junit-platform.properties` | JUnit 5 parallel execution, timeouts |
| `src/main/resources/config/capabilities.json` | Browser capabilities (Chrome, Firefox, Edge) |
| `src/main/resources/logback.xml` | Logging configuration |
| `pom.xml` | Maven build, plugins, profiles |
| `docker-compose.yml` | Docker/Selenium Grid setup |
| `Dockerfile` | Test runner container |

---

## JUnit Platform Properties

Edit `src/test/resources/junit-platform.properties`:

```properties
# Enable/disable parallel execution
junit.jupiter.execution.parallel.enabled=true

# Parallelism strategy
junit.jupiter.execution.parallel.config.strategy=dynamic
junit.jupiter.execution.parallel.config.dynamic.factor=0.5

# Or fixed threads
# junit.jupiter.execution.parallel.config.strategy=fixed
# junit.jupiter.execution.parallel.config.fixed.parallelism=4

# Timeouts
junit.jupiter.execution.timeout.default=5m
junit.jupiter.execution.timeout.testable.method.default=2m
```

---

## Docker Quick Start

```bash
# Start Selenium Grid
docker-compose up -d selenium-hub chrome firefox

# View Grid Console
open http://localhost:4444

# Run tests in Docker
docker-compose run test-runner

# Run specific tests
docker-compose run test-runner mvn test -Dtest=SearchEngineTest

# View Allure Reports
docker-compose up -d allure
open http://localhost:5050

# Stop all
docker-compose down
```

---

## Troubleshooting

### Tests Timing Out
```bash
export PAGE_LOAD_TIMEOUT=60
export EXPLICIT_WAIT=20
mvn test
```

### Out of Memory
```bash
export MAVEN_OPTS="-Xmx2048m"
mvn test
```

### Parallel Tests Failing
```properties
# Reduce parallelism in junit-platform.properties
junit.jupiter.execution.parallel.config.dynamic.factor=0.25
```

---

## Configuration Priority

1. **Environment Variables** (highest)
2. **Maven System Properties** (`-Dproperty=value`)
3. **JUnit Platform Properties**
4. **Settings Class Defaults** (lowest)

---

## Quick Examples

### Cross-Browser Testing
```bash
for browser in chrome firefox edge; do
    BROWSER=$browser mvn test -Dtest="SearchEngineTest"
done
```

### Different Environments
```bash
# Staging
ENVIRONMENT=staging BASE_URL=https://staging.example.com mvn test

# Production
ENVIRONMENT=prod BASE_URL=https://example.com mvn test
```

### Performance Testing
```bash
PAGE_LOAD_THRESHOLD_MS=2000 API_RESPONSE_THRESHOLD_MS=1000 \
mvn test -Dtest="PerformanceMonitorTest"
```

---

**📖 For complete documentation, see [docs/CONFIGURATION.md](docs/CONFIGURATION.md)**

