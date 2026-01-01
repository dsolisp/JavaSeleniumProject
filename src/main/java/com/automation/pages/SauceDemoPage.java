package com.automation.pages;

import com.automation.locators.SauceLocators;
import com.automation.utils.TestDataManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Map;

/**
 * Page object for SauceDemo e-commerce test site.
 */
public class SauceDemoPage extends BasePage {

    // Data manager for loading credentials from JSON
    private static final TestDataManager dataManager = new TestDataManager();

    // Cached credentials loaded from test_data.json
    private static final Map<String, String> STANDARD_USER_CREDS = dataManager.getStandardUserCredentials();
    private static final Map<String, String> LOCKED_OUT_USER_CREDS = dataManager.getLockedOutUserCredentials();

    // Legacy constants for backward compatibility (deprecated - use DataManager methods instead)
    @Deprecated
    public static final String STANDARD_USER = STANDARD_USER_CREDS.getOrDefault("username", "standard_user");
    @Deprecated
    public static final String LOCKED_OUT_USER = LOCKED_OUT_USER_CREDS.getOrDefault("username", "locked_out_user");
    @Deprecated
    public static final String PASSWORD = STANDARD_USER_CREDS.getOrDefault("password", "secret_sauce");

    public SauceDemoPage(WebDriver driver) {
        super(driver);
    }

    public SauceDemoPage open() {
        navigateTo(settings.getSauceDemoUrl());
        log.info("Opened SauceDemo page");
        return this;
    }

    // ═══════════════════════════════════════════════════════════════════
    // LOGIN METHODS
    // ═══════════════════════════════════════════════════════════════════

