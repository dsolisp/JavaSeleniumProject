package com.automation.pages.sauce;

import com.automation.locators.SauceLocators;
import com.automation.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Checkout pages for SauceDemo.
 */
public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterInfo(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(SauceLocators.FIRST_NAME_INPUT));
        type(SauceLocators.FIRST_NAME_INPUT, firstName);
        type(SauceLocators.LAST_NAME_INPUT, lastName);
        type(SauceLocators.POSTAL_CODE_INPUT, postalCode);
        return this;
    }

    public CheckoutPage continueToOverview() {
        click(SauceLocators.CONTINUE_BUTTON);
        return this;
    }

    public CheckoutPage fillInfoAndContinue(String firstName, String lastName, String postalCode) {
        enterInfo(firstName, lastName, postalCode);
        return continueToOverview();
    }

    public CheckoutPage finish() {
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

    public String getOrderTotal() {
        try {
            WebElement total = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.className("summary_total_label")));
            return total.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public String getError() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("[data-test='error']")));
            return error.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

    public CartPage cancel() {
        click(SauceLocators.CANCEL_BUTTON);
        return new CartPage(driver);
    }
}

