package com.automation.unit;

import com.automation.utils.SqlConnection;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for SQL connection utilities.
 * Tests core database functions with SQLite.
 * Equivalent to Python's tests/unit/test_sql_functions.py
 */
@Epic("Database Testing")
@Feature("SQL Connection")
@DisplayName("SQL Connection Tests")
class SqlConnectionTest {

    private Path tempDbPath;
    private Connection conn;

    @BeforeEach
    void setUp() throws Exception {
        // Create a temporary SQLite database
        tempDbPath = Files.createTempFile("test_db_", ".db");
        conn = java.sql.DriverManager.getConnection("jdbc:sqlite:" + tempDbPath.toAbsolutePath());
        
        // Create test table
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)");
            stmt.execute("INSERT INTO users (name, age) VALUES ('Alice', 30)");
            stmt.execute("INSERT INTO users (name, age) VALUES ('Bob', 25)");
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
        Files.deleteIfExists(tempDbPath);
    }

    // ═══════════════════════════════════════════════════════════════════
    // IDENTIFIER VALIDATION TESTS
    // ═══════════════════════════════════════════════════════════════════

    @ParameterizedTest
    @ValueSource(strings = {"users", "test_data", "Table123", "_private"})
    @Story("Identifier Validation")
    @Description("Valid identifiers should be accepted")
    @DisplayName("Valid identifiers should pass validation")
    void validIdentifiersShouldPass(String identifier) {
        assertThat(SqlConnection.validateIdentifier(identifier, "table"))
                .isEqualTo(identifier);
    }

    @ParameterizedTest
    @ValueSource(strings = {"users; DROP", "table-name", "user's", ""})
    @Story("Identifier Validation")
    @Description("Invalid identifiers should be rejected")
    @DisplayName("Invalid identifiers should throw exception")
    void invalidIdentifiersShouldThrow(String identifier) {
        assertThatThrownBy(() -> SqlConnection.validateIdentifier(identifier, "table"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Story("Identifier Validation")
    @Description("Null identifier should be rejected")
    @DisplayName("Null identifier should throw exception")
    void nullIdentifierShouldThrow() {
        assertThatThrownBy(() -> SqlConnection.validateIdentifier(null, "table"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // ═══════════════════════════════════════════════════════════════════
    // CONNECTION TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Connection")
    @Description("Connection to non-existent file should fail")
    @DisplayName("Get connection should throw for non-existent file")
    void getConnectionShouldThrowForNonExistentFile() {
        assertThatThrownBy(() -> SqlConnection.getConnection("nonexistent.db"))
                .isInstanceOf(SQLException.class)
                .hasMessageContaining("not found");
    }

    @Test
    @Story("Connection")
    @Description("Connection to valid database should succeed")
    @DisplayName("Get connection should succeed for valid file")
    void getConnectionShouldSucceedForValidFile() throws SQLException {
        try (Connection testConn = SqlConnection.getConnection(tempDbPath.toString())) {
            assertThat(testConn).isNotNull();
            assertThat(testConn.isClosed()).isFalse();
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // QUERY TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Query Execution")
    @Description("FetchOne should return single result")
    @DisplayName("Fetch one should return single row")
    void fetchOneShouldReturnSingleRow() throws SQLException {
        Map<String, Object> row = SqlConnection.fetchOne(conn, 
                "SELECT name FROM users WHERE age = ?", 30);
        
        assertThat(row).isNotNull();
        assertThat(row.get("name")).isEqualTo("Alice");
    }

    @Test
    @Story("Query Execution")
    @Description("FetchOne should return null for no results")
    @DisplayName("Fetch one should return null when no match")
    void fetchOneShouldReturnNullWhenNoMatch() throws SQLException {
        Map<String, Object> row = SqlConnection.fetchOne(conn, 
                "SELECT name FROM users WHERE age = ?", 999);
        
        assertThat(row).isNull();
    }

    @Test
    @Story("Query Execution")
    @Description("FetchAll should return all matching rows")
    @DisplayName("Fetch all should return all rows")
    void fetchAllShouldReturnAllRows() throws SQLException {
        List<Map<String, Object>> rows = SqlConnection.fetchAll(conn,
                "SELECT * FROM users ORDER BY name");

        assertThat(rows).hasSize(2);
        assertThat(rows.get(0).get("name")).isEqualTo("Alice");
        assertThat(rows.get(1).get("name")).isEqualTo("Bob");
    }

    // ═══════════════════════════════════════════════════════════════════
    // CRUD TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("CRUD Operations")
    @Description("Insert should add new row and return ID")
    @DisplayName("Insert should add new row")
    void insertShouldAddNewRow() throws SQLException {
        long rowId = SqlConnection.insert(conn, "users",
                Map.of("name", "Charlie", "age", 35));

        assertThat(rowId).isGreaterThan(0);

        Map<String, Object> row = SqlConnection.fetchOne(conn,
                "SELECT * FROM users WHERE id = ?", rowId);
        assertThat(row.get("name")).isEqualTo("Charlie");
        assertThat(row.get("age")).isEqualTo(35);
    }

    @Test
    @Story("CRUD Operations")
    @Description("Insert with invalid table should return -1")
    @DisplayName("Insert with invalid table should fail")
    void insertWithInvalidTableShouldFail() {
        long result = SqlConnection.insert(conn, "users; DROP", Map.of("name", "Evil"));
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @Story("CRUD Operations")
    @Description("Update should modify existing rows")
    @DisplayName("Update should modify rows")
    void updateShouldModifyRows() throws SQLException {
        int affected = SqlConnection.update(conn, "users",
                Map.of("age", 31), "name = ?", "Alice");

        assertThat(affected).isEqualTo(1);

        Map<String, Object> row = SqlConnection.fetchOne(conn,
                "SELECT age FROM users WHERE name = ?", "Alice");
        assertThat(row.get("age")).isEqualTo(31);
    }

    @Test
    @Story("CRUD Operations")
    @Description("Delete should remove matching rows")
    @DisplayName("Delete should remove rows")
    void deleteShouldRemoveRows() throws SQLException {
        int deleted = SqlConnection.delete(conn, "users", "name = ?", "Bob");

        assertThat(deleted).isEqualTo(1);

        Map<String, Object> row = SqlConnection.fetchOne(conn,
                "SELECT * FROM users WHERE name = ?", "Bob");
        assertThat(row).isNull();
    }

    // ═══════════════════════════════════════════════════════════════════
    // UTILITY TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Utility Functions")
    @Description("GetTableNames should return all tables")
    @DisplayName("Get table names should return tables")
    void getTableNamesShouldReturnTables() throws SQLException {
        List<String> tables = SqlConnection.getTableNames(conn);
        assertThat(tables).contains("users");
    }

    @Test
    @Story("Utility Functions")
    @Description("GetTableInfo should return column information")
    @DisplayName("Get table info should return columns")
    void getTableInfoShouldReturnColumns() throws SQLException {
        List<Map<String, Object>> info = SqlConnection.getTableInfo(conn, "users");

        List<String> columnNames = info.stream()
                .map(row -> (String) row.get("name"))
                .toList();

        assertThat(columnNames).contains("id", "name", "age");
    }
}

