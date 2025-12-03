package com.automation.playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

/**
 * Playwright page object for SauceDemo e-commerce site.
 * Mirrors the Selenium SauceDemoPage implementation.
 */
public class PlaywrightSauceDemoPage extends PlaywrightBasePage {

    private static final String URL = "https://www.saucedemo.com";

    // Selectors (CSS)
    private static final String USERNAME_INPUT = "#user-name";
    private static final String PASSWORD_INPUT = "#password";
    private static final String LOGIN_BUTTON = "#login-button";
    private static final String ERROR_MESSAGE = "[data-test='error']";
    private static final String INVENTORY_ITEMS = ".inventory_item";
    private static final String INVENTORY_ITEM_NAME = ".inventory_item_name";
    private static final String CART_BADGE = ".shopping_cart_badge";
    private static final String CART_LINK = ".shopping_cart_link";
    private static final String CHECKOUT_BUTTON = "#checkout";
    private static final String FIRST_NAME_INPUT = "#first-name";
    private static final String LAST_NAME_INPUT = "#last-name";
    private static final String POSTAL_CODE_INPUT = "#postal-code";
    private static final String CONTINUE_BUTTON = "#continue";
    private static final String FINISH_BUTTON = "#finish";
    private static final String COMPLETE_HEADER = ".complete-header";
    private static final String MENU_BUTTON = "#react-burger-menu-btn";
    private static final String LOGOUT_LINK = "#logout_sidebar_link";
    private static final String SORT_DROPDOWN = "[data-test='product-sort-container']";

    public PlaywrightSauceDemoPage(Page page) {
        super(page);
    }

    // ═══════════════════════════════════════════════════════════════════
    // NAVIGATION & LOGIN
    // ═══════════════════════════════════════════════════════════════════

    public PlaywrightSauceDemoPage open() {
        navigateTo(URL);
        logger.info("Opened SauceDemo login page");
        return this;
    }

    public PlaywrightSauceDemoPage login(String username, String password) {
        logger.info("Logging in as: {}", username);
        fill(USERNAME_INPUT, username);
        fill(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
        return this;
    }

    public PlaywrightSauceDemoPage loginAsStandardUser() {
        return login("standard_user", "secret_sauce");
    }

    public boolean isOnInventoryPage() {
        return getCurrentUrl().contains("/inventory.html");
    }

    public String getErrorMessage() {
        if (isVisible(ERROR_MESSAGE)) {
            return getText(ERROR_MESSAGE);
        }
        return "";
    }

    // ═══════════════════════════════════════════════════════════════════
    // INVENTORY
    // ═══════════════════════════════════════════════════════════════════

    public int getInventoryItemCount() {
        return count(INVENTORY_ITEMS);
    }

    public List<String> getInventoryItemNames() {
        return getAll(INVENTORY_ITEM_NAME).stream()
                .map(Locator::textContent)
                .toList();
    }

    public PlaywrightSauceDemoPage addItemToCartByIndex(int index) {
        String selector = ".inventory_item:nth-child(%d) button".formatted(index + 1);
        click(selector);
        return this;
    }

    public PlaywrightSauceDemoPage addFirstItemToCart() {
        return addItemToCartByIndex(0);
    }

    public PlaywrightSauceDemoPage sortBy(String value) {
        selectOption(SORT_DROPDOWN, value);
        return this;
    }

    // ═══════════════════════════════════════════════════════════════════
    // CART & CHECKOUT
    // ═══════════════════════════════════════════════════════════════════

    public String getCartBadgeCount() {
        if (isVisible(CART_BADGE)) {
            return getText(CART_BADGE);
        }
        return "0";
    }

    public PlaywrightSauceDemoPage openCart() {
        click(CART_LINK);
        return this;
    }

    public PlaywrightSauceDemoPage checkout() {
        click(CHECKOUT_BUTTON);
        return this;
    }

    public PlaywrightSauceDemoPage fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        fill(FIRST_NAME_INPUT, firstName);
        fill(LAST_NAME_INPUT, lastName);
        fill(POSTAL_CODE_INPUT, postalCode);
        click(CONTINUE_BUTTON);
        return this;
    }

    public PlaywrightSauceDemoPage finishCheckout() {
        click(FINISH_BUTTON);
        return this;
    }

    public boolean isOrderComplete() {
        return isVisible(COMPLETE_HEADER);
    }

    public String getCompleteMessage() {
        return getText(COMPLETE_HEADER);
    }

    // ═══════════════════════════════════════════════════════════════════
    // LOGOUT
    // ═══════════════════════════════════════════════════════════════════

    public PlaywrightSauceDemoPage logout() {
        click(MENU_BUTTON);
        waitForVisible(LOGOUT_LINK);
        click(LOGOUT_LINK);
        return this;
    }

    public boolean isOnLoginPage() {
        return isVisible(LOGIN_BUTTON);
    }
}

