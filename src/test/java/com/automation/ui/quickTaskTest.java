package com.automation.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Quick scratch test — opens SauceDemo and logs in.
 * No page objects, no extensions, no pom.xml changes.
 * Selenium Manager (built into Selenium 4.6+) handles ChromeDriver automatically.
 */
class quickTaskTest {

    WebDriver driver;
    WebDriverWait wait;

    // ─── credentials (hardcoded for scratch purposes) ────────────────────────
    static final String URL      = "https://www.saucedemo.com";
    static final String USERNAME = "standard_user";
    static final String PASSWORD = "secret_sauce";

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");   // remove this line to watch the browser
        options.addArguments("--window-size=1280,800");

        driver = new ChromeDriver(options);
        wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void opensLoginPage() {
        driver.get(URL);

        // Page title
        assertEquals("Swag Labs", driver.getTitle());

        // Username field is visible
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))
        );
        assertTrue(usernameField.isDisplayed(), "Username field should be visible");
    }

    @Test
    void logsInWithValidCredentials() {
        driver.get(URL);

        // Fill in credentials and submit
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")))
                .sendKeys(USERNAME);
        driver.findElement(By.id("password")).sendKeys(PASSWORD);
        driver.findElement(By.id("login-button")).click();

        // After login we should land on the inventory page
        wait.until(ExpectedConditions.urlContains("inventory"));

        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("inventory"), "Should be on inventory page after login");

        // Products header is visible
        WebElement header = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("title"))
        );
        assertEquals("Products", header.getText());
    }
}
