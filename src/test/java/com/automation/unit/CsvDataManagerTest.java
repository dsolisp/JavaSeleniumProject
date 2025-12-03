package com.automation.unit;

import com.automation.utils.TestDataManager;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for CSV data loading functionality.
 */
@DisplayName("CSV Data Manager Tests")
@Tag("unit")
class CsvDataManagerTest {

    private TestDataManager dataManager;

    @BeforeEach
    void setUp() {
        dataManager = new TestDataManager();
    }

    // ═══════════════════════════════════════════════════════════════════
    // BASIC CSV LOADING
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Should load CSV file as list of maps")
    void shouldLoadCsvAsListOfMaps() {
        List<Map<String, String>> users = dataManager.loadCsv("test_users.csv");

        assertThat(users).isNotEmpty();
        assertThat(users).hasSizeGreaterThanOrEqualTo(5);

        // Verify first row has expected columns
        Map<String, String> firstUser = users.getFirst();
        assertThat(firstUser).containsKey("username");
        assertThat(firstUser).containsKey("password");
        assertThat(firstUser).containsKey("email");
        assertThat(firstUser).containsKey("role");
        assertThat(firstUser).containsKey("enabled");
    }

    @Test
    @DisplayName("Should correctly parse CSV values")
    void shouldCorrectlyParseCsvValues() {
        List<Map<String, String>> users = dataManager.loadCsv("test_users.csv");

        Map<String, String> standardUser = users.stream()
            .filter(u -> "standard_user".equals(u.get("username")))
            .findFirst()
            .orElseThrow();

        assertThat(standardUser.get("username")).isEqualTo("standard_user");
        assertThat(standardUser.get("password")).isEqualTo("secret_sauce");
        assertThat(standardUser.get("email")).isEqualTo("standard@example.com");
        assertThat(standardUser.get("role")).isEqualTo("user");
        assertThat(standardUser.get("enabled")).isEqualTo("true");
    }

    @Test
    @DisplayName("Should handle CSV with different user roles")
    void shouldHandleDifferentUserRoles() {
        List<Map<String, String>> users = dataManager.loadCsv("test_users.csv");

        long adminCount = users.stream()
            .filter(u -> "admin".equals(u.get("role")))
            .count();
        long userCount = users.stream()
            .filter(u -> "user".equals(u.get("role")))
            .count();

        assertThat(adminCount).isGreaterThanOrEqualTo(1);
        assertThat(userCount).isGreaterThanOrEqualTo(4);
    }

    @Test
    @DisplayName("Should load same data on multiple reads")
    void shouldLoadSameDataOnMultipleReads() {
        List<Map<String, String>> users1 = dataManager.loadCsv("test_users.csv");
        List<Map<String, String>> users2 = dataManager.loadCsv("test_users.csv");

        assertThat(users1).isEqualTo(users2);
    }
}

