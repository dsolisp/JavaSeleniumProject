package com.automation.ui.sauce;

import com.automation.extensions.PageObjectExtension;
import com.automation.extensions.SauceAuthExtension;
import com.automation.extensions.SauceAuthenticated;
import com.automation.extensions.SharedDriver;
import com.automation.extensions.WebDriverExtension;
import com.automation.pages.sauce.CartPage;
import com.automation.pages.sauce.CheckoutPage;
import com.automation.pages.sauce.InventoryPage;
import com.automation.pages.sauce.LoginPage;
import com.automation.utils.TestDataManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

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
@ExtendWith({WebDriverExtension.class, SauceAuthExtension.class, PageObjectExtension.class})
class SauceDemoTest {

    private final TestDataManager testData = new TestDataManager();

    // ═══════════════════════════════════════════════════════════════════
    // LOGIN TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("smoke")
    @Story("Login")
    @DisplayName("should login with valid credentials")
    void shouldLoginWithValidCredentials(LoginPage loginPage) {
        Map<String, String> user = testData.getStandardUserCredentials();
        InventoryPage inventory = Allure.step("Log in as standard user", () -> {
            return loginPage.open().login(user.get("username"), user.get("password"));
        });
        Allure.step("Verify the inventory is displayed", () -> {
            assertThat(inventory.getItemCount()).isGreaterThan(0);
        });
    }

    @Test
    @Story("Login")
    @DisplayName("should show error for locked out user")
    void shouldShowErrorForLockedOutUser(LoginPage loginPage) {
        Map<String, String> user = testData.getLockedOutUserCredentials();
        Allure.step("Attempt login as locked-out user", () -> {
            loginPage.open().login(user.get("username"), user.get("password"));
        });
        Allure.step("Verify the locked-out error is shown", () -> {
            assertThat(loginPage.hasLoginError()).isTrue();
            assertThat(loginPage.getLoginErrorMessage()).contains("locked out");
        });
    }

    @Test
    @Story("Login")
    @DisplayName("should show error for invalid credentials")
    void shouldShowErrorForInvalidCredentials(LoginPage loginPage) {
        Map<String, String> user = testData.getInvalidCredentials();
        Allure.step("Attempt login with invalid credentials", () -> {
            loginPage.open().login(user.get("username"), user.get("password"));
        });
        Allure.step("Verify the credentials-mismatch error is shown", () -> {
            assertThat(loginPage.hasLoginError()).isTrue();
            assertThat(loginPage.getLoginErrorMessage()).contains("do not match");
        });
    }

    @Test
    @Story("Login")
    @DisplayName("should show error for empty username")
    void shouldShowErrorForEmptyUsername(LoginPage loginPage) {
        String password = testData.getStandardUserCredentials().get("password");
        Allure.step("Attempt login with an empty username", () -> {
            loginPage.open().login("", password);
        });
        Allure.step("Verify the username-required error is shown", () -> {
            assertThat(loginPage.hasLoginError()).isTrue();
            assertThat(loginPage.getLoginErrorMessage()).contains("Username is required");
        });
    }

    @Test
    @Story("Login")
    @DisplayName("should show error for empty password")
    void shouldShowErrorForEmptyPassword(LoginPage loginPage) {
        Map<String, String> user = testData.getStandardUserCredentials();
        Allure.step("Attempt login with an empty password", () -> {
            loginPage.open().login(user.get("username"), "");
        });
        Allure.step("Verify the password-required error is shown", () -> {
            assertThat(loginPage.hasLoginError()).isTrue();
            assertThat(loginPage.getLoginErrorMessage()).contains("Password is required");
        });
    }

    // ═══════════════════════════════════════════════════════════════════
    // INVENTORY TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("smoke")
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should display 6 products")
    void shouldDisplay6Products(InventoryPage inventory) {
        Allure.step("Verify 6 products are displayed", () -> {
            assertThat(inventory.getItemCount()).isEqualTo(6);
        });
    }

