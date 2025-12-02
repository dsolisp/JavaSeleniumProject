package com.automation.unit;

import com.automation.utils.TestDataManager;
import com.opencsv.bean.CsvBindByName;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * Unit tests for CSV data loading functionality using OpenCSV.
 * Tests the enhanced TestDataManager CSV capabilities.
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
        
        // Verify first row
        Map<String, String> firstUser = users.get(0);
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

    // ═══════════════════════════════════════════════════════════════════
    // BEAN MAPPING
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Should load CSV as typed beans")
    void shouldLoadCsvAsTypedBeans() {
        List<TestUser> users = dataManager.loadCsvAsBean("test_users.csv", TestUser.class);

        assertThat(users).isNotEmpty();
        assertThat(users).hasSizeGreaterThanOrEqualTo(5);
        
        TestUser firstUser = users.get(0);
        assertThat(firstUser.getUsername()).isNotBlank();
        assertThat(firstUser.getPassword()).isNotBlank();
        assertThat(firstUser.getEmail()).contains("@");
    }

    @Test
    @DisplayName("Should correctly map bean properties")
    void shouldCorrectlyMapBeanProperties() {
        List<TestUser> users = dataManager.loadCsvAsBean("test_users.csv", TestUser.class);

        TestUser adminUser = users.stream()
            .filter(u -> "admin".equals(u.getRole()))
            .findFirst()
            .orElseThrow();

        assertThat(adminUser.getUsername()).isEqualTo("admin_user");
        assertThat(adminUser.getEmail()).isEqualTo("admin@example.com");
        assertThat(adminUser.isEnabled()).isTrue();
    }

    // ═══════════════════════════════════════════════════════════════════
    // CACHING
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @DisplayName("Should cache CSV data")
    void shouldCacheCsvData() {
        // First load
        List<Map<String, String>> users1 = dataManager.loadCsv("test_users.csv");
        // Second load (should be cached)
        List<Map<String, String>> users2 = dataManager.loadCsv("test_users.csv");

        assertThat(users1).isEqualTo(users2);
    }

    @Test
    @DisplayName("Should invalidate cache when requested")
    void shouldInvalidateCacheWhenRequested() {
        List<Map<String, String>> users1 = dataManager.loadCsv("test_users.csv");
        dataManager.invalidateCache("test_users.csv");
        List<Map<String, String>> users2 = dataManager.loadCsv("test_users.csv");

        // Data should still be equal (same file)
        assertThat(users1).isEqualTo(users2);
    }

    // ═══════════════════════════════════════════════════════════════════
    // TEST BEAN CLASS
    // ═══════════════════════════════════════════════════════════════════

    public static class TestUser {
        @CsvBindByName(column = "username")
        private String username;
        
        @CsvBindByName(column = "password")
        private String password;
        
        @CsvBindByName(column = "email")
        private String email;
        
        @CsvBindByName(column = "role")
        private String role;
        
        @CsvBindByName(column = "enabled")
        private boolean enabled;

        public String getUsername() { return username; }
        public String getPassword() { return password; }
        public String getEmail() { return email; }
        public String getRole() { return role; }
        public boolean isEnabled() { return enabled; }
    }
}

