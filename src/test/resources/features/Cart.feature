@cart @web
Feature: SauceDemo Shopping Cart
  As a logged in user
  I want to manage items in my shopping cart
  So that I can purchase products

  Background:
    Given I am logged in as a standard user

  @smoke
  Scenario: Add single item to cart
    When I add "Sauce Labs Backpack" to the cart
    Then the cart badge should show "1"

  @regression
  Scenario: Add multiple items to cart
    When I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bike Light" to the cart
    And I add "Sauce Labs Bolt T-Shirt" to the cart
    Then the cart badge should show "3"

  @regression
  Scenario: Remove item from cart
    Given I have added "Sauce Labs Backpack" to the cart
    When I go to the cart page
    And I remove "Sauce Labs Backpack" from the cart
    Then the cart should be empty

  @regression
  Scenario: View cart contents
    Given I have added "Sauce Labs Backpack" to the cart
    And I have added "Sauce Labs Bike Light" to the cart
    When I go to the cart page
    Then I should see 2 items in the cart
    And I should see "Sauce Labs Backpack" in the cart
    And I should see "Sauce Labs Bike Light" in the cart

