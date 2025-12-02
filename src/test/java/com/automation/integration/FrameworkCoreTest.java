package com.automation.integration;

import com.automation.utils.SqlConnection;
import com.automation.utils.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;

import static org.assertj.core.api.Assertions.*;

/**
 * Framework core integration tests.
 * Tests WebDriverFactory, DatabaseFactory, and BasePage integration.
 * Equivalent to Python's tests/integration/test_framework_core.py
 */
@Epic("Integration Testing")
@Feature("Framework Core")
@DisplayName("Framework Core Integration Tests")
@Tag("integration")
class FrameworkCoreTest {

    private WebDriver driver;
    private Path tempDbPath;

    @AfterEach
    void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }
        if (tempDbPath != null) {
            Files.deleteIfExists(tempDbPath);
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // WEBDRIVER FACTORY TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("WebDriver Factory")
    @Description("Test WebDriver creation with headless Chrome")
    @DisplayName("WebDriver factory should create headless Chrome driver")
    void webDriverFactoryShouldCreateHeadlessChromeDriver() {
        driver = WebDriverFactory.createDriver("chrome", true);
        
        assertThat(driver).isNotNull();
        
        // Navigate to data URL with test HTML
        String testHtml = "data:text/html,<html><body><h1>Test Page</h1>" +
                "<input name='test' value='framework'></body></html>";
        driver.get(testHtml);
        
        WebElement element = driver.findElement(By.name("test"));
        assertThat(element).isNotNull();
        assertThat(element.getAttribute("value")).isEqualTo("framework");
    }

    @Test
    @Story("WebDriver Factory")
    @Description("Test WebDriver can navigate and find elements")
    @DisplayName("WebDriver should navigate and find elements")
    void webDriverShouldNavigateAndFindElements() {
        driver = WebDriverFactory.createDriver("chrome", true);
        
        String testHtml = "data:text/html," +
                "<html><body>" +
                "<h1 id='title'>Integration Test</h1>" +
                "<input id='input1' type='text'>" +
                "<button id='btn1'>Click Me</button>" +
                "</body></html>";
        driver.get(testHtml);
        
        assertThat(driver.findElement(By.id("title")).getText())
                .isEqualTo("Integration Test");
        assertThat(driver.findElement(By.id("input1"))).isNotNull();
        assertThat(driver.findElement(By.id("btn1"))).isNotNull();
    }

    // ═══════════════════════════════════════════════════════════════════
    // DATABASE FACTORY TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Database Factory")
    @Description("Test database connection creation")
    @DisplayName("Database factory should create SQLite connection")
    void databaseFactoryShouldCreateConnection() throws Exception {
        String chinookDb = "src/main/resources/data/chinook.db";
        
        try (Connection conn = SqlConnection.getConnection(chinookDb)) {
            assertThat(conn).isNotNull();
            assertThat(conn.isClosed()).isFalse();
            
            // Verify can query
            var tables = SqlConnection.getTableNames(conn);
            assertThat(tables).isNotEmpty();
        }
    }

    @Test
    @Story("Database Factory")
    @Description("Test database CRUD operations")
    @DisplayName("Database should support CRUD operations")
    void databaseShouldSupportCrudOperations() throws Exception {
        tempDbPath = Files.createTempFile("test_db_", ".db");
        
        try (Connection conn = java.sql.DriverManager.getConnection(
                "jdbc:sqlite:" + tempDbPath.toAbsolutePath())) {
            
            // Create table
            try (var stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE test_results (id INTEGER PRIMARY KEY, " +
                        "test_name TEXT, result TEXT)");
            }
            
            // Insert
            long id = SqlConnection.insert(conn, "test_results", 
                    java.util.Map.of("test_name", "test1", "result", "passed"));
            assertThat(id).isGreaterThan(0);
            
            // Read
            var row = SqlConnection.fetchOne(conn, 
                    "SELECT * FROM test_results WHERE id = ?", id);
            assertThat(row).isNotNull();
            assertThat(row.get("test_name")).isEqualTo("test1");
            
            // Update
            int updated = SqlConnection.update(conn, "test_results",
                    java.util.Map.of("result", "failed"), "id = ?", id);
            assertThat(updated).isEqualTo(1);
            
            // Delete
            int deleted = SqlConnection.delete(conn, "test_results", "id = ?", id);
            assertThat(deleted).isEqualTo(1);
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // BASE PAGE INTEGRATION TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("BasePage Integration")
    @Description("Test BasePage navigation and element interaction")
    @DisplayName("BasePage should navigate and interact with elements")
    void basePageShouldNavigateAndInteract() {
        driver = WebDriverFactory.createDriver("chrome", true);
        TestPage testPage = new TestPage(driver);

        String testHtml = "data:text/html," +
                "<html><head><title>Framework Test Page</title></head><body>" +
                "<h1 id='title'>Test Framework</h1>" +
                "<input id='input1' name='testinput' placeholder='Enter text'>" +
                "<button id='btn1'>Click Me</button>" +
                "</body></html>";

        testPage.navigateTo(testHtml);

        // Test title
        assertThat(testPage.getTitle()).contains("Framework Test Page");

        // Test finding elements
        assertThat(testPage.checkVisible(By.id("title"))).isTrue();
        assertThat(testPage.checkVisible(By.id("input1"))).isTrue();

        // Test typing
        testPage.typeText(By.id("input1"), "Hello Framework!");
        WebElement input = driver.findElement(By.id("input1"));
        assertThat(input.getDomProperty("value")).isEqualTo("Hello Framework!");

        // Test clicking
        testPage.clickElement(By.id("btn1"));
    }

    @Test
    @Story("BasePage Integration")
    @Description("Test BasePage element actions integration")
    @DisplayName("BasePage element actions should work correctly")
    void basePageElementActionsShouldWork() {
        driver = WebDriverFactory.createDriver("chrome", true);
        TestPage testPage = new TestPage(driver);

        String testHtml = "data:text/html," +
                "<html><body>" +
                "<input id='test' name='test' value='initial'>" +
                "<button id='clickme'>Click</button>" +
                "</body></html>";
        testPage.navigateTo(testHtml);

        // Find element
        WebElement element = testPage.findElement(By.id("test"));
        assertThat(element).isNotNull();

        // Type in element
        testPage.typeText(By.id("test"), "new value");

        // Click button
        testPage.clickElement(By.id("clickme"));

        // Verify no exceptions
        assertThat(driver.findElement(By.id("test"))).isNotNull();
    }

    @Test
    @Story("Framework Integration")
    @Description("Test complete framework integration with driver and database")
    @DisplayName("Framework should integrate driver and database")
    void frameworkShouldIntegrateDriverAndDatabase() throws Exception {
        driver = WebDriverFactory.createDriver("chrome", true);
        tempDbPath = Files.createTempFile("framework_test_", ".db");

        try (Connection conn = java.sql.DriverManager.getConnection(
                "jdbc:sqlite:" + tempDbPath.toAbsolutePath())) {

            // Create test table
            try (var stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE test_log (id INTEGER PRIMARY KEY, " +
                        "action TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");
            }

            TestPage testPage = new TestPage(driver);

            // Navigate
            String testHtml = "data:text/html,<html><body>" +
                    "<h1>Integration Test</h1><p>Framework working!</p></body></html>";
            testPage.navigateTo(testHtml);

            assertThat(testPage.getCurrentPageUrl()).contains("data:text/html");

            // Log action to database
            SqlConnection.insert(conn, "test_log",
                    java.util.Map.of("action", "navigation_completed"));

            // Verify log
            var logs = SqlConnection.fetchAll(conn, "SELECT * FROM test_log");
            assertThat(logs).hasSize(1);
            assertThat(logs.get(0).get("action")).isEqualTo("navigation_completed");
        }
    }
}

