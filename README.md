# Java Selenium Test Automation Framework

> **Portfolio project** — Selenium WebDriver 4, JUnit 5, REST Assured, Allure, and optional modules (visual, accessibility, BDD, Gatling, Playwright). Breadth is intentional for demos; a lean production stack is called out in [Feature matrix](docs/FEATURE_MATRIX.md).

## Quick start

**Prerequisites:** Java 21+, Maven 3.8+, Chrome/Firefox/Edge.

```bash
./setup_env.sh          # macOS/Linux — verify env, deps, quick compile check
# setup_env.bat         # Windows

mvn clean compile
mvn test
BROWSER=firefox mvn test
HEADLESS=true mvn test
```

**Targeted runs:**

```bash
mvn test -Dtest="**/unit/*Test"
mvn test -Dtest="**/backend/*Test"       # API, DB, contract, schema
mvn test -Dtest="**/ui/**/*Test"         # SauceDemo, practice app, visual
mvn gatling:test                         # load tests (Gatling; hits API_BASE_URL, default JSONPlaceholder — see LoadSimulation.java)
mvn test -Dgroups="accessibility"        # accessibility
# BDD: Cucumber is a declared optional stack in `pom.xml`; when a `*Runner` exists, Surefire will pick it up — see docs/FEATURE_MATRIX.md
```

**Reports:** `target/surefire-reports/` after `mvn test`; Allure: `mvn allure:serve`; coverage: `mvn test jacoco:report` → `target/site/jacoco/index.html`.

## Where the code lives

| Area | Path |
|------|------|
| Config & URLs | `src/main/java/com/automation/config/` (`Settings`, constants) |
| WebDriver lifecycle | `src/main/java/com/automation/utils/` (`WebDriverFactory`, …) |
| UI model | `src/main/java/com/automation/pages/`, `locators/` |
| Tests | `src/test/java/com/automation/` (`web/`, `api/`, `unit/`, `visual/`, …) |
| Build | `pom.xml` |

## Stack (summary)

Selenium + JUnit 5 + AssertJ; REST Assured for API; Allure + SLF4J/Logback; Maven, Docker, GitHub Actions. Optional: Shutterbug, Axe, Cucumber, Gatling, Playwright. Details: [Feature matrix](docs/FEATURE_MATRIX.md).

## Repository layout (abbreviated)

```
src/main/java/com/automation/
  config/  pages/  locators/  utils/  playwright/  accessibility/  parallel/
src/test/java/com/automation/
  unit/  backend/  ui/  integration/  performance/  accessibility/
  extensions/  observability/
scripts/  Dockerfile  docker-compose.yml  .github/workflows/
```

## Learn & configure

| Doc | Purpose |
|-----|---------|
| [Zero-to-Hero (canonical)](docs/ZERO_TO_HERO.md) | Pointer to the full tutorial path |
| [Zero-to-Hero tutorial](docs/ZERO_TO_HERO_TUTORIAL.md) | Step-by-step framework build + **appendix** (POM snippets, patterns, retries) |
| [Feature matrix](docs/FEATURE_MATRIX.md) | Which tools are core vs optional |
| [Configuration](docs/CONFIGURATION.md) | **Environment variables**, JUnit, Maven, Docker |
| [OTel test-run attributes](../shared-docs/docs/OTEL_TEST_RUN_ATTRIBUTES.md) | Minimal `service.name` / `git.sha` / `test.suite` contract (monorepo) |
| [Web testing](docs/WEB_TESTING.md) / [API testing](docs/API_TESTING.md) / [Visual testing](docs/VISUAL_TESTING.md) | Topic guides |

Suggested order: **web + config** → **api** → optional topics (visual, performance, BDD, Playwright).

### Lighthouse-style vs Google Lighthouse CLI

**Lighthouse-style** here means **Axe** on the page plus a **scoring-style** accessibility test — not Google’s **Lighthouse CLI** in default CI. JVM load demos use **Gatling** (`mvn gatling:test` when configured).

| Area | Spec / entry |
|------|----------------|
| Accessibility / scoring | [`src/test/java/com/automation/accessibility/LighthouseAccessibilityTest.java`](src/test/java/com/automation/accessibility/LighthouseAccessibilityTest.java) |
| Browser perf + load | [`src/test/java/com/automation/performance/PerformanceTest.java`](src/test/java/com/automation/performance/PerformanceTest.java), [`src/test/java/com/automation/performance/LoadSimulation.java`](src/test/java/com/automation/performance/LoadSimulation.java) |
| Playwright Java (smoke) | [`src/test/java/com/automation/playwright/PlaywrightSmokeTest.java`](src/test/java/com/automation/playwright/PlaywrightSmokeTest.java) |

## Docker

```bash
docker-compose up -d selenium-hub chrome firefox
docker-compose run test-runner
# Grid UI: http://localhost:4444 — Allure in compose may be on :5050 per your compose file
```

## CI & quality

```bash
./scripts/run_ci_checks.sh
mvn verify    # tests + static analysis (see `pom.xml` for profiles)
```

## Python equivalent (sibling repo)

| Python | Java |
|--------|------|
| pytest | JUnit 5 |
| requests | REST Assured |
| Selenium | Selenium (+ optional Playwright module) |
| Faker | Datafaker |

---

**Java 21** · **Maven** · **Selenium 4** · **JUnit 5**

Portfolio / learning use — open an issue for implementation questions.
