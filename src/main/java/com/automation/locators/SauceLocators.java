package com.automation.locators;

import org.openqa.selenium.By;

/**
 * Locators for SauceDemo application.
 * Equivalent to Python's locators/sauce_locators.py
 */
public final class SauceLocators {

    private SauceLocators() {
        // Utility class - prevent instantiation
    }

    // ═══════════════════════════════════════════════════════════════════
    // LOGIN PAGE LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Username input field.
     */
    public static final By USERNAME_INPUT = By.id("user-name");

    /**
     * Password input field.
     */
    public static final By PASSWORD_INPUT = By.id("password");

    /**
     * Login button.
     */
    public static final By LOGIN_BUTTON = By.id("login-button");

    /**
     * Login error message container.
     */
    public static final By LOGIN_ERROR = By.cssSelector("[data-test='error']");

    /**
     * Login error button (close).
     */
    public static final By LOGIN_ERROR_BUTTON = By.cssSelector(".error-button");

    // ═══════════════════════════════════════════════════════════════════
    // INVENTORY PAGE LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Inventory container.
     */
    public static final By INVENTORY_CONTAINER = By.id("inventory_container");

    /**
     * Inventory items.
     */
    public static final By INVENTORY_ITEMS = By.cssSelector(".inventory_item");

    /**
     * Item names.
     */
    public static final By ITEM_NAMES = By.cssSelector(".inventory_item_name");

    /**
     * Item prices.
     */
    public static final By ITEM_PRICES = By.cssSelector(".inventory_item_price");

    /**
     * Item descriptions.
     */
    public static final By ITEM_DESCRIPTIONS = By.cssSelector(".inventory_item_desc");

    /**
     * Add to cart buttons.
     */
    public static final By ADD_TO_CART_BUTTONS = By.cssSelector("button[data-test^='add-to-cart']");

    /**
     * Remove from cart buttons.
     */
    public static final By REMOVE_BUTTONS = By.cssSelector("button[data-test^='remove']");

    /**
     * Sort dropdown.
     */
    public static final By SORT_DROPDOWN = By.cssSelector("[data-test='product-sort-container']");

    // ═══════════════════════════════════════════════════════════════════
    // CART LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Shopping cart icon.
     */
    public static final By CART_ICON = By.id("shopping_cart_container");

    /**
     * Cart badge (item count).
     */
    public static final By CART_BADGE = By.cssSelector(".shopping_cart_badge");

    /**
     * Cart items.
     */
    public static final By CART_ITEMS = By.cssSelector(".cart_item");

    /**
     * Checkout button.
     */
    public static final By CHECKOUT_BUTTON = By.id("checkout");

    /**
     * Continue shopping button.
     */
    public static final By CONTINUE_SHOPPING_BUTTON = By.id("continue-shopping");

    // ═══════════════════════════════════════════════════════════════════
    // CHECKOUT LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * First name input.
     */
    public static final By FIRST_NAME_INPUT = By.id("first-name");

    /**
     * Last name input.
     */
    public static final By LAST_NAME_INPUT = By.id("last-name");

    /**
     * Postal code input.
     */
    public static final By POSTAL_CODE_INPUT = By.id("postal-code");

    /**
     * Continue button on checkout.
     */
    public static final By CONTINUE_BUTTON = By.id("continue");

    /**
     * Finish checkout button.
     */
    public static final By FINISH_BUTTON = By.id("finish");

    /**
     * Cancel button.
     */
    public static final By CANCEL_BUTTON = By.id("cancel");

    // ═══════════════════════════════════════════════════════════════════
    // HEADER & MENU LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Menu button (hamburger).
     */
    public static final By MENU_BUTTON = By.id("react-burger-menu-btn");

    /**
     * Logout link in menu.
     */
    public static final By LOGOUT_LINK = By.id("logout_sidebar_link");

    /**
     * Menu wrap container (for animation detection).
     */
    public static final By MENU_WRAP = By.className("bm-menu-wrap");

    /**
     * App logo.
     */
    public static final By APP_LOGO = By.cssSelector(".app_logo");

    // ═══════════════════════════════════════════════════════════════════
    // ORDER COMPLETE LOCATORS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Complete header on order confirmation.
     */
    public static final By COMPLETE_HEADER = By.className("complete-header");

    /**
     * Shopping cart link.
     */
    public static final By CART_LINK = By.className("shopping_cart_link");

    /**
     * Cart item name in cart page.
     */
    public static final By CART_ITEM_NAME = By.cssSelector(".inventory_item_name");

    /**
     * Order summary total.
     */
    public static final By ORDER_TOTAL = By.className("summary_total_label");

    /**
     * Checkout error message.
     */
    public static final By CHECKOUT_ERROR = By.cssSelector("[data-test='error']");

    // ═══════════════════════════════════════════════════════════════════
    // DYNAMIC LOCATOR BUILDERS
    // ═══════════════════════════════════════════════════════════════════

    /**
     * Build add-to-cart button locator for a product by name.
     */
    public static By addToCartButton(String productName) {
        String buttonId = productName.toLowerCase()
                .replace(" ", "-")
                .replace(".", "");
        return By.xpath("//button[@data-test='add-to-cart-%s']".formatted(buttonId));
    }

    /**
     * Build remove button locator for a product by name.
     */
    public static By removeButton(String productName) {
        String buttonId = productName.toLowerCase()
                .replace(" ", "-")
                .replace(".", "");
        return By.xpath("//button[@data-test='remove-%s']".formatted(buttonId));
    }
}

