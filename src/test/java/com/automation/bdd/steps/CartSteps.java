package com.automation.bdd.steps;

import com.automation.pages.SauceDemoPage;
import com.automation.utils.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions for Cart feature.
 */
public class CartSteps {
    
    private WebDriver driver;
    private SauceDemoPage sauceDemoPage;
    
    @Before("@cart")
    public void setUp() {
        driver = WebDriverFactory.createDriver("chrome", true);
        sauceDemoPage = new SauceDemoPage(driver);
    }
    
    @After("@cart")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Given("I am logged in as a standard user")
    public void iAmLoggedInAsAStandardUser() {
        sauceDemoPage.navigateTo("https://www.saucedemo.com");
        sauceDemoPage.login("standard_user", "secret_sauce");
        assertThat(sauceDemoPage.isOnInventoryPage()).isTrue();
    }
    
    @When("I add {string} to the cart")
    public void iAddToTheCart(String productName) {
        sauceDemoPage.addProductToCart(productName);
    }
    
    @Given("I have added {string} to the cart")
    public void iHaveAddedToTheCart(String productName) {
        sauceDemoPage.addProductToCart(productName);
    }
    
    @Then("the cart badge should show {string}")
    public void theCartBadgeShouldShow(String expectedCount) {
        String actualCount = sauceDemoPage.getCartBadgeCount();
        assertThat(actualCount)
            .as("Cart badge should show correct count")
            .isEqualTo(expectedCount);
    }
    
    @When("I go to the cart page")
    public void iGoToTheCartPage() {
        sauceDemoPage.goToCart();
    }
    
    @When("I remove {string} from the cart")
    public void iRemoveFromTheCart(String productName) {
        sauceDemoPage.removeProductFromCart(productName);
    }
    
    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        assertThat(sauceDemoPage.getCartItemCount())
            .as("Cart should be empty")
            .isEqualTo(0);
    }
    
    @Then("I should see {int} items in the cart")
    public void iShouldSeeItemsInTheCart(int expectedCount) {
        assertThat(sauceDemoPage.getCartItemCount())
            .as("Cart should have expected number of items")
            .isEqualTo(expectedCount);
    }
    
    @Then("I should see {string} in the cart")
    public void iShouldSeeInTheCart(String productName) {
        assertThat(sauceDemoPage.isProductInCart(productName))
            .as("Product should be in cart: " + productName)
            .isTrue();
    }
}

