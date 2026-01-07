
Feature: GitHub repository API validation

  @api @smoke
  Scenario: Repository exists
    Given the GitHub repository "cucumber/docs" exists

  @api @negative @smoke
  Scenario: Repository doesn't exist
    Given the GitHub repository "fakeRepo/fakeDocs" does not exist