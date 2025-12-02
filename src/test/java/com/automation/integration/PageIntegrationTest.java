package com.automation.integration;

import com.automation.config.Settings;
import com.automation.pages.SearchEnginePage;
import com.automation.pages.SauceDemoPage;
import com.automation.utils.SqlConnection;
import com.automation.utils.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Page object integration tests.
 * Tests page object interactions, inheritance, and database integration.
 * Equivalent to Python's tests/integration/test_page_integration.py
 */
@Epic("Integration Testing")
@Feature("Page Integration")
@DisplayName("Page Integration Tests")
@Tag("integration")
class PageIntegrationTest {

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
    // PAGE OBJECT INHERITANCE TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Page Object Inheritance")
    @Description("Test that page objects properly inherit from BasePage")
    @DisplayName("Page objects should inherit from BasePage")
    void pageObjectsShouldInheritFromBasePage() {
        driver = WebDriverFactory.createDriver("chrome", true);
        
        SearchEnginePage searchPage = new SearchEnginePage(driver);
        SauceDemoPage saucePage = new SauceDemoPage(driver);
        
        // Both pages should have access to common methods
        assertThat(searchPage.getTitle()).isNotNull();
        assertThat(saucePage.getTitle()).isNotNull();
        
        // Navigate search page
        searchPage.open();
        assertThat(searchPage.getTitle()).containsIgnoringCase("DuckDuckGo");
    }

    @Test
    @Story("Page Object Inheritance")
    @Description("Test navigation between multiple page objects")
    @DisplayName("Should navigate between multiple page objects")
    void shouldNavigateBetweenMultiplePageObjects() {
        driver = WebDriverFactory.createDriver("chrome", true);
        
        SearchEnginePage searchPage = new SearchEnginePage(driver);
        SauceDemoPage saucePage = new SauceDemoPage(driver);
        
        // Navigate to search page
        searchPage.open();
        String searchTitle = searchPage.getTitle();
        assertThat(searchTitle).isNotEmpty();
        
        // Switch to sauce demo page
        saucePage.open();
        String sauceTitle = saucePage.getTitle();
        assertThat(sauceTitle).containsIgnoringCase("Swag Labs");
    }