    @Test
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should display product names")
    void shouldDisplayProductNames(InventoryPage inventory) {
        Allure.step("Verify product names are present", () -> {
            assertThat(inventory.getItemNames()).hasSize(6);
            assertThat(inventory.getItemNames().get(0)).isNotBlank();
        });
    }

    @Test
    @Tag("smoke")
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should add item to cart and update badge")
    void shouldAddItemToCartAndUpdateBadge(InventoryPage inventory) {
        Allure.step("Add the first item to the cart", () -> {
            inventory.addItemToCart(0);
        });
        Allure.step("Verify the cart badge shows 1", () -> {
            assertThat(inventory.getCartBadgeCount()).isEqualTo(1);
        });
    }

    @Test
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should add multiple items to cart")
    void shouldAddMultipleItemsToCart(InventoryPage inventory) {
        Allure.step("Add two items to the cart", () -> {
            inventory.addItemToCart(0);
            inventory.addItemToCart(1);
        });
        Allure.step("Verify the cart badge shows 2", () -> {
            assertThat(inventory.getCartBadgeCount()).isEqualTo(2);
        });
    }

    @Test
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should sort products by name A-Z")
    void shouldSortProductsByNameAZ(InventoryPage inventory) {
        Allure.step("Sort products by name A-Z", () -> {
            inventory.sortBy("az");
        });
        Allure.step("Verify products are sorted alphabetically", () -> {
            java.util.List<String> names = inventory.getItemNames();
            java.util.List<String> sortedNames = new java.util.ArrayList<>(names);
            java.util.Collections.sort(sortedNames);
            assertThat(names).isEqualTo(sortedNames);
        });
    }

    @Test
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should sort products by price low to high")
    void shouldSortProductsByPriceLowToHigh(InventoryPage inventory) {
        Allure.step("Sort products by price low to high", () -> {
            inventory.sortBy("lohi");
        });
        Allure.step("Verify prices are in ascending order", () -> {
            java.util.List<String> prices = inventory.getItemPrices();
            java.util.List<Double> numericPrices = prices.stream()
                    .map(p -> Double.parseDouble(p.replace("$", "")))
                    .toList();

            java.util.List<Double> sortedPrices = new java.util.ArrayList<>(numericPrices);
            java.util.Collections.sort(sortedPrices);
            assertThat(numericPrices).isEqualTo(sortedPrices);
        });
    }

    // ═══════════════════════════════════════════════════════════════════
    // CHECKOUT TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("smoke")
    @Story("Checkout")
    @SauceAuthenticated
    @DisplayName("should complete full checkout flow")
    void shouldCompleteFullCheckoutFlow(InventoryPage inventory) {
        Map<String, String> info = testData.getCheckoutInfo();
        CartPage cart = Allure.step("Add an item and open the cart", () -> {
            inventory.addItemToCart(0);
            return inventory.openCart();
        });
        Allure.step("Verify the cart has 1 item", () -> {
            assertThat(cart.getItemCount()).isEqualTo(1);
        });

        CheckoutPage checkout = cart.startCheckout();
        Allure.step("Fill checkout information and finish the order", () -> {
            checkout.fillInfoAndContinue(
                    info.get("firstName"), info.get("lastName"), info.get("postalCode")).finish();
        });
        Allure.step("Verify the order completion message", () -> {
            assertThat(checkout.isOrderComplete()).isTrue();
            assertThat(checkout.getCompleteMessage()).containsIgnoringCase("thank you");
        });
    }

    @Test
    @Story("Checkout")
    @SauceAuthenticated
    @DisplayName("should allow removing item from cart")
    void shouldAllowRemovingItemFromCart(InventoryPage inventory) {
        CartPage cart = Allure.step("Add an item and open the cart", () -> {
            inventory.addItemToCart(0);
            return inventory.openCart();
        });
        Allure.step("Verify the cart has 1 item", () -> {
            assertThat(cart.getItemCount()).isEqualTo(1);
        });

        Allure.step("Remove the item from the cart", () -> {
            cart.removeItemByIndex(0);
        });
        Allure.step("Verify the cart is empty", () -> {
            assertThat(cart.getItemCount()).isEqualTo(0);
        });
    }
}

