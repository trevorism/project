Feature: Context Root of this API
  In order to use the API, it must be available

  Scenario: Root of the API HTTPS
    Given the application is alive
    When I navigate to "https://project.trevorism.com"
    Then then a link to the help page is displayed

