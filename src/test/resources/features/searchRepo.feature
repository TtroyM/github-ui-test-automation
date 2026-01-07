
Feature: Search for various repos (repositories)

  @ui @regression @search
  Scenario Outline: Validate repositories that exist pop up and invalid repositories do not

    Given the user is on GitHub Home Page
    When the user searches for "<repo>"
    Then the correct "<repo>" will be in search results

    Examples:
      |       repo           |
      | cucumber/docs        |
      | TtroyM/Escaping-Orcs |