package com.automation.pages.sauce;

import com.automation.locators.SauceLocators;
import com.automation.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Cart page for SauceDemo.
 */
public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> items = findElements(SauceLocators.CART_ITEM_NAME);
        return items.stream().anyMatch(item -> item.getText().equals(productName));
    }

    public CartPage removeProduct(String productName) {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                SauceLocators.removeButton(productName)));
        button.click();
        log.info("Removed {} from cart", productName);
        return this;
    }

    public CartPage removeItemByIndex(int index) {
        List<WebElement> buttons = findElements(SauceLocators.REMOVE_BUTTONS);
        if (index < buttons.size()) {
            buttons.get(index).click();
        }
        return this;
    }

    public int getItemCount() {
        // Direct driver.findElements (no wait) — cart can legitimately be empty.
        return driver.findElements(SauceLocators.CART_ITEMS).size(); // gavel-ignore: selector-leak
    }

    public CheckoutPage startCheckout() {
        click(SauceLocators.CHECKOUT_BUTTON);
        return new CheckoutPage(driver);
    }

    public InventoryPage continueShopping() {
        click(SauceLocators.CONTINUE_SHOPPING_BUTTON);
        return new InventoryPage(driver);
    }
}

