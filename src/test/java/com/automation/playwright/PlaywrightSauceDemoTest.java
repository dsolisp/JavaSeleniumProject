package com.automation.playwright;

import com.microsoft.playwright.Page;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Playwright-based tests for SauceDemo.
 * Mirrors the Selenium SauceDemoTest to demonstrate both frameworks.
 */
@Epic("Playwright Tests")
@Feature("SauceDemo E-Commerce")
@DisplayName("Playwright SauceDemo Tests")
@Tag("playwright")
class PlaywrightSauceDemoTest {

    private static final Logger logger = LoggerFactory.getLogger(PlaywrightSauceDemoTest.class);

    private Page page;
    private PlaywrightSauceDemoPage saucePage;

    @BeforeEach
    void setUp() {
        page = PlaywrightFactory.createPage();
        saucePage = new PlaywrightSauceDemoPage(page);
    }

    @AfterEach
    void tearDown() {
        PlaywrightFactory.close();
    }

    // ═══════════════════════════════════════════════════════════════════
    // LOGIN TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Login")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify standard user can login successfully")
    @DisplayName("Standard user login should succeed")
    void standardUserLoginShouldSucceed() {
        logger.info("Testing standard user login");

        saucePage.open()
                .loginAsStandardUser();

        assertThat(saucePage.isOnInventoryPage())
                .as("Should be on inventory page after login")
                .isTrue();

        assertThat(saucePage.getInventoryItemCount())
                .as("Should see products")
                .isGreaterThan(0);
    }

    @Test
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify locked out user sees error message")
    @DisplayName("Locked out user should see error")
    void lockedOutUserShouldSeeError() {
        logger.info("Testing locked out user");

        saucePage.open()
                .login("locked_out_user", "secret_sauce");

        assertThat(saucePage.getErrorMessage())
                .as("Should see locked out error")
                .containsIgnoringCase("locked out");
    }

    @Test
    @Story("Login")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify invalid credentials show error")
    @DisplayName("Invalid credentials should show error")
    void invalidCredentialsShouldShowError() {
        logger.info("Testing invalid credentials");

        saucePage.open()
                .login("invalid_user", "wrong_password");

        assertThat(saucePage.getErrorMessage())
                .as("Should see error message")
                .isNotEmpty();
    }

    // ═══════════════════════════════════════════════════════════════════
    // INVENTORY TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Inventory")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify inventory displays 6 products")
    @DisplayName("Inventory should show 6 products")
    void inventoryShouldShowSixProducts() {
        saucePage.open()
                .loginAsStandardUser();

        assertThat(saucePage.getInventoryItemCount())
                .as("Should have 6 products")
                .isEqualTo(6);
    }

    @Test
    @Story("Inventory")
    @Severity(SeverityLevel.MINOR)
    @Description("Verify products can be sorted by name")
    @DisplayName("Products should be sortable by name")
    void productsShouldBeSortableByName() {
        saucePage.open()
                .loginAsStandardUser()
                .sortBy("za");

        List<String> names = saucePage.getInventoryItemNames();

        assertThat(names)
                .as("Products should be sorted Z-A")
                .isSortedAccordingTo((a, b) -> b.compareTo(a));
    }

    // ═══════════════════════════════════════════════════════════════════
    // CART TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify adding item updates cart badge")
    @DisplayName("Adding item should update cart badge")
    void addingItemShouldUpdateCartBadge() {
        saucePage.open()
                .loginAsStandardUser()
                .addFirstItemToCart();

        assertThat(saucePage.getCartBadgeCount())
                .as("Cart badge should show 1")
                .isEqualTo("1");
    }

    // ═══════════════════════════════════════════════════════════════════
    // CHECKOUT TESTS
    // ═══════════════════════════════════════════════════════════════════

    @Test
    @Story("Checkout")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify complete checkout flow")
    @DisplayName("Complete checkout should succeed")
    void completeCheckoutShouldSucceed() {
        saucePage.open()
                .loginAsStandardUser()
                .addFirstItemToCart()
                .openCart()
                .checkout()
                .fillCheckoutInfo("John", "Doe", "12345")
                .finishCheckout();

        assertThat(saucePage.isOrderComplete())
                .as("Order should be complete")
                .isTrue();

        assertThat(saucePage.getCompleteMessage())
                .as("Should see thank you message")
                .containsIgnoringCase("thank you");
    }
}

