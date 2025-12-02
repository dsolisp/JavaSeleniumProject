package com.automation.unit;

import com.automation.config.Constants;
import com.automation.config.Settings;
import com.automation.locators.*;
import com.automation.utils.TestDataManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;

import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.*;

/**
 * Regression protection tests to verify framework modules are properly configured.
 * Equivalent to Python's tests/unit/test_regression_protection.py
 * 
 * These tests catch breaking changes early in the CI pipeline.
 */
@Epic("Framework Stability")
@Feature("Regression Protection")
@DisplayName("Regression Protection Tests")
@Tag("unit")
@Tag("regression")
class RegressionProtectionTest {

    // ═══════════════════════════════════════════════════════════════════
    // MODULE IMPORT/INSTANTIATION TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Description("Verify all config classes are loadable")
    @DisplayName("Config classes should be loadable")
    void configClassesShouldBeLoadable() {
        assertThatCode(() -> {
            Settings.getInstance();
            Class.forName("com.automation.config.Constants");
        }).doesNotThrowAnyException();
    }

    @Test
    @Description("Verify all utility classes are loadable")
    @DisplayName("Utility classes should be loadable")
    void utilityClassesShouldBeLoadable() {
        assertThatCode(() -> {
            Class.forName("com.automation.utils.WebDriverFactory");
            Class.forName("com.automation.utils.TestDataManager");
            Class.forName("com.automation.utils.ScreenshotService");
            Class.forName("com.automation.utils.SqlConnection");
            Class.forName("com.automation.utils.ErrorHandler");
        }).doesNotThrowAnyException();
    }

    @Test
    @Description("Verify all locator classes are loadable")
    @DisplayName("Locator classes should be loadable")
    void locatorClassesShouldBeLoadable() {
        assertThatCode(() -> {
            Class.forName("com.automation.locators.SearchEngineLocators");
            Class.forName("com.automation.locators.ResultPageLocators");
            Class.forName("com.automation.locators.SauceLocators");
            Class.forName("com.automation.locators.TestFrameworkLocators");
        }).doesNotThrowAnyException();
    }

    @Test
    @Description("Verify all page classes are loadable")
    @DisplayName("Page classes should be loadable")
    void pageClassesShouldBeLoadable() {
        assertThatCode(() -> {
            Class.forName("com.automation.pages.BasePage");
            Class.forName("com.automation.pages.SearchEnginePage");
            Class.forName("com.automation.pages.SauceDemoPage");
        }).doesNotThrowAnyException();
    }

    // ═══════════════════════════════════════════════════════════════════
    // CONSTANTS VALIDATION
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Description("Verify essential constants are defined")
    @DisplayName("Essential constants should be defined")
    void essentialConstantsShouldBeDefined() {
        assertThat(Constants.USER_AGENT_CHROME).isNotEmpty();
        assertThat(Constants.DEFAULT_EXPLICIT_WAIT).isGreaterThan(0);
        assertThat(Constants.DEFAULT_PAGE_LOAD_TIMEOUT).isGreaterThan(0);
        assertThat(Constants.DEFAULT_RETRY_ATTEMPTS).isGreaterThan(0);
        assertThat(Constants.BROWSER_CHROME).isEqualTo("chrome");
    }

    @Test
    @Description("Verify Constants class is not instantiable")
    @DisplayName("Constants class should not be instantiable")
    void constantsClassShouldNotBeInstantiable() throws Exception {
        var constructor = Constants.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();
    }

    // ═══════════════════════════════════════════════════════════════════
    // SETTINGS VALIDATION
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Description("Verify Settings singleton works correctly")
    @DisplayName("Settings should be a working singleton")
    void settingsShouldBeWorkingSingleton() {
        Settings settings1 = Settings.getInstance();
        Settings settings2 = Settings.getInstance();
        
        assertThat(settings1).isSameAs(settings2);
        assertThat(settings1.getBaseUrl()).isNotEmpty();
        assertThat(settings1.getBrowser()).isNotEmpty();
    }

    @Test
    @Description("Verify Settings has required methods")
    @DisplayName("Settings should have required methods")
    void settingsShouldHaveRequiredMethods() throws Exception {
        Class<?> clazz = Settings.class;
        
        assertThat(clazz.getMethod("getInstance")).isNotNull();
        assertThat(clazz.getMethod("getBaseUrl")).isNotNull();
        assertThat(clazz.getMethod("getBrowser")).isNotNull();
        assertThat(clazz.getMethod("isHeadless")).isNotNull();
        assertThat(clazz.getMethod("getExplicitWait")).isNotNull();
    }

    // ═══════════════════════════════════════════════════════════════════
    // LOCATORS VALIDATION
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Description("Verify SearchEngineLocators has required locators")
    @DisplayName("SearchEngineLocators should have required locators")
    void searchEngineLocatorsShouldHaveRequiredLocators() {
        assertThat(SearchEngineLocators.SEARCH_INPUT).isNotNull();
        assertThat(SearchEngineLocators.SEARCH_BUTTON).isNotNull();
    }

    @Test
    @Description("Verify SauceLocators has required locators")
    @DisplayName("SauceLocators should have required locators")
    void sauceLocatorsShouldHaveRequiredLocators() {
        assertThat(SauceLocators.USERNAME_INPUT).isNotNull();
        assertThat(SauceLocators.PASSWORD_INPUT).isNotNull();
        assertThat(SauceLocators.LOGIN_BUTTON).isNotNull();
        assertThat(SauceLocators.INVENTORY_ITEMS).isNotNull();
    }

    // ═══════════════════════════════════════════════════════════════════
    // DATA MANAGER VALIDATION
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Description("Verify TestDataManager can generate test data")
    @DisplayName("TestDataManager should generate test data")
    void testDataManagerShouldGenerateTestData() {
        TestDataManager tdm = new TestDataManager();
        
        var userData = tdm.generate()
            .withEmail()
            .withUsername()
            .withPassword()
            .build();
        
        assertThat(userData).containsKeys("email", "username", "password");
        assertThat((String) userData.get("email")).contains("@");
    }
}

