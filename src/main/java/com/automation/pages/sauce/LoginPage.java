package com.automation.pages.sauce;

import com.automation.locators.SauceLocators;
import com.automation.pages.BasePage;
import com.automation.utils.TestDataManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;

/**
 * Login page for SauceDemo.
 */
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        navigateTo(settings.getSauceDemoUrl());
        log.info("Opened SauceDemo login page");
        return this;
    }

    public InventoryPage login(String username, String password) {
        type(SauceLocators.USERNAME_INPUT, username);
        type(SauceLocators.PASSWORD_INPUT, password);
        click(SauceLocators.LOGIN_BUTTON);
        log.info("Logged in as: {}", username);
        waitForLoginResult();
        return new InventoryPage(driver);
    }

    public InventoryPage loginAsStandardUser() {
        TestDataManager dataManager = new TestDataManager();
        Map<String, String> creds = dataManager.getStandardUserCredentials();
        return login(creds.get("username"), creds.get("password"));
    }

    public void enterUsername(String username) {
        type(SauceLocators.USERNAME_INPUT, username);
    }

    public void enterPassword(String password) {
        type(SauceLocators.PASSWORD_INPUT, password);
    }

    public void clickLoginButton() {
        click(SauceLocators.LOGIN_BUTTON);
        waitForLoginResult();
    }

    /**
     * Wait for login to complete (either success or error).
     */
    private void waitForLoginResult() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(SauceLocators.INVENTORY_CONTAINER),
                ExpectedConditions.visibilityOfElementLocated(SauceLocators.LOGIN_ERROR)
        ));
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

    public boolean isOnInventoryPage() {
        try {
            return isElementVisible(SauceLocators.INVENTORY_CONTAINER);
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public LoginPage logout() {
        click(SauceLocators.MENU_BUTTON);
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
}

