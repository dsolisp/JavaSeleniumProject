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
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
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
    // CONSOLIDATED TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("smoke")
    @Story("Login")
    @DisplayName("should verify all login scenarios (positive and negatives)")
    void testAllLoginScenarios(LoginPage loginPage) {
        Allure.step("Verify all login scenarios with soft assertions", () ->
                SoftAssertions.assertSoftly(softly -> {
                    // Positive: Valid credentials
                    Map<String, String> standard = testData.getStandardUserCredentials();
                    InventoryPage inventory = loginPage.open().login(standard.get("username"), standard.get("password"));
                    softly.assertThat(inventory.getItemCount()).as("inventory item count").isGreaterThan(0);

                    // Negative: Locked out user
                    Map<String, String> locked = testData.getLockedOutUserCredentials();
                    loginPage.open().login(locked.get("username"), locked.get("password"));
                    softly.assertThat(loginPage.hasLoginError()).as("locked out error visible").isTrue();
                    softly.assertThat(loginPage.getLoginErrorMessage()).as("locked out error message").contains("locked out");

                    // Negative: Invalid credentials
                    Map<String, String> invalid = testData.getInvalidCredentials();
                    loginPage.open().login(invalid.get("username"), invalid.get("password"));
                    softly.assertThat(loginPage.hasLoginError()).as("invalid creds error visible").isTrue();
                    softly.assertThat(loginPage.getLoginErrorMessage()).as("invalid creds error message").contains("do not match");

                    // Negative: Empty username
                    loginPage.open().login("", standard.get("password"));
                    softly.assertThat(loginPage.hasLoginError()).as("empty user error visible").isTrue();
                    softly.assertThat(loginPage.getLoginErrorMessage()).as("empty user error message").contains("Username is required");

                    // Negative: Empty password
                    loginPage.open().login(standard.get("username"), "");
                    softly.assertThat(loginPage.hasLoginError()).as("empty pass error visible").isTrue();
                    softly.assertThat(loginPage.getLoginErrorMessage()).as("empty pass error message").contains("Password is required");
                }));
    }

    @Test
    @Tag("smoke")
    @Story("Inventory")
    @SauceAuthenticated
    @DisplayName("should verify inventory display and sorting")
    void testInventoryDisplayAndSorting(InventoryPage inventory) {
        Allure.step("Verify product display and sorting with soft assertions", () ->
                SoftAssertions.assertSoftly(softly -> {
                    // Display
                    softly.assertThat(inventory.getItemCount()).as("item count").isEqualTo(6);
                    List<String> names = inventory.getItemNames();
                    softly.assertThat(names).as("item names count").hasSize(6);
                    softly.assertThat(names.get(0)).as("first item name").isNotBlank();

                    // Sort A-Z
                    inventory.sortBy("az");
                    List<String> namesAz = inventory.getItemNames();
                    List<String> sortedNames = new java.util.ArrayList<>(namesAz);
                    java.util.Collections.sort(sortedNames);
                    softly.assertThat(namesAz).as("sorted names A-Z").isEqualTo(sortedNames);

                    // Sort Price Low to High
                    inventory.sortBy("lohi");
                    List<Double> numericPrices = inventory.getItemPrices().stream()
                            .map(p -> Double.parseDouble(p.replace("$", "")))
                            .toList();
                    List<Double> sortedPrices = new java.util.ArrayList<>(numericPrices);
                    java.util.Collections.sort(sortedPrices);
                    softly.assertThat(numericPrices).as("sorted prices low-high").isEqualTo(sortedPrices);
                }));
    }

    @Test
    @Tag("smoke")
    @Story("Checkout")
    @SauceAuthenticated
    @DisplayName("should verify cart and checkout flow")
    void testCartAndCheckoutFlow(InventoryPage inventory) {
        Allure.step("Verify cart operations and checkout flow", () -> {
            SoftAssertions.assertSoftly(softly -> {
                // Add and verify badge
                inventory.addItemToCart(0);
                softly.assertThat(inventory.getCartBadgeCount()).as("badge after 1 item").isEqualTo(1);
                inventory.addItemToCart(1);
                softly.assertThat(inventory.getCartBadgeCount()).as("badge after 2 items").isEqualTo(2);

                // Open cart and remove item
                CartPage cart = inventory.openCart();
                softly.assertThat(cart.getItemCount()).as("cart item count before removal").isEqualTo(2);
                cart.removeItemByIndex(0);
                softly.assertThat(cart.getItemCount()).as("cart item count after removal").isEqualTo(1);

                // Full checkout flow
                Map<String, String> info = testData.getCheckoutInfo();
                CheckoutPage checkout = cart.startCheckout();
                checkout.fillInfoAndContinue(
                        info.get("firstName"), info.get("lastName"), info.get("postalCode")).finish();

                softly.assertThat(checkout.isOrderComplete()).as("order complete").isTrue();
                softly.assertThat(checkout.getCompleteMessage()).as("completion message").containsIgnoringCase("thank you");
            });
        });
    }

}

