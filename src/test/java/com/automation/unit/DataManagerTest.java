package com.automation.unit;

import com.automation.utils.DataManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for DataManager.
 */
@DisplayName("DataManager Tests")
class DataManagerTest {

    @TempDir
    Path tempDir;

    private DataManager dataManager;

    @BeforeEach
    void setUp() {
        dataManager = new DataManager(tempDir);
    }

    @Test
    @DisplayName("Should save and load JSON data")
    void shouldSaveAndLoadJsonData() throws Exception {
        Map<String, Object> data = Map.of(
                "name", "Test",
                "value", 123,
                "active", true
        );
        
        dataManager.saveJson("test_data.json", data);
        
        Map<String, Object> loaded = dataManager.loadJson("test_data");
        
        assertThat(loaded).containsEntry("name", "Test");
        assertThat(loaded).containsEntry("value", 123);
        assertThat(loaded).containsEntry("active", true);
    }

    @Test
    @DisplayName("Should load JSON as specific type")
    void shouldLoadJsonAsSpecificType() throws Exception {
        String json = """
                {
                    "title": "Test Book",
                    "pages": 100
                }
                """;
        Files.writeString(tempDir.resolve("book.json"), json);
        
        TestBook book = dataManager.loadJson("book", TestBook.class);
        
        assertThat(book.title).isEqualTo("Test Book");
        assertThat(book.pages).isEqualTo(100);
    }

    @Test
    @DisplayName("Should save and load YAML data")
    void shouldSaveAndLoadYamlData() throws Exception {
        String yaml = """
                name: Test Config
                timeout: 30
                features:
                  - feature1
                  - feature2
                """;
        Files.writeString(tempDir.resolve("config.yml"), yaml);
        
        Map<String, Object> loaded = dataManager.loadYaml("config");
        
        assertThat(loaded).containsEntry("name", "Test Config");
        assertThat(loaded).containsEntry("timeout", 30);
    }

    @Test
    @DisplayName("Should throw exception for missing JSON file")
    void shouldThrowExceptionForMissingJsonFile() {
        assertThatThrownBy(() -> dataManager.loadJson("nonexistent"))
                .isInstanceOf(DataManager.DataLoadException.class);
    }

    @Test
    @DisplayName("Should throw exception for missing YAML file")
    void shouldThrowExceptionForMissingYamlFile() {
        assertThatThrownBy(() -> dataManager.loadYaml("nonexistent"))
                .isInstanceOf(DataManager.DataLoadException.class);
    }

    @Test
    @DisplayName("Should save test results with timestamp")
    void shouldSaveTestResultsWithTimestamp() {
        List<DataManager.TestResult> results = List.of(
                new DataManager.TestResult("test1", true, 100, null, LocalDateTime.now()),
                new DataManager.TestResult("test2", false, 200, "Failed assertion", LocalDateTime.now())
        );
        
        Path savedPath = dataManager.saveTestResults(results, "dev");
        
        assertThat(savedPath).exists();
        assertThat(savedPath.toString()).contains("dev");
        assertThat(savedPath.toString()).contains("test_results_");
    }

    @Test
    @DisplayName("Should create directories when saving")
    void shouldCreateDirectoriesWhenSaving() {
        Map<String, Object> data = Map.of("key", "value");
        
        dataManager.saveJson("nested/folder/data.json", data);
        
        assertThat(tempDir.resolve("nested/folder/data.json")).exists();
    }

    @Test
    @DisplayName("TestResult record should work correctly")
    void testResultRecordShouldWorkCorrectly() {
        LocalDateTime now = LocalDateTime.now();
        DataManager.TestResult result = new DataManager.TestResult(
                "testName", true, 150, null, now
        );
        
        assertThat(result.testName()).isEqualTo("testName");
        assertThat(result.passed()).isTrue();
        assertThat(result.durationMs()).isEqualTo(150);
        assertThat(result.errorMessage()).isNull();
        assertThat(result.timestamp()).isEqualTo(now);
    }

    @Test
    @DisplayName("Default constructor should use default data directory")
    void defaultConstructorShouldUseDefaultDataDirectory() {
        DataManager defaultManager = new DataManager();
        assertThat(defaultManager).isNotNull();
    }

    // Helper class for type-specific loading test
    static class TestBook {
        public String title;
        public int pages;
    }
}

