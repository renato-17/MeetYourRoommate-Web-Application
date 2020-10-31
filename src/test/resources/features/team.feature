Feature: Student functionality

  Scenario Outline: A user gets the teams

    Given I sending team to be created with name "<name>"
    Then I should be able to see my team

    Examples:
    | name |
    | UPC |