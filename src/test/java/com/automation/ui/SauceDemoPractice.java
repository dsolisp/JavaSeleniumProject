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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * SAUCEDEMO SELENIUM PRACTICE — interview-style challenges, no page objects.
 *
 * Run all:    mvn test -Dtest=SauceDemoPractice
 * Run one:    mvn test -Dtest=SauceDemoPractice#challenge2_lockedOutUserShowsError
 *
 * Each test is a CHALLENGE. Read the comment, try to write it yourself, then
 * compare with the solution. Remove "--headless=new" in setUp() to watch it.
 */
class SauceDemoPractice {

    WebDriver driver;
    WebDriverWait wait;

    static final String URL = "https://www.saucedemo.com";
    static final String USER = "standard_user";
    static final String PASS = "secret_sauce";

    // ─── Locators (the exact ones used by the real suite) ──────────────────
    static final By USERNAME = By.id("user-name");
    static final By PASSWORD = By.id("password");
    static final By LOGIN_BTN = By.id("login-button");
    static final By ERROR = By.cssSelector("[data-test='error']");
    static final By ITEM_NAMES = By.cssSelector(".inventory_item_name");
    static final By ITEM_PRICES = By.cssSelector(".inventory_item_price");
    static final By SORT = By.cssSelector("[data-test='product-sort-container']");
    static final By ADD_BUTTONS = By.cssSelector("button[data-test^='add-to-cart']");
    static final By CART_BADGE = By.cssSelector(".shopping_cart_badge");

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1280,800");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    /** Reusable helper — log in with the given credentials. */
    void login(String user, String pass) {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME)).sendKeys(user);
        driver.findElement(PASSWORD).sendKeys(pass);
        driver.findElement(LOGIN_BTN).click();
    }

    // CHALLENGE 1: Log in as standard_user and confirm you reach the inventory.
    @Test
    void challenge1_validLoginReachesInventory() {
        login(USER, PASS);
        wait.until(ExpectedConditions.urlContains("inventory"));
        assertTrue(driver.getCurrentUrl().contains("inventory"));
        assertEquals(6, driver.findElements(ITEM_NAMES).size());
    }

    // CHALLENGE 2: Log in as "locked_out_user" and assert the error text.
    @Test
    void challenge2_lockedOutUserShowsError() {
        login("locked_out_user", PASS);
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR));
        assertTrue(error.getText().contains("locked out"),
                "Expected a 'locked out' message, got: " + error.getText());
    }

    // CHALLENGE 3: Sort products A→Z and verify the names are alphabetical.
    @Test
    void challenge3_sortNamesAscending() {
        login(USER, PASS);
        wait.until(ExpectedConditions.urlContains("inventory"));
        new Select(driver.findElement(SORT)).selectByValue("az");

        List<String> names = new ArrayList<>();
        for (WebElement el : driver.findElements(ITEM_NAMES)) names.add(el.getText());

        List<String> expected = new ArrayList<>(names);
        Collections.sort(expected);
        assertEquals(expected, names, "Names should be sorted A→Z");
    }

    // CHALLENGE 4: Sort by price Low→High and verify the prices ascend.
    @Test
    void challenge4_sortPricesLowToHigh() {
        login(USER, PASS);
        wait.until(ExpectedConditions.urlContains("inventory"));
        new Select(driver.findElement(SORT)).selectByValue("lohi");

        List<Double> prices = new ArrayList<>();
        for (WebElement el : driver.findElements(ITEM_PRICES)) {
            prices.add(Double.parseDouble(el.getText().replace("$", "")));
        }

        List<Double> expected = new ArrayList<>(prices);
        Collections.sort(expected);
        assertEquals(expected, prices, "Prices should ascend");
    }

    // CHALLENGE 5: Add 2 items and verify the cart badge shows "2".
    @Test
    void challenge5_cartBadgeCountsItems() {
        login(USER, PASS);
        wait.until(ExpectedConditions.urlContains("inventory"));

        List<WebElement> addButtons = driver.findElements(ADD_BUTTONS);
        addButtons.get(0).click();
        addButtons.get(1).click();

        String badge = wait.until(ExpectedConditions.visibilityOfElementLocated(CART_BADGE)).getText();
        assertEquals("2", badge);
    }

    // CHALLENGE 6: Full checkout — add an item and complete the order.
    @Test
    void challenge6_completeCheckout() {
        login(USER, PASS);
        wait.until(ExpectedConditions.urlContains("inventory"));

        driver.findElements(ADD_BUTTONS).get(0).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Ada");
        driver.findElement(By.id("last-name")).sendKeys("Lovelace");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();

        WebElement header = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".complete-header")));
        assertEquals("Thank you for your order!", header.getText());
    }
}
