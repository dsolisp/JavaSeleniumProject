package com.automation.bdd.steps;

import com.automation.pages.sauce.CartPage;
import com.automation.pages.sauce.CheckoutPage;
import com.automation.pages.sauce.InventoryPage;
import com.automation.pages.sauce.LoginPage;
import org.openqa.selenium.WebDriver;

/**
 * Shared test context for Cucumber step definitions.
 * Uses Cucumber's built-in dependency injection (PicoContainer).
 */
public class SharedContext {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.inventoryPage = new InventoryPage(driver);
        this.cartPage = new CartPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
    }

    public LoginPage getLoginPage() { return loginPage; }
    public InventoryPage getInventoryPage() { return inventoryPage; }
    public CartPage getCartPage() { return cartPage; }
    public CheckoutPage getCheckoutPage() { return checkoutPage; }

    public void cleanup() {
        if (driver != null) {
            driver.quit();
            driver = null;
            loginPage = null;
            inventoryPage = null;
            cartPage = null;
            checkoutPage = null;
        }
    }
}

