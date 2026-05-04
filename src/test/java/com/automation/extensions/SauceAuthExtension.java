package com.automation.extensions;

import com.automation.config.Settings;
import com.automation.data.UserCredentials;
import com.automation.data.UserFactory;
import com.automation.utils.WebDriverFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.JavascriptExecutor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JUnit 5 extension that bypasses the SauceDemo login UI by injecting a
 * cached cookie state (ADR-009 — storage state reuse).
 *
 * <p>Activates only on test methods (or classes) annotated with
 * {@link SauceAuthenticated}. On first activation per JVM it performs a single
 * UI login and persists the resulting cookies to {@code .auth/sauce.json};
 * subsequent tests reuse that file. Set the env var {@code NO_CACHE_AUTH=true}
 * to force a fresh login.
 *
 * <p>Must be registered AFTER {@link WebDriverExtension} so the driver is
 * available when this extension runs:
 * <pre>{@code @ExtendWith({WebDriverExtension.class, SauceAuthExtension.class})}</pre>
 */
public class SauceAuthExtension implements BeforeAllCallback, BeforeEachCallback {

    private static final Logger logger = LoggerFactory.getLogger(SauceAuthExtension.class);
    private static final Path AUTH_FILE = Path.of(".auth", "sauce.json");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Object LOCK = new Object();
    private static volatile boolean cachePrimed = false;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (classNeedsAuth(context)) {
            ensureAuthFileExists();
        }
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        if (!isAuthRequested(context)) {
            return;
        }

        WebDriver driver = WebDriverExtension.getDriver();
        if (driver == null) {
            throw new IllegalStateException(
                    "SauceAuthExtension requires WebDriverExtension to be registered first");
        }

