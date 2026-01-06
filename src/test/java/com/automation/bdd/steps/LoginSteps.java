package com.automation.bdd.steps;

import com.automation.pages.SauceDemoPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions for Login feature.
 */
public class LoginSteps {

    private final SharedContext context;

    public LoginSteps(SharedContext context) {
        this.context = context;
    }

    private SauceDemoPage sauceDemoPage() {
        return context.getSauceDemoPage();
    }

    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        sauceDemoPage().open();
    }
    
    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        sauceDemoPage().enterUsername(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        sauceDemoPage().enterPassword(password);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        sauceDemoPage().clickLoginButton();
    }

    @Then("I should be on the inventory page")
    public void iShouldBeOnTheInventoryPage() {
        assertThat(sauceDemoPage().isOnInventoryPage())
            .as("Should be on inventory page after login")
            .isTrue();
    }

    @Then("I should see products displayed")
    public void iShouldSeeProductsDisplayed() {
        assertThat(sauceDemoPage().getInventoryItemCount())
            .as("Should see products on inventory page")
            .isGreaterThan(0);
    }

    @Then("I should see an error message containing {string}")
    public void iShouldSeeAnErrorMessageContaining(String expectedText) {
        String errorMessage = sauceDemoPage().getErrorMessage();
        assertThat(errorMessage)
            .as("Error message should contain expected text")
            .containsIgnoringCase(expectedText);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        String errorMessage = sauceDemoPage().getErrorMessage();
        assertThat(errorMessage)
            .as("Should see an error message")
            .isNotNull()
            .isNotEmpty();
    }
}

