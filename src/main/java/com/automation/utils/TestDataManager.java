package com.automation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Test Data Manager for loading JSON, YAML, and CSV test data files.
 * Also provides random data generation using Datafaker.
 */
public class TestDataManager {

    private static final Logger logger = LoggerFactory.getLogger(TestDataManager.class);

    private final Path dataDir;
    private final ObjectMapper jsonMapper;
    private final ObjectMapper yamlMapper;
    private final Faker faker;

    public TestDataManager() {
        this(Path.of("src/test/resources/data"));
    }

    public TestDataManager(Path dataDir) {
        this.dataDir = dataDir;
        this.faker = new Faker();

        this.jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());

        this.yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.registerModule(new JavaTimeModule());
    }

    // ═══════════════════════════════════════════════════════════════════
    // DATA LOADING
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Load data from JSON or YAML file.
     */
    public Map<String, Object> load(String filename) {
        Path path = resolvePath(filename);
        String ext = getFileExtension(path.toString());

        try {
            ObjectMapper mapper = (ext.equals(".yml") || ext.equals(".yaml")) ? yamlMapper : jsonMapper;
            return mapper.readValue(path.toFile(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new DataLoadException("Failed to load: " + filename, e);
        }
    }

    /**
     * Load data as a specific type.
     */
    public <T> T load(String filename, Class<T> type) {
        Path path = resolvePath(filename);
        String ext = getFileExtension(path.toString());

        try {
            ObjectMapper mapper = (ext.equals(".yml") || ext.equals(".yaml")) ? yamlMapper : jsonMapper;
            return mapper.readValue(path.toFile(), type);
        } catch (IOException e) {
            throw new DataLoadException("Failed to load: " + filename, e);
        }
    }

    /**
     * Load CSV file as list of maps.
     */
    public List<Map<String, String>> loadCsv(String filename) {
        Path path = resolvePath(filename, ".csv");

        try (Reader reader = Files.newBufferedReader(path);
             CSVReader csvReader = new CSVReaderBuilder(reader).build()) {

            String[] headers = csvReader.readNext();
            if (headers == null) return Collections.emptyList();

            List<Map<String, String>> result = new ArrayList<>();
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 0; i < headers.length && i < values.length; i++) {
                    row.put(headers[i].trim(), values[i].trim());
                }
                result.add(row);
            }
            return result;
        } catch (Exception e) {
            throw new DataLoadException("Failed to load CSV: " + filename, e);
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // DATA GENERATION (using Datafaker)
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Generate random test data using Datafaker.
     */
    public TestDataBuilder generate() {
        return new TestDataBuilder(faker);
    }

    /**
     * Get the Faker instance for direct access.
     */
    public Faker getFaker() {
        return faker;
    }

    public static class TestDataBuilder {
        private final Map<String, Object> data = new LinkedHashMap<>();
        private final Faker faker;

        TestDataBuilder(Faker faker) {
            this.faker = faker;
        }

        public TestDataBuilder withEmail() {
            data.put("email", faker.internet().emailAddress());
            return this;
        }

        public TestDataBuilder withUsername() {
            data.put("username", faker.internet().username());
            return this;
        }

        public TestDataBuilder withPassword() {
            data.put("password", faker.internet().password(8, 16, true, true, true));
            return this;
        }

        public TestDataBuilder withName() {
            data.put("firstName", faker.name().firstName());
            data.put("lastName", faker.name().lastName());
            return this;
        }

        public TestDataBuilder withPhone() {
            data.put("phone", faker.phoneNumber().cellPhone());
            return this;
        }

        public TestDataBuilder withAddress() {
            data.put("street", faker.address().streetAddress());
            data.put("city", faker.address().city());
            data.put("state", faker.address().stateAbbr());
            data.put("zipCode", faker.address().zipCode());
            return this;
        }

        public TestDataBuilder with(String key, Object value) {
            data.put(key, value);
            return this;
        }

        public Map<String, Object> build() {
            return new LinkedHashMap<>(data);
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // SAUCEDEMO CREDENTIALS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Get SauceDemo credentials for a specific user type.
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> getSauceDemoCredentials(String userType) {
        Map<String, Object> testData = load("test_data");
        Map<String, Map<String, String>> credentials =
                (Map<String, Map<String, String>>) testData.get("saucedemo_credentials");

        if (credentials != null && credentials.containsKey(userType)) {
            return credentials.get(userType);
        }
        logger.warn("No credentials found for user type: {}", userType);
        return Collections.emptyMap();
    }

    public Map<String, String> getStandardUserCredentials() {
        return getSauceDemoCredentials("standard_user");
    }

    public Map<String, String> getLockedOutUserCredentials() {
        return getSauceDemoCredentials("locked_out_user");
    }

    // ═══════════════════════════════════════════════════════════════════
    // HELPER METHODS
    // ═══════════════════════════════════════════════════════════════════

    private Path resolvePath(String filename, String... defaultExtensions) {
        // Check if file exists as-is
        Path path = dataDir.resolve(filename);
        if (Files.exists(path)) return path;

        // Try with extensions
        String[] extensions = defaultExtensions.length > 0
                ? defaultExtensions
                : new String[]{".json", ".yml", ".yaml"};

        for (String ext : extensions) {
            path = dataDir.resolve(filename + ext);
            if (Files.exists(path)) return path;
        }

        // Return default path (will fail with clear error)
        return dataDir.resolve(filename + extensions[0]);
    }

    private String getFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        return lastDot > 0 ? filename.substring(lastDot) : "";
    }

    public static class DataLoadException extends RuntimeException {
        public DataLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
