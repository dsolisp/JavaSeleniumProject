# 🎓 SDET Bootcamp Curriculum
## Transform from Beginner to Job-Ready Automation Engineer

> **Version:** 2.0 | **Duration:** 13 Modules (0-12) (~60-80 hours) | **Level:** Beginner → Advanced

---

## 🧠 How This Bootcamp Teaches You

This curriculum is designed using **evidence-based learning techniques** proven by cognitive science research:

### Learning Techniques Applied

| Technique | How We Use It | Why It Works |
|-----------|---------------|--------------|
| **🔄 Spaced Repetition** | Core concepts revisited across modules | Strengthens long-term memory |
| **📝 Active Recall** | Self-check questions before solutions | Forces brain to retrieve, not just recognize |
| **🪜 Scaffolded Learning** | Guided → Semi-guided → Independent | Builds confidence progressively |
| **👁️ Worked Examples** | Complete solutions with line-by-line explanations | Reduces cognitive load while learning |
| **🎯 Deliberate Practice** | Focused challenges targeting specific skills | Develops expertise efficiently |
| **🔀 Interleaving** | Mixing challenge types within modules | Improves transfer and retention |
| **❓ Elaborative Interrogation** | "Why?" and "What if?" prompts | Deepens understanding |
| **🖼️ Dual Coding** | Diagrams + code + explanations | Multiple memory pathways |
| **🪞 Metacognition** | Reflection prompts after challenges | Awareness of learning process |

### The Learning Cycle (Each Topic)

```
┌─────────────────────────────────────────────────────────────────┐
│  1. WHY IT MATTERS                                              │
│     Real-world context & motivation                             │
├─────────────────────────────────────────────────────────────────┤
│  2. CONCEPT EXPLANATION                                         │
│     Clear definitions, mental models, diagrams                  │
├─────────────────────────────────────────────────────────────────┤
│  3. SELF-CHECK QUESTIONS                                        │
│     Test understanding BEFORE seeing code                       │
├─────────────────────────────────────────────────────────────────┤
│  4. WORKED EXAMPLE                                              │
│     Complete solution with step-by-step explanation             │
├─────────────────────────────────────────────────────────────────┤
│  5. COMMON MISTAKES                                             │
│     What goes wrong & how to fix it                             │
├─────────────────────────────────────────────────────────────────┤
│  6. YOUR TURN (Challenge)                                       │
│     Apply the concept with scaffolded guidance                  │
├─────────────────────────────────────────────────────────────────┤
│  7. REFLECTION                                                  │
│     What did you learn? What was hard? What questions remain?   │
└─────────────────────────────────────────────────────────────────┘
```

### How to Use This Curriculum

1. **📖 Read actively** - Don't skim. Pause and think at each section.
2. **✋ Stop at self-checks** - Answer BEFORE looking at solutions.
3. **⌨️ Type code yourself** - Don't copy-paste. Muscle memory matters.
4. **🔴 Embrace errors** - Debugging is learning. Errors are data.
5. **📓 Keep a learning journal** - Note insights, questions, struggles.
6. **🗣️ Explain out loud** - Teaching forces understanding (Feynman Technique).
7. **⏰ Take breaks** - 25 min focused work → 5 min break (Pomodoro).

---

## 📊 Progress Tracking Dashboard

