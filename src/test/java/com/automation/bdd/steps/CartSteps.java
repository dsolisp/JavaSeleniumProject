package com.automation.bdd.steps;

import com.automation.pages.sauce.CartPage;
import com.automation.pages.sauce.InventoryPage;
import com.automation.pages.sauce.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step definitions for Cart feature.
 */
public class CartSteps {

    private final SharedContext context;

    public CartSteps(SharedContext context) {
        this.context = context;
    }

    private LoginPage loginPage() { return context.getLoginPage(); }
    private InventoryPage inventoryPage() { return context.getInventoryPage(); }
    private CartPage cartPage() { return context.getCartPage(); }

    @Given("I am logged in as a standard user")
    public void iAmLoggedInAsAStandardUser() {
        loginPage().open().loginAsStandardUser();
        assertThat(loginPage().isOnInventoryPage()).isTrue();
    }

    @When("I add {string} to the cart")
    public void iAddToTheCart(String productName) {
        inventoryPage().addProductToCart(productName);
    }

    @Given("I have added {string} to the cart")
    public void iHaveAddedToTheCart(String productName) {
        inventoryPage().addProductToCart(productName);
    }

    @Then("the cart badge should show {string}")
    public void theCartBadgeShouldShow(String expectedCount) {
        String actualCount = inventoryPage().getCartBadgeText();
        assertThat(actualCount)
                .as("Cart badge should show correct count")
                .isEqualTo(expectedCount);
    }

    @When("I go to the cart page")
    public void iGoToTheCartPage() {
        inventoryPage().openCart();
    }

    @When("I remove {string} from the cart")
    public void iRemoveFromTheCart(String productName) {
        cartPage().removeProduct(productName);
    }

    @Then("the cart should be empty")
    public void theCartShouldBeEmpty() {
        assertThat(inventoryPage().getCartBadgeCount())
                .as("Cart should be empty")
                .isEqualTo(0);
    }

    @Then("I should see {int} items in the cart")
    public void iShouldSeeItemsInTheCart(int expectedCount) {
        assertThat(cartPage().getItemCount())
                .as("Cart should have expected number of items")
                .isEqualTo(expectedCount);
    }

    @Then("I should see {string} in the cart")
    public void iShouldSeeInTheCart(String productName) {
        assertThat(cartPage().isProductInCart(productName))
                .as("Product should be in cart: " + productName)
                .isTrue();
    }
}

