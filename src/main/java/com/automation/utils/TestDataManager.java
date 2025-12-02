package com.automation.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Enhanced Test Data Manager with multi-format support, caching, and data generation.
 * Equivalent to Python's utils/test_data_manager.py
 */
public class TestDataManager {

    private static final Logger logger = LoggerFactory.getLogger(TestDataManager.class);
    
    private final Path dataDir;
    private final ObjectMapper jsonMapper;
    private final ObjectMapper yamlMapper;
    private final Map<String, CacheEntry<?>> cache = new ConcurrentHashMap<>();
    private final String environment;
    private final Random random = new Random();
    
    private static final long DEFAULT_CACHE_TTL_MS = 300_000; // 5 minutes

    public TestDataManager() {
        this(Paths.get("src/test/resources/data"), System.getenv().getOrDefault("ENV", "dev"));
    }

    public TestDataManager(Path dataDir, String environment) {
        this.dataDir = dataDir;
        this.environment = environment;
        
        this.jsonMapper = new ObjectMapper();
        jsonMapper.registerModule(new JavaTimeModule());
        
        this.yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.registerModule(new JavaTimeModule());
    }

    // ═══════════════════════════════════════════════════════════════════
    // MULTI-FORMAT LOADING
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Load data from any supported format (JSON, YAML, CSV) with caching.
     */
    public Map<String, Object> load(String filename) {
        return loadWithCache(filename, () -> loadRaw(filename), new TypeReference<>() {});
    }

    /**
     * Load data as a specific type with caching.
     */
    public <T> T load(String filename, Class<T> type) {
        return loadWithCache(filename + ":" + type.getName(), () -> loadRaw(filename, type), null);
    }

    /**
     * Load CSV file as list of maps.
     */
    public List<Map<String, String>> loadCsv(String filename) {
        String cacheKey = filename + ":csv";
        return loadWithCache(cacheKey, () -> loadCsvRaw(filename), null);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> loadRaw(String filename) {
        String ext = getFileExtension(filename);
        return switch (ext) {
            case ".csv" -> (Map<String, Object>) (Object) Map.of("data", loadCsvRaw(filename));
            case ".yml", ".yaml" -> loadYamlRaw(filename);
            default -> loadJsonRaw(filename);
        };
    }

    private <T> T loadRaw(String filename, Class<T> type) {
        try {
            Path path = resolvePath(filename);
            String ext = getFileExtension(path.toString());
            ObjectMapper mapper = ext.equals(".yml") || ext.equals(".yaml") ? yamlMapper : jsonMapper;
            return mapper.readValue(path.toFile(), type);
        } catch (IOException e) {
            throw new DataLoadException("Failed to load: " + filename, e);
        }
    }

    private Map<String, Object> loadJsonRaw(String filename) {
        try {
            Path path = resolvePath(filename, ".json");
            return jsonMapper.readValue(path.toFile(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new DataLoadException("Failed to load JSON: " + filename, e);
        }
    }

    private Map<String, Object> loadYamlRaw(String filename) {
        try {
            Path path = resolvePath(filename, ".yml", ".yaml");
            return yamlMapper.readValue(path.toFile(), new TypeReference<>() {});
        } catch (IOException e) {
            throw new DataLoadException("Failed to load YAML: " + filename, e);
        }
    }

    private List<Map<String, String>> loadCsvRaw(String filename) {
        try {
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
            }
        } catch (Exception e) {
            throw new DataLoadException("Failed to load CSV: " + filename, e);
        }
    }

    /**
     * Load CSV file as a list of typed beans using OpenCSV.
     * Requires the bean class to have @CsvBindByName annotations.
     */
    public <T> List<T> loadCsvAsBean(String filename, Class<T> beanClass) {
        String cacheKey = filename + ":csv:" + beanClass.getName();
        return loadWithCache(cacheKey, () -> loadCsvAsBeanRaw(filename, beanClass), null);
    }

    private <T> List<T> loadCsvAsBeanRaw(String filename, Class<T> beanClass) {
        try {
            Path path = resolvePath(filename, ".csv");
            try (Reader reader = Files.newBufferedReader(path)) {
                HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
                strategy.setType(beanClass);

                CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(reader)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

                return csvToBean.parse();
            }
        } catch (Exception e) {
            throw new DataLoadException("Failed to load CSV as bean: " + filename, e);
        }
    }

    // ═══════════════════════════════════════════════════════════════════
    // ENVIRONMENT-SPECIFIC LOADING
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Load environment-specific data with fallback to default.
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> loadForEnvironment(String filename) {
        // Try environment-specific file first
        String envFilename = filename + "_" + environment;
        Path envPath = resolvePath(envFilename, ".json", ".yml", ".yaml");
        
        if (Files.exists(envPath)) {
            logger.debug("Loading environment-specific data: {}", envFilename);
            return load(envFilename);
        }
        
        // Fall back to base file with environment section
        Map<String, Object> baseData = load(filename);
        if (baseData.containsKey("environments") && baseData.get("environments") instanceof Map) {
            Map<String, Object> envs = (Map<String, Object>) baseData.get("environments");
            if (envs.containsKey(environment)) {
                Map<String, Object> result = new LinkedHashMap<>(baseData);
                result.putAll((Map<String, Object>) envs.get(environment));
                result.remove("environments");
                return result;
            }
        }
        
        return baseData;
    }

    // ═══════════════════════════════════════════════════════════════════
    // CACHING
    // ═══════════════════════════════════════════════════════════════════

    @SuppressWarnings("unchecked")
    private <T> T loadWithCache(String key, DataSupplier<T> loader, TypeReference<T> typeRef) {
        CacheEntry<?> entry = cache.get(key);
        if (entry != null && !entry.isExpired()) {
            logger.debug("Cache hit for: {}", key);
            return (T) entry.value;
        }
        
        T value = loader.get();
        cache.put(key, new CacheEntry<>(value, System.currentTimeMillis() + DEFAULT_CACHE_TTL_MS));
        logger.debug("Cached: {}", key);
        return value;
    }

    /**
     * Clear all cached data.
     */
    public void clearCache() {
        cache.clear();
        logger.info("Cache cleared");
    }

    /**
     * Invalidate specific cache entry.
     */
    public void invalidateCache(String filename) {
        cache.entrySet().removeIf(e -> e.getKey().startsWith(filename));
    }

    // ═══════════════════════════════════════════════════════════════════
    // DATA GENERATION
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Generate random test data.
     */
    public TestDataBuilder generate() {
        return new TestDataBuilder();
    }

    public class TestDataBuilder {
        private final Map<String, Object> data = new LinkedHashMap<>();

        public TestDataBuilder withEmail() {
            data.put("email", "test_" + System.currentTimeMillis() + "@example.com");
            return this;
        }

        public TestDataBuilder withEmail(String domain) {
            data.put("email", "test_" + System.currentTimeMillis() + "@" + domain);
            return this;
        }

        public TestDataBuilder withUsername() {
            data.put("username", "user_" + random.nextInt(100000));
            return this;
        }

        public TestDataBuilder withPassword() {
            data.put("password", "Pass" + random.nextInt(10000) + "!");
            return this;
        }

        public TestDataBuilder withName() {
            String[] firstNames = {"John", "Jane", "Bob", "Alice", "Charlie"};
            String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones"};
            data.put("firstName", firstNames[random.nextInt(firstNames.length)]);
            data.put("lastName", lastNames[random.nextInt(lastNames.length)]);
            return this;
        }

        public TestDataBuilder withPhone() {
            data.put("phone", String.format("+1%010d", random.nextInt(1_000_000_000)));
            return this;
        }

        public TestDataBuilder withAddress() {
            data.put("street", random.nextInt(9999) + " Main St");
            data.put("city", "Anytown");
            data.put("state", "CA");
            data.put("zipCode", String.format("%05d", random.nextInt(100000)));
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
    // SCHEMA VALIDATION
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Validate data against a simple schema.
     */
    public ValidationResult validate(Map<String, Object> data, Map<String, FieldSchema> schema) {
        List<String> errors = new ArrayList<>();

        for (Map.Entry<String, FieldSchema> entry : schema.entrySet()) {
            String field = entry.getKey();
            FieldSchema fieldSchema = entry.getValue();
            Object value = data.get(field);

            if (fieldSchema.required && value == null) {
                errors.add("Missing required field: " + field);
                continue;
            }

            if (value != null && fieldSchema.type != null) {
                if (!fieldSchema.type.isInstance(value)) {
                    errors.add("Field " + field + " expected " + fieldSchema.type.getSimpleName());
                }
            }

            if (value instanceof String strVal && fieldSchema.pattern != null) {
                if (!strVal.matches(fieldSchema.pattern)) {
                    errors.add("Field " + field + " does not match pattern");
                }
            }
        }

        return new ValidationResult(errors.isEmpty(), errors);
    }

    // ═══════════════════════════════════════════════════════════════════
    // HELPER METHODS
    // ═══════════════════════════════════════════════════════════════════

    private Path resolvePath(String filename, String... extensions) {
        // Check if file has extension already
        if (Files.exists(dataDir.resolve(filename))) {
            return dataDir.resolve(filename);
        }

        for (String ext : extensions) {
            Path path = dataDir.resolve(filename + ext);
            if (Files.exists(path)) return path;
        }

        return dataDir.resolve(filename + (extensions.length > 0 ? extensions[0] : ".json"));
    }

    private String getFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        return lastDot > 0 ? filename.substring(lastDot) : "";
    }

    public String getEnvironment() {
        return environment;
    }

    // ═══════════════════════════════════════════════════════════════════
    // INNER CLASSES
    // ═══════════════════════════════════════════════════════════════════

    private record CacheEntry<T>(T value, long expiresAt) {
        boolean isExpired() {
            return System.currentTimeMillis() > expiresAt;
        }
    }

    @FunctionalInterface
    private interface DataSupplier<T> {
        T get();
    }

    public record FieldSchema(boolean required, Class<?> type, String pattern) {
        public static FieldSchema required(Class<?> type) {
            return new FieldSchema(true, type, null);
        }
        public static FieldSchema optional(Class<?> type) {
            return new FieldSchema(false, type, null);
        }
        public static FieldSchema pattern(String pattern) {
            return new FieldSchema(true, String.class, pattern);
        }
    }

    public record ValidationResult(boolean valid, List<String> errors) {}

    public static class DataLoadException extends RuntimeException {
        public DataLoadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
