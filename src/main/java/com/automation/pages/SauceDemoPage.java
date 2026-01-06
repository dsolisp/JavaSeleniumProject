package com.automation.pages;

import com.automation.pages.sauce.CartPage;
import com.automation.pages.sauce.CheckoutPage;
import com.automation.pages.sauce.InventoryPage;
import com.automation.pages.sauce.LoginPage;
import org.openqa.selenium.WebDriver;

/**
 * Facade for SauceDemo pages - maintains backward compatibility.
 * For new tests, prefer using individual page classes directly:
 * LoginPage, InventoryPage, CartPage, CheckoutPage
 */
public class SauceDemoPage extends BasePage {

    private final LoginPage loginPage;
    private final InventoryPage inventoryPage;
    private final CartPage cartPage;
    private final CheckoutPage checkoutPage;

    public SauceDemoPage(WebDriver driver) {
        super(driver);
        this.loginPage = new LoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
    }

    // ═══════════════════════════════════════════════════════════════════
    // NAVIGATION
    // ═══════════════════════════════════════════════════════════════════

    public SauceDemoPage open() {
        loginPage.open();
        return this;
    }

    // ═══════════════════════════════════════════════════════════════════
    // LOGIN (delegates to LoginPage)
    // ═══════════════════════════════════════════════════════════════════

    public SauceDemoPage login(String username, String password) {
        loginPage.login(username, password);
        return this;
    }

    public SauceDemoPage loginAsStandardUser() {
        loginPage.loginAsStandardUser();
        return this;
    }

    public void enterUsername(String username) { loginPage.enterUsername(username); }
    public void enterPassword(String password) { loginPage.enterPassword(password); }
    public void clickLoginButton() { loginPage.clickLoginButton(); }
    public boolean hasLoginError() { return loginPage.hasLoginError(); }
    public String getLoginErrorMessage() { return loginPage.getLoginErrorMessage(); }
    public String getErrorMessage() { return loginPage.getLoginErrorMessage(); }
    public boolean isLoggedIn() { return loginPage.isOnInventoryPage(); }
    public boolean isOnInventoryPage() { return loginPage.isOnInventoryPage(); }

    public SauceDemoPage logout() {
        loginPage.logout();
        return this;
    }

    // ═══════════════════════════════════════════════════════════════════
    // INVENTORY (delegates to InventoryPage)
    // ═══════════════════════════════════════════════════════════════════

    public int getInventoryItemCount() { return inventoryPage.getItemCount(); }

    public SauceDemoPage addItemToCart(int index) {
        inventoryPage.addItemToCart(index);
        return this;
    }

    public SauceDemoPage addFirstThreeItemsToCart() {
        inventoryPage.addFirstThreeItems();
        return this;
    }

    public void addProductToCart(String productName) {
        inventoryPage.addProductToCart(productName);
    }

    public int getCartItemCount() { return inventoryPage.getCartBadgeCount(); }
    public String getCartBadgeCount() { return inventoryPage.getCartBadgeText(); }

    public SauceDemoPage openCart() {
        inventoryPage.openCart();
        return this;
    }

    public void goToCart() { inventoryPage.openCart(); }

    // ═══════════════════════════════════════════════════════════════════
    // CART (delegates to CartPage)
    // ═══════════════════════════════════════════════════════════════════

    public boolean isProductInCart(String productName) {
        return cartPage.isProductInCart(productName);
    }

    public void removeProductFromCart(String productName) {
        cartPage.removeProduct(productName);
    }

    // ═══════════════════════════════════════════════════════════════════
    // CHECKOUT (delegates to CheckoutPage)
    // ═══════════════════════════════════════════════════════════════════

    public SauceDemoPage startCheckout() {
        cartPage.startCheckout();
        return this;
    }

    public void clickCheckout() { cartPage.startCheckout(); }

    public SauceDemoPage fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        checkoutPage.fillInfoAndContinue(firstName, lastName, postalCode);
        return this;
    }

    public void enterCheckoutInfo(String firstName, String lastName, String postalCode) {
        checkoutPage.enterInfo(firstName, lastName, postalCode);
    }

    public SauceDemoPage continueCheckout() {
        checkoutPage.continueToOverview();
        return this;
    }

    public void clickContinue() { checkoutPage.continueToOverview(); }

    public SauceDemoPage finishCheckout() {
        checkoutPage.finish();
        return this;
    }

    public void clickFinish() { checkoutPage.finish(); }

    public boolean isOrderComplete() { return checkoutPage.isOrderComplete(); }
    public boolean isCheckoutComplete() { return checkoutPage.isOrderComplete(); }
    public String getCompleteMessage() { return checkoutPage.getCompleteMessage(); }
    public String getConfirmationMessage() { return checkoutPage.getCompleteMessage(); }
    public String getOrderTotal() { return checkoutPage.getOrderTotal(); }
    public String getCheckoutError() { return checkoutPage.getError(); }
}

