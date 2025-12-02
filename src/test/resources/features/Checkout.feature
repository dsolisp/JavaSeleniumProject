@checkout @web
Feature: SauceDemo Checkout Process
  As a logged in user with items in cart
  I want to complete the checkout process
  So that I can purchase my items

  Background:
    Given I am logged in as a standard user
    And I have items in my cart

  @smoke
  Scenario: Complete checkout successfully
    When I go to the cart page
    And I proceed to checkout
    And I enter checkout information:
      | firstName | lastName | postalCode |
      | Test      | User     | 12345      |
    And I continue to overview
    And I finish the checkout
    Then I should see the order confirmation
    And the confirmation message should contain "Thank you"

  @regression
  Scenario: Checkout displays order total
    When I go to the cart page
    And I proceed to checkout
    And I enter checkout information:
      | firstName | lastName | postalCode |
      | Test      | User     | 12345      |
    And I continue to overview
    Then I should see the order total

  @negative
  Scenario: Checkout fails without required information
    When I go to the cart page
    And I proceed to checkout
    And I click continue without entering information
    Then I should see a checkout error message

