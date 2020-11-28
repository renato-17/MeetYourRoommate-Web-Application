Feature: Get Properties feature

  Scenario: As a lessor I want to get the properties
    Given the following properties
      |address           | description         | lessor_id |
      |Av. Larco 123     | Departamento para 2 | 1         |
      |Av. Barranco 452  | Mini departamento   | 1         |
    When the user requests all the properties
    Then all the properties are returned