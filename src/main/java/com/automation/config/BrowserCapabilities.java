package com.automation.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Loads browser capabilities from capabilities.json.
 * Equivalent to Python's config/capabilities.json
 */
public class BrowserCapabilities {
    
    private static final Logger logger = LoggerFactory.getLogger(BrowserCapabilities.class);
    private static final String CAPABILITIES_FILE = "/config/capabilities.json";
    private static JsonNode capabilities;
    
    static {
        loadCapabilities();
    }
    
    private static void loadCapabilities() {
        try (InputStream is = BrowserCapabilities.class.getResourceAsStream(CAPABILITIES_FILE)) {
            if (is != null) {
                ObjectMapper mapper = new ObjectMapper();
                capabilities = mapper.readTree(is);
                logger.info("Loaded browser capabilities from {}", CAPABILITIES_FILE);
            } else {
                logger.warn("capabilities.json not found, using defaults");
            }
        } catch (Exception e) {
            logger.error("Error loading capabilities.json: {}", e.getMessage());
        }
    }
    
    public static ChromeOptions getChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        if (capabilities != null) {
            JsonNode chrome = capabilities.path("chrome").path("chromeOptions");

            // Add arguments
            if (!chrome.isMissingNode() && chrome.has("args")) {
                chrome.get("args").forEach(arg -> options.addArguments(arg.asText()));
            }

            // Add preferences
            if (!chrome.isMissingNode() && chrome.has("prefs")) {
                Map<String, Object> prefs = new HashMap<>();
                chrome.get("prefs").fields().forEachRemaining(entry -> {
                    JsonNode value = entry.getValue();
                    if (value.isBoolean()) {
                        prefs.put(entry.getKey(), value.asBoolean());
                    } else if (value.isInt()) {
                        prefs.put(entry.getKey(), value.asInt());
                    } else {
                        prefs.put(entry.getKey(), value.asText());
                    }
                });
                options.setExperimentalOption("prefs", prefs);
            }

            // Add excludeSwitches
            if (!chrome.isMissingNode() && chrome.has("excludeSwitches")) {
                List<String> switches = new ArrayList<>();
                chrome.get("excludeSwitches").forEach(s -> switches.add(s.asText()));
                options.setExperimentalOption("excludeSwitches", switches);
            }
        }

        if (headless) {
            options.addArguments("--headless=new");
        }

        return options;
    }

    public static FirefoxOptions getFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (capabilities != null) {
            JsonNode firefox = capabilities.path("firefox").path("firefoxOptions");

            if (!firefox.isMissingNode() && firefox.has("args")) {
                firefox.get("args").forEach(arg -> options.addArguments(arg.asText()));
            }

            if (!firefox.isMissingNode() && firefox.has("prefs")) {
                firefox.get("prefs").fields().forEachRemaining(entry -> {
                    JsonNode value = entry.getValue();
                    if (value.isBoolean()) {
                        options.addPreference(entry.getKey(), value.asBoolean());
                    } else if (value.isInt()) {
                        options.addPreference(entry.getKey(), value.asInt());
                    } else {
                        options.addPreference(entry.getKey(), value.asText());
                    }
                });
            }
        }

        if (headless) {
            options.addArguments("--headless");
        }

        return options;
    }

    public static EdgeOptions getEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();

        if (capabilities != null) {
            JsonNode edge = capabilities.path("edge").path("edgeOptions");

            if (!edge.isMissingNode() && edge.has("args")) {
                edge.get("args").forEach(arg -> options.addArguments(arg.asText()));
            }
        }

        if (headless) {
            options.addArguments("--headless=new");
        }

        return options;
    }
}