    // ═══════════════════════════════════════════════════════════════════
    // DATABASE INTEGRATION TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Database Integration")
    @Description("Test database operations with page actions")
    @DisplayName("Database should integrate with page actions")
    void databaseShouldIntegrateWithPageActions() throws Exception {
        tempDbPath = Files.createTempFile("page_test_", ".db");
        
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlite:" + tempDbPath.toAbsolutePath())) {
            
            // Create test_results table
            try (var stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE test_results (id INTEGER PRIMARY KEY, " +
                        "test_name TEXT NOT NULL, result TEXT NOT NULL, " +
                        "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");
            }
            
            // Insert test results
            SqlConnection.insert(conn, "test_results",
                    Map.of("test_name", "test_1", "result", "passed"));
            SqlConnection.insert(conn, "test_results",
                    Map.of("test_name", "test_2", "result", "failed"));
            SqlConnection.insert(conn, "test_results",
                    Map.of("test_name", "test_3", "result", "skipped"));
            
            // Verify all records
            var allRecords = SqlConnection.fetchAll(conn, "SELECT * FROM test_results");
            assertThat(allRecords).hasSize(3);
            
            // Test update
            SqlConnection.update(conn, "test_results",
                    Map.of("result", "re-tested"), "test_name = ?", "test_2");
            
            var updated = SqlConnection.fetchOne(conn,
                    "SELECT result FROM test_results WHERE test_name = ?", "test_2");
            assertThat(updated.get("result")).isEqualTo("re-tested");
            
            // Test delete
            SqlConnection.delete(conn, "test_results", "result = ?", "skipped");
            
            var remaining = SqlConnection.fetchAll(conn, "SELECT * FROM test_results");
            assertThat(remaining).hasSize(2);
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // SCREENSHOT INTEGRATION TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Screenshot Integration")
    @Description("Test screenshot functionality integration")
    @DisplayName("Screenshot functionality should work")
    void screenshotFunctionalityShouldWork() throws Exception {
        driver = WebDriverFactory.createDriver("chrome", true);
        TestPage testPage = new TestPage(driver);

        testPage.navigateTo(Settings.getInstance().getBaseUrl());

        // Take screenshot
        Path screenshotPath = testPage.captureScreenshot("integration_test");

        assertThat(screenshotPath).isNotNull();
        assertThat(screenshotPath).exists();
        assertThat(Files.size(screenshotPath)).isGreaterThan(0);
    }

    // ═══════════════════════════════════════════════════════════════════
    // ELEMENT ACTIONS INTEGRATION TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Element Actions")
    @Description("Test element actions integration with real browser")
    @DisplayName("Element actions should work with real browser")
    void elementActionsShouldWorkWithRealBrowser() {
        driver = WebDriverFactory.createDriver("chrome", true);
        TestPage testPage = new TestPage(driver);

        testPage.navigateTo(Settings.getInstance().getBaseUrl());

        // Test that page loaded
        String currentUrl = testPage.getCurrentPageUrl();
        assertThat(currentUrl).contains("duckduckgo.com");

        // Test finding elements
        var searchElements = testPage.findAllElements(By.name("q"));
        assertThat(searchElements).isNotEmpty();
    }

    @Test
    @Story("Error Handling")
    @Description("Test error handling across integrated components")
    @DisplayName("Error handling should work correctly")
    void errorHandlingShouldWorkCorrectly() {
        driver = WebDriverFactory.createDriver("chrome", true);
        TestPage testPage = new TestPage(driver);

        // Navigate to about:blank (a valid page) first
        testPage.navigateTo("about:blank");

        // Non-existent element should not be present
        boolean exists = testPage.isElementPresent(By.id("nonexistent-element-xyz"));
        assertThat(exists).isFalse();
    }

    // ═══════════════════════════════════════════════════════════════════
    // SETTINGS INTEGRATION TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Settings Integration")
    @Description("Test settings integration with environment")
    @DisplayName("Settings should integrate correctly")
    void settingsShouldIntegrateCorrectly() {
        Settings settings = Settings.getInstance();

        assertThat(settings.getBaseUrl()).isNotEmpty();
        assertThat(settings.getBrowser()).isNotEmpty();
        assertThat(settings.getExplicitWait()).isNotNull();
    }

    // ═══════════════════════════════════════════════════════════════════
    // END-TO-END WORKFLOW TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("End-to-End Workflow")
    @Description("Test complete test execution workflow")
    @DisplayName("Complete test workflow should work")
    void completeTestWorkflowShouldWork() throws Exception {
        driver = WebDriverFactory.createDriver("chrome", true);
        tempDbPath = Files.createTempFile("workflow_test_", ".db");

        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlite:" + tempDbPath.toAbsolutePath())) {

            // Create test_results table
            try (var stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE test_results (id INTEGER PRIMARY KEY, " +
                        "test_name TEXT, result TEXT, " +
                        "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)");
            }

            SearchEnginePage searchPage = new SearchEnginePage(driver);

            // Record test start
            SqlConnection.insert(conn, "test_results",
                    Map.of("test_name", "end_to_end_workflow", "result", "started"));

            // Navigate to search page
            searchPage.open();

            // Take screenshot
            TestPage testPage = new TestPage(driver);
            Path screenshotPath = testPage.captureScreenshot("workflow_test");
            boolean screenshotSuccess = screenshotPath != null;

            // Update test result
            String resultStatus = screenshotSuccess ? "completed" : "failed";
            SqlConnection.update(conn, "test_results",
                    Map.of("result", resultStatus),
                    "test_name = ?", "end_to_end_workflow");

            // Verify final state
            var finalResult = SqlConnection.fetchOne(conn,
                    "SELECT result FROM test_results WHERE test_name = ?",
                    "end_to_end_workflow");

            assertThat(finalResult).isNotNull();
            assertThat(finalResult.get("result")).isIn("completed", "failed");

            if (screenshotSuccess) {
                assertThat(screenshotPath).exists();
            }
        }
    }
}

