
Feature: Login validation

  Scenario: Required field validation
    Given the user is on GitHub login page
    When the user clicks the Sign In button
    Then an error message should be displayed


  Scenario Outline: Invalid credentials show an authentication error

    Given the user is on GitHub login page
    When the user enters username "<email>" and password "<password>"
    And the user clicks the Sign In button
    Then an authentication error should be displayed

    Examples:
      | email        | password       |
      | invalidUser1 | invalidPass1   |
      | invalidUser2 | invalidPass2   |