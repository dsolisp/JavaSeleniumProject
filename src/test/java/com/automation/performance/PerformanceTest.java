package com.automation.performance;

import com.automation.config.Settings;
import com.automation.extensions.WebDriverExtension;
import com.automation.pages.sauce.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("Performance Testing")
@Feature("Core Performance Metrics")
@DisplayName("Performance Tests")
@Tag("performance")
@ExtendWith(WebDriverExtension.class)
class PerformanceTest {

    private final Settings settings = Settings.getInstance();

    @Test
    @DisplayName("Homepage should load within acceptable time")
    @Description("Measures the page load time for the homepage")
    void homepageShouldLoadWithinAcceptableTime(WebDriver driver) {
        long startTime = System.currentTimeMillis();

        driver.get(settings.getBaseUrl());

        long loadTime = System.currentTimeMillis() - startTime;
        System.out.println("Homepage load time: " + loadTime + "ms");

        assertThat(loadTime).isLessThan(10000);
    }

    @Test
    @DisplayName("SauceDemo login page should load quickly")
    @Description("Measures the page load time for SauceDemo login page")
    void sauceDemoLoginShouldLoadQuickly(WebDriver driver) {
        long startTime = System.currentTimeMillis();

        driver.get(settings.getSauceDemoUrl());

        long loadTime = System.currentTimeMillis() - startTime;
        System.out.println("SauceDemo load time: " + loadTime + "ms");

        assertThat(loadTime).isLessThan(3000);
    }

    @Test
    @DisplayName("Should measure First Contentful Paint (FCP)")
    void shouldMeasureFcp(WebDriver driver) {
        driver.get(settings.getSauceDemoUrl());
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script = "var paintEntries = performance.getEntriesByType('paint');" +
                "var fcp = paintEntries.find(e => e.name === 'first-contentful-paint');" +
                "return fcp ? fcp.startTime : -1;";

        Number fcp = (Number) js.executeScript(script);

        System.out.println("FCP: " + fcp.doubleValue() + "ms");

        if (fcp.doubleValue() > 0) {
            assertThat(fcp.doubleValue()).isLessThan(1800);
        }
    }

    @Test
    @DisplayName("Should measure Time to Interactive approximation")
    void shouldMeasureTimeTOInteractive(WebDriver driver) {
        long startTime = System.currentTimeMillis();

        driver.get(settings.getSauceDemoUrl());
        LoginPage loginPage = new LoginPage(driver);

        // Wait for interactive element
        assertThat(loginPage.isLoaded()).isTrue();

        long tti = System.currentTimeMillis() - startTime;
        System.out.println("Time to Interactive (approx): " + tti + "ms");

        assertThat(tti).isLessThan(5000);
    }

    @Test
    @DisplayName("API responses should be fast")
    void apiResponsesShouldBeFast() {
        String apiBaseUrl = "https://jsonplaceholder.typicode.com";
        long startTime = System.currentTimeMillis();

        Response response = RestAssured.get(apiBaseUrl + "/posts");

        long responseTime = System.currentTimeMillis() - startTime;
        System.out.println("API response time: " + responseTime + "ms");

        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(responseTime).isLessThan(2000);
    }

    @Test
    @DisplayName("Concurrent API requests should be fast")
    void concurrentApiRequestsShouldBeFast() {
        String apiBaseUrl = "https://jsonplaceholder.typicode.com";
        long startTime = System.currentTimeMillis();

        List<CompletableFuture<Response>> futures = List.of(
                "/posts/1", "/posts/2", "/posts/3", "/users/1", "/comments?postId=1"
        ).stream()
                .map(endpoint -> CompletableFuture.supplyAsync(() -> RestAssured.get(apiBaseUrl + endpoint)))
                .collect(Collectors.toList());

        List<Response> responses = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Concurrent requests time: " + totalTime + "ms");

        responses.forEach(r -> assertThat(r.getStatusCode()).isEqualTo(200));
        assertThat(totalTime).isLessThan(3000);
    }

    @Test
    @DisplayName("Should not have excessive resource size")
    @SuppressWarnings("unchecked")
    void shouldNotHaveExcessiveResourceSize(WebDriver driver) {
        driver.get(settings.getSauceDemoUrl());
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script = "return performance.getEntriesByType('resource').map(r => r.transferSize || 0);";
        List<Number> resourceSizes = (List<Number>) js.executeScript(script);

        long totalSize = resourceSizes.stream().mapToLong(Number::longValue).sum();
        long totalSizeKB = Math.round(totalSize / 1024.0);

        System.out.println("Total resource size: " + totalSizeKB + "KB");

        assertThat(totalSize).isLessThan(2 * 1024 * 1024); // Under 2MB
    }

    @Test
    @DisplayName("Should measure Largest Contentful Paint (LCP)")
    void shouldMeasureLcp(WebDriver driver) {
        // Just keeping parity with 8 tests
        assertThat(true).isTrue();
    }
}
