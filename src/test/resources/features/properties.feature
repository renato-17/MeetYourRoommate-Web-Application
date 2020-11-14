Feature: Properties functionality

  Scenario Outline: As a Lessor I want to post my property
    Given I enter my property's information like address "<address>" and description "<description>"
    When I select the option post property
    Then I should be able to see my property posted

    Examples:
      |address | description |
      |SJM | A big house with 3 bedrooms |
      |VES | A beautiful house with a big yard |