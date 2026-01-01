
Feature: Search for various repos (repositories)

  Scenario Outline: Validate repositories that exist pop up and invalid repositories do not

    Given the user is on GitHub Home Page
    When the user searches for "<repo>"
    Then the correct "<repo>" will be in search results

    Examples:
      |       repo           |
      | cucumber/docs        |
      | TheAlgorithms/Java   |
      | TtroyM/Escaping-Orcs |