| # | Module | Duration | Prerequisites | Key Skills | Status |
|---|--------|----------|---------------|------------|--------|
| 0 | [Environment Setup](#module-0-environment-setup--project-creation) | 1 hr | None | Java, Maven, IntelliJ, pom.xml | ⬜ |
| 1 | [Selenium Fundamentals](#module-1-selenium-fundamentals--first-tests) | 4 hrs | Module 0 | WebDriver, JUnit 5, Locators, Assertions | ⬜ |
| 2 | [Page Object Model Deep Dive](#module-2-page-object-model-deep-dive) | 5 hrs | Module 1 | POM, BasePage, Encapsulation, Fluent API | ⬜ |
| 3 | [Advanced Locators & Synchronization](#module-3-advanced-locators--synchronization) | 4 hrs | Module 2 | XPath axes, CSS, Explicit/Fluent Waits | ⬜ |
| 4 | [Java Fundamentals for SDETs](#module-4-java-fundamentals-for-sdets) | 5 hrs | Module 3 | Collections, Streams, Lambdas, OOP | ⬜ |
| 5 | [Test Data Management](#module-5-test-data-management) | 5 hrs | Module 4 | JSON/CSV/YAML, Faker, Data Factories | ⬜ |
| 6 | [API Testing with REST Assured](#module-6-api-testing-with-rest-assured) | 6 hrs | Module 5 | HTTP methods, Auth, JSON validation | ⬜ |
| 7 | [Advanced API Automation](#module-7-advanced-api-automation) | 5 hrs | Module 6 | API chaining, Schema validation, POJO | ⬜ |
| 8 | [Hybrid Testing (API + UI)](#module-8-hybrid-testing-api--ui) | 5 hrs | Module 7 | Test optimization, State setup via API | ⬜ |
| 9 | [Design Patterns & Architecture](#module-9-design-patterns--framework-architecture) | 6 hrs | Module 8 | Factory, Builder, Singleton, DI | ⬜ |
| 10 | [Database Testing & Validation](#module-10-database-testing--validation) | 4 hrs | Module 9 | JDBC, SQL queries, Data verification | ⬜ |
| 11 | [Reporting, Logging & Debugging](#module-11-reporting-logging--debugging) | 4 hrs | Module 10 | Allure, SLF4J, Screenshots, Debugging | ⬜ |
| 12 | [CI/CD & Capstone Project](#module-12-cicd-integration--capstone-project) | 8 hrs | All modules | GitHub Actions, Docker, Full framework | ⬜ |

**Legend:** ⬜ Not Started | 🔄 In Progress | ✅ Completed

**Total Estimated Time:** ~60-80 hours (including capstone project)

---

## 🛠️ Technology Stack

### Primary Technologies
| Technology | Version | Purpose |
|------------|---------|---------|
| **Java** | 21 | Primary programming language |
| **Selenium WebDriver** | 4.27+ | Browser automation |
| **JUnit 5** | 5.11+ | Test framework |
| **REST Assured** | 5.5+ | API testing |
| **AssertJ** | 3.27+ | Fluent assertions |
| **Allure** | 2.29+ | Test reporting |

### Supporting Technologies
| Technology | Purpose |
|------------|---------|
| **Maven** | Build & dependency management |
| **SLF4J + Logback** | Logging framework |
| **Jackson** | JSON parsing |
| **Lombok** | Boilerplate reduction |
| **Docker** | Containerized execution |
| **GitHub Actions** | CI/CD pipeline |

---

## 📈 Skill Level Definitions

### 🟢 Beginner (Bloom's: Remember, Understand)
- Can write basic tests with guidance
- Understands fundamental concepts
- Follows established patterns
- Needs code review for quality

### 🟡 Intermediate (Bloom's: Apply, Analyze)
- Writes tests independently
- Applies design patterns correctly
- Debugs issues effectively
- Creates reusable components

### 🔴 Advanced (Bloom's: Evaluate, Create)
- Architects test frameworks
- Optimizes for performance/maintainability
- Mentors other team members
- Makes strategic technical decisions

---

## 🎯 Assessment Criteria

Each challenge is graded on these dimensions:

| Dimension | Weight | Criteria |
|-----------|--------|----------|
| **Functionality** | 25% | Tests pass, correct behavior verified |
| **Code Organization** | 20% | Clean structure, proper naming, DRY |
| **Design Patterns** | 20% | Appropriate pattern usage, SOLID principles |
| **Error Handling** | 15% | Graceful failures, proper cleanup |
| **Maintainability** | 20% | Readable, documented, extensible |

### Self-Assessment Rubric
After each challenge, rate yourself honestly:

| Level | Description |
|-------|-------------|
| 🔴 **Stuck** | Couldn't complete without looking at solution |
| 🟡 **Struggled** | Completed with hints, took much longer than expected |
| 🟢 **Solid** | Completed independently, minor issues |
| ⭐ **Mastered** | Completed quickly, could explain to others |

---

## 📚 Detailed Module Breakdown

---

# Module 0: Environment Setup & Project Creation

## 🎯 Learning Objectives

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List required tools for SDET work | Checklist verification |
| **Understand** | Explain what Maven does and why we need it | Written explanation |
| **Apply** | Create a new Maven project with dependencies | Working project |
| **Verify** | Run a sample test to confirm setup | Green test execution |

---

## 📖 Topic 0.1: Installing Java 21

### 🤔 Why This Matters

Java is the foundation of our test framework. Using the **LTS (Long Term Support)** version ensures stability and compatibility with all libraries.

### 📋 Installation Steps

#### macOS (using Homebrew)
```bash
# Install Homebrew if not already installed
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"

# Install Java 21
brew install openjdk@21

# Add to PATH (add to ~/.zshrc or ~/.bash_profile)
echo 'export PATH="/opt/homebrew/opt/openjdk@21/bin:$PATH"' >> ~/.zshrc
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 21)' >> ~/.zshrc

# Reload shell
source ~/.zshrc

# Verify installation
java -version
```

#### Windows
1. Download from [Adoptium](https://adoptium.net/) (Eclipse Temurin 21)
2. Run installer, check "Set JAVA_HOME variable"
3. Open new Command Prompt and verify:
```cmd
java -version
echo %JAVA_HOME%
```

#### Linux (Ubuntu/Debian)
```bash
sudo apt update
sudo apt install openjdk-21-jdk

# Set JAVA_HOME
echo 'export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64' >> ~/.bashrc
source ~/.bashrc

java -version
```

### ✅ Verification Checkpoint
```bash
# Should show: openjdk version "21.x.x"
java -version

# Should show path to Java installation
echo $JAVA_HOME
```

---

## 📖 Topic 0.2: Installing Maven

### 🤔 Why This Matters

Maven handles:
- **Dependency management** - Downloads libraries (Selenium, JUnit, etc.)
- **Build lifecycle** - Compile, test, package
- **Project structure** - Standardized folder layout
- **Plugin execution** - Allure reports, Surefire test runner

### 📋 Installation Steps

#### macOS
```bash
brew install maven

# Verify
mvn -version
```

#### Windows
1. Download from [Maven Downloads](https://maven.apache.org/download.cgi) (Binary zip)
2. Extract to `C:\Program Files\Apache\maven`
3. Add to System Environment Variables:
   - `MAVEN_HOME` = `C:\Program Files\Apache\maven`
   - Add `%MAVEN_HOME%\bin` to `PATH`
4. Open new Command Prompt:
```cmd
mvn -version
```

#### Linux
```bash
sudo apt install maven
mvn -version
```

### ✅ Verification Checkpoint
```bash
# Should show Maven version and Java version
mvn -version

# Expected output:
# Apache Maven 3.9.x
# Java version: 21.x.x
```

---

## 📖 Topic 0.3: IDE Setup (IntelliJ IDEA)

### 🤔 Why This Matters

IntelliJ IDEA is the industry standard for Java development. The **Community Edition is free** and has everything you need.

### 📋 Installation Steps

1. Download [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/)
2. Install and launch
3. Configure settings:

#### Essential Settings
```
File → Settings (or IntelliJ IDEA → Preferences on Mac)

├── Build, Execution, Deployment
│   └── Build Tools → Maven
│       └── Maven home path: (auto-detected or set manually)
│
├── Editor → Code Style → Java
│   └── Scheme: Default (or Google Style)
│
├── Editor → General → Auto Import
│   └── ✅ Add unambiguous imports on the fly
│   └── ✅ Optimize imports on the fly
│
└── Plugins → Install:
    └── Lombok
    └── .env files support (optional)
```

---

## 📖 Topic 0.4: Creating Your First Maven Project

### 🤔 Why This Matters

This is the project structure you'll use for the entire bootcamp and in real-world SDET work.

### 📋 Option 1: Create via IntelliJ

1. **File → New → Project**
2. Select **Maven Archetype**
3. Configure:
   - Name: `sdet-bootcamp`
   - Location: Your preferred folder
   - JDK: 21
   - Archetype: `maven-archetype-quickstart`
   - GroupId: `com.yourname`
   - ArtifactId: `sdet-bootcamp`
4. Click **Create**

### 📋 Option 2: Create via Command Line

```bash
mvn archetype:generate \
  -DgroupId=com.yourname \
  -DartifactId=sdet-bootcamp \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false

cd sdet-bootcamp
```

### 📁 Expected Project Structure

```
sdet-bootcamp/
├── pom.xml                          ← Project configuration
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/yourname/        ← Application code (rarely used in testing)
│   └── test/
│       ├── java/
│       │   └── com/yourname/        ← Your test classes go here
│       └── resources/
│           ├── config.properties    ← Configuration files
│           └── testdata/            ← Test data files (JSON, CSV)
└── target/                          ← Generated files (ignored by git)
```

---

## 📖 Topic 0.5: The Complete pom.xml

### 🤔 Why This Matters

The `pom.xml` is the heart of your Maven project. It defines ALL dependencies and plugins.

### 📋 Replace Your pom.xml With This

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <!-- ═══════════════════════════════════════════════════════════════ -->
    <!-- PROJECT INFO                                                     -->
    <!-- ═══════════════════════════════════════════════════════════════ -->
    <groupId>com.yourname</groupId>
    <artifactId>sdet-bootcamp</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>SDET Bootcamp Project</name>
    <description>Test automation framework for SDET bootcamp</description>

    <!-- ═══════════════════════════════════════════════════════════════ -->
    <!-- VERSION PROPERTIES (Single source of truth)                      -->
    <!-- ═══════════════════════════════════════════════════════════════ -->
    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>

        <!-- Testing -->
        <selenium.version>4.27.0</selenium.version>
        <junit.version>5.11.3</junit.version>
        <assertj.version>3.27.0</assertj.version>
        <rest-assured.version>5.5.0</rest-assured.version>

        <!-- Utilities -->
        <jackson.version>2.18.2</jackson.version>
        <lombok.version>1.18.36</lombok.version>
        <faker.version>2.4.2</faker.version>
        <owner.version>1.0.12</owner.version>

        <!-- Logging -->
        <slf4j.version>2.0.16</slf4j.version>
        <logback.version>1.5.12</logback.version>

        <!-- Reporting -->
        <allure.version>2.29.1</allure.version>
        <aspectj.version>1.9.22.1</aspectj.version>

        <!-- Plugins -->
        <maven-surefire.version>3.5.2</maven-surefire.version>
        <maven-compiler.version>3.13.0</maven-compiler.version>
    </properties>

    <!-- ═══════════════════════════════════════════════════════════════ -->
    <!-- DEPENDENCIES                                                     -->
    <!-- ═══════════════════════════════════════════════════════════════ -->
    <dependencies>
        <!-- ─── Selenium WebDriver ─────────────────────────────────── -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
        </dependency>

        <!-- ─── JUnit 5 ────────────────────────────────────────────── -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- ─── AssertJ (Fluent Assertions) ────────────────────────── -->
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>${assertj.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- ─── REST Assured (API Testing) ─────────────────────────── -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>${rest-assured.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- ─── Jackson (JSON Parsing) ─────────────────────────────── -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson.version}</version>
        </dependency>

        <!-- ─── Lombok (Reduce Boilerplate) ────────────────────────── -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>

        <!-- ─── JavaFaker (Test Data Generation) ───────────────────── -->
        <dependency>
            <groupId>net.datafaker</groupId>
            <artifactId>datafaker</artifactId>
            <version>${faker.version}</version>
        </dependency>

        <!-- ─── Owner (Configuration Management) ───────────────────── -->
        <dependency>
            <groupId>org.aeonbits.owner</groupId>
            <artifactId>owner</artifactId>
            <version>${owner.version}</version>
        </dependency>

        <!-- ─── SLF4J + Logback (Logging) ──────────────────────────── -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>${logback.version}</version>
        </dependency>

        <!-- ─── Allure Reporting ───────────────────────────────────── -->
        <dependency>
            <groupId>io.qameta.allure</groupId>
            <artifactId>allure-junit5</artifactId>
            <version>${allure.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <!-- ═══════════════════════════════════════════════════════════════ -->
    <!-- BUILD CONFIGURATION                                              -->
    <!-- ═══════════════════════════════════════════════════════════════ -->
    <build>
        <plugins>
            <!-- ─── Compiler Plugin ────────────────────────────────── -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>${maven-compiler.version}</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>

            <!-- ─── Surefire Plugin (Test Runner) ──────────────────── -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>${maven-surefire.version}</version>
                <configuration>
                    <argLine>
                        -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
                    </argLine>
                    <systemPropertyVariables>
                        <allure.results.directory>${project.build.directory}/allure-results</allure.results.directory>
                    </systemPropertyVariables>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>org.aspectj</groupId>
                        <artifactId>aspectjweaver</artifactId>
                        <version>${aspectj.version}</version>
                    </dependency>
                </dependencies>
            </plugin>

            <!-- ─── Allure Maven Plugin ────────────────────────────── -->
            <plugin>
                <groupId>io.qameta.allure</groupId>
                <artifactId>allure-maven</artifactId>
                <version>2.13.0</version>
                <configuration>
                    <reportVersion>${allure.version}</reportVersion>
                    <resultsDirectory>${project.build.directory}/allure-results</resultsDirectory>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### 📚 Understanding the pom.xml

| Section | Purpose |
|---------|---------|
| `<properties>` | Centralized version management |
| `<dependencies>` | Libraries your project needs |
| `<scope>test</scope>` | Only available during tests |
| `<build><plugins>` | Tools that run during build |

---

## 📖 Topic 0.6: First Test (Verification)

### 🤔 Why This Matters

Before diving into Selenium, let's verify your setup works with a simple test.

### 📋 Create Your First Test

Create file: `src/test/java/com/yourname/SetupVerificationTest.java`

```java
package com.yourname;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

class SetupVerificationTest {

    @Test
    @DisplayName("Verify JUnit 5 is working")
    void junitWorking() {
        assertThat(1 + 1).isEqualTo(2);
        System.out.println("✅ JUnit 5 is working!");
    }

    @Test
    @DisplayName("Verify AssertJ is working")
    void assertJWorking() {
        String message = "Hello, SDET!";
        assertThat(message)
            .isNotNull()
            .startsWith("Hello")
            .contains("SDET")
            .hasSize(12);
        System.out.println("✅ AssertJ is working!");
    }

    @Test
    @DisplayName("Verify Selenium WebDriver is working")
    void seleniumWorking() {
        // Configure Chrome to run headless (no visible window)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.google.com");
            assertThat(driver.getTitle()).contains("Google");
            System.out.println("✅ Selenium WebDriver is working!");
        } finally {
            driver.quit();
        }
    }
}
```

### 📋 Run Your Tests

#### Option 1: IntelliJ
- Right-click on test file → Run 'SetupVerificationTest'
- Or click the green play button next to the class/method

#### Option 2: Command Line
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=SetupVerificationTest

# Run with output visible
mvn test -Dtest=SetupVerificationTest -DtrimStackTrace=false
```

### ✅ Expected Output
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.yourname.SetupVerificationTest
✅ JUnit 5 is working!
✅ AssertJ is working!
✅ Selenium WebDriver is working!
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
[INFO] -------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] -------------------------------------------------------
```

---

## ⚠️ Troubleshooting Common Setup Issues

### Issue 1: "JAVA_HOME not set"
```bash
# Check if set
echo $JAVA_HOME

# Fix (macOS/Linux) - add to ~/.zshrc or ~/.bashrc
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
```

### Issue 2: "ChromeDriver version mismatch"
```
# Selenium 4.11+ manages drivers automatically!
# If you see this error, update Selenium version in pom.xml
```

### Issue 3: Maven dependencies not downloading
```bash
# Force re-download
mvn clean install -U

# Check Maven settings
mvn help:effective-settings
```

### Issue 4: IntelliJ not recognizing test folder
```
# Right-click on src/test/java folder
# Mark Directory as → Test Sources Root
```

### Issue 5: Lombok not working
```
# In IntelliJ:
# 1. Install Lombok plugin: Settings → Plugins → Lombok
# 2. Enable annotation processing:
#    Settings → Build → Compiler → Annotation Processors
#    ✅ Enable annotation processing
```

---

## 🎯 Challenge 0.1: Complete Setup Verification

### Scaffolding Level: 🟢 Fully Guided

**Task:** Verify your complete setup is working.

### Checklist

- [ ] Java 21 installed (`java -version` shows 21.x)
- [ ] Maven installed (`mvn -version` shows 3.9.x)
- [ ] IntelliJ IDEA installed with Lombok plugin
- [ ] Project created with correct structure
- [ ] pom.xml contains all dependencies
- [ ] SetupVerificationTest runs with 3 passing tests
- [ ] `mvn test` runs successfully from command line

### Bonus Verification
```bash
# Generate Allure report (verifies Allure plugin works)
mvn allure:serve
# This should open a browser with an Allure report
```

### ⏱️ Time: 30-60 minutes (depending on download speeds)

---

## 🔄 Module 0 Reflection

Before proceeding to Module 1, answer these questions:

1. **What does Maven do?** (Explain in your own words)

2. **What is the purpose of `<scope>test</scope>` in pom.xml?**

3. **Why do we use `--headless=new` in the Selenium test?**

4. **Confidence Check:**
   - [ ] I can create a new Maven project
   - [ ] I understand the pom.xml structure
   - [ ] I can run tests from IntelliJ and command line
   - [ ] I know where to put test files

---

# Module 1: Selenium Fundamentals & First Tests

## 🎯 Learning Objectives (Bloom's Taxonomy)

By completing this module, you will be able to:

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List the 6 locator strategies in Selenium | Self-check quiz |
| **Understand** | Explain how WebDriver communicates with browsers | Written explanation |
| **Apply** | Write tests using JUnit 5 lifecycle annotations | Challenge 1.1 |
| **Analyze** | Compare different locator strategies for reliability | Challenge 1.2 |
| **Evaluate** | Judge whether a test is properly structured | Code review exercise |
| **Create** | Design a test suite for a login page | Challenge 1.3 |

---

## 📖 Topic 1.1: WebDriver Architecture

### 🤔 Why This Matters

> **Real-world scenario:** You write a test that works locally but fails in CI. Understanding WebDriver architecture helps you debug why. Is it a driver version mismatch? Network timeout? Browser configuration?

Every minute you invest understanding the fundamentals saves hours of debugging later.

### 📚 The Concept

Selenium WebDriver is a **browser automation framework**. But what happens when you write `driver.findElement(By.id("login"))`?

```
┌─────────────────┐     HTTP/JSON      ┌─────────────────┐     Native API     ┌─────────────────┐
│   Your Test     │ ──────────────────▶│   Browser       │ ──────────────────▶│    Browser      │
│   Code (Java)   │                    │   Driver        │                    │    (Chrome)     │
│                 │ ◀────────────────── │   (chromedriver)│ ◀────────────────── │                 │
└─────────────────┘     Response       └─────────────────┘     DOM Result     └─────────────────┘
         │                                      │                                      │
         │                                      │                                      │
    WebDriver API                     W3C WebDriver                           Actual browser
    (Java bindings)                   Protocol (JSON)                         interactions
```

**Key insight:** Your code never talks to the browser directly. It talks to a **driver executable** that translates commands.

### 💡 Mental Model: The Translator

Think of WebDriver like an interpreter at the UN:
- **You (Java code)** speak Java
- **The interpreter (ChromeDriver)** translates to browser-speak
- **The diplomat (Chrome)** performs actions and reports back

If the interpreter isn't there, or speaks the wrong language version, communication fails.

### ✋ Self-Check Questions (Answer before continuing!)

<details>
<summary>1. If your test works on Chrome but fails on Firefox, which component is most likely the cause?</summary>

**Answer:** The browser driver (geckodriver vs chromedriver). Each browser needs its own "interpreter" that speaks its specific protocol.
</details>

<details>
<summary>2. Why does Selenium require you to download chromedriver separately?</summary>

**Answer:** ChromeDriver is maintained by the Chrome team, not Selenium. It's a separate executable that must match your Chrome version. (Note: Selenium 4.6+ includes automatic driver management via Selenium Manager.)
</details>

<details>
<summary>3. What protocol does WebDriver use to communicate with browser drivers?</summary>

**Answer:** HTTP with JSON payloads, following the W3C WebDriver specification. This is why you sometimes see "localhost:9515" in driver logs—it's running a local HTTP server.
</details>

---

## 📖 Topic 1.2: JUnit 5 Lifecycle

### 🤔 Why This Matters

> **Real-world scenario:** Your test suite has 100 tests. If each test opens a new browser, runs for 10 seconds, and closes it, that's 1000+ seconds of browser startup overhead. Understanding lifecycle lets you optimize: share browsers where safe, isolate where needed.

### 📚 The Concept

JUnit 5 provides hooks to run code at specific points in the test lifecycle:

```
┌─────────────────────────────────────────────────────────────────┐
│                        TEST CLASS                               │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │  @BeforeAll (runs ONCE before any test)                   │  │
│  │  └── Setup shared resources (database connections, etc.)  │  │
│  └───────────────────────────────────────────────────────────┘  │
│                              │                                  │
│                              ▼                                  │
│  ┌─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐  │
│  │           FOR EACH @Test METHOD:                          │  │
│  │  ┌─────────────────────────────────────────────────────┐  │  │
│  │  │  @BeforeEach                                        │  │  │
│  │  │  └── Fresh browser, clean state                     │  │  │
│  │  └─────────────────────────────────────────────────────┘  │  │
│  │                         │                                 │  │
│  │                         ▼                                 │  │
│  │  ┌─────────────────────────────────────────────────────┐  │  │
│  │  │  @Test                                              │  │  │
│  │  │  └── The actual test logic                          │  │  │
│  │  └─────────────────────────────────────────────────────┘  │  │
│  │                         │                                 │  │
│  │                         ▼                                 │  │
│  │  ┌─────────────────────────────────────────────────────┐  │  │
│  │  │  @AfterEach                                         │  │  │
│  │  │  └── Close browser, cleanup                         │  │  │
│  │  └─────────────────────────────────────────────────────┘  │  │
│  └ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─ ─┘  │
│                              │                                  │
│                              ▼                                  │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │  @AfterAll (runs ONCE after all tests)                    │  │
│  │  └── Release shared resources                             │  │
│  └───────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

### 💡 Mental Model: Restaurant Service

- **@BeforeAll** = Opening the restaurant (turn on lights, prep kitchen)
- **@BeforeEach** = Setting a fresh table for each customer
- **@Test** = Serving the customer
- **@AfterEach** = Clearing and resetting the table
- **@AfterAll** = Closing the restaurant

### ✋ Self-Check Questions

<details>
<summary>1. If you have 5 test methods, how many times does @BeforeEach run?</summary>

**Answer:** 5 times—once before each test method.
</details>

<details>
<summary>2. Why must @BeforeAll methods be `static`?</summary>

**Answer:** Because @BeforeAll runs before any test instance is created. There's no `this` object yet, so the method must belong to the class (static), not an instance.
</details>

<details>
<summary>3. What happens if @AfterEach throws an exception?</summary>

**Answer:** The cleanup fails, but JUnit still marks the test result based on the @Test method. However, subsequent tests may fail due to unclean state (like a browser left open). Always handle exceptions in cleanup!
</details>

---

## 📖 Topic 1.3: Locator Strategies

### 🤔 Why This Matters

> **Real-world scenario:** The developer changes a button's class from "btn-primary" to "btn-action". If your test used that class as a locator, it breaks. If you used a stable ID, it survives. Locator strategy = test stability.

### 📚 The Concept: 6 Locator Strategies

| Strategy | Syntax | When to Use | Stability |
|----------|--------|-------------|-----------|
| **ID** | `By.id("login")` | Element has unique ID | ⭐⭐⭐⭐⭐ |
| **Name** | `By.name("username")` | Form fields | ⭐⭐⭐⭐ |
| **CSS Selector** | `By.cssSelector(".btn.primary")` | Complex selection | ⭐⭐⭐⭐ |
| **XPath** | `By.xpath("//div[@class='x']")` | When CSS fails | ⭐⭐⭐ |
| **Link Text** | `By.linkText("Click here")` | Anchor elements | ⭐⭐ |
| **Class Name** | `By.className("btn")` | Single class | ⭐⭐ |

### 💡 Mental Model: Finding a Person in a Crowd

- **ID** = Calling their unique name ("Hey, John Smith!")
- **Name** = Looking for their name tag
- **CSS Selector** = "The person wearing a red hat AND blue shoes"
- **XPath** = "The third person in the second row from the left"
- **Link Text** = "The person holding a sign that says 'Click Here'"
- **Class Name** = "Anyone wearing a red hat" (might match many people!)

### 📊 Locator Priority Decision Tree

```
                    ┌─────────────────────┐
                    │ Does element have   │
                    │ a unique ID?        │
                    └──────────┬──────────┘
                               │
              ┌────────────────┴────────────────┐
              │ YES                             │ NO
              ▼                                 ▼
      ┌───────────────┐               ┌─────────────────────┐
      │ Use By.id()   │               │ Is it a form field  │
      │ ⭐ BEST       │               │ with name attr?     │
      └───────────────┘               └──────────┬──────────┘
                                                 │
                                  ┌──────────────┴──────────────┐
                                  │ YES                         │ NO
                                  ▼                             ▼
                          ┌───────────────┐           ┌─────────────────────┐
                          │ Use By.name() │           │ Can you write a     │
                          │ ⭐⭐ GOOD     │           │ simple CSS selector? │
                          └───────────────┘           └──────────┬──────────┘
                                                                 │
                                                  ┌──────────────┴──────────────┐
                                                  │ YES                         │ NO
                                                  ▼                             ▼
                                          ┌───────────────┐           ┌─────────────────┐
                                          │ Use CSS       │           │ Use XPath       │
                                          │ ⭐⭐⭐ GOOD   │           │ (last resort)   │
                                          └───────────────┘           └─────────────────┘
```

### ✋ Self-Check Questions

<details>
<summary>1. Given `<button id="submit" class="btn primary">Send</button>`, what's the best locator?</summary>

**Answer:** `By.id("submit")` - ID is unique and most stable.
</details>

<details>
<summary>2. Given `<input type="email" name="user_email" class="form-control">`, write two valid locators.</summary>

**Answer:**
- `By.name("user_email")` - Best choice, semantic
- `By.cssSelector("input[type='email']")` - Also valid
</details>

<details>
<summary>3. Why is `By.className("btn")` often a bad choice?</summary>

**Answer:** "btn" is a common class used on many buttons. It's not unique, so `findElement()` might return the wrong button, or `findElements()` returns too many.
</details>

---

## 📖 Topic 1.4: AssertJ Fluent Assertions

### 🤔 Why This Matters

> **Real-world scenario:** Test fails with message: `expected true but was false`. What failed? You have no idea. AssertJ gives you: `Expecting element to be displayed but was hidden`. Clear, actionable, debuggable.

### 📚 The Concept

AssertJ provides fluent, readable assertions with excellent failure messages.

**JUnit (traditional):**
```java
assertEquals("Swag Labs", driver.getTitle());
// Failure: expected: <Swag Labs> but was: <SauceLabs>
```

**AssertJ (fluent):**
```java
assertThat(driver.getTitle()).isEqualTo("Swag Labs");
// Failure: expected: "Swag Labs" but was: "SauceLabs"

// Even better with custom message:
assertThat(driver.getTitle())
    .as("Page title after navigating to SauceDemo")
    .isEqualTo("Swag Labs");
```

### 💡 AssertJ Cheat Sheet for UI Testing

```java
// String assertions
assertThat(title).contains("Swag");
assertThat(title).startsWith("Swag");
assertThat(title).isNotEmpty();

// Boolean assertions
assertThat(element.isDisplayed()).isTrue();
assertThat(element.isEnabled()).isTrue();

// Collection assertions
assertThat(products).hasSize(6);
assertThat(products).isNotEmpty();

// Soft assertions (collect all failures)
SoftAssertions soft = new SoftAssertions();
soft.assertThat(title).contains("Swag");
soft.assertThat(element.isDisplayed()).isTrue();
soft.assertAll();  // Reports ALL failures, not just first
```

---

## 🔨 Worked Example: Complete Test Structure

Before you attempt the challenges, study this complete example:

```java
package com.automation.bootcamp;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.utils.WebDriverFactory;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Module 1 - Worked Example: Complete Test Structure")
class WorkedExampleTest {

    // WHY: Instance variable so each test gets its own driver
    private WebDriver driver;

    // WHY: Constants prevent typos and enable easy updates
    private static final String BASE_URL = "https://www.saucedemo.com";

    @BeforeEach
    void setUp() {
        // WHY: Fresh browser for each test = test isolation
        // WHY: headless=true for CI/CD, false for debugging
        driver = WebDriverFactory.createDriver("chrome", true);

        // WHY: Navigate in setUp so every test starts on the page
        driver.get(BASE_URL);
    }

    @AfterEach
    void tearDown() {
        // WHY: Null check prevents NullPointerException if setUp failed
        if (driver != null) {
            // WHY: quit() closes browser AND ends driver session
            // WHY: close() only closes current window
            driver.quit();
        }
    }

    @Test
    @DisplayName("Should verify page title contains expected text")
    void shouldVerifyPageTitle() {
        // ARRANGE: Already done in @BeforeEach

        // ACT: Get the page title
        String actualTitle = driver.getTitle();

        // ASSERT: Verify with descriptive message
        assertThat(actualTitle)
            .as("Page title should contain 'Swag Labs'")
            .containsIgnoringCase("Swag Labs");
    }

    @Test
    @DisplayName("Should verify login form elements are visible")
    void shouldVerifyLoginFormElements() {
        // ARRANGE: Page already loaded

        // ACT: Find elements
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // ASSERT: All elements should be visible
        assertThat(usernameField.isDisplayed())
            .as("Username field should be visible")
            .isTrue();
        assertThat(passwordField.isDisplayed())
            .as("Password field should be visible")
            .isTrue();
        assertThat(loginButton.isDisplayed())
            .as("Login button should be visible")
            .isTrue();
    }
}
```

### 📝 Key Patterns to Notice

| Pattern | Why It Matters |
|---------|----------------|
| Constants for URLs | Change once, update everywhere |
| Null check in tearDown | Prevents cascade failures |
| `@DisplayName` | Clear test reports |
| AAA structure | Arrange, Act, Assert = readable tests |
| `.as()` descriptions | Debuggable failures |

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: Forgetting to quit the browser

```java
// ❌ BAD: Browser stays open, memory leaks
@AfterEach
void tearDown() {
    driver.close();  // Only closes window, not driver!
}

// ✅ GOOD: Properly ends session
@AfterEach
void tearDown() {
    if (driver != null) {
        driver.quit();  // Closes all windows AND driver
    }
}
```

### Mistake 2: Using Thread.sleep() instead of proper waits

```java
// ❌ BAD: Always waits 5 seconds, even if element appears immediately
Thread.sleep(5000);
WebElement button = driver.findElement(By.id("submit"));

// ✅ GOOD: Waits UP TO 10 seconds, returns immediately when found
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
```

### Mistake 3: Hardcoding in test methods

```java
// ❌ BAD: Magic strings everywhere
driver.get("https://www.saucedemo.com");
driver.findElement(By.id("user-name")).sendKeys("standard_user");

// ✅ GOOD: Constants and helper methods
private static final String BASE_URL = "https://www.saucedemo.com";
private static final By USERNAME_FIELD = By.id("user-name");

driver.get(BASE_URL);
driver.findElement(USERNAME_FIELD).sendKeys(TestData.STANDARD_USER);
```

---

## 🎯 Challenge 1.1: Login Page Validation (Beginner)

### Scaffolding Level: 🟢 Guided (Step-by-step hints available)

**Scenario:** Verify SauceDemo login page displays correctly.

### Requirements
- Navigate to https://www.saucedemo.com
- Verify page title contains "Swag Labs"
- Verify username, password fields, and login button are visible

### Acceptance Criteria
- [ ] Test passes in headless mode
- [ ] Uses `@DisplayName` annotation
- [ ] Proper browser cleanup in `@AfterEach`
- [ ] AssertJ fluent assertions used

### Step-by-Step Guidance

<details>
<summary>Step 1: Create the test class structure</summary>

```java
@DisplayName("Module 1 - Challenge 1.1: Login Page Validation")
class Module1_LoginPageValidationTest {
    private WebDriver driver;
    private static final String URL = "https://www.saucedemo.com";

    // Add @BeforeEach and @AfterEach next...
}
```
</details>

<details>
<summary>Step 2: Add lifecycle methods</summary>

```java
@BeforeEach
void setUp() {
    driver = WebDriverFactory.createDriver("chrome", true);
}

@AfterEach
void tearDown() {
    if (driver != null) {
        driver.quit();
    }
}
```
</details>

<details>
<summary>Step 3: Write the test method</summary>

```java
@Test
@DisplayName("Should display all login page elements")
void shouldDisplayLoginPageElements() {
    driver.get(URL);
    // Now add assertions for title and elements...
}
```
</details>

### ⏱️ Time Estimate: 30-45 minutes

### ✅ Self-Assessment After Completion

Rate yourself honestly:
- 🔴 **Stuck**: Needed to look at all hints/solution
- 🟡 **Struggled**: Used some hints, took longer than expected
- 🟢 **Solid**: Completed with minimal hints
- ⭐ **Mastered**: Completed without hints, could explain to others

---

## 🎯 Challenge 1.2: Login Flow Tests (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided (Fewer hints)

**Scenario:** Automate complete login flows for different user types.

### Requirements

| Test Case | Credentials | Expected Result |
|-----------|-------------|-----------------|
| Valid login | standard_user / secret_sauce | URL contains "inventory" |
| Locked user | locked_out_user / secret_sauce | Error message displayed |
| Empty creds | (empty) / (empty) | "Username is required" |

### Acceptance Criteria
- [ ] 3 independent test cases (each can run alone)
- [ ] Helper method `performLogin(username, password)` for reusability
- [ ] Exact error message validation
- [ ] No code duplication

### Hints

<details>
<summary>Hint 1: Locators you'll need</summary>

- Username: `By.id("user-name")`
- Password: `By.id("password")`
- Login button: `By.id("login-button")`
- Error message: `By.cssSelector("[data-test='error']")` or `By.className("error-message-container")`
</details>

<details>
<summary>Hint 2: Helper method signature</summary>

```java
private void performLogin(String username, String password) {
    // Find fields
    // Enter credentials
    // Click login
}
```
</details>

### ⏱️ Time Estimate: 45-60 minutes

---

## 🎯 Challenge 1.3: Inventory Verification (Intermediate-Advanced)

### Scaffolding Level: 🔴 Independent (Minimal guidance)

**Scenario:** Verify product inventory displays correctly after login.

### Requirements
- Login as standard_user
- Verify exactly 6 products displayed
- Each product has: image, title, price, add-to-cart button
- All prices start with "$"

### Acceptance Criteria
- [ ] Uses `findElements()` for collections
- [ ] Stream operations for validation
- [ ] Descriptive failure messages

### You're on your own for this one!

No hints provided. Use what you learned. If stuck for more than 20 minutes, review:
1. The worked example above
2. Java Streams documentation
3. AssertJ collection assertions

### ⏱️ Time Estimate: 60-90 minutes

---

## 🔄 Module 1 Reflection

After completing all challenges, answer these questions in your learning journal:

1. **What was the most confusing concept?** How did you resolve it?

2. **Which locator strategy did you find most reliable?** Why?

3. **What would you do differently** if you started Challenge 1.1 again?

4. **Explain WebDriver architecture** to an imaginary colleague. Did you struggle? That's a sign to review.

5. **Rate your confidence (1-5)** on each skill:
   - [ ] Writing JUnit 5 tests with lifecycle methods
   - [ ] Finding elements with different locator strategies
   - [ ] Writing fluent assertions with AssertJ
   - [ ] Proper browser lifecycle management

---

## 🔗 Spaced Repetition Checkpoint

**Revisit these concepts in:**
- **Day 3**: Without looking, list the 6 locator strategies
- **Week 1**: Explain WebDriver architecture to someone
- **Week 2**: Refactor Challenge 1.1 to use Page Object Model (after Module 2)

---



# Module 2: Page Object Model Deep Dive

## 🎯 Learning Objectives (Bloom's Taxonomy)

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | Define the Page Object Model pattern | Self-check quiz |
| **Understand** | Explain why POM reduces test maintenance | Written explanation |
| **Apply** | Create page objects for a login flow | Challenge 2.1 |
| **Analyze** | Compare tests with and without POM | Before/after analysis |
| **Evaluate** | Critique a poorly designed page object | Code review exercise |
| **Create** | Design a complete page object hierarchy | Challenge 2.3 |

---

## 📖 Topic 2.1: Why Page Object Model Exists

### 🤔 Why This Matters

> **Real-world scenario:** The login button's ID changes from `login-button` to `btn-signin`. You have 47 tests that reference this button. Without POM, you update 47 files. With POM, you update 1 line in 1 file. That's the difference between 5 minutes and 5 hours.

### 📚 The Problem POM Solves

Look at this test without POM:

```java
// ❌ Test #1
driver.findElement(By.id("user-name")).sendKeys("standard_user");
driver.findElement(By.id("password")).sendKeys("secret_sauce");
driver.findElement(By.id("login-button")).click();

// ❌ Test #2 - SAME locators duplicated!
driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
driver.findElement(By.id("password")).sendKeys("secret_sauce");
driver.findElement(By.id("login-button")).click();

// ❌ Test #3, #4, #5... the nightmare continues
```

**Problems:**
1. **Duplication**: Same locator appears in every test
2. **Fragility**: One ID change breaks many tests
3. **Unreadable**: What does `By.id("login-button")` actually do?
4. **No abstraction**: Tests know too much about HTML structure

### 📚 The POM Solution

```java
// ✅ LoginPage encapsulates all login logic
public class LoginPage extends BasePage {
    // Locators in ONE place
    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BTN = By.id("login-button");

    public InventoryPage loginAs(String username, String password) {
        type(USERNAME, username);
        type(PASSWORD, password);
        click(LOGIN_BTN);
        return new InventoryPage(driver);
    }
}

// ✅ Test reads like requirements!
@Test
void shouldLoginSuccessfully() {
    InventoryPage inventory = loginPage.loginAs("standard_user", "secret_sauce");
    assertThat(inventory.getProductCount()).isEqualTo(6);
}
```

### 💡 Mental Model: The Restaurant Menu

Think of Page Objects like a restaurant menu:
- **Without POM**: You walk into the kitchen and tell the chef every step: "Get flour, add water, knead dough, add sauce, add cheese, bake at 450°"
- **With POM**: You order "One pepperoni pizza please" and the kitchen handles the details

The menu (page object) **abstracts** the complexity. You don't need to know HOW it works.

### ✋ Self-Check Questions

<details>
<summary>1. If a locator changes, how many files should you need to update in a well-designed POM?</summary>

**Answer:** Exactly 1 file—the page object that contains that locator. Tests should never directly reference locators.
</details>

<details>
<summary>2. Should a page object method return a WebElement? Why or why not?</summary>

**Answer:** Generally NO. Returning WebElements exposes implementation details to tests. Return meaningful values (String, int, boolean) or other page objects instead. Exception: Sometimes for complex dynamic scenarios, but wrap it carefully.
</details>

<details>
<summary>3. What's the Single Responsibility of a LoginPage?</summary>

**Answer:** Handle all interactions related to the login page—entering credentials, clicking login, reading error messages. NOT verifying login success (that's the test's job) or navigating to other unrelated pages.
</details>

---

## 📖 Topic 2.2: The BasePage Pattern

### 🤔 Why This Matters

> **Real-world scenario:** Every page needs to wait for elements, click buttons, and type text. Without BasePage, you duplicate wait logic 50 times. With BasePage, you write it once and inherit everywhere.

### 📚 The Concept

BasePage is an **abstract class** that contains common functionality all page objects share:

```
┌─────────────────────────────────────────────────────────────────┐
│                       BasePage (abstract)                       │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │  • WebDriver reference                                    │  │
│  │  • WebDriverWait instance                                 │  │
│  │  • Common methods: click(), type(), getText(), isVisible()│  │
│  │  • Utility methods: takeScreenshot(), scrollTo()          │  │
│  └───────────────────────────────────────────────────────────┘  │
└────────────────────────────────────┬────────────────────────────┘
                                     │ extends
            ┌────────────────────────┼────────────────────────┐
            │                        │                        │
            ▼                        ▼                        ▼
    ┌───────────────┐       ┌───────────────┐       ┌───────────────┐
    │   LoginPage   │       │ InventoryPage │       │   CartPage    │
    │               │       │               │       │               │
    │ • login()     │       │ • addToCart() │       │ • checkout()  │
    │ • getError()  │       │ • getProducts│       │ • getTotal()  │
    └───────────────┘       └───────────────┘       └───────────────┘
```

### 📚 BasePage Implementation

```java
public abstract class BasePage {
    // Protected = accessible by child classes
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    // Constructor injection of WebDriver
    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ─── Common Actions ────────────────────────────────

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
        );
        element.clear();  // Always clear first!
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText();
    }

    protected boolean isVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // ─── Page State ────────────────────────────────────

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected String getTitle() {
        return driver.getTitle();
    }
}
```

### 💡 Key Design Decisions

| Decision | Why |
|----------|-----|
| `abstract class` | Can't instantiate BasePage directly—it's incomplete |
| `protected` fields | Child classes need access, but not the outside world |
| `final` driver/wait | Prevents accidental reassignment |
| Constructor requires driver | No page object without a driver (enforced dependency) |
| Methods have waits built-in | Reduces flakiness, child classes don't think about timing |

### ✋ Self-Check Questions

<details>
<summary>1. Why is BasePage abstract?</summary>

**Answer:** You never create "a base page"—it's incomplete and meaningless. You always create specific pages (LoginPage, CartPage). Abstract prevents: `new BasePage(driver);`
</details>

<details>
<summary>2. Why use `protected` instead of `public` for methods like click()?</summary>

**Answer:** `click(By locator)` takes a raw locator—we don't want tests calling this directly. Only page objects should use these building blocks. Tests call higher-level methods like `loginPage.login()`.
</details>

<details>
<summary>3. What happens if you forget to call `element.clear()` before `sendKeys()`?</summary>

**Answer:** Text gets appended to existing content! If field had "old" and you send "new", you get "oldnew". Always clear first.
</details>

---

## 📖 Topic 2.3: Fluent Interface Design

### 🤔 Why This Matters

> **Real-world scenario:** Compare these two test styles:
> - **Without fluent**: 5 lines of code, hard to follow
> - **With fluent**: 1 statement, reads like English

### 📚 The Concept

Fluent interface = **method chaining** where each method returns an object (often `this` or another page).

```java
// ❌ Non-fluent: Many separate statements
loginPage.open();
loginPage.enterUsername("standard_user");
loginPage.enterPassword("secret_sauce");
InventoryPage inventory = loginPage.clickLogin();
inventory.addToCart("Sauce Labs Backpack");
CartPage cart = inventory.openCart();

// ✅ Fluent: Chained, readable
new LoginPage(driver)
    .open()
    .loginAs("standard_user", "secret_sauce")
    .addToCart("Sauce Labs Backpack")
    .openCart();
```

### 📚 Implementing Fluent Methods

```java
public class LoginPage extends BasePage {

    // Returns 'this' for chaining on same page
    public LoginPage open() {
        driver.get("https://www.saucedemo.com");
        return this;  // ← Enables chaining
    }

    public LoginPage enterUsername(String username) {
        type(USERNAME_FIELD, username);
        return this;  // ← Enables chaining
    }

    public LoginPage enterPassword(String password) {
        type(PASSWORD_FIELD, password);
        return this;  // ← Enables chaining
    }

    // Returns new page when navigation happens
    public InventoryPage clickLogin() {
        click(LOGIN_BUTTON);
        return new InventoryPage(driver);  // ← Page transition
    }

    // Convenience method combining multiple steps
    public InventoryPage loginAs(String username, String password) {
        return enterUsername(username)
            .enterPassword(password)
            .clickLogin();
    }
}
```

### 💡 The Rule: Return What Makes Sense

| Action | Return Type | Why |
|--------|-------------|-----|
| Stay on same page | `return this;` | More actions possible on this page |
| Navigate to new page | `return new OtherPage(driver);` | Test continues on new page |
| Get data | `return String/int/List` | Test needs the value |
| Verify state | `return boolean` | Test makes decisions |

### ✋ Self-Check Questions

<details>
<summary>1. What should `addToCart(productName)` return if you stay on the same page?</summary>

**Answer:** `return this;` (the same InventoryPage) so you can chain more actions like `.addToCart("item2").addToCart("item3")`
</details>

<details>
<summary>2. What should `clickCheckout()` return?</summary>

**Answer:** `return new CheckoutPage(driver);` because clicking checkout navigates to a different page.
</details>

---

## 🔨 Worked Example: Complete LoginPage

```java
package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // ─── Locators (private, static, final) ────────────
    private static final By USERNAME_FIELD = By.id("user-name");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By ERROR_MESSAGE = By.cssSelector("[data-test='error']");

    // ─── Page URL ─────────────────────────────────────
    private static final String URL = "https://www.saucedemo.com";

    // ─── Constructor ──────────────────────────────────
    public LoginPage(WebDriver driver) {
        super(driver);  // Pass driver to BasePage
    }

    // ─── Navigation ───────────────────────────────────
    public LoginPage open() {
        driver.get(URL);
        return this;
    }

    // ─── Actions (fluent) ─────────────────────────────
    public LoginPage enterUsername(String username) {
        type(USERNAME_FIELD, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(PASSWORD_FIELD, password);
        return this;
    }

    public InventoryPage clickLogin() {
        click(LOGIN_BUTTON);
        return new InventoryPage(driver);
    }

    // ─── Convenience Methods ──────────────────────────
    public InventoryPage loginAs(String username, String password) {
        return enterUsername(username)
                .enterPassword(password)
                .clickLogin();
    }

    public LoginPage attemptInvalidLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        click(LOGIN_BUTTON);
        return this;  // Stay on login page for error checking
    }

    // ─── State Verification ───────────────────────────
    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    public boolean isErrorDisplayed() {
        return isVisible(ERROR_MESSAGE);
    }
}
```

### 📝 Design Patterns Used

| Pattern | Where | Why |
|---------|-------|-----|
| **Encapsulation** | Private locators | Hide implementation from tests |
| **Fluent Interface** | Return `this` | Enable method chaining |
| **Factory-ish** | Return new pages | Create next page in flow |
| **Single Responsibility** | One class = one page | Maintainability |

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: Exposing locators publicly

```java
// ❌ BAD: Tests can use locators directly
public class LoginPage {
    public static final By USERNAME = By.id("user-name");  // PUBLIC!
}

// Test bypasses the page object:
driver.findElement(LoginPage.USERNAME).sendKeys("user");

// ✅ GOOD: Locators are private
private static final By USERNAME = By.id("user-name");
```

### Mistake 2: Returning WebElements

```java
// ❌ BAD: Exposes implementation
public WebElement getLoginButton() {
    return driver.findElement(LOGIN_BUTTON);
}

// ✅ GOOD: Return meaningful data
public boolean isLoginButtonEnabled() {
    return driver.findElement(LOGIN_BUTTON).isEnabled();
}
```

### Mistake 3: Assertions in page objects

```java
// ❌ BAD: Page object makes assertions
public void verifyLoginSuccessful() {
    assertThat(driver.getCurrentUrl()).contains("inventory");
}

// ✅ GOOD: Page object returns data, test asserts
public String getCurrentUrl() {
    return driver.getCurrentUrl();
}

// In test:
assertThat(loginPage.getCurrentUrl()).contains("inventory");
```

---

## 🎯 Challenge 2.1: Create LoginPage Object (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Refactor Challenge 1.2 tests to use Page Object Model.

### Requirements
- Create `LoginPage` class extending `BasePage`
- Encapsulate all locators as private constants
- Implement: `open()`, `loginAs()`, `getErrorMessage()`, `isErrorDisplayed()`
- Return `InventoryPage` from successful login

### Acceptance Criteria
- [ ] No `driver.findElement()` calls in test class
- [ ] Fluent interface design (method chaining)
- [ ] Private locators, public methods
- [ ] Page objects handle waits internally

### Hints

<details>
<summary>Hint 1: Class structure</summary>

```java
public class LoginPage extends BasePage {
    // Private locators
    // Constructor taking WebDriver
    // Public methods returning this or other pages
}
```
</details>

<details>
<summary>Hint 2: Test should look like this</summary>

```java
@Test
void shouldLoginSuccessfully() {
    InventoryPage inventory = new LoginPage(driver)
        .open()
        .loginAs("standard_user", "secret_sauce");

    assertThat(inventory.isLoaded()).isTrue();
}
```
</details>

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 2.2: Create InventoryPage Object (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Build page object for the product inventory page.

### Requirements
- Implement: `getProductCount()`, `getProductNames()`, `addToCart(productName)`, `openCart()`
- Create inner record `Product(String name, String price, String description)`
- Handle dynamic product list with streams

### Acceptance Criteria
- [ ] Clean API for product interactions
- [ ] No exposed WebElements (return Strings, ints, Products)
- [ ] Proper use of collections and streams

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 2.3: Complete Shopping Flow (Advanced)

### Scaffolding Level: 🔴 Independent

**Scenario:** Create full e-commerce page object hierarchy.

### Requirements
- Create: `LoginPage` → `InventoryPage` → `CartPage` → `CheckoutPage` → `ConfirmationPage`
- Implement complete purchase flow test
- Each page returns next page in flow

### Acceptance Criteria
- [ ] Complete page hierarchy (5 pages)
- [ ] All pages extend BasePage
- [ ] Test reads like: `login().addToCart().checkout().complete()`
- [ ] No test code references any locators

### The Test Should Look Like This

```java
@Test
void shouldCompletePurchase() {
    ConfirmationPage confirmation = new LoginPage(driver)
        .open()
        .loginAs("standard_user", "secret_sauce")
        .addToCart("Sauce Labs Backpack")
        .openCart()
        .checkout()
        .fillInfo("John", "Doe", "12345")
        .finishCheckout();

    assertThat(confirmation.isOrderComplete()).isTrue();
    assertThat(confirmation.getThankYouMessage()).contains("Thank you");
}
```

### ⏱️ Time: 90-120 minutes

---

## 🔄 Module 2 Reflection

After completing all challenges:

1. **Compare your Challenge 1.2 code to Challenge 2.1.** What changed? What improved?

2. **If a developer changes the login button ID**, how many files would you need to update?

3. **Explain POM to someone who's never heard of it.** Use the restaurant menu analogy or create your own.

4. **Rate your confidence (1-5):**
   - [ ] Creating BasePage with common methods
   - [ ] Designing fluent interfaces
   - [ ] Knowing when to return `this` vs new page
   - [ ] Keeping locators private

---

## 🔗 Spaced Repetition Checkpoint

**Review Module 1 concepts:**
- Can you still list the 6 locator strategies?
- Explain WebDriver architecture without looking

**Revisit Module 2 in:**
- **Week 2**: Review your page objects—would you design them differently now?
- **Week 3**: Refactor to use more advanced patterns (Factory, Builder)

---




# Module 3: Advanced Locators & Synchronization

## 🎯 Learning Objectives (Bloom's Taxonomy)

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List XPath axes and their purposes | Self-check quiz |
| **Understand** | Explain why Thread.sleep() causes flaky tests | Written explanation |
| **Apply** | Write complex XPath and CSS selectors | Challenge 3.1 |
| **Analyze** | Compare wait strategies for different scenarios | Decision matrix |
| **Evaluate** | Debug a flaky test caused by timing issues | Challenge 3.2 |
| **Create** | Design a synchronization strategy for AJAX apps | Challenge 3.3 |

---

## 📖 Topic 3.1: Advanced XPath Strategies

### 🤔 Why This Matters

> **Real-world scenario:** The developer creates a button with no ID, no unique class, nested inside three divs. CSS selectors can't reach it. XPath axes let you navigate: "Find the button that's a sibling of the element containing 'Submit Order' text."

XPath is your **escape hatch** when clean locators don't exist.

### 📚 The XPath Axes Map

```
                              ancestor::
                                  ↑
                                  │
                           parent::│
                                  ↑
                                  │
preceding-sibling:: ←─────── [ELEMENT] ───────→ following-sibling::
                                  │
                                  ↓
                             child::
                                  │
                                  ↓
                           descendant::
```

### 📚 XPath Axes Reference

| Axis | Meaning | Example |
|------|---------|---------|
| `ancestor::` | Any parent up the tree | `//span[text()='Error']/ancestor::form` |
| `parent::` | Direct parent only | `//input[@id='email']/parent::div` |
| `child::` | Direct children only | `//ul/child::li` |
| `descendant::` | All nested elements | `//div[@class='container']/descendant::button` |
| `following-sibling::` | Siblings after | `//label[text()='Email']/following-sibling::input` |
| `preceding-sibling::` | Siblings before | `//button[@id='submit']/preceding-sibling::input` |
| `following::` | Everything after in document | Rarely used |
| `preceding::` | Everything before in document | Rarely used |

### 📚 XPath Functions Cheat Sheet

```xpath
# Text matching
//button[text()='Submit']                    # Exact text
//button[contains(text(), 'Submit')]         # Contains text
//button[starts-with(text(), 'Sub')]         # Starts with
//button[normalize-space()='Submit']         # Trim whitespace

# Attribute matching
//div[@class='active']                       # Exact class (CAREFUL!)
//div[contains(@class, 'active')]            # Contains class (SAFER)
//input[@type='text' and @name='user']       # Multiple conditions
//input[@type='text' or @type='email']       # Either condition
//input[not(@disabled)]                      # Negation

# Position-based
(//button)[1]                                # First button in document
(//button)[last()]                           # Last button
//ul/li[position() <= 3]                     # First 3 items
//table//tr[2]/td[3]                         # Row 2, Column 3
```

### 💡 Mental Model: Family Tree

Think of XPath axes like navigating a family tree:
- **ancestor** = grandparents, great-grandparents...
- **parent** = mom or dad only
- **child** = your kids only
- **descendant** = kids, grandkids, great-grandkids...
- **sibling** = brothers and sisters

### ✋ Self-Check Questions

<details>
<summary>1. Why is `[@class='btn']` risky but `[contains(@class, 'btn')]` safer?</summary>

**Answer:** HTML elements often have multiple classes: `class="btn btn-primary active"`. The exact match `[@class='btn']` fails because the class attribute isn't exactly "btn". `contains()` finds "btn" within the full string.
</details>

<details>
<summary>2. Write XPath to find the input field that comes after a label with text "Password"</summary>

**Answer:** `//label[text()='Password']/following-sibling::input` or `//label[contains(text(),'Password')]/following-sibling::input[1]`
</details>

<details>
<summary>3. When would you use `ancestor::` axis?</summary>

**Answer:** When you find an element but need its container. Example: You find an error message, but need to highlight the parent form: `//span[@class='error']/ancestor::form`
</details>

---

## 📖 Topic 3.2: CSS Selectors vs XPath

### 🤔 Why This Matters

> **Real-world scenario:** Your test runs 1000 times per day. Using CSS selectors instead of XPath can save milliseconds per lookup—which adds up to minutes over thousands of tests.

### 📚 Head-to-Head Comparison

| Feature | CSS Selector | XPath |
|---------|--------------|-------|
| **Speed** | ⚡ Faster | Slower |
| **Text matching** | ❌ Not supported | ✅ `text()='Submit'` |
| **Traverse up** | ❌ Can't go to parent | ✅ `parent::`, `ancestor::` |
| **Readability** | ✅ Often cleaner | Complex syntax |
| **Browser support** | ✅ Native | Via JavaScript |

### 📚 CSS Selector Patterns

```css
/* Basic selectors */
#login-button              /* ID */
.btn-primary               /* Class */
input[type='email']        /* Attribute */

/* Combinators */
div.container > p          /* Direct child */
div.container p            /* Any descendant */
h2 + p                     /* Adjacent sibling (immediately after) */
h2 ~ p                     /* General sibling (any after) */

/* Pseudo-selectors */
li:first-child             /* First child */
li:last-child              /* Last child */
li:nth-child(2)            /* Second child */
li:nth-child(odd)          /* Odd children */
input:not([disabled])      /* Negation */

/* Attribute patterns */
a[href^='https']           /* Starts with */
a[href$='.pdf']            /* Ends with */
a[href*='download']        /* Contains */
```

### 💡 Decision Tree: CSS or XPath?

```
                    ┌─────────────────────┐
                    │ Need to match text? │
                    └──────────┬──────────┘
                               │
              ┌────────────────┴────────────────┐
              │ YES                             │ NO
              ▼                                 ▼
      ┌───────────────┐               ┌─────────────────────┐
      │ Use XPath     │               │ Need to go UP the   │
      │ text()='...'  │               │ DOM (to parent)?    │
      └───────────────┘               └──────────┬──────────┘
                                                 │
                                  ┌──────────────┴──────────────┐
                                  │ YES                         │ NO
                                  ▼                             ▼
                          ┌───────────────┐           ┌─────────────────┐
                          │ Use XPath     │           │ Use CSS         │
                          │ ancestor::    │           │ (faster)        │
                          └───────────────┘           └─────────────────┘
```

### ✋ Self-Check Questions

<details>
<summary>1. Convert this XPath to CSS: `//input[@type='text']`</summary>

**Answer:** `input[type='text']`
</details>

<details>
<summary>2. Can you convert this XPath to CSS? `//span[text()='Error']/parent::div`</summary>

**Answer:** **No!** CSS cannot match by text content and cannot traverse upward to parents. This requires XPath.
</details>

---

## 📖 Topic 3.3: Wait Strategies Deep Dive

### 🤔 Why This Matters

> **Real-world scenario:** Test passes locally, fails in CI. Why? Your machine is fast, CI is slower. The button wasn't clickable yet when Selenium tried to click it. Proper waits = reliable tests.

### 📚 The Three Types of Waits

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                           IMPLICIT WAIT                                      │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │  Set ONCE, applies to ALL findElement calls                          │    │
│  │  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  │    │
│  │                                                                       │    │
│  │  ⚠️ Problems:                                                        │    │
│  │  • Slows down negative tests (waiting for element NOT to exist)      │    │
│  │  • Mixes poorly with explicit waits                                   │    │
│  │  • Hidden behavior—not obvious in code                                │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│                           EXPLICIT WAIT (Recommended)                        │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │  Wait for SPECIFIC condition on SPECIFIC element                      │    │
│  │                                                                       │    │
│  │  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));│    │
│  │  wait.until(ExpectedConditions.elementToBeClickable(locator));        │    │
│  │                                                                       │    │
│  │  ✅ Benefits:                                                         │    │
│  │  • Explicit in code—clear what you're waiting for                     │    │
│  │  • Returns immediately when condition met                             │    │
│  │  • Many built-in conditions available                                 │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│                           FLUENT WAIT (Configurable)                         │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │  Most flexible—configure polling, timeout, ignored exceptions         │    │
│  │                                                                       │    │
│  │  Wait<WebDriver> wait = new FluentWait<>(driver)                      │    │
│  │      .withTimeout(Duration.ofSeconds(30))                             │    │
│  │      .pollingEvery(Duration.ofMillis(500))                            │    │
│  │      .ignoring(NoSuchElementException.class)                          │    │
│  │      .ignoring(StaleElementReferenceException.class);                 │    │
│  │                                                                       │    │
│  │  ✅ Use when: Custom polling intervals, multiple ignored exceptions   │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 📚 ExpectedConditions Reference

```java
// Visibility conditions
ExpectedConditions.visibilityOfElementLocated(locator)    // Present AND visible
ExpectedConditions.invisibilityOfElementLocated(locator)  // Gone or hidden
ExpectedConditions.presenceOfElementLocated(locator)      // In DOM (may be hidden)

// Interactivity conditions
ExpectedConditions.elementToBeClickable(locator)          // Visible AND enabled
ExpectedConditions.elementToBeSelected(locator)           // Checkbox/radio checked

// Text conditions
ExpectedConditions.textToBePresentInElementLocated(locator, "text")
ExpectedConditions.textToBe(locator, "exact text")

// URL/Title conditions
ExpectedConditions.urlContains("inventory")
ExpectedConditions.titleIs("Swag Labs")

// Multiple elements
ExpectedConditions.numberOfElementsToBe(locator, 6)
ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0)

// Frame/Alert conditions
ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator)
ExpectedConditions.alertIsPresent()
```

### 💡 Mental Model: Waiting at a Restaurant

- **Thread.sleep()** = "I'll come back in exactly 10 minutes" (might be too early or too late)
- **Implicit Wait** = "Check every few seconds if my table is ready" (but you check EVERYTHING this way)
- **Explicit Wait** = "Tell me specifically when TABLE 5 is ready" (targeted, efficient)
- **Fluent Wait** = "Check every 30 seconds, ignore the 'kitchen busy' messages, timeout after 10 minutes"

### ✋ Self-Check Questions

<details>
<summary>1. Why is Thread.sleep(5000) bad?</summary>

**Answer:**
1. Always waits full 5 seconds even if element appears in 0.5 seconds
2. If element takes 6 seconds, test fails anyway
3. Wastes time in fast cases, unreliable in slow cases
</details>

<details>
<summary>2. What's the difference between `visibilityOfElementLocated` and `presenceOfElementLocated`?</summary>

**Answer:**
- `presenceOfElementLocated`: Element exists in DOM (could be hidden with `display:none`)
- `visibilityOfElementLocated`: Element exists AND is visible (has width/height, not hidden)
</details>

<details>
<summary>3. When would you use Fluent Wait instead of WebDriverWait?</summary>

**Answer:** When you need custom polling intervals (e.g., check every 2 seconds instead of default 500ms) or need to ignore multiple exception types during waiting.
</details>

---

## 🔨 Worked Example: Custom Wait Utility

```java
public class WaitUtils {
    private final WebDriver driver;
    private final Duration defaultTimeout;

    public WaitUtils(WebDriver driver) {
        this(driver, Duration.ofSeconds(10));
    }

    public WaitUtils(WebDriver driver, Duration defaultTimeout) {
        this.driver = driver;
        this.defaultTimeout = defaultTimeout;
    }

    // ─── Standard Waits ────────────────────────────────────

    public WebElement waitForVisible(By locator) {
        return new WebDriverWait(driver, defaultTimeout)
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForClickable(By locator) {
        return new WebDriverWait(driver, defaultTimeout)
            .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForInvisible(By locator) {
        return new WebDriverWait(driver, defaultTimeout)
            .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // ─── Custom Conditions ─────────────────────────────────

    public void waitForTextToBe(By locator, String expectedText) {
        new WebDriverWait(driver, defaultTimeout)
            .until(ExpectedConditions.textToBe(locator, expectedText));
    }

    public List<WebElement> waitForMinimumElements(By locator, int minCount) {
        return new WebDriverWait(driver, defaultTimeout)
            .until(driver -> {
                List<WebElement> elements = driver.findElements(locator);
                return elements.size() >= minCount ? elements : null;
            });
    }

    // ─── Retry with Stale Element Handling ─────────────────

    public <T> T retryOnStale(Supplier<T> action, int maxAttempts) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return action.get();
            } catch (StaleElementReferenceException e) {
                if (attempt == maxAttempts) throw e;
                // Brief pause before retry
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        }
        throw new RuntimeException("Retry exhausted");
    }
}
```

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: Mixing implicit and explicit waits

```java
// ❌ BAD: Unpredictable behavior!
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
new WebDriverWait(driver, Duration.ofSeconds(5))  // Which timeout applies?
    .until(ExpectedConditions.visibilityOfElementLocated(locator));

// ✅ GOOD: Use explicit waits only
// Don't set implicit wait at all
new WebDriverWait(driver, Duration.ofSeconds(10))
    .until(ExpectedConditions.visibilityOfElementLocated(locator));
```

### Mistake 2: Not handling StaleElementReferenceException

```java
// ❌ BAD: Element can go stale between find and action
WebElement button = driver.findElement(By.id("submit"));
// ... page updates via AJAX ...
button.click();  // StaleElementReferenceException!

// ✅ GOOD: Re-find or use fluent wait
new FluentWait<>(driver)
    .withTimeout(Duration.ofSeconds(10))
    .ignoring(StaleElementReferenceException.class)
    .until(driver -> {
        driver.findElement(By.id("submit")).click();
        return true;
    });
```

### Mistake 3: Wrong ExpectedCondition

```java
// ❌ BAD: presenceOf doesn't ensure visible/clickable
wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btn")));
driver.findElement(By.id("btn")).click();  // May fail if hidden!

// ✅ GOOD: Wait for clickable
wait.until(ExpectedConditions.elementToBeClickable(By.id("btn"))).click();
```

---

## 🎯 Challenge 3.1: Dynamic Table Handling (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Automate a table with dynamic data.

**Site:** https://the-internet.herokuapp.com/tables

### Requirements
- Extract all data from both tables into Java objects
- Sort by column and verify order
- Find specific cell by row/column combination
- Create reusable `TableHelper` class

### Acceptance Criteria
- [ ] Uses XPath axes for cell navigation
- [ ] Handles both table structures
- [ ] Generic methods work with any table
- [ ] No hardcoded row/column indices

### Hints

<details>
<summary>Hint 1: XPath for table cells</summary>

```xpath
//table[@id='table1']//tr/td  <!-- All cells -->
//table[@id='table1']//tr[2]/td[3]  <!-- Row 2, Column 3 -->
//table[@id='table1']//th[text()='Last Name']/ancestor::table//tr/td[1]  <!-- All values in column by header -->
```
</details>

<details>
<summary>Hint 2: TableHelper structure</summary>

```java
public class TableHelper {
    private WebElement table;

    public List<String> getColumnValues(String headerName) { }
    public String getCellValue(int row, int col) { }
    public void clickSortHeader(String headerName) { }
}
```
</details>

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 3.2: Wait Strategy Implementation (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Handle various loading patterns.

**Site:** https://the-internet.herokuapp.com/dynamic_loading

### Requirements
- Complete both dynamic loading examples (hidden element, element rendered after)
- Implement custom wait conditions
- Create `WaitUtils` class with reusable waits
- Handle StaleElementReferenceException

### Acceptance Criteria
- [ ] ZERO `Thread.sleep()` usage
- [ ] Custom ExpectedCondition for "loading spinner gone"
- [ ] Retry logic for stale elements
- [ ] Configurable timeout values

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 3.3: Complex Element Interactions (Advanced)

### Scaffolding Level: 🔴 Independent

**Scenario:** Handle iframes, alerts, and advanced interactions.

### Sites
- https://the-internet.herokuapp.com/iframe
- https://the-internet.herokuapp.com/javascript_alerts
- https://the-internet.herokuapp.com/drag_and_drop

### Requirements
- Switch into/out of iframes, type in WYSIWYG editor
- Handle all 3 alert types: alert, confirm, prompt
- Perform drag-and-drop with Actions class
- Create clean utility methods for each

### Acceptance Criteria
- [ ] `IFrameHelper.switchTo()` and `switchBack()` methods
- [ ] `AlertHelper.accept()`, `dismiss()`, `sendText()` methods
- [ ] Drag-and-drop working reliably
- [ ] All actions wrapped in proper waits

### ⏱️ Time: 90-120 minutes

---

## 🔄 Module 3 Reflection

1. **What's your go-to locator strategy now?** Has it changed from Module 1?

2. **Describe a scenario** where you'd need XPath over CSS.

3. **Draw the wait decision tree** from memory. Check your answer.

4. **Rate your confidence (1-5):**
   - [ ] Writing complex XPath with axes
   - [ ] Choosing between CSS and XPath
   - [ ] Implementing explicit waits correctly
   - [ ] Handling StaleElementReferenceException

---

## 🔗 Spaced Repetition Checkpoint

**Review earlier modules:**
- Write a page object method with built-in waits (Modules 2 + 3)
- Which lifecycle annotation for browser cleanup? (@AfterEach)

**Revisit Module 3 in:**
- **Week 2**: Refactor your page objects to use WaitUtils
- **Week 3**: Add custom ExpectedConditions to your framework

---



# Module 4: Java Fundamentals for SDETs

## 🎯 Learning Objectives (Bloom's Taxonomy)

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List the 4 OOP principles | Self-check quiz |
| **Understand** | Explain when to use List vs Set vs Map | Decision matrix |
| **Apply** | Process collections using Stream API | Challenge 4.1 |
| **Analyze** | Identify code that violates OOP principles | Code review |
| **Evaluate** | Choose appropriate exception handling strategy | Challenge 4.2 |
| **Create** | Design thread-safe data generation | Challenge 4.3 |

---

## 📖 Topic 4.1: OOP Principles in Test Automation

### 🤔 Why This Matters

> **Real-world scenario:** You join a team with 500 test classes. Without OOP, each class has copy-pasted code everywhere. One change to login flow requires updating 50 files. With OOP, you update 1 base class.

### 📚 The Four Pillars Applied

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                            ENCAPSULATION                                     │
│  "Hide the HOW, expose the WHAT"                                            │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │  ❌ Test knows:  driver.findElement(By.id("user")).sendKeys("bob")  │    │
│  │  ✅ Test knows:  loginPage.enterUsername("bob")                      │    │
│  │                                                                       │    │
│  │  The locator is ENCAPSULATED inside LoginPage                        │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│                            INHERITANCE                                       │
│  "Don't repeat yourself—inherit common behavior"                             │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │                    BasePage                                          │    │
│  │         click() | type() | waitForVisible()                          │    │
│  │                      ▲                                               │    │
│  │       ┌──────────────┼──────────────┐                                │    │
│  │       │              │              │                                │    │
│  │   LoginPage    InventoryPage    CartPage                             │    │
│  │                                                                       │    │
│  │  All pages INHERIT common methods from BasePage                      │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│                            POLYMORPHISM                                      │
│  "Same interface, different implementations"                                 │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │  interface Browser {                                                  │    │
│  │      void open(String url);                                          │    │
│  │  }                                                                    │    │
│  │                                                                       │    │
│  │  class ChromeBrowser implements Browser { ... }                       │    │
│  │  class FirefoxBrowser implements Browser { ... }                      │    │
│  │                                                                       │    │
│  │  // Test doesn't care which browser—same interface!                   │    │
│  │  Browser browser = BrowserFactory.create("chrome");                   │    │
│  │  browser.open("https://example.com");                                 │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│                            ABSTRACTION                                       │
│  "Simplify complex operations behind simple interfaces"                      │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │  // Complex Selenium operations ABSTRACTED away:                      │    │
│  │  public void login(String user, String pass) {                        │    │
│  │      wait.until(visible(USERNAME)).clear();                           │    │
│  │      wait.until(visible(USERNAME)).sendKeys(user);                    │    │
│  │      wait.until(visible(PASSWORD)).clear();                           │    │
│  │      wait.until(visible(PASSWORD)).sendKeys(pass);                    │    │
│  │      wait.until(clickable(LOGIN_BTN)).click();                        │    │
│  │      wait.until(urlContains("inventory"));                            │    │
│  │  }                                                                    │    │
│  │                                                                       │    │
│  │  // Test just sees the ABSTRACTION:                                   │    │
│  │  loginPage.login("standard_user", "secret_sauce");                    │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘
```

### ✋ Self-Check Questions

<details>
<summary>1. Which OOP principle is violated when a test directly calls `driver.findElement()`?</summary>

**Answer:** **Encapsulation**. The test knows about implementation details (locators, WebDriver). It should only know about business methods like `loginPage.login()`.
</details>

<details>
<summary>2. Why is `BasePage` typically abstract?</summary>

**Answer:** You never create "a base page"—it's incomplete. It exists only to provide common functionality to real pages. `abstract` prevents `new BasePage()`.
</details>

<details>
<summary>3. Give an example of polymorphism in test automation.</summary>

**Answer:**
- `WebDriver` interface implemented by `ChromeDriver`, `FirefoxDriver`, `EdgeDriver`
- `ExpectedCondition<T>` with many implementations
- Custom `TestDataProvider` interface with `JsonDataProvider`, `CsvDataProvider`
</details>

---

## 📖 Topic 4.2: Java Collections for Testing

### 🤔 Why This Matters

> **Real-world scenario:** You need to verify 100 products have unique names, are sorted by price, and grouped by category. Without understanding collections, you write 50 lines of nested loops. With collections + streams, it's 5 lines.

### 📚 Collection Decision Tree

```
                    ┌─────────────────────────┐
                    │ What do you need?       │
                    └────────────┬────────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│ Ordered         │    │ Unique values   │    │ Key-Value       │
│ sequence?       │    │ only?           │    │ lookup?         │
└────────┬────────┘    └────────┬────────┘    └────────┬────────┘
         │                      │                      │
         ▼                      ▼                      ▼
    ┌─────────┐           ┌─────────┐           ┌─────────┐
    │  LIST   │           │   SET   │           │   MAP   │
    └────┬────┘           └────┬────┘           └────┬────┘
         │                     │                     │
    ┌────┴────┐           ┌────┴────┐           ┌────┴────┐
    │ArrayList│           │ HashSet │           │ HashMap │
    │LinkedList│          │ TreeSet │           │ TreeMap │
    └─────────┘           │LinkedSet│           │LinkedMap│
                          └─────────┘           └─────────┘
```

### 📚 When to Use Each

| Collection | Use Case in Testing | Example |
|------------|---------------------|---------|
| `ArrayList` | Store products, test steps, results | `List<Product> products` |
| `LinkedList` | Queue of actions, frequent add/remove | `Queue<TestStep> steps` |
| `HashSet` | Verify uniqueness, remove duplicates | `Set<String> uniqueNames` |
| `TreeSet` | Sorted unique values | Sorted categories |
| `HashMap` | Test data by key, locators by name | `Map<String, By> locators` |
| `LinkedHashMap` | Preserve insertion order + key lookup | Ordered test data |

### 📚 Code Examples

```java
// ─── LIST: Ordered, allows duplicates ────────────────────
List<String> productNames = new ArrayList<>();
productNames.add("Backpack");
productNames.add("Bike Light");
productNames.add("Backpack");  // Duplicate allowed!
// Result: [Backpack, Bike Light, Backpack]

// ─── SET: Unique elements only ───────────────────────────
Set<String> uniqueNames = new HashSet<>(productNames);
// Result: {Backpack, Bike Light}  // Duplicate removed!

// ─── MAP: Key-Value pairs ────────────────────────────────
Map<String, String> users = new HashMap<>();
users.put("standard", "secret_sauce");
users.put("locked_out", "secret_sauce");
String password = users.get("standard");  // "secret_sauce"

// ─── Immutable collections (Java 9+) ─────────────────────
List<String> browsers = List.of("chrome", "firefox", "edge");
// browsers.add("safari");  // UnsupportedOperationException!

Map<String, Integer> ports = Map.of(
    "http", 80,
    "https", 443
);
```

### ✋ Self-Check Questions

<details>
<summary>1. You need to verify product names are unique. Which collection helps?</summary>

**Answer:** `Set` (HashSet). Put all names in a Set, compare size to original list. If sizes differ, there were duplicates.
```java
Set<String> unique = new HashSet<>(names);
assertThat(unique).hasSameSizeAs(names);  // Fails if duplicates exist
```
</details>

<details>
<summary>2. You need to look up test data by environment name. Which collection?</summary>

**Answer:** `Map<String, TestData>` - key is environment name, value is data.
```java
Map<String, String> urls = Map.of(
    "dev", "https://dev.example.com",
    "staging", "https://staging.example.com",
    "prod", "https://example.com"
);
String url = urls.get(System.getProperty("env", "dev"));
```
</details>

---

## 📖 Topic 4.3: Stream API for Test Data Processing

### 🤔 Why This Matters

> **Real-world scenario:** Extract all product prices, filter those over $20, calculate the average. Without streams: 15 lines of loops. With streams: 3 lines that read like English.

### 📚 Stream Operations Pipeline

```
┌──────────────┐    ┌──────────────┐    ┌──────────────┐    ┌──────────────┐
│   SOURCE     │───▶│ INTERMEDIATE │───▶│ INTERMEDIATE │───▶│  TERMINAL    │
│              │    │  OPERATION   │    │  OPERATION   │    │  OPERATION   │
│ Collection   │    │   filter()   │    │    map()     │    │  collect()   │
│   .stream()  │    │   sorted()   │    │  distinct()  │    │   toList()   │
└──────────────┘    └──────────────┘    └──────────────┘    └──────────────┘
```

### 📚 Stream Operations Reference

```java
// ─── FILTER: Keep only matching elements ─────────────────
List<Product> expensive = products.stream()
    .filter(p -> p.getPrice() > 20.0)
    .toList();

// ─── MAP: Transform each element ─────────────────────────
List<String> names = products.stream()
    .map(Product::getName)           // Method reference
    .map(String::toUpperCase)        // Chain transforms
    .toList();

// ─── SORTED: Order elements ──────────────────────────────
List<Product> byPrice = products.stream()
    .sorted(Comparator.comparing(Product::getPrice))
    .toList();

// Descending order
List<Product> byPriceDesc = products.stream()
    .sorted(Comparator.comparing(Product::getPrice).reversed())
    .toList();

// ─── DISTINCT: Remove duplicates ─────────────────────────
List<String> uniqueCategories = products.stream()
    .map(Product::getCategory)
    .distinct()
    .toList();

// ─── MATCHING: Check conditions ──────────────────────────
boolean anyExpensive = products.stream()
    .anyMatch(p -> p.getPrice() > 50);      // At least one?

boolean allInStock = products.stream()
    .allMatch(Product::isInStock);           // All of them?

boolean noneOutOfStock = products.stream()
    .noneMatch(p -> p.getStock() == 0);      // None of them?

// ─── FINDING: Get specific elements ──────────────────────
Optional<Product> first = products.stream()
    .filter(p -> p.getName().contains("Sauce"))
    .findFirst();

// ─── REDUCING: Aggregate to single value ─────────────────
double total = products.stream()
    .mapToDouble(Product::getPrice)
    .sum();

double average = products.stream()
    .mapToDouble(Product::getPrice)
    .average()
    .orElse(0.0);

// ─── GROUPING: Organize by category ──────────────────────
Map<String, List<Product>> byCategory = products.stream()
    .collect(Collectors.groupingBy(Product::getCategory));

// ─── COUNTING: Count by group ────────────────────────────
Map<String, Long> countByCategory = products.stream()
    .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
```

### 💡 Mental Model: Assembly Line

Think of streams like a factory assembly line:
- **Source** (`.stream()`) = Raw materials enter the line
- **Intermediate ops** (`.filter()`, `.map()`) = Workers transform items
- **Terminal op** (`.collect()`, `.count()`) = Final product comes off the line

Each worker (intermediate operation) does ONE thing and passes to the next.

### ✋ Self-Check Questions

<details>
<summary>1. What's wrong with this code?</summary>

```java
products.stream()
    .filter(p -> p.getPrice() > 20);
```

**Answer:** No terminal operation! Streams are lazy—nothing happens until you add `.toList()`, `.count()`, `.forEach()`, etc.
</details>

<details>
<summary>2. Convert this loop to a stream:</summary>

```java
List<String> names = new ArrayList<>();
for (Product p : products) {
    if (p.getPrice() > 20) {
        names.add(p.getName().toUpperCase());
    }
}
```

**Answer:**
```java
List<String> names = products.stream()
    .filter(p -> p.getPrice() > 20)
    .map(p -> p.getName().toUpperCase())
    .toList();
```
</details>

---

## 🔨 Worked Example: Product Processor

```java
public class ProductProcessor {
    private final List<Product> products;

    public ProductProcessor(List<Product> products) {
        this.products = List.copyOf(products);  // Immutable copy
    }

    // Filter by price range
    public List<Product> filterByPriceRange(double min, double max) {
        return products.stream()
            .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
            .toList();
    }

    // Get all names containing text
    public List<String> findNamesContaining(String text) {
        return products.stream()
            .map(Product::getName)
            .filter(name -> name.toLowerCase().contains(text.toLowerCase()))
            .toList();
    }

    // Group by price category
    public Map<String, List<Product>> groupByPriceCategory() {
        return products.stream()
            .collect(Collectors.groupingBy(this::categorize));
    }

    private String categorize(Product p) {
        if (p.getPrice() < 20) return "Budget";
        if (p.getPrice() < 40) return "Mid-range";
        return "Premium";
    }

    // Statistics
    public DoubleSummaryStatistics getPriceStatistics() {
        return products.stream()
            .mapToDouble(Product::getPrice)
            .summaryStatistics();  // min, max, avg, sum, count!
    }
}
```

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: Modifying collection while streaming

```java
// ❌ BAD: ConcurrentModificationException risk
products.stream()
    .filter(p -> p.getPrice() > 50)
    .forEach(p -> products.remove(p));

// ✅ GOOD: Create new collection
List<Product> affordable = products.stream()
    .filter(p -> p.getPrice() <= 50)
    .toList();
```

### Mistake 2: Forgetting Optional handling

```java
// ❌ BAD: NoSuchElementException if empty
Product first = products.stream()
    .filter(p -> p.getName().equals("NonExistent"))
    .findFirst()
    .get();  // Throws if empty!

// ✅ GOOD: Handle Optional properly
Product first = products.stream()
    .filter(p -> p.getName().equals("NonExistent"))
    .findFirst()
    .orElse(null);  // Or orElseThrow() with custom exception
```

### Mistake 3: Using streams for simple operations

```java
// ❌ OVERKILL: Stream for single check
boolean isEmpty = list.stream().count() == 0;

// ✅ SIMPLER: Use collection method
boolean isEmpty = list.isEmpty();
```

---

## 🎯 Challenge 4.1: Product Data Processor (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Process SauceDemo inventory using Java streams.

### Requirements
- Create `Product` record with: name, description, price, imageUrl
- Extract all products from inventory page into Product objects
- Implement filtering: by price range, by name pattern
- Sort products: by price asc/desc, by name alphabetically
- Group by price category (Budget < $20, Mid-range, Premium > $40)
- Calculate statistics: min, max, average, total price

### Acceptance Criteria
- [ ] Uses Java records (or POJOs with equals/hashCode)
- [ ] Stream operations for ALL transformations (no loops)
- [ ] Method references where appropriate (`Product::getName`)
- [ ] Returns immutable collections (`.toList()`)

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 4.2: Custom Exception Framework (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Create robust exception handling for your framework.

### Requirements
```
AutomationException (base)
├── ElementNotFoundException
│   └── includes: locator, timeout, page name
├── PageLoadException
│   └── includes: expected URL, actual URL, timeout
└── ValidationException
    └── includes: field name, expected value, actual value
```

### Acceptance Criteria
- [ ] Exception hierarchy with clear inheritance
- [ ] Informative `getMessage()` with all context
- [ ] Proper exception chaining (`cause`)
- [ ] Retry utility that catches transient exceptions

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 4.3: Parallel Test Data Generator (Advanced)

### Scaffolding Level: 🔴 Independent

**Scenario:** Generate test data concurrently for performance.

### Requirements
- Create `TestDataGenerator` using ExecutorService
- Generate 1000 users, 500 products, 200 orders concurrently
- Use CompletableFuture for async orchestration
- Measure and compare: sequential vs parallel generation time

### Acceptance Criteria
- [ ] Thread-safe with concurrent collections or synchronization
- [ ] Configurable thread pool size
- [ ] Proper shutdown of ExecutorService
- [ ] At least 2x performance improvement over sequential

### ⏱️ Time: 90-120 minutes

---

## 🔄 Module 4 Reflection

1. **Which OOP principle** do you find most useful in test automation?

2. **Write a stream pipeline** from memory that: filters, maps, and collects.

3. **Explain to a rubber duck** why we use Optional instead of null.

4. **Rate your confidence (1-5):**
   - [ ] Applying OOP principles to test code
   - [ ] Choosing the right collection type
   - [ ] Writing stream pipelines
   - [ ] Creating custom exceptions

---

## 🔗 Spaced Repetition Checkpoint

**Review earlier modules:**
- Name 3 XPath axes (Module 3)
- What does `return this;` enable in page objects? (Module 2)

**Revisit Module 4 in:**
- **Week 2**: Refactor page objects to use streams for product lists
- **Week 3**: Add custom exceptions to your framework

---



# Module 5: Test Data Management

## 🎯 Learning Objectives (Bloom's Taxonomy)

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List test data formats and their uses | Self-check quiz |
| **Understand** | Explain why hardcoded test data is problematic | Written explanation |
| **Apply** | Parse JSON/CSV/YAML files into Java objects | Challenge 5.1 |
| **Analyze** | Compare data sources for different scenarios | Decision matrix |
| **Evaluate** | Choose appropriate data generation strategy | Challenge 5.2 |
| **Create** | Design data-driven test architecture | Challenge 5.3 |

---

## 📖 Topic 5.1: Why Test Data Management Matters

### 🤔 Real-World Scenario

> **The Problem:** Your test creates user "john@test.com". Another tester runs the same test. It fails because "john@test.com" already exists. CI runs 50 parallel tests—20 fail from data collisions.

> **The Solution:** Dynamic test data generation. Each test run creates unique data: "john_a1b2c3@test.com". No collisions. No cleanup needed.

### 📚 Test Data Maturity Levels

```
┌─────────────────────────────────────────────────────────────────────────────┐
│  LEVEL 1: Hardcoded                                                          │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │  String username = "standard_user";                                  │    │
│  │  String password = "secret_sauce";                                   │    │
│  │                                                                       │    │
│  │  ❌ Problems: Can't change without code change, same data every run  │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘
                                    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│  LEVEL 2: Externalized Files                                                 │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │  TestData data = loadFromJson("users.json");                         │    │
│  │                                                                       │    │
│  │  ✅ Better: Data separate from code                                  │    │
│  │  ❌ Still: Static data, same values each run                         │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘
                                    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│  LEVEL 3: Generated Data (Faker)                                             │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │  User user = UserFactory.createRandomUser();                         │    │
│  │  // user.email = "john_8x7y@test.com"  (unique each time)            │    │
│  │                                                                       │    │
│  │  ✅ Great: Unique data, no collisions, realistic                     │    │
│  │  ✅ Bonus: Can seed for reproducibility                              │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘
                                    ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│  LEVEL 4: Parameterized Tests                                                │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │  @ParameterizedTest                                                   │    │
│  │  @CsvSource({"valid_user,pass", "invalid_user,fail"})                │    │
│  │  void testLogin(String user, String expected) { }                     │    │
│  │                                                                       │    │
│  │  ✅ Best: One test method, many scenarios from data                  │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘
```

### ✋ Self-Check Questions

<details>
<summary>1. Why is `String email = "test@example.com"` problematic in tests?</summary>

**Answer:**
1. **Data collisions**: Parallel tests or re-runs may conflict
2. **Brittleness**: If that user is deleted from DB, all tests fail
3. **Not realistic**: Doesn't test edge cases in email formats
4. **Hard to maintain**: Changing requires code changes
</details>

<details>
<summary>2. What's the advantage of seeded random data?</summary>

**Answer:** **Reproducibility**. With `new Faker(new Random(12345))`, you get the same "random" data every time. If a test fails, you can reproduce the exact data that caused the failure.
</details>

---

## 📖 Topic 5.2: Data Formats Comparison

### 📚 When to Use Each Format

| Format | Best For | Example Use Case |
|--------|----------|------------------|
| **JSON** | API payloads, complex nested data | Request/response bodies |
| **CSV** | Tabular test cases, bulk imports | Login credentials table |
| **YAML** | Configuration, readable hierarchies | Environment configs |
| **Properties** | Simple key-value settings | URLs, timeouts |

### 📚 JSON with Jackson

```java
// ─── Setup ObjectMapper ──────────────────────────────────
ObjectMapper mapper = new ObjectMapper()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .registerModule(new JavaTimeModule());

// ─── Read Single Object ──────────────────────────────────
User user = mapper.readValue(
    new File("src/test/resources/data/user.json"),
    User.class
);

// ─── Read List of Objects ────────────────────────────────
List<User> users = mapper.readValue(
    new File("src/test/resources/data/users.json"),
    new TypeReference<List<User>>() {}
);

// ─── Read from Classpath Resource ────────────────────────
User user = mapper.readValue(
    getClass().getResourceAsStream("/data/user.json"),
    User.class
);

// ─── Write Object to JSON ────────────────────────────────
mapper.writerWithDefaultPrettyPrinter()
    .writeValue(new File("output.json"), user);
```

### 📚 CSV with OpenCSV

```java
// ─── Read CSV to List of Objects ─────────────────────────
try (Reader reader = new FileReader("testdata/users.csv")) {
    List<User> users = new CsvToBeanBuilder<User>(reader)
        .withType(User.class)
        .build()
        .parse();
}

// ─── User class with CSV annotations ─────────────────────
public class User {
    @CsvBindByName(column = "username")
    private String username;

    @CsvBindByName(column = "password")
    private String password;

    @CsvBindByName(column = "expected_result")
    private boolean expectedResult;
}
```

### 📚 YAML Configuration

```yaml
# config/environments.yml
environments:
  dev:
    baseUrl: https://dev.saucedemo.com
    timeout: 30
    headless: true
  staging:
    baseUrl: https://staging.saucedemo.com
    timeout: 45
    headless: true
  prod:
    baseUrl: https://www.saucedemo.com
    timeout: 60
    headless: false
```

```java
// Reading YAML with Jackson
ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
Map<String, EnvironmentConfig> configs = yamlMapper.readValue(
    new File("config/environments.yml"),
    new TypeReference<Map<String, Map<String, EnvironmentConfig>>>() {}
).get("environments");
```

---

## 📖 Topic 5.3: Test Data Factories with Faker

### 🤔 Why This Matters

> **Real-world scenario:** You need 1000 users for load testing. Each needs unique email, realistic name, valid phone format, and address. Manually creating this data? Hours. With Faker? 10 lines of code.

### 📚 Faker Basics

```java
Faker faker = new Faker();  // Default: English locale

// ─── Names ───────────────────────────────────────────────
faker.name().firstName()      // "John"
faker.name().lastName()       // "Smith"
faker.name().fullName()       // "John Smith"
faker.name().username()       // "john.smith42"

// ─── Internet ────────────────────────────────────────────
faker.internet().emailAddress()        // "john.smith42@gmail.com"
faker.internet().safeEmailAddress()    // "john@example.org"
faker.internet().password(8, 16, true) // "aB3$kL9m"
faker.internet().url()                 // "https://www.example.com"

// ─── Address ─────────────────────────────────────────────
faker.address().streetAddress()  // "123 Main St"
faker.address().city()           // "San Francisco"
faker.address().state()          // "California"
faker.address().zipCode()        // "94102"
faker.address().country()        // "United States"

// ─── Numbers ─────────────────────────────────────────────
faker.number().numberBetween(1, 100)   // 42
faker.number().randomDouble(2, 1, 50)  // 29.99

// ─── Dates ───────────────────────────────────────────────
faker.date().past(30, TimeUnit.DAYS)   // Date within last 30 days
faker.date().future(7, TimeUnit.DAYS)  // Date within next 7 days
faker.date().birthday(18, 65)          // Birthday for 18-65 year old
```

### 📚 Complete Factory Pattern

```java
public class UserFactory {
    private static final Faker faker = new Faker();

    // ─── Random User ─────────────────────────────────────
    public static User random() {
        return User.builder()
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .email(faker.internet().safeEmailAddress())
            .phone(faker.phoneNumber().cellPhone())
            .address(AddressFactory.random())
            .build();
    }

    // ─── Specific User Types ─────────────────────────────
    public static User admin() {
        return random().toBuilder()
            .role("admin")
            .permissions(List.of("read", "write", "delete", "admin"))
            .build();
    }

    public static User guest() {
        return random().toBuilder()
            .role("guest")
            .permissions(List.of("read"))
            .build();
    }

    // ─── Seeded for Reproducibility ──────────────────────
    public static User seeded(long seed) {
        Faker seededFaker = new Faker(new Random(seed));
        return User.builder()
            .firstName(seededFaker.name().firstName())
            .lastName(seededFaker.name().lastName())
            .email(seededFaker.internet().safeEmailAddress())
            .build();
    }

    // ─── Batch Generation ────────────────────────────────
    public static List<User> randomList(int count) {
        return IntStream.range(0, count)
            .mapToObj(i -> random())
            .toList();
    }
}
```

### ✋ Self-Check Questions

<details>
<summary>1. How do you make Faker generate the same data every run?</summary>

**Answer:** Use a seeded Random:
```java
Faker faker = new Faker(new Random(12345));
// Always generates same sequence of "random" values
```
</details>

<details>
<summary>2. How would you generate German addresses?</summary>

**Answer:** Use Locale:
```java
Faker germanFaker = new Faker(new Locale("de", "DE"));
germanFaker.address().city();  // "München"
```
</details>

---

## 📖 Topic 5.4: JUnit 5 Parameterized Tests

### 🤔 Why This Matters

> **Real-world scenario:** You have 15 login test cases: valid user, invalid password, locked user, expired user, etc. Without parameterization: 15 nearly identical test methods. With parameterization: 1 method, 15 data sets.

### 📚 Parameterization Sources

```java
// ─── @ValueSource: Simple single values ──────────────────
@ParameterizedTest
@ValueSource(strings = {"standard_user", "problem_user", "performance_glitch_user"})
void testValidUserLogin(String username) {
    loginPage.login(username, "secret_sauce");
    assertThat(driver.getCurrentUrl()).contains("inventory");
}

// ─── @CsvSource: Inline CSV data ─────────────────────────
@ParameterizedTest(name = "Login {0}: expect {1}")
@CsvSource({
    "standard_user, success",
    "locked_out_user, locked error",
    "invalid_user, invalid credentials"
})
void testLoginOutcomes(String username, String expectedOutcome) {
    // Test implementation
}

// ─── @CsvFileSource: External CSV file ───────────────────
@ParameterizedTest
@CsvFileSource(resources = "/data/login-tests.csv", numLinesToSkip = 1)
void testFromCsv(String username, String password, boolean shouldSucceed) {
    loginPage.login(username, password);
    if (shouldSucceed) {
        assertThat(driver.getCurrentUrl()).contains("inventory");
    } else {
        assertThat(loginPage.getErrorMessage()).isNotEmpty();
    }
}

// ─── @MethodSource: Complex objects ──────────────────────
@ParameterizedTest
@MethodSource("provideLoginScenarios")
void testComplexScenarios(LoginTestCase testCase) {
    loginPage.login(testCase.username(), testCase.password());
    assertThat(loginPage.isLoggedIn()).isEqualTo(testCase.shouldSucceed());
}

static Stream<LoginTestCase> provideLoginScenarios() {
    return Stream.of(
        new LoginTestCase("standard_user", "secret_sauce", true),
        new LoginTestCase("locked_out_user", "secret_sauce", false),
        new LoginTestCase("", "secret_sauce", false)
    );
}

// ─── @ArgumentsSource: Reusable provider ─────────────────
@ParameterizedTest
@ArgumentsSource(UserDataProvider.class)
void testWithProvider(User user, boolean expectedResult) {
    // Test implementation
}

class UserDataProvider implements ArgumentsProvider {
    @Override
    public Stream<Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
            Arguments.of(UserFactory.admin(), true),
            Arguments.of(UserFactory.guest(), true),
            Arguments.of(null, false)
        );
    }
}
```

### ✋ Self-Check Questions

<details>
<summary>1. When would you use @MethodSource over @CsvSource?</summary>

**Answer:** When you need:
- Complex objects (not just primitives/strings)
- Logic to generate test data
- Reuse between test classes
- Dynamic data based on environment
</details>

<details>
<summary>2. What does `@ParameterizedTest(name = "Login {0}: expect {1}")` do?</summary>

**Answer:** Customizes the test name in reports. `{0}` = first parameter, `{1}` = second. Results in readable names like "Login standard_user: expect success".
</details>

---

## 🔨 Worked Example: Complete Test Data Architecture

```java
// ─── TestDataLoader.java ─────────────────────────────────
public class TestDataLoader {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static <T> T loadJson(String path, Class<T> type) {
        try {
            return JSON_MAPPER.readValue(
                TestDataLoader.class.getResourceAsStream(path),
                type
            );
        } catch (IOException e) {
            throw new TestDataException("Failed to load: " + path, e);
        }
    }

    public static <T> List<T> loadJsonList(String path, Class<T> type) {
        try {
            return JSON_MAPPER.readValue(
                TestDataLoader.class.getResourceAsStream(path),
                JSON_MAPPER.getTypeFactory().constructCollectionType(List.class, type)
            );
        } catch (IOException e) {
            throw new TestDataException("Failed to load list: " + path, e);
        }
    }
}

// ─── Usage in Test ───────────────────────────────────────
class LoginTest {
    @ParameterizedTest
    @MethodSource("loadLoginTestCases")
    void testLogin(LoginTestCase testCase) {
        // Test implementation using testCase data
    }

    static Stream<LoginTestCase> loadLoginTestCases() {
        return TestDataLoader
            .loadJsonList("/data/login-cases.json", LoginTestCase.class)
            .stream();
    }
}
```

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: Hardcoded file paths

```java
// ❌ BAD: Breaks on different machines
new File("/Users/john/project/testdata/users.json")

// ✅ GOOD: Use classpath resources
getClass().getResourceAsStream("/data/users.json")
```

### Mistake 2: Not handling missing data gracefully

```java
// ❌ BAD: NPE if file missing
User user = mapper.readValue(new File("users.json"), User.class);

// ✅ GOOD: Fail with helpful message
public User loadUser(String path) {
    InputStream stream = getClass().getResourceAsStream(path);
    if (stream == null) {
        throw new TestDataException("File not found: " + path);
    }
    return mapper.readValue(stream, User.class);
}
```

### Mistake 3: Same Faker instance not shared

```java
// ❌ BAD: New Faker each call = inconsistent data
public String randomName() {
    return new Faker().name().fullName();  // New instance each time!
}

// ✅ GOOD: Shared instance
private static final Faker faker = new Faker();
public String randomName() {
    return faker.name().fullName();
}
```

---

## 🎯 Challenge 5.1: Multi-Format Data Loader (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Create unified test data loading from multiple formats.

### Requirements
- Create `TestDataLoader` supporting JSON, CSV, YAML
- Auto-detect format from file extension
- Return typed objects using generics
- Handle missing files with clear error messages

### Acceptance Criteria
- [ ] Supports `.json`, `.csv`, `.yml`/`.yaml` extensions
- [ ] Generic methods: `load(path, Class<T>)` and `loadList(path, Class<T>)`
- [ ] Uses Jackson for JSON/YAML, OpenCSV for CSV
- [ ] Helpful exceptions for missing files or parse errors

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 5.2: Dynamic Test Data Factory (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Implement realistic test data generation.

### Requirements
- Create factories: `UserFactory`, `ProductFactory`, `OrderFactory`
- Use Faker library for realistic data
- Support multiple locales (US, UK, Germany)
- Implement seeded mode for reproducibility

### Acceptance Criteria
- [ ] All factories use Faker with builder pattern
- [ ] `createRandom()`, `createAdmin()`, `createSeeded(long)` methods
- [ ] `setLocale(Locale)` changes data generation
- [ ] Batch generation: `createList(int count)`

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 5.3: Data-Driven Test Suite (Advanced)

### Scaffolding Level: 🔴 Independent

**Scenario:** Create comprehensive parameterized test suite.

### Requirements
- Load test scenarios from external JSON file
- Support test metadata (id, category, priority, enabled)
- Filter tests by category or priority at runtime
- Generate reports showing which data sets passed/failed

### Test Data Structure
```json
{
  "testCases": [
    {
      "id": "TC001",
      "category": "login",
      "priority": "high",
      "enabled": true,
      "input": {"username": "standard_user", "password": "secret_sauce"},
      "expected": {"success": true, "redirectTo": "/inventory.html"}
    }
  ]
}
```

### Acceptance Criteria
- [ ] External JSON file drives test execution
- [ ] `@MethodSource` loads and filters test cases
- [ ] System property controls category filter: `-Dcategory=login`
- [ ] Test names include test case ID

### ⏱️ Time: 90-120 minutes

---

## 🔄 Module 5 Reflection

1. **Which data format** would you choose for login test cases? Why?

2. **Explain the factory pattern** to a teammate who hasn't seen it.

3. **What's the risk** of using `new Faker()` inside each test method?

4. **Rate your confidence (1-5):**
   - [ ] Parsing JSON/CSV/YAML files
   - [ ] Using Faker for test data generation
   - [ ] Writing parameterized tests
   - [ ] Designing data-driven test architecture

---

## 🔗 Spaced Repetition Checkpoint

**Review earlier modules:**
- What's the difference between `visibilityOf` and `presenceOf`? (Module 3)
- Name 3 stream terminal operations (Module 4)

**Revisit Module 5 in:**
- **Week 2**: Add Faker to your existing page object tests
- **Week 3**: Convert 3 regular tests to parameterized tests

---



# Module 6: API Testing with REST Assured

## 🎯 Learning Objectives (Bloom's Taxonomy)

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List HTTP methods and their purposes | Self-check quiz |
| **Understand** | Explain REST principles and status codes | Written explanation |
| **Apply** | Write API tests using REST Assured | Challenge 6.1 |
| **Analyze** | Debug API test failures using logs | Challenge 6.2 |
| **Evaluate** | Choose appropriate validation strategy | Decision matrix |
| **Create** | Design authentication test architecture | Challenge 6.3 |

---

## 📖 Topic 6.1: REST API Fundamentals

### 🤔 Why This Matters

> **Real-world scenario:** Your company's mobile app talks to a REST API. The UI team can't test until the API works. API tests run in seconds (vs minutes for UI), catch bugs earlier, and don't need browser setup. API testing is 10x faster than UI testing.

### 📚 What is REST?

REST = **RE**presentational **S**tate **T**ransfer

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                          REST ARCHITECTURE                                   │
│                                                                              │
│   CLIENT                         SERVER                                      │
│   ┌─────────┐                   ┌─────────┐                                 │
│   │ Mobile  │───── HTTP ───────▶│         │                                 │
│   │   App   │◀─── Request ──────│  REST   │                                 │
│   └─────────┘      JSON         │   API   │                                 │
│                                 │         │                                 │
│   ┌─────────┐                   │         │     ┌─────────┐                 │
│   │   Web   │───── HTTP ───────▶│         │────▶│ Database│                 │
│   │   App   │◀─── Response ─────│         │◀────│         │                 │
│   └─────────┘      JSON         └─────────┘     └─────────┘                 │
│                                                                              │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 📚 HTTP Methods (CRUD Operations)

| Method | CRUD | Purpose | Example | Has Body |
|--------|------|---------|---------|----------|
| **GET** | Read | Retrieve data | Get user profile | No |
| **POST** | Create | Create new resource | Create new user | Yes |
| **PUT** | Update | Replace entire resource | Update all user fields | Yes |
| **PATCH** | Update | Partial update | Update just email | Yes |
| **DELETE** | Delete | Remove resource | Delete user | No |

### 📚 HTTP Status Codes

```
┌─────────────────────────────────────────────────────────────────────────────┐
│  2xx SUCCESS                                                                 │
│  ├── 200 OK           - Request succeeded                                   │
│  ├── 201 Created      - Resource created (POST)                             │
│  └── 204 No Content   - Success but no body (DELETE)                        │
├─────────────────────────────────────────────────────────────────────────────┤
│  4xx CLIENT ERROR                                                            │
│  ├── 400 Bad Request  - Invalid request data                                │
│  ├── 401 Unauthorized - No/invalid authentication                           │
│  ├── 403 Forbidden    - Authenticated but not authorized                    │
│  ├── 404 Not Found    - Resource doesn't exist                              │
│  └── 422 Unprocessable- Validation failed                                   │
├─────────────────────────────────────────────────────────────────────────────┤
│  5xx SERVER ERROR                                                            │
│  ├── 500 Internal     - Server crashed                                      │
│  ├── 502 Bad Gateway  - Upstream server failed                              │
│  └── 503 Unavailable  - Server overloaded/maintenance                       │
└─────────────────────────────────────────────────────────────────────────────┘
```

### ✋ Self-Check Questions

<details>
<summary>1. What's the difference between 401 and 403?</summary>

**Answer:**
- **401 Unauthorized**: "Who are you?" - No authentication or invalid credentials
- **403 Forbidden**: "I know who you are, but you can't do this" - Authenticated but lacks permission
</details>

<details>
<summary>2. Why is GET idempotent but POST isn't?</summary>

**Answer:**
- **Idempotent** = Same request multiple times = same result
- **GET** `/users/1` - Always returns same user (unless data changes)
- **POST** `/users` - Creates NEW user each time = different result each call
</details>

<details>
<summary>3. When would you use PUT vs PATCH?</summary>

**Answer:**
- **PUT**: Replace entire resource. Must send ALL fields.
- **PATCH**: Partial update. Send only fields you're changing.
```json
// PUT /users/1 - Must include everything
{"name": "John", "email": "john@test.com", "age": 30, "role": "admin"}

// PATCH /users/1 - Just the email
{"email": "newemail@test.com"}
```
</details>

---

## 📖 Topic 6.2: REST Assured Fundamentals

### 🤔 Why REST Assured?

> REST Assured lets you write API tests that read like English:
> "**Given** this setup, **When** I call this endpoint, **Then** verify this response."

### 📚 The Given-When-Then Pattern

```java
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

given()                                    // SETUP: Headers, body, auth
    .baseUri("https://api.example.com")
    .header("Accept", "application/json")
    .contentType(ContentType.JSON)
    .body(requestBody)
.when()                                    // ACTION: HTTP method + endpoint
    .post("/users")
.then()                                    // VERIFY: Status, body, headers
    .statusCode(201)
    .body("id", notNullValue())
    .body("name", equalTo("John"));
```

### 📚 Complete Examples

```java
// ─── GET Request ─────────────────────────────────────────
@Test
void shouldGetUserById() {
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
    .when()
        .get("/users/1")
    .then()
        .statusCode(200)
        .body("name", equalTo("Leanne Graham"))
        .body("email", containsString("@"));
}

// ─── POST Request ────────────────────────────────────────
@Test
void shouldCreatePost() {
    String requestBody = """
        {
            "title": "My Post",
            "body": "Content here",
            "userId": 1
        }
        """;

    given()
        .baseUri("https://jsonplaceholder.typicode.com")
        .contentType(ContentType.JSON)
        .body(requestBody)
    .when()
        .post("/posts")
    .then()
        .statusCode(201)
        .body("id", notNullValue())
        .body("title", equalTo("My Post"));
}

// ─── PUT Request ─────────────────────────────────────────
@Test
void shouldUpdatePost() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {"title": "Updated", "body": "New content", "userId": 1}
            """)
    .when()
        .put("/posts/1")
    .then()
        .statusCode(200)
        .body("title", equalTo("Updated"));
}

// ─── DELETE Request ──────────────────────────────────────
@Test
void shouldDeletePost() {
    given()
        .baseUri("https://jsonplaceholder.typicode.com")
    .when()
        .delete("/posts/1")
    .then()
        .statusCode(200);
}
```

### 💡 Mental Model: Restaurant Ordering

- **Given** = "I'd like to order, I'm a VIP member, here's my membership card"
- **When** = "I'll have the steak, medium rare"
- **Then** = "Verify I get steak, it's medium rare, and I get the VIP discount"

---

## 📖 Topic 6.3: Response Validation & Extraction

### 📚 Hamcrest Matchers Reference

```java
// ─── Equality ────────────────────────────────────────────
.body("name", equalTo("John"))           // Exact match
.body("name", not(equalTo("Jane")))      // Not equal
.body("name", equalToIgnoringCase("JOHN"))

// ─── Strings ─────────────────────────────────────────────
.body("email", containsString("@"))
.body("email", startsWith("john"))
.body("email", endsWith(".com"))
.body("email", matchesPattern("[a-z]+@[a-z]+\\.[a-z]+"))

// ─── Numbers ─────────────────────────────────────────────
.body("age", greaterThan(18))
.body("age", lessThanOrEqualTo(65))
.body("price", closeTo(29.99, 0.01))     // Within tolerance

// ─── Null/Empty ──────────────────────────────────────────
.body("id", notNullValue())
.body("deletedAt", nullValue())
.body("items", not(empty()))

// ─── Collections ─────────────────────────────────────────
.body("$", hasSize(10))                  // Array size
.body("items", hasItem("Backpack"))      // Contains item
.body("items", hasItems("Backpack", "Bike Light"))
.body("items", containsInAnyOrder("B", "A", "C"))
```

### 📚 JsonPath Navigation

```java
// ─── Nested Objects ──────────────────────────────────────
// JSON: {"user": {"address": {"city": "NYC"}}}
.body("user.address.city", equalTo("NYC"))

// ─── Array Access ────────────────────────────────────────
// JSON: {"items": ["A", "B", "C"]}
.body("items[0]", equalTo("A"))          // First item
.body("items[-1]", equalTo("C"))         // Last item
.body("items.size()", equalTo(3))        // Array size

// ─── Array of Objects ────────────────────────────────────
// JSON: {"users": [{"name": "John"}, {"name": "Jane"}]}
.body("users[0].name", equalTo("John"))
.body("users.name", hasItems("John", "Jane"))

// ─── Filtering (GPath) ───────────────────────────────────
// Find all users where age > 30
.body("users.findAll { it.age > 30 }.name", hasItem("Bob"))

// Find first user with email containing 'admin'
.body("users.find { it.email.contains('admin') }.name", equalTo("Admin"))
```

### 📚 Extracting Values

```java
// ─── Extract Single Value ────────────────────────────────
String name = given()
    .get("/users/1")
    .then()
    .extract().path("name");

// ─── Extract as POJO ─────────────────────────────────────
User user = given()
    .get("/users/1")
    .then()
    .extract().as(User.class);

// ─── Extract List ────────────────────────────────────────
List<String> names = given()
    .get("/users")
    .then()
    .extract().jsonPath().getList("name", String.class);

// ─── Extract Response Object ─────────────────────────────
Response response = given()
    .get("/users/1")
    .then()
    .extract().response();

int statusCode = response.statusCode();
String body = response.body().asString();
String header = response.header("Content-Type");
```

### ✋ Self-Check Questions

<details>
<summary>1. How do you verify an array has exactly 5 elements?</summary>

**Answer:** `.body("$", hasSize(5))` or `.body("items", hasSize(5))` depending on the JSON structure.
</details>

<details>
<summary>2. Extract the email of the first user from `{"users": [{"email": "a@b.com"}]}`</summary>

**Answer:**
```java
String email = given().get("/users")
    .then().extract().path("users[0].email");
// or
.body("users[0].email", equalTo("a@b.com"))
```
</details>

---

## 📖 Topic 6.4: Request Specifications & Authentication

### 📚 Reusable Request Specification

```java
public class ApiTestBase {
    protected static RequestSpecification baseSpec;

    @BeforeAll
    static void setupSpec() {
        baseSpec = new RequestSpecBuilder()
            .setBaseUri("https://api.example.com")
            .setBasePath("/v1")
            .setContentType(ContentType.JSON)
            .addHeader("Accept", "application/json")
            .addFilter(new RequestLoggingFilter())   // Log requests
            .addFilter(new ResponseLoggingFilter())  // Log responses
            .build();
    }

    @Test
    void testWithSpec() {
        given()
            .spec(baseSpec)           // Reuse specification
            .queryParam("limit", 10)
        .when()
            .get("/users")
        .then()
            .statusCode(200);
    }
}
```

### 📚 Authentication Methods

```java
// ─── Basic Authentication ────────────────────────────────
given()
    .auth().basic("username", "password")
    .get("/protected");

// ─── Bearer Token ────────────────────────────────────────
given()
    .header("Authorization", "Bearer " + token)
    .get("/api/resource");

// ─── OAuth 2.0 ───────────────────────────────────────────
given()
    .auth().oauth2(accessToken)
    .get("/api/resource");

// ─── API Key in Header ───────────────────────────────────
given()
    .header("X-API-Key", "your-api-key")
    .get("/api/resource");

// ─── API Key in Query Param ──────────────────────────────
given()
    .queryParam("api_key", "your-api-key")
    .get("/api/resource");
```

### 📚 Logging for Debugging

```java
// ─── Log Everything ──────────────────────────────────────
given()
    .log().all()       // Log request: headers, body, etc.
.when()
    .get("/users")
.then()
    .log().all();      // Log response: status, headers, body

// ─── Log Only on Failure ─────────────────────────────────
given()
    .log().ifValidationFails()
.when()
    .get("/users")
.then()
    .log().ifValidationFails()
    .statusCode(200);

// ─── Log Specific Parts ──────────────────────────────────
given()
    .log().headers()   // Only headers
    .log().body()      // Only body
.when()
    .get("/users");
```

---

## 🔨 Worked Example: Complete CRUD Test

```java
class PostsApiTest {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static RequestSpecification spec;

    @BeforeAll
    static void setup() {
        spec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .addHeader("Accept", "application/json")
            .build();
    }

    @Test
    @DisplayName("CRUD: Create → Read → Update → Delete")
    void testCrudOperations() {
        // CREATE
        int postId = given().spec(spec)
            .body("""
                {"title": "Test Post", "body": "Content", "userId": 1}
                """)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("Test Post"))
            .extract().path("id");

        // READ
        given().spec(spec)
        .when()
            .get("/posts/{id}", postId)
        .then()
            .statusCode(200)
            .body("id", equalTo(postId));

        // UPDATE
        given().spec(spec)
            .body("""
                {"title": "Updated Title", "body": "New content", "userId": 1}
                """)
        .when()
            .put("/posts/{id}", postId)
        .then()
            .statusCode(200)
            .body("title", equalTo("Updated Title"));

        // DELETE
        given().spec(spec)
        .when()
            .delete("/posts/{id}", postId)
        .then()
            .statusCode(200);
    }
}
```

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: Forgetting Content-Type

```java
// ❌ BAD: Server doesn't know it's JSON
given()
    .body("{\"name\": \"John\"}")
    .post("/users");  // 415 Unsupported Media Type!

// ✅ GOOD: Specify content type
given()
    .contentType(ContentType.JSON)
    .body("{\"name\": \"John\"}")
    .post("/users");
```

### Mistake 2: Not extracting for chained tests

```java
// ❌ BAD: Can't use created ID
given().body(user).post("/users").then().statusCode(201);
given().get("/users/???");  // What ID?

// ✅ GOOD: Extract the ID
int userId = given()
    .body(user)
    .post("/users")
    .then()
    .statusCode(201)
    .extract().path("id");

given().get("/users/" + userId).then().statusCode(200);
```

### Mistake 3: Hardcoded base URI everywhere

```java
// ❌ BAD: Repetitive, hard to change
given().baseUri("https://api.example.com").get("/users");
given().baseUri("https://api.example.com").get("/posts");

// ✅ GOOD: Use RequestSpecification
RequestSpecification spec = new RequestSpecBuilder()
    .setBaseUri("https://api.example.com")
    .build();

given().spec(spec).get("/users");
given().spec(spec).get("/posts");
```

---

## 🎯 Challenge 6.1: CRUD Operations Suite (Intermediate)

### Scaffolding Level: 🟢 Guided

**Scenario:** Test complete CRUD operations on JSONPlaceholder API.

**API:** https://jsonplaceholder.typicode.com

### Requirements
- Create RequestSpecification with base URI and headers
- Test all CRUD operations on `/posts` endpoint
- Extract created post ID and use in subsequent operations
- Add response time assertions (< 2 seconds)

### Acceptance Criteria
- [ ] Shared RequestSpecification in @BeforeAll
- [ ] All 4 CRUD operations tested
- [ ] Response body validated with Hamcrest matchers
- [ ] Logging enabled for debugging

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 6.2: Response Validation Deep Dive (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Advanced JSON response validation.

**API:** https://jsonplaceholder.typicode.com

### Requirements
- GET /users - Validate nested address.geo.lat/lng
- GET /users - Verify all 10 users have valid email format
- GET /posts?userId=1 - Verify all posts belong to user 1
- GET /comments?postId=1 - Validate comment structure

### Acceptance Criteria
- [ ] Nested object validation (address.city, company.name)
- [ ] Array size and content assertions
- [ ] GPath filtering (findAll, find)
- [ ] Optional: JSON Schema validation

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 6.3: Authentication & Error Handling (Advanced)

### Scaffolding Level: 🔴 Independent

**Scenario:** Test authenticated API endpoints and error scenarios.

### Requirements
Use a mock API like https://reqres.in (or similar):
- POST /api/login - Get token
- GET /api/users?page=1 - Authenticated request
- Test 400/401/404 error scenarios
- Implement token refresh pattern

### Acceptance Criteria
- [ ] Token extracted and stored
- [ ] RequestSpecification with Bearer auth
- [ ] 401 when no token, 400 for invalid data
- [ ] Clean error message assertions

### ⏱️ Time: 90-120 minutes

---

## 🔄 Module 6 Reflection

1. **Explain the Given-When-Then pattern** to someone who hasn't seen it.

2. **What's the advantage** of RequestSpecification over repeating setup?

3. **Draw the HTTP status code categories** from memory (2xx, 4xx, 5xx).

4. **Rate your confidence (1-5):**
   - [ ] Writing basic GET/POST/PUT/DELETE tests
   - [ ] Extracting values from responses
   - [ ] Using Hamcrest matchers effectively
   - [ ] Setting up authentication

---

## 🔗 Spaced Repetition Checkpoint

**Review earlier modules:**
- What stream operation filters elements? (Module 4)
- What's the Faker method for random email? (Module 5)

**Revisit Module 6 in:**
- **Week 2**: Add API tests alongside your UI tests
- **Week 3**: Create API client classes for your endpoints

---



# Module 7: Advanced API Automation

## 🎯 Learning Objectives (Bloom's Taxonomy)

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List Jackson annotations for JSON mapping | Self-check quiz |
| **Understand** | Explain why API chaining needs data extraction | Written explanation |
| **Apply** | Implement POJO-based API tests | Challenge 7.2 |
| **Analyze** | Debug API chain failures | Troubleshooting exercise |
| **Evaluate** | Choose between schema validation approaches | Decision matrix |
| **Create** | Design reusable API client architecture | Challenge 7.3 |

---

## 📖 Topic 7.1: API Chaining & Data Flow

### 🤔 Why This Matters

> **Real-world scenario:** To test order placement, you need: 1) Login → get token, 2) Create product → get productId, 3) Add to cart → get cartId, 4) Place order → verify. Each step depends on data from the previous. That's API chaining.

### 📚 The Chaining Pattern

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                          API CHAINING FLOW                                   │
│                                                                              │
│  ┌─────────┐      ┌─────────┐      ┌─────────┐      ┌─────────┐            │
│  │  LOGIN  │─────▶│  GET    │─────▶│  CREATE │─────▶│ VERIFY  │            │
│  │         │      │ PRODUCT │      │  ORDER  │      │  ORDER  │            │
│  └────┬────┘      └────┬────┘      └────┬────┘      └─────────┘            │
│       │                │                │                                   │
│       ▼                ▼                ▼                                   │
│    token          productId         orderId                                 │
│       │                │                │                                   │
│       └────────────────┴────────────────┘                                   │
│            Data flows to next request                                        │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 📚 Chaining Implementation

```java
@Test
void testCompleteOrderWorkflow() {
    // ─── Step 1: Login to get token ──────────────────────
    String token = given()
        .contentType(ContentType.JSON)
        .body("""
            {"username": "standard_user", "password": "secret_sauce"}
            """)
    .when()
        .post("/auth/login")
    .then()
        .statusCode(200)
        .extract().path("token");

    // ─── Step 2: Get product ID ──────────────────────────
    int productId = given()
        .header("Authorization", "Bearer " + token)
    .when()
        .get("/products")
    .then()
        .statusCode(200)
        .extract().path("[0].id");  // First product's ID

    // ─── Step 3: Add to cart ─────────────────────────────
    int cartId = given()
        .header("Authorization", "Bearer " + token)
        .contentType(ContentType.JSON)
        .body("""
            {"productId": %d, "quantity": 2}
            """.formatted(productId))
    .when()
        .post("/cart")
    .then()
        .statusCode(201)
        .extract().path("cartId");

    // ─── Step 4: Place order ─────────────────────────────
    int orderId = given()
        .header("Authorization", "Bearer " + token)
        .contentType(ContentType.JSON)
        .body("""
            {"cartId": %d, "paymentMethod": "card"}
            """.formatted(cartId))
    .when()
        .post("/orders")
    .then()
        .statusCode(201)
        .body("status", equalTo("confirmed"))
        .extract().path("orderId");

    // ─── Step 5: Verify order ────────────────────────────
    given()
        .header("Authorization", "Bearer " + token)
    .when()
        .get("/orders/{id}", orderId)
    .then()
        .statusCode(200)
        .body("id", equalTo(orderId))
        .body("items.size()", equalTo(1))
        .body("items[0].productId", equalTo(productId));
}
```

### ✋ Self-Check Questions

<details>
<summary>1. What happens if Step 2 fails in a 5-step chain?</summary>

**Answer:** Steps 3-5 cannot execute because they need data from Step 2. The test should fail fast with a clear message indicating which step failed.
</details>

<details>
<summary>2. How do you debug a chain failure at step 4?</summary>

**Answer:** Add `.log().all()` to each step to see requests/responses. Check that the data extracted from step 3 is correct. Verify step 3's response actually contains the expected field.
</details>

---

## 📖 Topic 7.2: POJO-Based Type-Safe Testing

### 🤔 Why This Matters

> **Raw JSON strings:** Typos compile fine, fail at runtime. No IDE autocomplete. No refactoring support.
> **POJOs:** Compile-time type checking. IDE autocomplete. Refactor-safe.

### 📚 POJO with Jackson Annotations

```java
@Data                        // Lombok: getters, setters, equals, hashCode, toString
@Builder                     // Lombok: builder pattern
@NoArgsConstructor           // Lombok: required for Jackson deserialization
@AllArgsConstructor          // Lombok: required for @Builder
public class User {

    private Integer id;

    @JsonProperty("name")    // JSON field "name" → Java field "fullName"
    private String fullName;

    private String email;

    @JsonIgnore              // Don't serialize this field
    private String password;

    @JsonProperty("address")
    private Address address; // Nested object

    @JsonFormat(pattern = "yyyy-MM-dd")  // Date formatting
    private LocalDate birthDate;
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String zipCode;

    @JsonProperty("geo")
    private GeoLocation location;
}
```

### 📚 Using POJOs in Tests

```java
@Test
void testWithPojos() {
    // ─── Create request using POJO ───────────────────────
    User newUser = User.builder()
        .fullName("John Doe")
        .email("john@example.com")
        .address(Address.builder()
            .street("123 Main St")
            .city("NYC")
            .build())
        .build();

    // ─── Send and deserialize response ───────────────────
    User createdUser = given()
        .contentType(ContentType.JSON)
        .body(newUser)                    // Auto-serialized to JSON
    .when()
        .post("/users")
    .then()
        .statusCode(201)
        .extract().as(User.class);        // Auto-deserialized to POJO

    // ─── Type-safe assertions ────────────────────────────
    assertThat(createdUser.getId()).isNotNull();
    assertThat(createdUser.getFullName()).isEqualTo("John Doe");
    assertThat(createdUser.getAddress().getCity()).isEqualTo("NYC");
}
```

### 📚 Jackson Annotations Reference

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@JsonProperty("name")` | Map JSON key to Java field | Different naming |
| `@JsonIgnore` | Exclude from serialization | Passwords |
| `@JsonInclude(NON_NULL)` | Skip null fields | Clean output |
| `@JsonFormat(pattern="")` | Date/time formatting | Dates |
| `@JsonAlias({"name", "userName"})` | Accept multiple JSON keys | API versioning |

### ✋ Self-Check Questions

<details>
<summary>1. Why do you need @NoArgsConstructor with @Builder?</summary>

**Answer:** Jackson requires a no-arg constructor for deserialization. @Builder creates an all-args constructor but not a no-arg one. @NoArgsConstructor adds it explicitly.
</details>

<details>
<summary>2. When would you use @JsonIgnore?</summary>

**Answer:** For sensitive data that shouldn't be serialized (passwords, tokens) or internal fields that aren't part of the API contract.
</details>

---

## 📖 Topic 7.3: JSON Schema Validation

### 🤔 Why This Matters

> **Without schema validation:** You check 5 fields. API adds 3 new required fields. Your tests still pass. Production breaks.
> **With schema validation:** New required fields = schema mismatch = test fails = bug caught.

### 📚 Creating JSON Schemas

```json
// src/test/resources/schemas/user-schema.json
{
    "$schema": "http://json-schema.org/draft-07/schema#",
    "type": "object",
    "required": ["id", "name", "email"],
    "properties": {
        "id": {
            "type": "integer",
            "minimum": 1
        },
        "name": {
            "type": "string",
            "minLength": 1,
            "maxLength": 100
        },
        "email": {
            "type": "string",
            "format": "email"
        },
        "address": {
            "type": "object",
            "properties": {
                "city": {"type": "string"},
                "zipCode": {"type": "string", "pattern": "^[0-9]{5}$"}
            }
        },
        "roles": {
            "type": "array",
            "items": {"type": "string"},
            "minItems": 1
        }
    },
    "additionalProperties": false
}
```

### 📚 Using Schema Validation

```java
import static io.restassured.module.jsv.JsonSchemaValidator.*;

@Test
void testUserResponseMatchesSchema() {
    given()
        .get("/users/1")
    .then()
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
}

// With custom validation configuration
@Test
void testWithCustomSchemaSettings() {
    JsonSchemaFactory factory = JsonSchemaFactory.byDefault();

    given()
        .get("/users")
    .then()
        .body(matchesJsonSchemaInClasspath("schemas/users-list-schema.json")
            .using(factory)
            .checked(true));  // Strict validation
}
```

---

## 🔨 Worked Example: Complete API Client

```java
public class UserApiClient {
    private final RequestSpecification spec;
    private final String baseUrl;

    public UserApiClient(String baseUrl, String token) {
        this.baseUrl = baseUrl;
        this.spec = new RequestSpecBuilder()
            .setBaseUri(baseUrl)
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", "Bearer " + token)
            .addFilter(new ResponseLoggingFilter(LogDetail.STATUS))
            .build();
    }

    // ─── CRUD Operations ─────────────────────────────────

    public User create(User user) {
        return given().spec(spec)
            .body(user)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"))
            .extract().as(User.class);
    }

    public User getById(int id) {
        return given().spec(spec)
        .when()
            .get("/users/{id}", id)
        .then()
            .statusCode(200)
            .extract().as(User.class);
    }

    public Optional<User> findByEmail(String email) {
        List<User> users = given().spec(spec)
            .queryParam("email", email)
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .extract().jsonPath().getList("$", User.class);

        return users.stream().findFirst();
    }

    public User update(int id, User user) {
        return given().spec(spec)
            .body(user)
        .when()
            .put("/users/{id}", id)
        .then()
            .statusCode(200)
            .extract().as(User.class);
    }

    public void delete(int id) {
        given().spec(spec)
        .when()
            .delete("/users/{id}", id)
        .then()
            .statusCode(204);
    }

    // ─── Bulk Operations ─────────────────────────────────

    public List<User> getAll() {
        return given().spec(spec)
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .extract().jsonPath().getList("$", User.class);
    }
}
```

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: Not handling nested objects

```java
// ❌ BAD: Nested object becomes LinkedHashMap
User user = response.as(User.class);
user.getAddress().getCity();  // ClassCastException!

// ✅ GOOD: Create proper nested POJOs
@Data
public class User {
    private Address address;  // Not Map<String, Object>
}
```

### Mistake 2: Forgetting ObjectMapper configuration

```java
// ❌ BAD: Fails on unknown properties
User user = new ObjectMapper().readValue(json, User.class);
// UnrecognizedPropertyException if API adds new field!

// ✅ GOOD: Configure mapper
ObjectMapper mapper = new ObjectMapper()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
```

### Mistake 3: Not validating intermediate steps

```java
// ❌ BAD: If login fails, you get confusing errors later
String token = given().body(creds).post("/login").path("token");
given().header("Authorization", "Bearer " + token)  // token might be null!
    .get("/protected");

// ✅ GOOD: Validate each step
String token = given()
    .body(creds)
    .post("/login")
.then()
    .statusCode(200)
    .body("token", notNullValue())
    .extract().path("token");
```

---

## 🎯 Challenge 7.1: E-Commerce API Workflow (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Automate complete order placement workflow.

### API Flow
```
POST /auth/login → token
GET /products → select product
POST /cart → add to cart
POST /orders → place order
GET /orders/{id} → verify order
```

### Acceptance Criteria
- [ ] All 5 steps chained with data passing
- [ ] Each step validates response before proceeding
- [ ] Final order verification includes items and total
- [ ] Error at any step fails with clear message

### ⏱️ Time: 90-120 minutes

---

## 🎯 Challenge 7.2: Type-Safe API Testing (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Implement POJO-based API testing.

### Requirements
Create POJOs with Lombok + Jackson for:
- `User` (id, name, email, address)
- `Post` (id, title, body, userId)
- `Comment` (id, postId, name, email, body)
- `Address` (street, suite, city, zipcode, geo)

### Acceptance Criteria
- [ ] All POJOs have @Data, @Builder, @NoArgsConstructor, @AllArgsConstructor
- [ ] Proper @JsonProperty for field name mismatches
- [ ] Factory methods: `User.random()`, `Post.forUser(userId)`
- [ ] Tests use `.as(User.class)` instead of JsonPath

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 7.3: Schema Validation Suite (Advanced)

### Scaffolding Level: 🔴 Independent

**Scenario:** Implement comprehensive schema validation.

### Requirements
- Create JSON schemas for: User, Post, Comment, Users List
- Include required fields, types, formats, nested objects
- Test that invalid responses fail schema validation
- Create schema for error responses (400, 404)

### Acceptance Criteria
- [ ] Schemas in `src/test/resources/schemas/`
- [ ] Schema validation in all GET tests
- [ ] Tests for schema violation detection
- [ ] Error response schema validated

### ⏱️ Time: 90-120 minutes

---

## 🔄 Module 7 Reflection

1. **Explain API chaining** to someone who's only done UI testing.

2. **What's the advantage** of POJOs over raw JsonPath extraction?

3. **When would schema validation catch bugs** that field-by-field assertions miss?

4. **Rate your confidence (1-5):**
   - [ ] Chaining API calls with data extraction
   - [ ] Creating POJOs with Jackson annotations
   - [ ] Writing JSON schemas
   - [ ] Building reusable API clients

---

## 🔗 Spaced Repetition Checkpoint

**Review earlier modules:**
- What's the Given-When-Then pattern? (Module 6)
- Name 3 Faker methods for user data (Module 5)

**Revisit Module 7 in:**
- **Week 2**: Add POJOs to your existing API tests
- **Week 3**: Add schema validation to all endpoints

---



# Module 8: Hybrid Testing (API + UI)

## 🎯 Learning Objectives (Bloom's Taxonomy)

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List advantages of API setup over UI setup | Self-check quiz |
| **Understand** | Explain the testing pyramid strategy | Written explanation |
| **Apply** | Implement hybrid tests with API setup | Challenge 8.1 |
| **Analyze** | Compare execution times: UI-only vs hybrid | Metrics analysis |
| **Evaluate** | Decide when to use API vs UI for each step | Decision matrix |
| **Create** | Design test orchestration architecture | Challenge 8.3 |

---

## 📖 Topic 8.1: The Testing Pyramid

### 🤔 Why This Matters

> **Real-world scenario:** Your team has 500 UI tests. They take 4 hours to run and 30% fail randomly due to flakiness. You convert 400 to API tests: now runs take 30 minutes with 99% reliability.

### 📚 The Testing Pyramid

```
                            ┌───────────┐
                            │    UI     │   5-10% of tests
                            │  Tests    │   Slowest, most fragile
                            ├───────────┤   Test: Critical user journeys
                           /│           │\
                          / │   API     │ \  30-40% of tests
                         /  │  Tests    │  \ Fast, reliable
                        /   ├───────────┤   \Test: Business logic, integrations
                       /   /│           │\   \
                      /   / │   Unit    │ \   \ 50-60% of tests
                     /   /  │  Tests    │  \   \Fastest, most stable
                    /   /   └───────────┘   \   \Test: Individual functions
                   /   /                     \   \
                  └───┴───────────────────────┴───┘

┌──────────────────────────────────────────────────────────────────────────────┐
│  KEY INSIGHT: Don't test everything through the UI!                          │
│                                                                               │
│  ✗ Login tested via UI → 10 seconds                                          │
│  ✓ Login tested via API → 0.1 seconds                                        │
│                                                                               │
│  100x faster. Same confidence. Less flakiness.                               │
└──────────────────────────────────────────────────────────────────────────────┘
```

### 📚 UI vs API: When to Use Which

| Test Goal | Use UI | Use API |
|-----------|--------|---------|
| Login works | ❌ | ✅ Once, then use token |
| Cart calculates correctly | ❌ | ✅ API returns totals |
| Checkout button is visible | ✅ | ❌ |
| Order is persisted | ❌ | ✅ Verify via API |
| Visual design correct | ✅ | ❌ |
| Form validation messages | ✅ | ❌ (user sees these) |

### ✋ Self-Check Questions

<details>
<summary>1. Why test login via API instead of UI?</summary>

**Answer:**
- **Speed**: API login = 0.1s, UI login = 5-10s
- **Reliability**: No DOM changes, no timing issues
- **Reusability**: Use token for all subsequent tests
- **Focus**: If login is tested once, don't re-test in every other test
</details>

<details>
<summary>2. What should ALWAYS be tested via UI?</summary>

**Answer:** Things only users experience:
- Visual layout and design
- Animations and transitions
- Form validation messages users see
- Accessibility (keyboard navigation, screen readers)
- Critical user journeys (the "happy path")
</details>

---

## 📖 Topic 8.2: Hybrid Testing Patterns

### 📚 Pattern 1: API Setup → UI Test → API Verify

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         HYBRID TEST STRUCTURE                                │
│                                                                              │
│  ┌────────────────────┐                                                     │
│  │   API SETUP        │   Fast, reliable                                    │
│  │   • Login          │   0.3 seconds                                       │
│  │   • Create user    │                                                     │
│  │   • Seed cart      │                                                     │
│  └─────────┬──────────┘                                                     │
│            │                                                                 │
│            ▼                                                                 │
│  ┌────────────────────┐                                                     │
│  │   UI EXECUTION     │   Test what users see                               │
│  │   • Navigate       │   5 seconds                                         │
│  │   • Click checkout │                                                     │
│  │   • Fill form      │                                                     │
│  │   • Submit         │                                                     │
│  └─────────┬──────────┘                                                     │
│            │                                                                 │
│            ▼                                                                 │
│  ┌────────────────────┐                                                     │
│  │   API VERIFY       │   Fast, accurate                                    │
│  │   • Get order      │   0.1 seconds                                       │
│  │   • Check DB state │                                                     │
│  └────────────────────┘                                                     │
│                                                                              │
│  Total: ~5.5 seconds (vs 15+ seconds for pure UI)                           │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 📚 Pattern 2: Cookie/Session Injection

Instead of UI login, inject the authentication directly:

```java
@Test
void testWithInjectedAuth() {
    // ─── Get auth token via API ──────────────────────────
    String token = apiClient.login("standard_user", "secret_sauce");

    // ─── Inject into browser ─────────────────────────────
    driver.get(BASE_URL);  // Must visit domain first
    Cookie authCookie = new Cookie.Builder("session-token", token)
        .domain("saucedemo.com")
        .path("/")
        .isSecure(true)
        .build();
    driver.manage().addCookie(authCookie);

    // ─── Now navigate authenticated ──────────────────────
    driver.get(INVENTORY_URL);  // Goes directly to inventory!

    // No login page interaction needed
    assertThat(inventoryPage.getProducts()).hasSize(6);
}
```

### 📚 Pattern 3: State Management Class

```java
public class TestStateManager {
    private final ApiClient api;
    private final WebDriver driver;

    // ─── Setup Scenarios ─────────────────────────────────

    public AuthenticatedUser setupLoggedInUser() {
        String token = api.login(Users.STANDARD);
        injectAuthCookie(token);
        return new AuthenticatedUser(Users.STANDARD.username(), token);
    }

    public ShoppingCart setupCartWithProducts(String... productIds) {
        AuthenticatedUser user = setupLoggedInUser();
        for (String productId : productIds) {
            api.addToCart(user.token(), productId);
        }
        return new ShoppingCart(user, List.of(productIds));
    }

    public CheckoutContext setupReadyToCheckout() {
        ShoppingCart cart = setupCartWithProducts("backpack", "bike-light");
        driver.get(CHECKOUT_URL);
        return new CheckoutContext(cart);
    }

    // ─── Cleanup ─────────────────────────────────────────

    public void cleanup(String token) {
        try {
            api.clearCart(token);
            api.cancelPendingOrders(token);
        } catch (Exception e) {
            // Log but don't fail test on cleanup errors
            log.warn("Cleanup failed: {}", e.getMessage());
        }
    }

    // ─── Helpers ─────────────────────────────────────────

    private void injectAuthCookie(String token) {
        driver.get(BASE_URL);
        driver.manage().addCookie(new Cookie("auth", token));
    }
}
```

### ✋ Self-Check Questions

<details>
<summary>1. Why call `driver.get(BASE_URL)` before adding cookies?</summary>

**Answer:** Browsers only accept cookies for the current domain. You must visit the domain first, then add cookies, then navigate to the actual page.
</details>

<details>
<summary>2. What's the benefit of API verification over UI verification?</summary>

**Answer:**
- **Speed**: API call = 0.1s vs scraping page = 1-2s
- **Accuracy**: API returns exact data, UI might display formatted
- **Stability**: No DOM changes or locator issues
- **Completeness**: API might have data not shown in UI
</details>

---

## 🔨 Worked Example: Complete Hybrid Test

```java
class HybridCheckoutTest {
    private WebDriver driver;
    private ApiClient api;
    private TestStateManager stateManager;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        api = new ApiClient(BASE_API_URL);
        stateManager = new TestStateManager(api, driver);
    }

    @Test
    @DisplayName("Hybrid: Checkout flow with API setup and verification")
    void testCheckoutFlow() {
        // ─── ARRANGE: Fast API setup ─────────────────────
        String token = api.login("standard_user", "secret_sauce");
        api.addToCart(token, "sauce-labs-backpack");
        api.addToCart(token, "sauce-labs-bike-light");

        // Inject auth into browser
        driver.get(BASE_URL);
        driver.manage().addCookie(new Cookie("session", token));

        // ─── ACT: UI only for critical user journey ──────
        driver.get(CART_URL);

        CartPage cartPage = new CartPage(driver);
        assertThat(cartPage.getItemCount()).isEqualTo(2);

        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        checkoutPage
            .enterFirstName("John")
            .enterLastName("Doe")
            .enterPostalCode("12345")
            .clickContinue();

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        String displayedTotal = overviewPage.getTotal();

        overviewPage.clickFinish();

        // ─── ASSERT: API verification ────────────────────
        // Verify order was created correctly via API
        Order order = api.getLatestOrder(token);

        assertThat(order).isNotNull();
        assertThat(order.getItems()).hasSize(2);
        assertThat(order.getItems())
            .extracting(Item::getProductId)
            .containsExactlyInAnyOrder("sauce-labs-backpack", "sauce-labs-bike-light");

        // Verify API total matches what UI displayed
        String expectedTotal = "$" + order.getTotal();
        assertThat(displayedTotal).isEqualTo(expectedTotal);
    }

    @AfterEach
    void cleanup() {
        stateManager.cleanup(token);
        driver.quit();
    }
}
```

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: Cookie domain mismatch

```java
// ❌ BAD: Cookie for wrong domain
driver.get("https://www.saucedemo.com");
driver.manage().addCookie(new Cookie("token", value));  // Goes to .saucedemo.com

driver.get("https://saucedemo.com/inventory");  // Different subdomain!
// Cookie not sent!

// ✅ GOOD: Ensure domain matches
Cookie cookie = new Cookie.Builder("token", value)
    .domain(".saucedemo.com")  // Leading dot = all subdomains
    .build();
```

### Mistake 2: Not waiting after cookie injection

```java
// ❌ BAD: Page may cache unauthenticated state
driver.manage().addCookie(authCookie);
driver.get(INVENTORY_URL);  // Might redirect to login!

// ✅ GOOD: Refresh or navigate again
driver.manage().addCookie(authCookie);
driver.navigate().refresh();  // Or driver.get(INVENTORY_URL) again
```

### Mistake 3: Mixing UI and API data without sync

```java
// ❌ BAD: UI may show stale data
api.addToCart(token, "product");
driver.get(CART_URL);
// UI might not show product if page cached!

// ✅ GOOD: Ensure page loads fresh data
api.addToCart(token, "product");
driver.get(CART_URL);
driver.navigate().refresh();  // Force fresh load
```

---

## 🎯 Challenge 8.1: API-Accelerated UI Tests (Intermediate)

### Scaffolding Level: 🟢 Guided

**Scenario:** Optimize existing UI tests using API setup.

### Your Existing UI-Only Test (Slow)
```java
void testPurchase_UIOnly() {
    // 10 seconds: Login via UI
    loginPage.login("standard_user", "secret_sauce");

    // 8 seconds: Add items via UI
    inventoryPage.addToCart("Backpack");
    inventoryPage.addToCart("Bike Light");

    // 5 seconds: Checkout
    cartPage.checkout();
    checkoutPage.fillInfo(...);
    checkoutPage.complete();

    // Total: ~25 seconds
}
```

### Convert to Hybrid (Fast)
- Use API for login (get token)
- Use API to add cart items
- Inject session cookie
- UI only for checkout flow

### Acceptance Criteria
- [ ] Execution time reduced by 50%+
- [ ] Login happens via API, not UI
- [ ] Cart populated via API, not clicks
- [ ] Checkout still tested via UI

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 8.2: End-to-End Verification (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Verify complete order flow across layers.

### Test Flow
```
UI: Login → Add Items → Checkout → Capture Order ID from confirmation
API: GET /orders/{id} → Verify items match, total matches
Optional DB: SELECT * FROM orders WHERE id = ? → Verify persisted
```

### Acceptance Criteria
- [ ] Order ID captured from UI confirmation page
- [ ] Same order retrieved via API
- [ ] API data matches what UI displayed
- [ ] All three layers (UI/API/DB) show consistent data

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 8.3: Test Data Orchestration (Advanced)

### Scaffolding Level: 🔴 Independent

**Scenario:** Build comprehensive test state management.

### Requirements
Create `TestOrchestrator` with:
- `setupNewUser()` → Creates user, returns credentials
- `setupReturningCustomer()` → User with order history
- `setupVipUser()` → User with VIP flag and discounts
- `cleanup(TestContext ctx)` → Removes all created data

### Acceptance Criteria
- [ ] Thread-safe for parallel execution (ThreadLocal or unique IDs)
- [ ] Automatic cleanup even on test failure (@AfterEach)
- [ ] Returns context objects with all needed data
- [ ] Logs what was created/cleaned

### ⏱️ Time: 90-120 minutes

---

## 🔄 Module 8 Reflection

1. **Calculate the time savings** if you converted 10 login tests from UI to API.

2. **Explain the testing pyramid** to a manager who wants "more UI tests."

3. **What's the risk** of only testing via API and never via UI?

4. **Rate your confidence (1-5):**
   - [ ] Setting up test state via API
   - [ ] Injecting authentication cookies
   - [ ] Verifying results via API
   - [ ] Designing state management classes

---

## 🔗 Spaced Repetition Checkpoint

**Review earlier modules:**
- What Jackson annotation maps JSON field to Java field? (Module 7)
- How do you wait for element to be clickable? (Module 3)

**Revisit Module 8 in:**
- **Week 2**: Convert 3 UI tests to hybrid
- **Week 3**: Measure time savings in CI pipeline

---



# Module 9: Design Patterns & Framework Architecture

## 🎯 Learning Objectives (Bloom's Taxonomy)

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List the SOLID principles | Self-check quiz |
| **Understand** | Explain when to use Factory vs Builder | Written explanation |
| **Apply** | Implement Builder pattern for test data | Challenge 9.2 |
| **Analyze** | Identify pattern violations in code | Code review exercise |
| **Evaluate** | Choose appropriate patterns for scenarios | Decision matrix |
| **Create** | Design framework architecture | Challenge 9.3 |

---

## 📖 Topic 9.1: Design Patterns for Test Automation

### 🤔 Why This Matters

> **Without patterns:** Every test creates WebDriver differently. Configuration scattered everywhere. Changing browsers requires editing 50 files.
> **With patterns:** One factory creates drivers. One config class. Change browser in one place.

### 📚 Pattern 1: Factory Pattern

**Purpose:** Create objects without exposing creation logic.

```java
public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        return switch (browser.toLowerCase()) {
            case "chrome" -> createChromeDriver();
            case "firefox" -> createFirefoxDriver();
            case "edge" -> createEdgeDriver();
            default -> throw new IllegalArgumentException("Unknown browser: " + browser);
        };
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        if (Config.isHeadless()) {
            options.addArguments("--headless");
        }
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        if (Config.isHeadless()) {
            options.addArguments("-headless");
        }
        return new FirefoxDriver(options);
    }
}

// Usage - tests don't know HOW driver is created
WebDriver driver = DriverFactory.createDriver(Config.getBrowser());
```

### 📚 Pattern 2: Builder Pattern

**Purpose:** Construct complex objects step by step.

```java
public class TestUser {
    private final String username;
    private final String email;
    private final String role;
    private final List<String> permissions;

    private TestUser(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.role = builder.role;
        this.permissions = List.copyOf(builder.permissions);  // Immutable
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String username = "default_user";
        private String email;
        private String role = "user";
        private List<String> permissions = new ArrayList<>();

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder withPermission(String perm) {
            this.permissions.add(perm);
            return this;
        }

        public TestUser build() {
            // Validation
            if (username == null || username.isBlank()) {
                throw new IllegalStateException("Username is required");
            }
            // Default email if not set
            if (email == null) {
                email = username + "@test.com";
            }
            return new TestUser(this);
        }
    }

    // Getters...
}

// Usage - readable, flexible
TestUser admin = TestUser.builder()
    .username("admin")
    .role("admin")
    .withPermission("delete")
    .withPermission("manage")
    .build();

TestUser basicUser = TestUser.builder()
    .username("john")
    .email("john@example.com")
    .build();  // Uses default role
```

### 📚 Pattern 3: Singleton Pattern

**Purpose:** Ensure only one instance exists (use sparingly!).

```java
public class Config {
    private static volatile Config instance;
    private final Properties properties;

    private Config() {
        properties = new Properties();
        loadProperties();
    }

    public static Config getInstance() {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }
        return instance;
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    private void loadProperties() {
        try (InputStream is = getClass().getResourceAsStream("/config.properties")) {
            properties.load(is);
        } catch (IOException e) {
            throw new ConfigurationException("Failed to load config", e);
        }
    }
}

// Usage
String baseUrl = Config.getInstance().get("base.url");
```

### ✋ Self-Check Questions

<details>
<summary>1. When would you use Factory vs Builder?</summary>

**Answer:**
- **Factory**: When you need to create different types of objects (Chrome vs Firefox driver)
- **Builder**: When you need to create complex objects with many optional parameters (TestUser with various fields)
</details>

<details>
<summary>2. Why is Singleton considered problematic?</summary>

**Answer:**
- Hard to test (global state)
- Hidden dependencies
- Thread-safety concerns
- Better alternative: Dependency Injection
</details>

---

## 📖 Topic 9.2: SOLID Principles in Test Automation

### 📚 SOLID Overview

```
┌──────────────────────────────────────────────────────────────────────────────┐
│                           SOLID PRINCIPLES                                    │
│                                                                               │
│  S - Single Responsibility    One class, one reason to change                │
│  O - Open/Closed              Open for extension, closed for modification    │
│  L - Liskov Substitution      Subtypes must be substitutable                 │
│  I - Interface Segregation    Many specific interfaces > one general         │
│  D - Dependency Inversion     Depend on abstractions, not concretions        │
└──────────────────────────────────────────────────────────────────────────────┘
```

### 📚 S - Single Responsibility

```java
// ❌ BAD: Page object does too much
public class LoginPage {
    public void login(String user, String pass) { ... }
    public void takeScreenshot() { ... }           // Not login's job!
    public void logToFile(String msg) { ... }      // Not login's job!
    public void sendEmail(String to) { ... }       // Definitely not!
}

// ✅ GOOD: Each class has one responsibility
public class LoginPage {
    public void login(String user, String pass) { ... }
    public boolean isLoggedIn() { ... }
}

public class ScreenshotHelper {
    public void takeScreenshot(WebDriver driver) { ... }
}

public class TestLogger {
    public void log(String message) { ... }
}
```

### 📚 O - Open/Closed

```java
// ❌ BAD: Must modify class to add new browser
public class DriverFactory {
    public WebDriver create(String browser) {
        if (browser.equals("chrome")) { ... }
        else if (browser.equals("firefox")) { ... }
        // Must add new else-if for every browser!
    }
}

// ✅ GOOD: Extend without modifying
public interface DriverProvider {
    WebDriver createDriver();
    boolean supports(String browser);
}

public class ChromeDriverProvider implements DriverProvider {
    public WebDriver createDriver() { return new ChromeDriver(); }
    public boolean supports(String browser) { return "chrome".equals(browser); }
}

public class DriverFactory {
    private final List<DriverProvider> providers;

    public WebDriver create(String browser) {
        return providers.stream()
            .filter(p -> p.supports(browser))
            .findFirst()
            .orElseThrow()
            .createDriver();
    }
}
// Add new browser = add new provider class, don't modify factory
```

### 📚 D - Dependency Inversion

```java
// ❌ BAD: Test depends on concrete implementation
public class CheckoutTest {
    private ChromeDriver driver = new ChromeDriver();  // Concrete!
    private RestAssuredClient api = new RestAssuredClient();  // Concrete!
}

// ✅ GOOD: Depend on abstractions, inject dependencies
public class CheckoutTest {
    private final WebDriver driver;      // Interface
    private final ApiClient api;         // Interface

    public CheckoutTest(WebDriver driver, ApiClient api) {
        this.driver = driver;
        this.api = api;
    }
}

// JUnit 5 with dependency injection
@ExtendWith(TestDependencyExtension.class)
class CheckoutTest {
    @Inject private WebDriver driver;
    @Inject private ApiClient api;
}
```

---

## 📖 Topic 9.3: Framework Architecture

### 📚 Layered Architecture

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         FRAMEWORK ARCHITECTURE                               │
│                                                                              │
│  ┌───────────────────────────────────────────────────────────────────────┐  │
│  │                         TEST LAYER                                     │  │
│  │  LoginTest.java    CheckoutTest.java    SearchTest.java               │  │
│  │  • Test methods    • Assertions         • Test data                   │  │
│  └───────────────────────────────────────────────────────────────────────┘  │
│                                    │                                         │
│                                    ▼                                         │
│  ┌───────────────────────────────────────────────────────────────────────┐  │
│  │                       BUSINESS LAYER                                   │  │
│  │  LoginPage.java    CartPage.java    UserApiClient.java                │  │
│  │  • Page objects    • API clients    • Business logic                  │  │
│  └───────────────────────────────────────────────────────────────────────┘  │
│                                    │                                         │
│                                    ▼                                         │
│  ┌───────────────────────────────────────────────────────────────────────┐  │
│  │                         CORE LAYER                                     │  │
│  │  BasePage.java    BaseApiClient.java    WaitUtils.java                │  │
│  │  • Common methods  • Shared utilities   • Wrappers                    │  │
│  └───────────────────────────────────────────────────────────────────────┘  │
│                                    │                                         │
│                                    ▼                                         │
│  ┌───────────────────────────────────────────────────────────────────────┐  │
│  │                     INFRASTRUCTURE LAYER                               │  │
│  │  Config.java    DriverFactory.java    ReportManager.java              │  │
│  │  • Configuration  • Driver management  • Logging/Reporting            │  │
│  └───────────────────────────────────────────────────────────────────────┘  │
│                                                                              │
│  RULE: Dependencies flow DOWN only. Never up or sideways.                   │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 📚 Package Structure

```
src/test/java/
├── tests/                          # TEST LAYER
│   ├── ui/
│   │   ├── LoginTest.java
│   │   └── CheckoutTest.java
│   └── api/
│       └── UserApiTest.java
│
├── pages/                          # BUSINESS LAYER (UI)
│   ├── LoginPage.java
│   ├── InventoryPage.java
│   └── CartPage.java
│
├── clients/                        # BUSINESS LAYER (API)
│   ├── UserApiClient.java
│   └── OrderApiClient.java
│
├── core/                           # CORE LAYER
│   ├── BasePage.java
│   ├── BaseApiClient.java
│   └── WaitUtils.java
│
├── infrastructure/                 # INFRASTRUCTURE LAYER
│   ├── config/
│   │   └── Config.java
│   ├── drivers/
│   │   └── DriverFactory.java
│   └── reporting/
│       └── AllureReportManager.java
│
└── data/                           # TEST DATA
    ├── TestUsers.java
    └── factories/
        └── UserFactory.java
```

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: God class page objects

```java
// ❌ BAD: One page object for entire site
public class SauceDemoPage {
    public void login() { ... }
    public void addToCart() { ... }
    public void checkout() { ... }
    public void search() { ... }
    // 500 lines of code!
}

// ✅ GOOD: One page object per page
public class LoginPage { ... }
public class InventoryPage { ... }
public class CartPage { ... }
```

### Mistake 2: Hardcoded dependencies

```java
// ❌ BAD: Can't test with different driver
public class LoginPage {
    private WebDriver driver = new ChromeDriver();
}

// ✅ GOOD: Inject dependency
public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
}
```

---

## 🎯 Challenge 9.1: Page Factory Implementation (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Create flexible page object factory.

### Requirements
```java
// Your factory should support:
PageFactory factory = new PageFactory(driver);

LoginPage login = factory.create(LoginPage.class);
InventoryPage inventory = factory.create(InventoryPage.class);

// With caching (same instance returned)
LoginPage login1 = factory.create(LoginPage.class);
LoginPage login2 = factory.create(LoginPage.class);
assertThat(login1).isSameAs(login2);

// With fresh instance
LoginPage fresh = factory.createFresh(LoginPage.class);
```

### Acceptance Criteria
- [ ] Generic `create(Class<T>)` method
- [ ] Page caching with `createFresh()` option
- [ ] Page readiness validation after creation
- [ ] Clear exception messages on failure

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 9.2: Fluent Builder Framework (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Create builders for all test entities.

### Requirements
Create builders for: User, Product, Order, Address with:
- Fluent API (method chaining)
- Nested builders (Order contains Items)
- Validation in `build()`
- Immutable objects produced

### Acceptance Criteria
- [ ] All builders follow same pattern
- [ ] Nested object building works
- [ ] `build()` validates required fields
- [ ] Objects are immutable after creation

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 9.3: Framework Architecture Design (Advanced)

### Scaffolding Level: 🔴 Independent

**Scenario:** Design complete test framework architecture.

### Requirements
- Draw architecture diagram (4 layers)
- Implement core interfaces
- Create extension points
- Document design decisions

### Acceptance Criteria
- [ ] Clear layer separation
- [ ] Dependencies flow down only
- [ ] Interface-based design
- [ ] README with architecture explanation

### ⏱️ Time: 90-120 minutes

---

## 🔄 Module 9 Reflection

1. **Explain the Factory pattern** to someone who's never heard of design patterns.

2. **Which SOLID principle** is violated when a page object also handles logging?

3. **Why is dependency injection** better than creating objects directly?

4. **Rate your confidence (1-5):**
   - [ ] Implementing Factory pattern
   - [ ] Implementing Builder pattern
   - [ ] Applying SOLID principles
   - [ ] Designing layered architecture

---

## 🔗 Spaced Repetition Checkpoint

**Review earlier modules:**
- What's the advantage of API setup over UI setup? (Module 8)
- What Jackson annotation ignores a field? (Module 7)

**Revisit Module 9 in:**
- **Week 2**: Refactor existing code to use patterns
- **Week 3**: Review architecture for SOLID violations

---



# Module 10: Database Testing & Validation

## 🎯 Learning Objectives (Bloom's Taxonomy)

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List JDBC connection components | Self-check quiz |
| **Understand** | Explain why database verification matters | Written explanation |
| **Apply** | Write SQL queries for test validation | Challenge 10.2 |
| **Analyze** | Debug data inconsistencies across layers | Troubleshooting exercise |
| **Evaluate** | Choose between DB verification approaches | Decision matrix |
| **Create** | Design test data lifecycle management | Challenge 10.3 |

---

## 📖 Topic 10.1: Why Database Testing?

### 🤔 Why This Matters

> **Real-world scenario:** User creates account via UI. API returns success. But database constraint fails silently. User can't log in next day. Without DB verification, this bug ships to production.

### 📚 The Three-Layer Verification

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                      THREE-LAYER VERIFICATION                                │
│                                                                              │
│  ┌─────────────────┐                                                        │
│  │       UI        │   "Order confirmed!" displayed                         │
│  │                 │   ✓ User sees success message                          │
│  └────────┬────────┘                                                        │
│           │                                                                  │
│           ▼                                                                  │
│  ┌─────────────────┐                                                        │
│  │      API        │   GET /orders/123 returns order                        │
│  │                 │   ✓ API returns correct data                           │
│  └────────┬────────┘                                                        │
│           │                                                                  │
│           ▼                                                                  │
│  ┌─────────────────┐                                                        │
│  │    DATABASE     │   SELECT * FROM orders WHERE id = 123                  │
│  │                 │   ✓ Data actually persisted                            │
│  └─────────────────┘                                                        │
│                                                                              │
│  COMPLETE VERIFICATION = All three layers agree                             │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 📚 When to Use Database Verification

| Scenario | DB Verification Needed? |
|----------|------------------------|
| User registration | ✅ Yes - verify user persisted |
| Login test | ❌ No - API/UI sufficient |
| Order placement | ✅ Yes - verify order + items |
| Search functionality | ❌ No - UI result sufficient |
| Data migration | ✅ Yes - verify all records |
| Soft delete | ✅ Yes - verify flag set, not deleted |

---

## 📖 Topic 10.2: JDBC Fundamentals

### 📚 Connection Pattern

```java
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "test_user";
    private static final String PASSWORD = "test_pass";

    // ─── Simple Connection ───────────────────────────────
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ─── Connection Pooling (Recommended) ────────────────
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000);  // 30 seconds
        dataSource = new HikariDataSource(config);
    }

    public static Connection getPooledConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void shutdown() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
```

### 📚 Query Utilities

```java
public class DatabaseUtils {

    // ─── Query for single value ──────────────────────────
    public static <T> T queryForValue(String sql, Class<T> type, Object... params) {
        try (Connection conn = DatabaseConnection.getPooledConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setParameters(stmt, params);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getObject(1, type);
            }
            return null;
        } catch (SQLException e) {
            throw new DatabaseException("Query failed: " + sql, e);
        }
    }

    // ─── Query for list of maps ──────────────────────────
    public static List<Map<String, Object>> queryForList(String sql, Object... params) {
        List<Map<String, Object>> results = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getPooledConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setParameters(stmt, params);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {
                Map<String, Object> row = new LinkedHashMap<>();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    row.put(meta.getColumnName(i), rs.getObject(i));
                }
                results.add(row);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Query failed: " + sql, e);
        }
        return results;
    }

    // ─── Execute INSERT/UPDATE/DELETE ────────────────────
    public static int executeUpdate(String sql, Object... params) {
        try (Connection conn = DatabaseConnection.getPooledConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setParameters(stmt, params);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseException("Update failed: " + sql, e);
        }
    }

    // ─── Helper: Set parameters ──────────────────────────
    private static void setParameters(PreparedStatement stmt, Object... params)
            throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }
}
```

### ✋ Self-Check Questions

<details>
<summary>1. Why use connection pooling instead of creating new connections?</summary>

**Answer:**
- **Performance**: Creating connections is expensive (network handshake, authentication)
- **Resource management**: Pools limit max connections, preventing DB overload
- **Reuse**: Connections are returned to pool, not closed
</details>

<details>
<summary>2. Why use PreparedStatement instead of string concatenation?</summary>

**Answer:**
- **SQL Injection prevention**: Parameters are escaped automatically
- **Performance**: Statement can be cached and reused
- **Type safety**: Parameters are properly typed
</details>

---

## 📖 Topic 10.3: Test Data Lifecycle

### 📚 Setup and Cleanup Pattern

```java
class OrderDatabaseTest {
    private static final int TEST_USER_ID = 99999;

    @BeforeEach
    void setupTestData() {
        // Create test user
        DatabaseUtils.executeUpdate(
            "INSERT INTO users (id, name, email) VALUES (?, ?, ?)",
            TEST_USER_ID, "Test User", "test@example.com"
        );
    }

    @AfterEach
    void cleanupTestData() {
        // Delete in correct order (foreign key constraints)
        DatabaseUtils.executeUpdate(
            "DELETE FROM order_items WHERE order_id IN " +
            "(SELECT id FROM orders WHERE user_id = ?)",
            TEST_USER_ID
        );
        DatabaseUtils.executeUpdate(
            "DELETE FROM orders WHERE user_id = ?",
            TEST_USER_ID
        );
        DatabaseUtils.executeUpdate(
            "DELETE FROM users WHERE id = ?",
            TEST_USER_ID
        );
    }

    @Test
    void verifyOrderCreatedInDatabase() {
        // ─── ACT: Create order via UI ────────────────────
        loginAs(TEST_USER_ID);
        inventoryPage.addToCart("backpack");
        cartPage.checkout();
        checkoutPage.completeOrder();

        // ─── ASSERT: Verify in database ──────────────────
        List<Map<String, Object>> orders = DatabaseUtils.queryForList(
            "SELECT * FROM orders WHERE user_id = ? ORDER BY created_at DESC",
            TEST_USER_ID
        );

        assertThat(orders).hasSize(1);

        Map<String, Object> order = orders.get(0);
        assertThat(order.get("status")).isEqualTo("PENDING");
        assertThat(order.get("user_id")).isEqualTo(TEST_USER_ID);
        assertThat(order.get("created_at")).isNotNull();
    }
}
```

### 📚 Transaction Rollback Pattern

```java
class TransactionalDatabaseTest {
    private Connection connection;

    @BeforeEach
    void beginTransaction() throws SQLException {
        connection = DatabaseConnection.getPooledConnection();
        connection.setAutoCommit(false);  // Start transaction
    }

    @AfterEach
    void rollbackTransaction() throws SQLException {
        if (connection != null) {
            connection.rollback();  // Undo all changes
            connection.close();
        }
    }

    @Test
    void testWithAutomaticCleanup() throws SQLException {
        // Insert test data
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO users (name, email) VALUES (?, ?)")) {
            stmt.setString(1, "Test User");
            stmt.setString(2, "test@example.com");
            stmt.executeUpdate();
        }

        // Test logic here...

        // No cleanup needed - rollback in @AfterEach undoes everything
    }
}
```

---

## 🔨 Worked Example: Complete DB Verification

```java
class UserRegistrationDatabaseTest {
    private ApiClient api;
    private WebDriver driver;

    @Test
    @DisplayName("User registration persists correctly to database")
    void testUserRegistrationPersistence() {
        // ─── ARRANGE ─────────────────────────────────────
        String uniqueEmail = "test_" + System.currentTimeMillis() + "@example.com";
        String password = "SecurePass123!";

        // ─── ACT: Register via UI ────────────────────────
        driver.get(REGISTRATION_URL);
        RegistrationPage regPage = new RegistrationPage(driver);
        regPage
            .enterName("John Doe")
            .enterEmail(uniqueEmail)
            .enterPassword(password)
            .clickRegister();

        // Verify UI shows success
        assertThat(regPage.getSuccessMessage())
            .contains("Registration successful");

        // ─── ASSERT: Verify via API ──────────────────────
        User apiUser = api.getUserByEmail(uniqueEmail);
        assertThat(apiUser).isNotNull();
        assertThat(apiUser.getName()).isEqualTo("John Doe");

        // ─── ASSERT: Verify in Database ──────────────────
        Map<String, Object> dbUser = DatabaseUtils.queryForList(
            "SELECT * FROM users WHERE email = ?",
            uniqueEmail
        ).get(0);

        // Verify all fields
        assertThat(dbUser.get("name")).isEqualTo("John Doe");
        assertThat(dbUser.get("email")).isEqualTo(uniqueEmail);
        assertThat(dbUser.get("password_hash")).isNotNull();  // Hashed, not plain
        assertThat(dbUser.get("password_hash")).isNotEqualTo(password);  // Not stored plain

        // Verify audit fields
        assertThat(dbUser.get("created_at")).isNotNull();
        assertThat(dbUser.get("updated_at")).isNotNull();
        assertThat(dbUser.get("is_active")).isEqualTo(true);

        // ─── CLEANUP ─────────────────────────────────────
        DatabaseUtils.executeUpdate(
            "DELETE FROM users WHERE email = ?",
            uniqueEmail
        );
    }
}
```

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: Not closing connections

```java
// ❌ BAD: Connection leak
Connection conn = DriverManager.getConnection(url);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);
// If exception occurs, connection never closed!

// ✅ GOOD: Try-with-resources
try (Connection conn = DriverManager.getConnection(url);
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery(sql)) {
    // Process results
}  // All resources automatically closed
```

### Mistake 2: SQL injection vulnerability

```java
// ❌ BAD: SQL injection possible
String sql = "SELECT * FROM users WHERE email = '" + email + "'";
// If email = "'; DROP TABLE users; --" ... disaster!

// ✅ GOOD: Parameterized query
String sql = "SELECT * FROM users WHERE email = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, email);  // Safely escaped
```

### Mistake 3: Wrong cleanup order

```java
// ❌ BAD: Foreign key constraint violation
DatabaseUtils.executeUpdate("DELETE FROM users WHERE id = ?", userId);
// Fails if orders table references this user!

// ✅ GOOD: Delete children first
DatabaseUtils.executeUpdate("DELETE FROM order_items WHERE order_id IN (SELECT id FROM orders WHERE user_id = ?)", userId);
DatabaseUtils.executeUpdate("DELETE FROM orders WHERE user_id = ?", userId);
DatabaseUtils.executeUpdate("DELETE FROM users WHERE id = ?", userId);
```

---

## 🎯 Challenge 10.1: Database Connection Framework (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Build reusable database utilities.

### Requirements
- `DatabaseManager` with HikariCP connection pooling
- Support for MySQL, PostgreSQL, SQLite via config
- Generic query methods: `queryForValue`, `queryForList`, `executeUpdate`
- Proper exception handling and logging

### Acceptance Criteria
- [ ] Connection pooling with configurable pool size
- [ ] Database type configurable via properties file
- [ ] All resources properly closed (try-with-resources)
- [ ] Clear error messages on connection failure

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 10.2: Data Verification Tests (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Verify UI actions in database.

### Test Cases
1. Create user via UI → Verify user exists in DB
2. Update profile → Verify changes persisted
3. Delete account → Verify soft delete (is_active = false)
4. Place order → Verify order + items in DB

### Using SQLite for Local Testing
```java
// In-memory SQLite - no installation needed
String url = "jdbc:sqlite::memory:";
Connection conn = DriverManager.getConnection(url);

// Create schema
conn.createStatement().execute("""
    CREATE TABLE users (
        id INTEGER PRIMARY KEY,
        name TEXT,
        email TEXT UNIQUE,
        is_active INTEGER DEFAULT 1
    )
""");
```

### Acceptance Criteria
- [ ] All 4 test cases implemented
- [ ] Uses SQLite for portability
- [ ] Proper setup/cleanup in @BeforeEach/@AfterEach
- [ ] Assertions on all relevant fields

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 10.3: Test Data Factory with DB (Advanced)

### Scaffolding Level: 🔴 Independent

**Scenario:** Generate and persist test data.

### Requirements
- Combine Faker with database insertion
- Create related entities: User → Orders → OrderItems
- Support transactional rollback for cleanup
- Generate bulk data for performance testing

### Acceptance Criteria
- [ ] Factory creates realistic data with Faker
- [ ] Relationships maintained (foreign keys)
- [ ] Transaction rollback cleans up automatically
- [ ] Can generate 100+ records efficiently

### ⏱️ Time: 90-120 minutes

---

## 🔄 Module 10 Reflection

1. **When would you verify in database** vs just checking API response?

2. **Explain connection pooling** to someone who's never used databases.

3. **What's the risk** of not cleaning up test data?

4. **Rate your confidence (1-5):**
   - [ ] Writing JDBC connection code
   - [ ] Executing parameterized queries
   - [ ] Managing test data lifecycle
   - [ ] Verifying data across UI/API/DB

---

## 🔗 Spaced Repetition Checkpoint

**Review earlier modules:**
- What's the Factory pattern used for? (Module 9)
- How do you inject auth cookies? (Module 8)

**Revisit Module 10 in:**
- **Week 2**: Add DB verification to existing tests
- **Week 3**: Implement transaction rollback cleanup

---



# Module 11: Reporting, Logging & Debugging

## 🎯 Learning Objectives (Bloom's Taxonomy)

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List Allure annotation types | Self-check quiz |
| **Understand** | Explain log levels and when to use each | Written explanation |
| **Apply** | Implement screenshot capture on failure | Challenge 11.1 |
| **Analyze** | Debug flaky test root causes | Troubleshooting exercise |
| **Evaluate** | Choose appropriate logging strategy | Decision matrix |
| **Create** | Design comprehensive reporting system | Challenge 11.3 |

---

## 📖 Topic 11.1: Why Reporting Matters

### 🤔 Why This Matters

> **Without good reporting:** Test fails. You see "AssertionError". You have no idea what happened. You spend 30 minutes debugging.
> **With good reporting:** Test fails. You see screenshot, step-by-step log, API responses. You fix in 5 minutes.

### 📚 The Reporting Pyramid

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         REPORTING PYRAMID                                    │
│                                                                              │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │                    EXECUTIVE SUMMARY                                 │    │
│  │  "95% pass rate, 3 critical failures, 2 flaky tests"               │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
│                                    │                                         │
│                                    ▼                                         │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │                    TEST RESULTS                                      │    │
│  │  Pass/Fail status, duration, categories, trends                     │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
│                                    │                                         │
│                                    ▼                                         │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │                    STEP-BY-STEP DETAILS                              │    │
│  │  Each action, screenshots, API calls, assertions                    │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
│                                    │                                         │
│                                    ▼                                         │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │                    DEBUG LOGS                                        │    │
│  │  Timestamps, thread IDs, full stack traces                          │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 📖 Topic 11.2: Allure Reporting

### 📚 Allure Annotations

```java
@Epic("E-Commerce Platform")           // Highest level grouping
@Feature("Shopping Cart")              // Feature being tested
@Story("Add items to cart")            // User story
public class CartTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)  // BLOCKER, CRITICAL, NORMAL, MINOR, TRIVIAL
    @Description("Verify user can add multiple items to cart")
    @Link(name = "JIRA-123", url = "https://jira.company.com/JIRA-123")
    @Issue("BUG-456")                  // Known issue link
    @TmsLink("TC-789")                 // Test management system link
    void shouldAddMultipleItemsToCart() {
        // Test implementation
    }
}
```

### 📚 Step-Level Reporting

```java
public class CartPage extends BasePage {

    @Step("Add product '{productName}' to cart")
    public CartPage addToCart(String productName) {
        logger.info("Adding {} to cart", productName);
        WebElement product = findProduct(productName);
        product.findElement(By.cssSelector(".add-to-cart")).click();
        return this;
    }

    @Step("Verify cart contains {expectedCount} items")
    public CartPage verifyItemCount(int expectedCount) {
        int actualCount = getCartItemCount();
        assertThat(actualCount)
            .as("Cart item count")
            .isEqualTo(expectedCount);
        return this;
    }

    @Step("Proceed to checkout")
    public CheckoutPage proceedToCheckout() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
}
```

### 📚 Attachments

```java
public class AllureAttachments {

    // ─── Screenshot attachment ───────────────────────────
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // ─── Page source attachment ──────────────────────────
    @Attachment(value = "Page Source", type = "text/html")
    public static String attachPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    // ─── API response attachment ─────────────────────────
    @Attachment(value = "API Response", type = "application/json")
    public static String attachApiResponse(Response response) {
        return response.asPrettyString();
    }

    // ─── Text attachment ─────────────────────────────────
    @Attachment(value = "{name}", type = "text/plain")
    public static String attachText(String name, String content) {
        return content;
    }
}
```

### ✋ Self-Check Questions

<details>
<summary>1. What's the difference between @Epic, @Feature, and @Story?</summary>

**Answer:**
- **@Epic**: Highest level - major product area (e.g., "E-Commerce Platform")
- **@Feature**: Feature within epic (e.g., "Shopping Cart")
- **@Story**: Specific user story (e.g., "Add items to cart")

This creates a hierarchy in Allure reports for easy navigation.
</details>

<details>
<summary>2. When should you use @Step vs just logging?</summary>

**Answer:**
- **@Step**: For business-level actions visible in reports (user-facing)
- **Logging**: For technical details useful during debugging

Example: `@Step("Login as admin")` + `logger.debug("Entering password field")`
</details>

---

## 📖 Topic 11.3: Logging with SLF4J & Logback

### 📚 Logback Configuration

```xml
<!-- src/test/resources/logback.xml -->
<configuration>
    <!-- Console output -->
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- Rolling file output -->
    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/test-automation.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/test-%d{yyyy-MM-dd}.log</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- Package-specific levels -->
    <logger name="com.automation" level="DEBUG"/>
    <logger name="org.seleniumhq" level="WARN"/>
    <logger name="io.restassured" level="WARN"/>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE"/>
    </root>
</configuration>
```

### 📚 Log Levels Guide

| Level | When to Use | Example |
|-------|-------------|---------|
| **ERROR** | Test infrastructure failures | "Failed to connect to database" |
| **WARN** | Unexpected but recoverable | "Retry attempt 2/3" |
| **INFO** | Key test actions | "Login successful for user: admin" |
| **DEBUG** | Detailed debugging info | "Entering password into field #password" |
| **TRACE** | Very detailed (rarely used) | "Element found at coordinates (100, 200)" |

### 📚 Logging in Page Objects

```java
public class LoginPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);

    public InventoryPage login(String username, String password) {
        logger.info("Attempting login with username: {}", username);

        logger.debug("Entering username into field");
        usernameField.sendKeys(username);

        logger.debug("Entering password into field");
        passwordField.sendKeys(password);

        logger.debug("Clicking login button");
        loginButton.click();

        if (isErrorDisplayed()) {
            logger.warn("Login failed - error message displayed: {}", getErrorMessage());
            throw new LoginException("Login failed: " + getErrorMessage());
        }

        logger.info("Login successful, navigating to inventory page");
        return new InventoryPage(driver);
    }
}
```

---

## 📖 Topic 11.4: Screenshot on Failure

### 📚 JUnit 5 Extension

```java
public class ScreenshotOnFailureExtension implements TestWatcher, ParameterResolver {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotOnFailureExtension.class);

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("Test failed: {}", context.getDisplayName(), cause);

        // Get WebDriver from test instance
        Object testInstance = context.getRequiredTestInstance();
        WebDriver driver = extractDriver(testInstance);

        if (driver != null) {
            captureScreenshot(driver, context.getDisplayName());
            capturePageSource(driver, context.getDisplayName());
        }
    }

    private void captureScreenshot(WebDriver driver, String testName) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Save to file
            String fileName = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
            Files.write(Path.of(fileName), screenshot);
            logger.info("Screenshot saved: {}", fileName);

            // Attach to Allure
            Allure.addAttachment("Failure Screenshot", "image/png",
                new ByteArrayInputStream(screenshot), ".png");

        } catch (Exception e) {
            logger.error("Failed to capture screenshot", e);
        }
    }

    private WebDriver extractDriver(Object testInstance) {
        try {
            Field driverField = testInstance.getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            return (WebDriver) driverField.get(testInstance);
        } catch (Exception e) {
            logger.warn("Could not extract WebDriver from test instance");
            return null;
        }
    }
}

// Usage
@ExtendWith(ScreenshotOnFailureExtension.class)
class LoginTest {
    private WebDriver driver;
    // ...
}
```

---

## 🔨 Worked Example: Complete Reporting Setup

```java
@Epic("E-Commerce")
@Feature("User Authentication")
@ExtendWith(ScreenshotOnFailureExtension.class)
class LoginTest {
    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    void setup() {
        logger.info("Setting up test");
        driver = DriverFactory.createDriver("chrome");
        driver.get(Config.getBaseUrl());
        loginPage = new LoginPage(driver);
    }

    @Test
    @Story("Valid Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user can login with valid credentials")
    void shouldLoginWithValidCredentials() {
        logger.info("Starting valid login test");

        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");

        assertThat(inventoryPage.isDisplayed())
            .as("Inventory page should be displayed after login")
            .isTrue();

        logger.info("Test completed successfully");
    }

    @Test
    @Story("Invalid Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify error message for invalid credentials")
    void shouldShowErrorForInvalidCredentials() {
        logger.info("Starting invalid login test");

        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();

        String errorMessage = loginPage.getErrorMessage();
        AllureAttachments.attachText("Error Message", errorMessage);

        assertThat(errorMessage)
            .as("Error message should indicate invalid credentials")
            .contains("Username and password do not match");

        logger.info("Test completed successfully");
    }

    @AfterEach
    void teardown() {
        logger.info("Tearing down test");
        if (driver != null) {
            driver.quit();
        }
    }
}
```

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: Logging sensitive data

```java
// ❌ BAD: Password in logs!
logger.info("Logging in with password: {}", password);

// ✅ GOOD: Mask sensitive data
logger.info("Logging in with password: ****");
logger.debug("Password length: {}", password.length());
```

### Mistake 2: Not attaching context on failure

```java
// ❌ BAD: Just screenshot
@Override
public void testFailed(ExtensionContext context, Throwable cause) {
    takeScreenshot();
}

// ✅ GOOD: Full context
@Override
public void testFailed(ExtensionContext context, Throwable cause) {
    takeScreenshot();
    attachPageSource();
    attachBrowserLogs();
    attachCurrentUrl();
    attachCookies();
}
```

---

## 🎯 Challenge 11.1: Allure Reporting Suite (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Implement comprehensive Allure reporting.

### Requirements
- Add @Epic, @Feature, @Story to all test classes
- Add @Step to all page object methods
- Implement screenshot attachment on failure
- Configure environment.properties for Allure

### Acceptance Criteria
- [ ] All tests organized by Epic/Feature/Story
- [ ] Steps visible in Allure report
- [ ] Screenshots attached to failed tests
- [ ] Environment info (browser, URL) in report

### ⏱️ Time: 60-90 minutes

---

## 🎯 Challenge 11.2: Logging Framework (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Implement structured logging.

### Requirements
- Configure logback.xml with console + file appenders
- Add logging to all page objects (INFO for actions, DEBUG for details)
- Include thread ID for parallel test debugging
- Suppress noisy third-party logs

### Acceptance Criteria
- [ ] Logs written to console and file
- [ ] Rolling file with 30-day retention
- [ ] All page actions logged at INFO level
- [ ] Selenium/RestAssured logs at WARN level

### ⏱️ Time: 45-60 minutes

---

## 🎯 Challenge 11.3: Flaky Test Management (Advanced)

### Scaffolding Level: 🔴 Independent

**Scenario:** Implement flaky test detection and handling.

### Requirements
- Create @RetryOnFailure annotation with configurable retries
- Track which tests needed retries
- Implement @Quarantine annotation to skip known flaky tests
- Generate flakiness report

### Acceptance Criteria
- [ ] Retry extension retries failed tests
- [ ] Retry count visible in report
- [ ] Quarantined tests skipped with reason
- [ ] Flakiness metrics tracked

### ⏱️ Time: 90-120 minutes

---

## 🔄 Module 11 Reflection

1. **What information should a failure report include** to debug without re-running?

2. **Explain the difference** between @Step and logger.info().

3. **When would you use WARN vs ERROR** log level?

4. **Rate your confidence (1-5):**
   - [ ] Configuring Allure reporting
   - [ ] Setting up Logback logging
   - [ ] Implementing screenshot on failure
   - [ ] Debugging flaky tests

---

## 🔗 Spaced Repetition Checkpoint

**Review earlier modules:**
- What's the purpose of connection pooling? (Module 10)
- Name the SOLID principles (Module 9)

**Revisit Module 11 in:**
- **Week 2**: Add Allure to all existing tests
- **Week 3**: Analyze flaky test patterns

---



# Module 12: CI/CD Integration & Capstone Project

## 🎯 Learning Objectives (Bloom's Taxonomy)

| Level | Objective | How You'll Demonstrate It |
|-------|-----------|---------------------------|
| **Remember** | List GitHub Actions workflow triggers | Self-check quiz |
| **Understand** | Explain why tests run in containers | Written explanation |
| **Apply** | Configure GitHub Actions pipeline | Challenge 12.1 |
| **Analyze** | Debug CI pipeline failures | Troubleshooting exercise |
| **Evaluate** | Choose parallel execution strategy | Decision matrix |
| **Create** | Build complete test framework | Capstone Project |

---

## 📖 Topic 12.1: Why CI/CD for Test Automation?

### 🤔 Why This Matters

> **Without CI/CD:** Developer pushes code. Forgets to run tests. Bug ships to production. Customer finds it.
> **With CI/CD:** Developer pushes code. Tests run automatically. Bug caught. Customer never sees it.

### 📚 The CI/CD Pipeline

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         CI/CD PIPELINE FOR TESTS                             │
│                                                                              │
│  ┌─────────┐    ┌─────────┐    ┌─────────┐    ┌─────────┐    ┌─────────┐   │
│  │  CODE   │───▶│  BUILD  │───▶│  TEST   │───▶│ REPORT  │───▶│ NOTIFY  │   │
│  │  PUSH   │    │         │    │         │    │         │    │         │   │
│  └─────────┘    └─────────┘    └─────────┘    └─────────┘    └─────────┘   │
│                                                                              │
│  Triggers:       Compile        Run tests     Generate       Slack/Email    │
│  • Push          Download       Parallel      Allure         on failure     │
│  • PR            dependencies   Headless      Publish                       │
│  • Schedule                                                                  │
│  • Manual                                                                    │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 📚 Benefits of CI/CD

| Benefit | Description |
|---------|-------------|
| **Early Detection** | Bugs found before merge |
| **Consistency** | Same environment every run |
| **Visibility** | Everyone sees test results |
| **Speed** | Parallel execution |
| **History** | Track trends over time |

---

## 📖 Topic 12.2: GitHub Actions

### 📚 Basic Workflow Structure

```yaml
# .github/workflows/test.yml
name: Test Automation

# ─── TRIGGERS ─────────────────────────────────────────────
on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]
  schedule:
    - cron: '0 6 * * *'  # Daily at 6 AM UTC
  workflow_dispatch:      # Manual trigger

# ─── JOBS ─────────────────────────────────────────────────
jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
          cache: maven

      - name: Install Chrome
        uses: browser-actions/setup-chrome@v1

      - name: Run Tests
        run: mvn test -Dheadless=true

      - name: Generate Allure Report
        if: always()  # Run even if tests fail
        run: mvn allure:report

      - name: Upload Test Results
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: allure-results
          path: target/allure-results
```

### 📚 Matrix Strategy (Multiple Browsers)

```yaml
jobs:
  test:
    runs-on: ubuntu-latest
    strategy:
      fail-fast: false  # Continue other browsers if one fails
      matrix:
        browser: [chrome, firefox, edge]

    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: Run Tests on ${{ matrix.browser }}
        run: mvn test -Dbrowser=${{ matrix.browser }} -Dheadless=true
```

### ✋ Self-Check Questions

<details>
<summary>1. What does `if: always()` do in a workflow step?</summary>

**Answer:** It runs the step regardless of whether previous steps passed or failed. Essential for report generation and artifact upload - you want reports even when tests fail.
</details>

<details>
<summary>2. Why use `fail-fast: false` in matrix strategy?</summary>

**Answer:** Without it, if Chrome tests fail, Firefox and Edge tests are cancelled. With `fail-fast: false`, all browsers complete, giving you full results.
</details>

---

## 📖 Topic 12.3: Docker for Test Automation

### 📚 Why Docker?

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                         WITHOUT DOCKER                                       │
│                                                                              │
│  Developer A: "Tests pass on my machine!"                                   │
│  Developer B: "They fail on mine..."                                        │
│  CI Server:   "They fail here too..."                                       │
│                                                                              │
│  Problem: Different Chrome versions, different Java versions, different OS  │
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│                         WITH DOCKER                                          │
│                                                                              │
│  Everyone: "Tests run in identical container. Same result everywhere."      │
│                                                                              │
│  Solution: Container has exact Chrome version, Java version, dependencies   │
└─────────────────────────────────────────────────────────────────────────────┘
```

### 📚 Dockerfile for Tests

```dockerfile
# Dockerfile
FROM maven:3.9-eclipse-temurin-21

# Install Chrome
RUN apt-get update && apt-get install -y \
    wget gnupg2 \
    && wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" \
       >> /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source
COPY . .

# Run tests
CMD ["mvn", "test", "-Dheadless=true"]
```

### 📚 Selenium Grid with Docker Compose

```yaml
# docker-compose.yml
version: '3.8'
services:
  # ─── Selenium Hub ───────────────────────────────────────
  selenium-hub:
    image: selenium/hub:latest
    ports:
      - "4444:4444"
    environment:
      - GRID_MAX_SESSION=10

  # ─── Chrome Nodes ───────────────────────────────────────
  chrome:
    image: selenium/node-chrome:latest
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
    deploy:
      replicas: 3  # 3 Chrome instances

  # ─── Firefox Nodes ──────────────────────────────────────
  firefox:
    image: selenium/node-firefox:latest
    depends_on:
      - selenium-hub
    environment:
      - SE_EVENT_BUS_HOST=selenium-hub
      - SE_EVENT_BUS_PUBLISH_PORT=4442
      - SE_EVENT_BUS_SUBSCRIBE_PORT=4443
    deploy:
      replicas: 2  # 2 Firefox instances

  # ─── Test Runner ────────────────────────────────────────
  tests:
    build: .
    depends_on:
      - chrome
      - firefox
    environment:
      - SELENIUM_REMOTE_URL=http://selenium-hub:4444/wd/hub
    volumes:
      - ./allure-results:/app/target/allure-results
```

---

## 📖 Topic 12.4: Parallel Execution

### 📚 JUnit 5 Parallel Configuration

```properties
# src/test/resources/junit-platform.properties

# Enable parallel execution
junit.jupiter.execution.parallel.enabled=true

# Run test classes in parallel
junit.jupiter.execution.parallel.mode.default=concurrent

# Run test methods within a class in parallel
junit.jupiter.execution.parallel.mode.classes.default=concurrent

# Fixed thread pool
junit.jupiter.execution.parallel.config.strategy=fixed
junit.jupiter.execution.parallel.config.fixed.parallelism=4
```

### 📚 Thread-Safe Test Design

```java
// ❌ BAD: Shared WebDriver - not thread-safe
public class BaseTest {
    protected static WebDriver driver;  // Static = shared!
}

// ✅ GOOD: ThreadLocal WebDriver - thread-safe
public class BaseTest {
    protected static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeEach
    void setup() {
        WebDriver driver = DriverFactory.createDriver();
        driverThreadLocal.set(driver);
    }

    protected WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @AfterEach
    void teardown() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
```

---

## 🔨 Worked Example: Complete CI/CD Pipeline

```yaml
# .github/workflows/test-automation.yml
name: E-Commerce Test Suite

on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]
  schedule:
    - cron: '0 6 * * *'
  workflow_dispatch:

env:
  JAVA_VERSION: '21'

jobs:
  # ─── Unit & API Tests ───────────────────────────────────
  api-tests:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          java-version: ${{ env.JAVA_VERSION }}
          distribution: 'temurin'
          cache: maven

      - name: Run API Tests
        run: mvn test -Dgroups=api

      - name: Upload Results
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: api-test-results
          path: target/allure-results

  # ─── UI Tests (Matrix) ──────────────────────────────────
  ui-tests:
    runs-on: ubuntu-latest
    strategy:
      fail-fast: false
      matrix:
        browser: [chrome, firefox]

    steps:
      - uses: actions/checkout@v4

      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          java-version: ${{ env.JAVA_VERSION }}
          distribution: 'temurin'
          cache: maven

      - name: Install Browser
        uses: browser-actions/setup-chrome@v1
        if: matrix.browser == 'chrome'

      - name: Run UI Tests on ${{ matrix.browser }}
        run: mvn test -Dgroups=ui -Dbrowser=${{ matrix.browser }} -Dheadless=true

      - name: Upload Results
        if: always()
        uses: actions/upload-artifact@v4
        with:
          name: ui-test-results-${{ matrix.browser }}
          path: target/allure-results

  # ─── Generate Report ────────────────────────────────────
  report:
    needs: [api-tests, ui-tests]
    if: always()
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v4

      - name: Download All Results
        uses: actions/download-artifact@v4
        with:
          path: allure-results
          merge-multiple: true

      - name: Generate Allure Report
        uses: simple-elf/allure-report-action@master
        with:
          allure_results: allure-results
          allure_report: allure-report

      - name: Publish to GitHub Pages
        uses: peaceiris/actions-gh-pages@v3
        with:
          github_token: ${{ secrets.GITHUB_TOKEN }}
          publish_dir: allure-report

  # ─── Notify on Failure ──────────────────────────────────
  notify:
    needs: [api-tests, ui-tests]
    if: failure()
    runs-on: ubuntu-latest

    steps:
      - name: Send Slack Notification
        uses: 8398a7/action-slack@v3
        with:
          status: failure
          fields: repo,message,commit,author
        env:
          SLACK_WEBHOOK_URL: ${{ secrets.SLACK_WEBHOOK }}
```

---

## ⚠️ Common Mistakes & How to Fix Them

### Mistake 1: Not running in headless mode

```yaml
# ❌ BAD: Tries to open browser window in CI
- run: mvn test

# ✅ GOOD: Headless mode for CI
- run: mvn test -Dheadless=true
```

### Mistake 2: Not uploading artifacts on failure

```yaml
# ❌ BAD: Only uploads if tests pass
- name: Upload Results
  uses: actions/upload-artifact@v4
  with:
    path: target/allure-results

# ✅ GOOD: Always upload
- name: Upload Results
  if: always()
  uses: actions/upload-artifact@v4
  with:
    path: target/allure-results
```

---

## 🎯 Challenge 12.1: GitHub Actions Pipeline (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Create complete CI/CD pipeline.

### Requirements
- Trigger on push, PR, schedule, and manual
- Matrix strategy for Chrome and Firefox
- Generate and publish Allure report
- Slack notification on failure

### Acceptance Criteria
- [ ] Pipeline triggers on all events
- [ ] Tests run on both browsers
- [ ] Allure report published to GitHub Pages
- [ ] Slack notification configured

### ⏱️ Time: 90-120 minutes

---

## 🎯 Challenge 12.2: Docker Selenium Grid (Intermediate)

### Scaffolding Level: 🟡 Semi-Guided

**Scenario:** Run tests on Selenium Grid in Docker.

### Requirements
- docker-compose.yml with Hub + Chrome + Firefox nodes
- Tests connect to remote grid
- Scale nodes (3 Chrome, 2 Firefox)
- Video recording on failure (optional)

### Acceptance Criteria
- [ ] Grid starts with `docker-compose up`
- [ ] Tests execute on remote grid
- [ ] Multiple browser nodes running
- [ ] Results accessible after run

### ⏱️ Time: 90-120 minutes

---

## 🎓 Capstone Project: E-Commerce Test Framework

### 📋 Project Overview

Build a **complete, production-ready** test automation framework for an e-commerce application (SauceDemo or similar).

### 📋 Requirements Checklist

#### 1. Framework Architecture (20%)
- [ ] Layered project structure (tests → pages → core → infrastructure)
- [ ] Configuration management (dev, staging, prod environments)
- [ ] Page Object Model with fluent interfaces
- [ ] Utility classes (waits, screenshots, data)

#### 2. Test Coverage (25%)
- [ ] **UI Tests (10+ tests)**
  - Login (valid, invalid, locked user)
  - Product listing and sorting
  - Add/remove cart items
  - Complete checkout flow
  - Logout

- [ ] **API Tests (10+ tests)**
  - CRUD operations
  - Authentication
  - Error responses (400, 401, 404)
  - Schema validation

- [ ] **Hybrid Tests (5+ tests)**
  - API setup → UI verification
  - UI action → API verification

#### 3. Test Data (15%)
- [ ] External data files (JSON or CSV)
- [ ] Faker-based data factories
- [ ] Parameterized tests (@ParameterizedTest)
- [ ] Environment-specific configuration

#### 4. Reporting & Logging (20%)
- [ ] Allure with @Epic, @Feature, @Story, @Step
- [ ] Screenshots on failure
- [ ] Structured logging (Logback)
- [ ] API request/response logging

#### 5. CI/CD (20%)
- [ ] GitHub Actions workflow
- [ ] Parallel execution
- [ ] Allure report publishing
- [ ] Docker support (optional)

### 📋 Evaluation Rubric

| Category | Excellent (90-100%) | Good (70-89%) | Needs Work (<70%) |
|----------|---------------------|---------------|-------------------|
| **Code Quality** | Clean, patterns used, SOLID | Mostly clean, some patterns | Messy, no patterns |
| **Test Coverage** | 25+ tests, all types | 15-24 tests, most types | <15 tests |
| **Framework** | Extensible, maintainable | Works but rigid | Hard to maintain |
| **Documentation** | Complete README, comments | Basic README | No documentation |
| **CI/CD** | Full pipeline, reports | Basic pipeline | No CI/CD |

### 📋 Submission Deliverables

1. **GitHub Repository** with complete source code
2. **README.md** with:
   - Setup instructions
   - How to run tests
   - Architecture overview
3. **Sample Allure Report** (screenshot or link)
4. **Architecture Diagram** (can be ASCII or image)

### ⏱️ Time Estimate: 6-8 hours

---

## 🔄 Module 12 Reflection

1. **Why run tests in CI** instead of just locally?

2. **Explain the benefit** of Docker for test automation.

3. **What makes tests thread-safe** for parallel execution?

4. **Rate your confidence (1-5):**
   - [ ] Writing GitHub Actions workflows
   - [ ] Creating Docker configurations
   - [ ] Configuring parallel execution
   - [ ] Building complete frameworks

---

## 🔗 Final Spaced Repetition

**Review all modules:**
- Module 1: What's the difference between SDET and QA?
- Module 3: How do you handle StaleElementReferenceException?
- Module 6: What's the Given-When-Then pattern?
- Module 9: Name the SOLID principles

**Continue practicing:**
- **Week 1**: Complete capstone project
- **Week 2**: Add features to capstone
- **Month 1**: Apply to SDET positions!

---



# 📋 Appendix A: Interview Preparation

## Common SDET Interview Topics

### Technical Questions

#### Selenium & WebDriver
1. Explain the difference between `findElement()` and `findElements()`
2. What are the different types of waits in Selenium?
3. How do you handle dynamic elements?
4. Explain the Page Object Model and its benefits
5. How do you handle iframes and multiple windows?
6. What is the difference between `close()` and `quit()`?
7. How do you handle StaleElementReferenceException?

#### API Testing
1. Explain RESTful API principles
2. What are the differences between PUT and PATCH?
3. How do you validate JSON responses?
4. Explain authentication types (Basic, Bearer, OAuth)
5. How do you handle API chaining?

#### Java & OOP
1. Explain SOLID principles with examples
2. What design patterns have you used in test automation?
3. Explain the difference between abstract class and interface
4. How do you handle exceptions in test automation?
5. Explain Java Collections and when to use each

#### Framework Design
1. How would you design a test automation framework from scratch?
2. How do you handle test data management?
3. Explain your CI/CD pipeline setup
4. How do you handle flaky tests?
5. How do you generate test reports?

### Coding Challenges

#### Challenge 1: Implement Custom Wait
```java
// Implement a method that waits for an element to have specific text
public void waitForElementText(By locator, String expectedText, Duration timeout) {
    // Your implementation
}
```

#### Challenge 2: Table Data Extraction
```java
// Extract all data from an HTML table into a List<Map<String, String>>
public List<Map<String, String>> extractTableData(By tableLocator) {
    // Your implementation
}
```

#### Challenge 3: API Response Validator
```java
// Create a generic response validator that checks status code and body
public class ResponseValidator {
    public ResponseValidator expectStatus(int statusCode) { }
    public ResponseValidator expectBody(String path, Object value) { }
    public void validate(Response response) { }
}
```

### Behavioral Questions

1. Describe a challenging bug you found through automation
2. How do you prioritize which tests to automate?
3. How do you handle test maintenance as the application changes?
4. Describe your experience with CI/CD integration
5. How do you collaborate with developers and manual testers?

---

# 📋 Appendix B: Quick Reference

## Locator Strategies Priority
1. **ID** - Most reliable, fastest
2. **Name** - Good alternative to ID
3. **CSS Selector** - Flexible, performant
4. **XPath** - Most flexible, slower
5. **Link Text** - Only for anchor elements
6. **Class Name** - Use with caution (often not unique)

## Wait Cheat Sheet
```java
// Explicit Wait - Recommended
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

// Fluent Wait - For polling
Wait<WebDriver> fluentWait = new FluentWait<>(driver)
    .withTimeout(Duration.ofSeconds(30))
    .pollingEvery(Duration.ofMillis(500))
    .ignoring(NoSuchElementException.class);

// Implicit Wait - Set once, applies globally (not recommended with explicit)
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
```

## AssertJ Cheat Sheet
```java
// Basic assertions
assertThat(value).isEqualTo(expected);
assertThat(value).isNotNull();
assertThat(value).isTrue();

// String assertions
assertThat(text).contains("expected");
assertThat(text).startsWith("prefix");
assertThat(text).matches("regex.*");

// Collection assertions
assertThat(list).hasSize(5);
assertThat(list).contains("item1", "item2");
assertThat(list).extracting("name").containsExactly("a", "b");

// Exception assertions
assertThatThrownBy(() -> method())
    .isInstanceOf(IllegalArgumentException.class)
    .hasMessageContaining("error");
```

## REST Assured Cheat Sheet
```java
// GET request
given()
    .queryParam("key", "value")
    .header("Authorization", "Bearer token")
.when()
    .get("/endpoint")
.then()
    .statusCode(200)
    .body("field", equalTo("value"));

// POST request
given()
    .contentType(ContentType.JSON)
    .body(requestBody)
.when()
    .post("/endpoint")
.then()
    .statusCode(201);

// Extract response
String value = response.jsonPath().getString("field");
List<String> values = response.jsonPath().getList("items.name");
```

## Maven Commands
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=LoginTest

# Run specific test method
mvn test -Dtest=LoginTest#testValidLogin

# Run with specific profile
mvn test -Psmoke

# Generate Allure report
mvn allure:serve
```

---

# 📋 Appendix C: Resources

## Official Documentation
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [REST Assured Wiki](https://github.com/rest-assured/rest-assured/wiki)
- [Allure Framework](https://docs.qameta.io/allure/)
- [AssertJ](https://assertj.github.io/doc/)

## Practice Sites
- [SauceDemo](https://www.saucedemo.com) - E-commerce testing
- [The Internet](https://the-internet.herokuapp.com) - Various UI scenarios
- [JSONPlaceholder](https://jsonplaceholder.typicode.com) - API testing
- [ReqRes](https://reqres.in) - API testing with auth
- [Automation Exercise](https://automationexercise.com) - Full e-commerce

## Books
- "Java Test Automation" by Alan Richardson
- "Selenium WebDriver" by Boni García
- "Clean Code" by Robert C. Martin
- "Design Patterns" by Gang of Four

---

## 🎉 Congratulations!

Upon completing this bootcamp, you will have:

✅ **Technical Skills**
- Selenium WebDriver mastery
- API testing expertise
- Java programming proficiency
- Framework design knowledge

✅ **Practical Experience**
- 35+ hands-on coding challenges
- Real-world project implementation
- CI/CD pipeline setup

✅ **Interview Readiness**
- Common question preparation
- Coding challenge practice
- Portfolio project for demonstration

**You are now prepared for SDET roles at most technology companies!**

---

*Last Updated: January 2026*
*Version: 1.0*
