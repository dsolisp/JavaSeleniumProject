package com.automation.web;

import com.automation.pages.SauceDemoPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * E-commerce tests using SauceDemo site.
 * Equivalent to Python's tests/web/test_sauce.py
 */
@Epic("Web UI Testing")
@Feature("E-Commerce")
@DisplayName("SauceDemo E-Commerce Tests")
@Tag("web")
@Tag("ecommerce")
class SauceDemoTest extends BaseWebTest {

    private SauceDemoPage saucePage;

    @BeforeEach
    void setUpPage() {
        saucePage = new SauceDemoPage(driver);
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
        saucePage.open()
                .loginAsStandardUser();
        
        assertThat(saucePage.isLoggedIn())
                .as("User should be logged in")
                .isTrue();
        
        logger.info("Standard user login successful");
    }

    @Test
    @Story("Login")
    @Description("Verify locked out user sees error message")
    @DisplayName("Locked out user should see error")
    void lockedOutUserShouldSeeError() {
        saucePage.open()
                .login(SauceDemoPage.LOCKED_OUT_USER, SauceDemoPage.PASSWORD);
        
        assertThat(saucePage.hasLoginError())
                .as("Error message should be displayed")
                .isTrue();
        
        assertThat(saucePage.getLoginErrorMessage())
                .contains("locked out");
    }

    @Test
    @Story("Login")
    @Description("Verify invalid credentials show error")
    @DisplayName("Invalid credentials should show error")
    void invalidCredentialsShouldShowError() {
        saucePage.open()
                .login("invalid_user", "invalid_password");
        
        assertThat(saucePage.hasLoginError()).isTrue();
        assertThat(saucePage.getLoginErrorMessage())
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
        saucePage.open()
                .loginAsStandardUser()
                .addFirstThreeItemsToCart();
        
        assertThat(saucePage.getCartItemCount())
                .as("Cart should have 3 items")
                .isEqualTo(3);
        
        logger.info("Successfully added 3 items to cart");
    }

    @Test
    @Story("Cart")
    @Description("Verify cart is accessible after login")
    @DisplayName("Cart should be accessible after login")
    void cartShouldBeAccessibleAfterLogin() {
        saucePage.open()
                .loginAsStandardUser();

        // Verify user is logged in
        assertThat(saucePage.isLoggedIn())
                .as("User should be logged in")
                .isTrue();

        // Verify cart is empty initially (badge doesn't show for empty cart)
        assertThat(saucePage.getCartItemCount())
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
        saucePage.open()
                .loginAsStandardUser();
        
        int itemCount = saucePage.getInventoryItemCount();
        
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
        saucePage.open()
                .loginAsStandardUser()
                .addFirstThreeItemsToCart()
                .openCart()
                .startCheckout()
                .fillCheckoutInfo("John", "Doe", "12345")
                .finishCheckout();
        
        assertThat(saucePage.isOrderComplete())
                .as("Order should be complete")
                .isTrue();
        
        assertThat(saucePage.getCompleteMessage())
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
        saucePage.open()
                .loginAsStandardUser();
        
        assertThat(saucePage.isLoggedIn()).isTrue();
        
        saucePage.logout();
        
        // Should be back on login page
        assertThat(saucePage.isLoggedIn()).isFalse();
    }
}

