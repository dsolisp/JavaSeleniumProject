package com.automation.pages.sauce;

import com.automation.locators.SauceLocators;
import com.automation.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Inventory/Products page for SauceDemo.
 */
public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        List<WebElement> items = findElements(SauceLocators.INVENTORY_ITEMS);
        return items.size();
    }

    public InventoryPage addItemToCart(int index) {
        List<WebElement> buttons = findElements(SauceLocators.ADD_TO_CART_BUTTONS);
        if (index < buttons.size()) {
            buttons.get(index).click();
            log.info("Added item {} to cart", index);
        }
        return this;
    }

    public InventoryPage addProductToCart(String productName) {
        String buttonId = productName.toLowerCase()
                .replace(" ", "-")
                .replace(".", "");
        String xpath = "//button[@data-test='add-to-cart-%s']".formatted(buttonId);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        button.click();
        log.info("Added {} to cart", productName);
        return this;
    }

    public InventoryPage addFirstThreeItems() {
        for (int i = 0; i < 3; i++) {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(SauceLocators.ADD_TO_CART_BUTTONS));
            button.click();
            log.info("Added item {} to cart", i + 1);
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(SauceLocators.REMOVE_BUTTONS, i));
        }
        return this;
    }

    public int getCartBadgeCount() {
        try {
            waitForVisible(SauceLocators.CART_BADGE);
            String badgeText = getText(SauceLocators.CART_BADGE);
            return Integer.parseInt(badgeText);
        } catch (NoSuchElementException | TimeoutException e) {
            return 0;
        } catch (NumberFormatException e) {
            log.warn("Could not parse cart badge text");
            return 0;
        }
    }

    public String getCartBadgeText() {
        try {
            waitForVisible(SauceLocators.CART_BADGE);
            return getText(SauceLocators.CART_BADGE);
        } catch (NoSuchElementException | TimeoutException e) {
            return "0";
        }
    }

    public CartPage openCart() {
        click(SauceLocators.CART_LINK);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SauceLocators.CHECKOUT_BUTTON));
        return new CartPage(driver);
    }

    public LoginPage logout() {
        return new LoginPage(driver).logout();
    }
}

