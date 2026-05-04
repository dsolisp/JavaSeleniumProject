package com.automation.backend;

import com.automation.extensions.WebDriverExtension;
import com.automation.pages.sauce.InventoryPage;
import com.automation.pages.sauce.LoginPage;
import com.automation.data.UserFactory;
import com.automation.data.UserCredentials;
import com.automation.config.Constants;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Hybrid DB Testing Patterns
 * Equivalent to Cypress database.cy.ts.
 * Demonstrates 5 patterns for combining UI automation with a local SQLite database.
 */
@Epic("Database Testing")
@Feature("Hybrid Database/UI Patterns")
@DisplayName("Database Tests")
@Tag("api")
@Tag("database")
@ExtendWith(WebDriverExtension.class)
class DatabaseTest {

    private static final String DB_PATH = System.getProperty("user.dir") + "/" + Constants.Paths.DB;
    private static final String JDBC_URL = "jdbc:sqlite:" + DB_PATH;

    @BeforeAll
    static void setupDatabase() throws Exception {
        ProcessBuilder pb = new ProcessBuilder("bash", "scripts/seed_db.sh");
        pb.directory(new java.io.File(System.getProperty("user.dir")));
        Process p = pb.start();
        p.waitFor();
    }

    @Test
    @DisplayName("Example 1: Seed -> Login (Precondition)")
    void example1SeedsUserThenLogin(WebDriver driver) throws Exception {
        int testUserId = 101;
        String testUsername = "db_user";

        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pstmt = conn.prepareStatement("INSERT OR IGNORE INTO users VALUES (?, ?, ?)")) {
            pstmt.setInt(1, testUserId);
            pstmt.setString(2, testUsername);
            pstmt.setString(3, "customer");
            pstmt.executeUpdate();
        }

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(testUsername, "password123");

        assertThat(loginPage.hasLoginError()).isTrue();
        assertThat(loginPage.getLoginErrorMessage()).contains("do not match");
    }

    @Test
    @DisplayName("Example 2: UI Action -> DB Verification (Postcondition)")
    void example2LoginThenVerifyDb(WebDriver driver) throws Exception {
        UserCredentials user = UserFactory.standard();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(user.getUsername(), user.getPassword());

        assertThat(driver.getCurrentUrl()).contains("inventory.html");

        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username=?")) {
            pstmt.setString(1, user.getUsername());
            ResultSet rs = pstmt.executeQuery();
            
            assertThat(rs.next()).isTrue();
            assertThat(rs.getString("role")).isNotNull();
            assertThat(rs.next()).isFalse(); // only one
        }
    }

    @Test
    @DisplayName("Example 3: DB Data -> UI Assertion (Data-Driven)")
    void example3VerifyUiPriceMatchesDb(WebDriver driver) throws Exception {
        double dbPrice;
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT price FROM products WHERE name=?")) {
            pstmt.setString(1, "Sauce Labs Backpack");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            dbPrice = rs.getDouble("price");
        }

        UserCredentials user = UserFactory.standard();
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = loginPage.open().login(user.getUsername(), user.getPassword());

        String priceText = inventoryPage.getItemPrices().get(0);
        assertThat(priceText).contains(String.valueOf(dbPrice));
    }

    @Test
    @DisplayName("Example 4: Data-Driven Login (Iterate from DB)")
    void example4LoginEveryCustomer(WebDriver driver) throws Exception {
        List<String> usernames = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE role=? AND username != ?")) {
            pstmt.setString(1, "customer");
            pstmt.setString(2, "db_user");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }
        }

        for (String username : usernames) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            InventoryPage inventoryPage = loginPage.login(username, UserFactory.standard().getPassword());
            assertThat(driver.getCurrentUrl()).contains("inventory.html");
            inventoryPage.logout();
        }
    }

    @Test
    @DisplayName("Example 5: CRUD Lifecycle")
    void example5CrudLifecycleInDb() throws Exception {
        int newUserId = 999;

        try (Connection conn = DriverManager.getConnection(JDBC_URL)) {
            // Create
            try (PreparedStatement pstmt = conn.prepareStatement("INSERT OR REPLACE INTO users VALUES (?, ?, ?)")) {
                pstmt.setInt(1, newUserId);
                pstmt.setString(2, "test_cleanup_user");
                pstmt.setString(3, "tester");
                pstmt.executeUpdate();
            }

            // Read
            try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id=?")) {
                pstmt.setInt(1, newUserId);
                ResultSet rs = pstmt.executeQuery();
                assertThat(rs.next()).isTrue();
                assertThat(rs.getString("username")).isEqualTo("test_cleanup_user");
            }

            // Delete
            try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE id=?")) {
                pstmt.setInt(1, newUserId);
                pstmt.executeUpdate();
            }

            // Verify deletion
            try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id=?")) {
                pstmt.setInt(1, newUserId);
                ResultSet rs = pstmt.executeQuery();
                assertThat(rs.next()).isFalse();
            }
        }
    }
}
