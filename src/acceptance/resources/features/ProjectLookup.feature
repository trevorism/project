Feature: Get project for service

  Scenario: Get all projects
    Given the application is alive
    When the list of google cloud projects is requested
    Then the list of projects is returned

  Scenario: Lookup project by service name
    Given the application is alive
    When the service name "auth-provider" is requested
    Then the correct project is returned