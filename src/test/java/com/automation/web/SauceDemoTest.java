package com.automation.web;

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
class SauceDemoTest extends BaseWebTest {

    private LoginPage loginPage;
    private final TestDataManager testData = new TestDataManager();

    @BeforeEach
    void setUpPage() {
        loginPage = new LoginPage(driver);
    }

    // ═══════════════════════════════════════════════════════════════════
    // LOGIN TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("smoke")
    @Story("Login")
    @Description("Verify standard user can login successfully")
    @DisplayName("Standard user should login successfully")
    void standardUserShouldLoginSuccessfully() {
        InventoryPage inventory = loginPage.open().loginAsStandardUser();

        assertThat(inventory.getItemCount())
                .as("User should be on inventory page")
                .isGreaterThan(0);

        logger.info("Standard user login successful");
    }

    @Test
    @Story("Login")
    @Description("Verify locked out user sees error message")
    @DisplayName("Locked out user should see error")
    void lockedOutUserShouldSeeError() {
        Map<String, String> lockedUser = testData.getLockedOutUserCredentials();
        loginPage.open().login(lockedUser.get("username"), lockedUser.get("password"));

        assertThat(loginPage.hasLoginError())
                .as("Error message should be displayed")
                .isTrue();

        assertThat(loginPage.getLoginErrorMessage())
                .contains("locked out");
    }

    @Test
    @Story("Login")
    @Description("Verify invalid credentials show error")
    @DisplayName("Invalid credentials should show error")
    void invalidCredentialsShouldShowError() {
        loginPage.open().login("invalid_user", "invalid_password");

        assertThat(loginPage.hasLoginError()).isTrue();
        assertThat(loginPage.getLoginErrorMessage())
                .containsIgnoringCase("username and password");
    }

    // ═══════════════════════════════════════════════════════════════════
    // CART TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Tag("smoke")
    @Story("Cart")
    @Description("Verify user can add items to cart")
    @DisplayName("User should be able to add items to cart")
    void userShouldBeAbleToAddItemsToCart() {
        InventoryPage inventory = loginPage.open().loginAsStandardUser();
        inventory.addFirstThreeItems();

        assertThat(inventory.getCartBadgeCount())
                .as("Cart should have 3 items")
                .isEqualTo(3);

        logger.info("Successfully added 3 items to cart");
    }

    @Test
    @Story("Cart")
    @Description("Verify cart is accessible after login")
    @DisplayName("Cart should be accessible after login")
    void cartShouldBeAccessibleAfterLogin() {
        InventoryPage inventory = loginPage.open().loginAsStandardUser();

        assertThat(inventory.getItemCount())
                .as("User should be on inventory page")
                .isGreaterThan(0);

        assertThat(inventory.getCartBadgeCount())
                .as("Cart should be empty initially")
                .isEqualTo(0);
    }

    // ═══════════════════════════════════════════════════════════════════
    // INVENTORY TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Inventory")
    @Description("Verify inventory displays products")
    @DisplayName("Inventory should display products")
    void inventoryShouldDisplayProducts() {
        InventoryPage inventory = loginPage.open().loginAsStandardUser();

        int itemCount = inventory.getItemCount();

        assertThat(itemCount)
                .as("Inventory should have products")
                .isGreaterThan(0);

        logger.info("Found {} products in inventory", itemCount);
    }

    // ═══════════════════════════════════════════════════════════════════
    // CHECKOUT TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Checkout")
    @Description("Verify complete checkout workflow")
    @DisplayName("Complete checkout should succeed")
    void completeCheckoutShouldSucceed() {
        InventoryPage inventory = loginPage.open().loginAsStandardUser();
        inventory.addFirstThreeItems();
        CartPage cart = inventory.openCart();
        CheckoutPage checkout = cart.startCheckout();
        checkout.fillInfoAndContinue("John", "Doe", "12345").finish();

        assertThat(checkout.isOrderComplete())
                .as("Order should be complete")
                .isTrue();

        assertThat(checkout.getCompleteMessage())
                .containsIgnoringCase("thank you");

        logger.info("Checkout completed successfully");
    }

    // ═══════════════════════════════════════════════════════════════════
    // LOGOUT TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Logout")
    @Description("Verify user can logout")
    @DisplayName("User should be able to logout")
    void userShouldBeAbleToLogout() {
        InventoryPage inventory = loginPage.open().loginAsStandardUser();

        assertThat(inventory.getItemCount()).isGreaterThan(0);

        LoginPage afterLogout = inventory.logout();

        assertThat(afterLogout.isOnInventoryPage()).isFalse();
    }
}

