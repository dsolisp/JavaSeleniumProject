package com.automation.bdd;

import com.automation.config.Settings;
import com.automation.pages.sauce.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Cucumber step definitions for SauceDemo Login feature.
 * Driver lifecycle is managed by {@link Before}/{@link After} hooks (no picocontainer
 * injection needed here — a single scenario-scoped thread-local suffices).
 */
public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage",
                "--window-size=1280,720");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ── Background ────────────────────────────────────────────────────────────

    @Given("I am on the login page")
    public void navigateToLoginPage() {
        loginPage.open();
    }

    // ── Actions ───────────────────────────────────────────────────────────────

    @When("I enter username {string}")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    // ── Assertions ────────────────────────────────────────────────────────────

    @Then("I should be on the inventory page")
    public void verifyInventoryPage() {
        assertThat(loginPage.isOnInventoryPage())
                .as("Expected to be on the inventory page after login")
                .isTrue();
    }

    @Then("I should see products displayed")
    public void verifyProductsDisplayed() {
        var items = driver.findElements(By.className("inventory_item"));
        assertThat(items).as("Expected at least one inventory item").isNotEmpty();
    }

    @Then("I should see an error message containing {string}")
    public void verifyErrorMessageContains(String expectedText) {
        String error = loginPage.getLoginErrorMessage();
        assertThat(error)
                .as("Expected error message to contain '%s'", expectedText)
                .contains(expectedText);
    }
}
