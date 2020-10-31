Feature: Team functionality

  Scenario Outline: A Student want to Join a Team

    Given I send a team name "<name>"
    When I accept and select create team
    #Then I should be able to see my team

    Examples:
    | name |
    | UPC |