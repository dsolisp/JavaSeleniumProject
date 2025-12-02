package com.automation.bdd.steps;

import com.automation.pages.SauceDemoPage;
import com.automation.utils.WebDriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions for Checkout feature.
 */
public class CheckoutSteps {
    
    private WebDriver driver;
    private SauceDemoPage sauceDemoPage;
    
    @Before("@checkout")
    public void setUp() {
        driver = WebDriverFactory.createDriver("chrome", true);
        sauceDemoPage = new SauceDemoPage(driver);
    }
    
    @After("@checkout")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Given("I have items in my cart")
    public void iHaveItemsInMyCart() {
        sauceDemoPage.addProductToCart("Sauce Labs Backpack");
        sauceDemoPage.addProductToCart("Sauce Labs Bike Light");
    }
    
    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        sauceDemoPage.clickCheckout();
    }
    
    @When("I enter checkout information:")
    public void iEnterCheckoutInformation(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        Map<String, String> info = rows.get(0);
        sauceDemoPage.fillCheckoutInfo(
            info.get("firstName"),
            info.get("lastName"),
            info.get("postalCode")
        );
    }
    
    @When("I continue to overview")
    public void iContinueToOverview() {
        sauceDemoPage.clickContinue();
    }
    
    @When("I finish the checkout")
    public void iFinishTheCheckout() {
        sauceDemoPage.clickFinish();
    }
    
    @Then("I should see the order confirmation")
    public void iShouldSeeTheOrderConfirmation() {
        assertThat(sauceDemoPage.isCheckoutComplete())
            .as("Should see order confirmation")
            .isTrue();
    }
    
    @Then("the confirmation message should contain {string}")
    public void theConfirmationMessageShouldContain(String expectedText) {
        String message = sauceDemoPage.getConfirmationMessage();
        assertThat(message)
            .as("Confirmation message should contain expected text")
            .containsIgnoringCase(expectedText);
    }
    
    @Then("I should see the order total")
    public void iShouldSeeTheOrderTotal() {
        String total = sauceDemoPage.getOrderTotal();
        assertThat(total)
            .as("Should see order total")
            .isNotNull()
            .contains("$");
    }
    
    @When("I click continue without entering information")
    public void iClickContinueWithoutEnteringInformation() {
        sauceDemoPage.clickContinue();
    }
    
    @Then("I should see a checkout error message")
    public void iShouldSeeACheckoutErrorMessage() {
        String error = sauceDemoPage.getCheckoutError();
        assertThat(error)
            .as("Should see checkout error message")
            .isNotNull()
            .isNotEmpty();
    }
}

