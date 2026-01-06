package com.automation.bdd.steps;

import com.automation.pages.sauce.CartPage;
import com.automation.pages.sauce.CheckoutPage;
import com.automation.pages.sauce.InventoryPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions for Checkout feature.
 */
public class CheckoutSteps {

    private final SharedContext context;

    public CheckoutSteps(SharedContext context) {
        this.context = context;
    }

    private InventoryPage inventoryPage() { return context.getInventoryPage(); }
    private CartPage cartPage() { return context.getCartPage(); }
    private CheckoutPage checkoutPage() { return context.getCheckoutPage(); }

    @Given("I have items in my cart")
    public void iHaveItemsInMyCart() {
        inventoryPage().addProductToCart("Sauce Labs Backpack");
        inventoryPage().addProductToCart("Sauce Labs Bike Light");
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        cartPage().startCheckout();
    }

    @When("I enter checkout information:")
    public void iEnterCheckoutInformation(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        Map<String, String> info = rows.getFirst();
        checkoutPage().enterInfo(
                info.get("firstName"),
                info.get("lastName"),
                info.get("postalCode")
        );
    }

    @When("I continue to overview")
    public void iContinueToOverview() {
        checkoutPage().continueToOverview();
    }

    @When("I finish the checkout")
    public void iFinishTheCheckout() {
        checkoutPage().finish();
    }

    @Then("I should see the order confirmation")
    public void iShouldSeeTheOrderConfirmation() {
        assertThat(checkoutPage().isOrderComplete())
                .as("Should see order confirmation")
                .isTrue();
    }

    @Then("the confirmation message should contain {string}")
    public void theConfirmationMessageShouldContain(String expectedText) {
        String message = checkoutPage().getCompleteMessage();
        assertThat(message)
                .as("Confirmation message should contain expected text")
                .containsIgnoringCase(expectedText);
    }

    @Then("I should see the order total")
    public void iShouldSeeTheOrderTotal() {
        String total = checkoutPage().getOrderTotal();
        assertThat(total)
                .as("Should see order total")
                .isNotNull()
                .contains("$");
    }

    @When("I click continue without entering information")
    public void iClickContinueWithoutEnteringInformation() {
        checkoutPage().continueToOverview();
    }

    @Then("I should see a checkout error message")
    public void iShouldSeeACheckoutErrorMessage() {
        String error = checkoutPage().getError();
        assertThat(error)
                .as("Should see checkout error message")
                .isNotNull()
                .isNotEmpty();
    }
}

