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
 * Step definitions for Login feature.
 */
public class LoginSteps {
    
    private WebDriver driver;
    private SauceDemoPage sauceDemoPage;
    
    @Before("@web")
    public void setUp() {
        driver = WebDriverFactory.createDriver("chrome", true);
        sauceDemoPage = new SauceDemoPage(driver);
    }
    
    @After("@web")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        sauceDemoPage.navigateTo("https://www.saucedemo.com");
    }
    
    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        sauceDemoPage.enterUsername(username);
    }
    
    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        sauceDemoPage.enterPassword(password);
    }
    
    @When("I click the login button")
    public void iClickTheLoginButton() {
        sauceDemoPage.clickLoginButton();
    }
    
    @Then("I should be on the inventory page")
    public void iShouldBeOnTheInventoryPage() {
        assertThat(sauceDemoPage.isOnInventoryPage())
            .as("Should be on inventory page after login")
            .isTrue();
    }
    
    @Then("I should see products displayed")
    public void iShouldSeeProductsDisplayed() {
        assertThat(sauceDemoPage.getInventoryItemCount())
            .as("Should see products on inventory page")
            .isGreaterThan(0);
    }
    
    @Then("I should see an error message containing {string}")
    public void iShouldSeeAnErrorMessageContaining(String expectedText) {
        String errorMessage = sauceDemoPage.getErrorMessage();
        assertThat(errorMessage)
            .as("Error message should contain expected text")
            .containsIgnoringCase(expectedText);
    }
    
    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        String errorMessage = sauceDemoPage.getErrorMessage();
        assertThat(errorMessage)
            .as("Should see an error message")
            .isNotNull()
            .isNotEmpty();
    }
}