        ensureAuthFileExists();
        injectCookies(driver);
    }

    private boolean classNeedsAuth(ExtensionContext context) {
        return context.getTestClass()
                .map(cls -> cls.isAnnotationPresent(SauceAuthenticated.class)
                        || java.util.Arrays.stream(cls.getDeclaredMethods())
                                .anyMatch(m -> m.isAnnotationPresent(SauceAuthenticated.class)))
                .orElse(false);
    }

    // ── Annotation discovery ──────────────────────────────────────────────

    private boolean isAuthRequested(ExtensionContext context) {
        return context.getElement()
                .map(el -> el.isAnnotationPresent(SauceAuthenticated.class))
                .orElse(false)
                || context.getTestClass()
                .map(cls -> cls.isAnnotationPresent(SauceAuthenticated.class))
                .orElse(false);
    }

    // ── One-time login + cookie persistence ───────────────────────────────

    private void ensureAuthFileExists() throws Exception {
        boolean noCache = Boolean.parseBoolean(System.getenv().getOrDefault("NO_CACHE_AUTH", "false"));
        if (!noCache && cachePrimed && Files.exists(AUTH_FILE)) {
            return;
        }
        synchronized (LOCK) {
            if (!noCache && cachePrimed && Files.exists(AUTH_FILE)) {
                return;
            }
            if (noCache || !Files.exists(AUTH_FILE)) {
                primeAuthCache();
            }
            cachePrimed = true;
        }
    }

    private void primeAuthCache() throws Exception {
        Settings settings = Settings.getInstance();
        WebDriver tempDriver = WebDriverFactory.createDriver(settings.getBrowser(), true);
        try {
            navigateWithRetry(tempDriver, settings.getSauceDemoUrl());
            UserCredentials user = UserFactory.standard();
            tempDriver.findElement(By.id("user-name")).sendKeys(user.getUsername());
            tempDriver.findElement(By.id("password")).sendKeys(user.getPassword());
            tempDriver.findElement(By.id("login-button")).click();
            new WebDriverWait(tempDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.urlContains("/inventory.html"));

            List<Map<String, Object>> cookies = tempDriver.manage().getCookies().stream()
                    .map(SauceAuthExtension::serializeCookie)
                    .toList();
            // Capture localStorage (SauceDemo stores auth in localStorage)
            @SuppressWarnings("unchecked")
            Map<String, Object> localStorage = (Map<String, Object>) ((JavascriptExecutor) tempDriver)
                    .executeScript(
                        "return Object.keys(localStorage).reduce(function(acc,k){acc[k]=localStorage.getItem(k);return acc},{});"
                    );

            Files.createDirectories(AUTH_FILE.getParent());
            Map<String, Object> payload = new HashMap<>();
            payload.put("cookies", cookies);
            payload.put("localStorage", localStorage != null ? localStorage : new HashMap<>());
            MAPPER.writerWithDefaultPrettyPrinter().writeValue(AUTH_FILE.toFile(), payload);
            logger.info("[SauceAuthExtension] Primed auth cache: {}", AUTH_FILE);
        } finally {
            WebDriverFactory.quitDriver(tempDriver);
        }
    }

    // ── Cookie + localStorage injection per test ──────────────────────────

    private void injectCookies(WebDriver driver) throws Exception {
        Settings settings = Settings.getInstance();
        navigateWithRetry(driver, settings.getSauceDemoUrl());

        @SuppressWarnings("unchecked")
        Map<String, Object> payload = MAPPER.readValue(AUTH_FILE.toFile(), Map.class);

        // Restore HTTP cookies
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> cookies = (List<Map<String, Object>>) payload.getOrDefault("cookies", List.of());
        for (Map<String, Object> raw : cookies) {
            try {
                driver.manage().addCookie(deserializeCookie(raw));
            } catch (Exception ignore) {
                // skip cookies that cannot be re-added (domain mismatch, etc.)
            }
        }

        // Restore localStorage (SauceDemo auth state)
        @SuppressWarnings("unchecked")
        Map<String, Object> localStorage = (Map<String, Object>) payload.getOrDefault("localStorage", new HashMap<>());
        if (!localStorage.isEmpty()) {
            String setLocalStorage = localStorage.entrySet().stream()
                    .map(e -> "localStorage.setItem(" + MAPPER.valueToTree(e.getKey()) + "," + MAPPER.valueToTree(e.getValue()) + ");")
                    .reduce("", String::concat);
            ((JavascriptExecutor) driver).executeScript(setLocalStorage);
        }

        navigateWithRetry(driver, settings.getSauceDemoUrl() + "/inventory.html");
    }

    /**
     * Navigate with one retry on renderer timeout.
     * ChromeDriver's CDP exchange has a hard 30s timeout that can fire when
     * SauceDemo's third-party telemetry stalls; a single retry recovers cleanly.
     */
    private static void navigateWithRetry(WebDriver driver, String url) {
        try {
            driver.get(url);
        } catch (TimeoutException e) {
            logger.warn("[SauceAuthExtension] Navigation to {} timed out, retrying once", url);
            try {
                driver.navigate().refresh();
            } catch (Exception ignore) {
                // best effort
            }
            driver.get(url);
        }
    }

    private static Map<String, Object> serializeCookie(Cookie c) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", c.getName());
        map.put("value", c.getValue());
        map.put("domain", c.getDomain());
        map.put("path", c.getPath());
        map.put("secure", c.isSecure());
        map.put("httpOnly", c.isHttpOnly());
        if (c.getExpiry() != null) {
            map.put("expiry", c.getExpiry().getTime() / 1000);
        }
        return map;
    }

    private static Cookie deserializeCookie(Map<String, Object> raw) {
        Cookie.Builder b = new Cookie.Builder((String) raw.get("name"), (String) raw.get("value"))
                .domain((String) raw.get("domain"))
                .path((String) raw.getOrDefault("path", "/"))
                .isSecure(Boolean.TRUE.equals(raw.get("secure")))
                .isHttpOnly(Boolean.TRUE.equals(raw.get("httpOnly")));
        return b.build();
    }
}