    public SauceDemoPage login(String username, String password) {
        type(SauceLocators.USERNAME_INPUT, username);
        type(SauceLocators.PASSWORD_INPUT, password);
        click(SauceLocators.LOGIN_BUTTON);
        log.info("Logged in as: {}", username);
        // Wait for page transition - use explicit wait instead of Thread.sleep
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(SauceLocators.INVENTORY_CONTAINER),
                ExpectedConditions.visibilityOfElementLocated(SauceLocators.LOGIN_ERROR)
        ));
        return this;
    }

    public SauceDemoPage loginAsStandardUser() {
        return login(STANDARD_USER, PASSWORD);
    }

    public boolean isLoggedIn() {
        try {
            return isElementVisible(SauceLocators.INVENTORY_CONTAINER);
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public boolean hasLoginError() {
        return isElementVisible(SauceLocators.LOGIN_ERROR);
    }

    public String getLoginErrorMessage() {
        if (hasLoginError()) {
            return getText(SauceLocators.LOGIN_ERROR);
        }
        return "";
    }

    // ═══════════════════════════════════════════════════════════════════
    // INVENTORY METHODS
    // ═══════════════════════════════════════════════════════════════════

    public int getInventoryItemCount() {
        List<WebElement> items = findElements(SauceLocators.INVENTORY_ITEMS);
        return items.size();
    }

    public SauceDemoPage addItemToCart(int index) {
        List<WebElement> buttons = findElements(SauceLocators.ADD_TO_CART_BUTTONS);
        if (index < buttons.size()) {
            WebElement button = buttons.get(index);
            button.click();
            log.info("Added item {} to cart", index);
        }
        return this;
    }

    public SauceDemoPage addFirstThreeItemsToCart() {
        for (int i = 0; i < 3; i++) {
            // Wait for at least one add-to-cart button to be clickable
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(SauceLocators.ADD_TO_CART_BUTTONS));
            button.click();
            log.info("Added item {} to cart", i + 1);
            // Wait for button state change (Add -> Remove) instead of Thread.sleep
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(SauceLocators.REMOVE_BUTTONS, i));
        }
        return this;
    }

    public int getCartItemCount() {
        try {
            // Wait for badge to be visible before reading
            waitForVisible(SauceLocators.CART_BADGE);
            String badgeText = getText(SauceLocators.CART_BADGE);
            return Integer.parseInt(badgeText);
        } catch (NoSuchElementException | TimeoutException e) {
            // Cart is empty - badge not displayed
            log.debug("Cart badge not present - cart is empty");
            return 0;
        } catch (NumberFormatException e) {
            log.warn("Could not parse cart badge text");
            return 0;
        }
    }

    public SauceDemoPage openCart() {
        click(SauceLocators.CART_LINK);
        return this;
    }

    // ═══════════════════════════════════════════════════════════════════
    // CHECKOUT METHODS
    // ═══════════════════════════════════════════════════════════════════

    public SauceDemoPage startCheckout() {
        click(SauceLocators.CHECKOUT_BUTTON);
        return this;
    }

    /**
     * Fill checkout info and continue to overview (fluent API for regular tests).
     */
    public SauceDemoPage fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterCheckoutInfo(firstName, lastName, postalCode);
        click(SauceLocators.CONTINUE_BUTTON);
        return this;
    }

    /**
     * Enter checkout info without clicking continue (for BDD step-by-step).
     */
    public void enterCheckoutInfo(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SauceLocators.FIRST_NAME_INPUT));
        type(SauceLocators.FIRST_NAME_INPUT, firstName);
        type(SauceLocators.LAST_NAME_INPUT, lastName);
        type(SauceLocators.POSTAL_CODE_INPUT, postalCode);
    }

    public SauceDemoPage continueCheckout() {
        click(SauceLocators.CONTINUE_BUTTON);
        return this;
    }

    public SauceDemoPage finishCheckout() {
        click(SauceLocators.FINISH_BUTTON);
        return this;
    }

    public boolean isOrderComplete() {
        return isElementVisible(SauceLocators.COMPLETE_HEADER);
    }

    public String getCompleteMessage() {
        if (isOrderComplete()) {
            return getText(SauceLocators.COMPLETE_HEADER);
        }
        return "";
    }

    // ═══════════════════════════════════════════════════════════════════
    // LOGOUT
    // ═══════════════════════════════════════════════════════════════════

    public SauceDemoPage logout() {
        click(SauceLocators.MENU_BUTTON);
        // Wait for menu wrap to become visible (menu animation)
        wait.until(driver -> {
            WebElement menuWrap = driver.findElement(SauceLocators.MENU_WRAP);
            String hidden = menuWrap.getDomAttribute("aria-hidden");
            return "false".equals(hidden);
        });
        waitForClickable(SauceLocators.LOGOUT_LINK);
        click(SauceLocators.LOGOUT_LINK);
        log.info("Logged out");
        return this;
    }

    // ═══════════════════════════════════════════════════════════════════
    // BDD SUPPORT METHODS
    // ═══════════════════════════════════════════════════════════════════

    public void enterUsername(String username) {
        type(SauceLocators.USERNAME_INPUT, username);
    }

    public void enterPassword(String password) {
        type(SauceLocators.PASSWORD_INPUT, password);
    }

    public void clickLoginButton() {
        click(SauceLocators.LOGIN_BUTTON);
        // Wait for page transition
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(SauceLocators.INVENTORY_CONTAINER),
                ExpectedConditions.visibilityOfElementLocated(SauceLocators.LOGIN_ERROR)
        ));
    }

    public boolean isOnInventoryPage() {
        return isLoggedIn();
    }

    public String getErrorMessage() {
        return getLoginErrorMessage();
    }

    public void addProductToCart(String productName) {
        String buttonId = productName.toLowerCase()
                .replace(" ", "-")
                .replace(".", "");
        String xpath = "//button[@data-test='add-to-cart-%s']".formatted(buttonId);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                org.openqa.selenium.By.xpath(xpath)));
        button.click();
        log.info("Added {} to cart", productName);
    }

    public String getCartBadgeCount() {
        try {
            waitForVisible(SauceLocators.CART_BADGE);
            return getText(SauceLocators.CART_BADGE);
        } catch (NoSuchElementException | TimeoutException e) {
            return "0";
        }
    }

    public void goToCart() {
        openCart();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SauceLocators.CHECKOUT_BUTTON));
    }

    public void removeProductFromCart(String productName) {
        String buttonId = productName.toLowerCase()
                .replace(" ", "-")
                .replace(".", "");
        String xpath = "//button[@data-test='remove-%s']".formatted(buttonId);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                org.openqa.selenium.By.xpath(xpath)));
        button.click();
        log.info("Removed {} from cart", productName);
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> items = findElements(SauceLocators.CART_ITEM_NAME);
        return items.stream().anyMatch(item -> item.getText().equals(productName));
    }

    public void clickCheckout() {
        startCheckout();
    }

    public void clickContinue() {
        click(SauceLocators.CONTINUE_BUTTON);
    }

    public void clickFinish() {
        finishCheckout();
    }

    public boolean isCheckoutComplete() {
        return isOrderComplete();
    }

    public String getConfirmationMessage() {
        return getCompleteMessage();
    }

    public String getOrderTotal() {
        try {
            WebElement total = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    org.openqa.selenium.By.className("summary_total_label")));
            return total.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public String getCheckoutError() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    org.openqa.selenium.By.cssSelector("[data-test='error']")));
            return error.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }
}

