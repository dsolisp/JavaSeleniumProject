package com.automation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Multi-format data manager for test data and results.
 * Equivalent to Python's utils/test_data_manager.py
 *
 * Supports JSON, YAML, and CSV file formats.
 */
public class DataManager {

    private static final Logger logger = LoggerFactory.getLogger(DataManager.class);

    private final Path dataDir;
    private final ObjectMapper jsonMapper;
    private final ObjectMapper yamlMapper;

    public DataManager() {
        this(Paths.get("src/test/resources/data"));
    }

    public DataManager(Path dataDir) {
        this.dataDir = dataDir;
        
        this.jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        
        this.yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Load JSON file as a Map.
     */
    public Map<String, Object> loadJson(String filename) {
        Path filePath = resolveFilePath(filename, ".json");
        try {
            return jsonMapper.readValue(filePath.toFile(), new TypeReference<>() {});
        } catch (IOException e) {
            logger.error("Failed to load JSON file: {}", filePath, e);
            throw new DataLoadException("Failed to load JSON: " + filename, e);
        }
    }

    /**
     * Load JSON file as a specific type.
     */
    public <T> T loadJson(String filename, Class<T> type) {
        Path filePath = resolveFilePath(filename, ".json");
        try {
            return jsonMapper.readValue(filePath.toFile(), type);
        } catch (IOException e) {
            logger.error("Failed to load JSON file: {}", filePath, e);
            throw new DataLoadException("Failed to load JSON: " + filename, e);
        }
    }

    /**
     * Load YAML file as a Map.
     */
    public Map<String, Object> loadYaml(String filename) {
        Path filePath = resolveFilePath(filename, ".yml", ".yaml");
        try {
            return yamlMapper.readValue(filePath.toFile(), new TypeReference<>() {});
        } catch (IOException e) {
            logger.error("Failed to load YAML file: {}", filePath, e);
            throw new DataLoadException("Failed to load YAML: " + filename, e);
        }
    }

    /**
     * Load YAML file as a specific type.
     */
    public <T> T loadYaml(String filename, Class<T> type) {
        Path filePath = resolveFilePath(filename, ".yml", ".yaml");
        try {
            return yamlMapper.readValue(filePath.toFile(), type);
        } catch (IOException e) {
            logger.error("Failed to load YAML file: {}", filePath, e);
            throw new DataLoadException("Failed to load YAML: " + filename, e);
        }
    }

    /**
     * Load CSV file as a list of maps (each row is a map with header keys).
     */
    public List<Map<String, String>> loadCsv(String filename) {
        Path filePath = resolveFilePath(filename, ".csv");
        try (Reader reader = Files.newBufferedReader(filePath);
             CSVReader csvReader = new CSVReaderBuilder(reader).build()) {

            List<String[]> rows = csvReader.readAll();
            if (rows.isEmpty()) {
                return Collections.emptyList();
            }

            String[] headers = rows.get(0);
            List<Map<String, String>> result = new ArrayList<>();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                Map<String, String> rowMap = new LinkedHashMap<>();
                for (int j = 0; j < headers.length && j < row.length; j++) {
                    rowMap.put(headers[j], row[j]);
                }
                result.add(rowMap);
            }

            return result;
        } catch (IOException | CsvException e) {
            logger.error("Failed to load CSV file: {}", filePath, e);
            throw new DataLoadException("Failed to load CSV: " + filename, e);
        }
    }

    /**
     * Load test data from any supported format (JSON, YAML, CSV).
     * Auto-detects format based on file extension.
     */
    public Map<String, Object> loadTestData(String filename) {
        String lowerFilename = filename.toLowerCase();
        if (lowerFilename.endsWith(".csv")) {
            List<Map<String, String>> csvData = loadCsv(filename);
            Map<String, Object> result = new LinkedHashMap<>();
            result.put("data", csvData);
            return result;
        } else if (lowerFilename.endsWith(".yml") || lowerFilename.endsWith(".yaml")) {
            return loadYaml(filename);
        } else {
            return loadJson(filename);
        }
    }

    /**
     * Save data as JSON.
     */
    public void saveJson(String filename, Object data) {
        Path filePath = dataDir.resolve(filename.endsWith(".json") ? filename : filename + ".json");
        try {
            Path parentDir = filePath.getParent();
            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }
            jsonMapper.writeValue(filePath.toFile(), data);
            logger.info("Saved JSON to: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to save JSON file: {}", filePath, e);
            throw new DataSaveException("Failed to save JSON: " + filename, e);
        }
    }

    /**
     * Save test results with timestamp.
     */
    public Path saveTestResults(List<TestResult> results, String environment) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = String.format("results/%s/test_results_%s.json", environment, timestamp);
        
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("timestamp", LocalDateTime.now().toString());
        data.put("environment", environment);
        data.put("total_tests", results.size());
        data.put("passed", results.stream().filter(r -> r.passed).count());
        data.put("failed", results.stream().filter(r -> !r.passed).count());
        data.put("results", results);
        
        saveJson(filename, data);
        return dataDir.resolve(filename);
    }

    /**
     * Get test data for a specific scenario.
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getTestScenario(String scenarioName) {
        Map<String, Object> testData = loadJson("test_data");
        List<Map<String, Object>> scenarios = (List<Map<String, Object>>) testData.get("test_scenarios");

        if (scenarios != null) {
            return scenarios.stream()
                    .filter(s -> scenarioName.equals(s.get("name")))
                    .findFirst()
                    .orElse(Collections.emptyMap());
        }
        return Collections.emptyMap();
    }

    /**
     * Get SauceDemo credentials for a specific user type.
     * @param userType one of: standard_user, locked_out_user, problem_user,
     *                 performance_glitch_user, error_user, visual_user
     * @return Map containing username, password, and description
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getSauceDemoCredentials(String userType) {
        Map<String, Object> testData = loadJson("test_data");
        Map<String, Map<String, String>> credentials =
                (Map<String, Map<String, String>>) testData.get("saucedemo_credentials");

        if (credentials != null && credentials.containsKey(userType)) {
            return credentials.get(userType);
        }
        logger.warn("No credentials found for user type: {}", userType);
        return Collections.emptyMap();
    }

    /**
     * Get standard user credentials for SauceDemo.
     */
    public Map<String, String> getStandardUserCredentials() {
        return getSauceDemoCredentials("standard_user");
    }

    /**
     * Get locked out user credentials for SauceDemo.
     */
    public Map<String, String> getLockedOutUserCredentials() {
        return getSauceDemoCredentials("locked_out_user");
    }

    private Path resolveFilePath(String filename, String... extensions) {
        for (String ext : extensions) {
            String fileWithExt = filename.endsWith(ext) ? filename : filename + ext;
            Path path = dataDir.resolve(fileWithExt);
            if (Files.exists(path)) {
                return path;
            }
        }
        // Return first extension as default
        return dataDir.resolve(filename + extensions[0]);
    }

    // Record for test results
    public record TestResult(
            String testName,
            boolean passed,
            long durationMs,
            String errorMessage,
            LocalDateTime timestamp
    ) {}

    // Custom exceptions
    public static class DataLoadException extends RuntimeException {
        public DataLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class DataSaveException extends RuntimeException {
        public DataSaveException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

