package com.automation.ui.sauce;

import com.automation.extensions.SauceAuthExtension;
import com.automation.extensions.SauceAuthenticated;
import com.automation.extensions.SharedDriver;
import com.automation.extensions.WebDriverExtension;
import com.automation.pages.sauce.CartPage;
import com.automation.pages.sauce.CheckoutPage;
import com.automation.pages.sauce.InventoryPage;
import com.automation.pages.sauce.LoginPage;
import com.automation.utils.TestDataManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * E-commerce tests using SauceDemo site.
 */
@Epic("Web UI Testing")
@Feature("E-Commerce")
@DisplayName("SauceDemo E-Commerce Tests")
@Tag("web")
@Tag("ecommerce")
@SharedDriver
@ExtendWith({WebDriverExtension.class, SauceAuthExtension.class})
class SauceDemoTest {

    private static final Logger logger = LoggerFactory.getLogger(SauceDemoTest.class);
    private final TestDataManager testData = new TestDataManager();

    // ═══════════════════════════════════════════════════════════════════
    // LOGIN TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("smoke")
    @Story("Login")
    @DisplayName("should login with valid credentials")
    void shouldLoginWithValidCredentials(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        Map<String, String> user = testData.getStandardUserCredentials();
        InventoryPage inventory = loginPage.open().login(user.get("username"), user.get("password"));

        assertThat(inventory.getItemCount()).isGreaterThan(0);
    }

    @Test
    @Story("Login")
    @DisplayName("should show error for locked out user")
    void shouldShowErrorForLockedOutUser(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        Map<String, String> user = testData.getLockedOutUserCredentials();
        loginPage.open().login(user.get("username"), user.get("password"));

        assertThat(loginPage.hasLoginError()).isTrue();
        assertThat(loginPage.getLoginErrorMessage()).contains("locked out");
    }

    @Test
    @Story("Login")
    @DisplayName("should show error for invalid credentials")
    void shouldShowErrorForInvalidCredentials(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open().login("bad_user", "bad_password");

        assertThat(loginPage.hasLoginError()).isTrue();
        assertThat(loginPage.getLoginErrorMessage()).contains("do not match");
    }

    @Test
    @Story("Login")
    @DisplayName("should show error for empty username")
    void shouldShowErrorForEmptyUsername(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open().login("", "secret_sauce");

        assertThat(loginPage.hasLoginError()).isTrue();
        assertThat(loginPage.getLoginErrorMessage()).contains("Username is required");
    }

    @Test
    @Story("Login")
    @DisplayName("should show error for empty password")
    void shouldShowErrorForEmptyPassword(WebDriver driver) {
        LoginPage loginPage = new LoginPage(driver);
        Map<String, String> user = testData.getStandardUserCredentials();
        loginPage.open().login(user.get("username"), "");

        assertThat(loginPage.hasLoginError()).isTrue();
        assertThat(loginPage.getLoginErrorMessage()).contains("Password is required");
    }

    // ═══════════════════════════════════════════════════════════════════
    // INVENTORY TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("smoke")
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should display 6 products")
    void shouldDisplay6Products(WebDriver driver) {
        InventoryPage inventory = new InventoryPage(driver);
        assertThat(inventory.getItemCount()).isEqualTo(6);
    }

    @Test
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should display product names")
    void shouldDisplayProductNames(WebDriver driver) {
        InventoryPage inventory = new InventoryPage(driver);
        assertThat(inventory.getItemNames()).hasSize(6);
        assertThat(inventory.getItemNames().get(0)).isNotBlank();
    }

    @Test
    @Tag("smoke")
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should add item to cart and update badge")
    void shouldAddItemToCartAndUpdateBadge(WebDriver driver) {
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addItemToCart(0);
        assertThat(inventory.getCartBadgeCount()).isEqualTo(1);
    }

    @Test
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should add multiple items to cart")
    void shouldAddMultipleItemsToCart(WebDriver driver) {
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addItemToCart(0);
        inventory.addItemToCart(1);
        assertThat(inventory.getCartBadgeCount()).isEqualTo(2);
    }

    @Test
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should sort products by name A-Z")
    void shouldSortProductsByNameAZ(WebDriver driver) {
        InventoryPage inventory = new InventoryPage(driver);
        inventory.sortBy("az");
        java.util.List<String> names = inventory.getItemNames();
        java.util.List<String> sortedNames = new java.util.ArrayList<>(names);
        java.util.Collections.sort(sortedNames);
        assertThat(names).isEqualTo(sortedNames);
    }

    @Test
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should sort products by price low to high")
    void shouldSortProductsByPriceLowToHigh(WebDriver driver) {
        InventoryPage inventory = new InventoryPage(driver);
        inventory.sortBy("lohi");
        java.util.List<String> prices = inventory.getItemPrices();
        java.util.List<Double> numericPrices = prices.stream()
                .map(p -> Double.parseDouble(p.replace("$", "")))
                .toList();

        java.util.List<Double> sortedPrices = new java.util.ArrayList<>(numericPrices);
        java.util.Collections.sort(sortedPrices);
        assertThat(numericPrices).isEqualTo(sortedPrices);
    }

    // ═══════════════════════════════════════════════════════════════════
    // CHECKOUT TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("smoke")
    @Story("Checkout")
    @SauceAuthenticated
    @DisplayName("should complete full checkout flow")
    void shouldCompleteFullCheckoutFlow(WebDriver driver) {
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addItemToCart(0);
        CartPage cart = inventory.openCart();
        assertThat(cart.getItemCount()).isEqualTo(1);

        CheckoutPage checkout = cart.startCheckout();
        checkout.fillInfoAndContinue("John", "Doe", "12345").finish();

        assertThat(checkout.isOrderComplete()).isTrue();
        assertThat(checkout.getCompleteMessage()).containsIgnoringCase("thank you");
    }

    @Test
    @Story("Checkout")
    @SauceAuthenticated
    @DisplayName("should allow removing item from cart")
    void shouldAllowRemovingItemFromCart(WebDriver driver) {
        InventoryPage inventory = new InventoryPage(driver);
        inventory.addItemToCart(0);
        CartPage cart = inventory.openCart();
        assertThat(cart.getItemCount()).isEqualTo(1);

        cart.removeItemByIndex(0);
        assertThat(cart.getItemCount()).isEqualTo(0);
    }
}

