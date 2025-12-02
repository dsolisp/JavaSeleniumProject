@login @web
Feature: SauceDemo Login Functionality
  As a user of SauceDemo
  I want to be able to login with valid credentials
  So that I can access the inventory page

  Background:
    Given I am on the SauceDemo login page

  @smoke @positive
  Scenario: Successful login with standard user
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should be on the inventory page
    And I should see products displayed

  @negative
  Scenario: Login fails with locked out user
    When I enter username "locked_out_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should see an error message containing "locked out"

  @negative
  Scenario: Login fails with invalid credentials
    When I enter username "invalid_user"
    And I enter password "wrong_password"
    And I click the login button
    Then I should see an error message

  @smoke @positive
  Scenario Outline: Login with different valid users
    When I enter username "<username>"
    And I enter password "<password>"
    And I click the login button
    Then I should be on the inventory page

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |

