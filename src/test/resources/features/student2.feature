Feature: Student functionality

  Scenario Outline: As a Person I want to subscribe as Student
    Given I enter my information like first name "<name>", last name "<lastname>", dni "<dni>", phone number "<phoneNumber>", gender "<gender>", birthdate "<birthdate>", address "<address>", description "<description>", hobbies "<hobbies>", smoker "<smoker>"
    When I select subscribe as Student
    Then I should be able to enter to the platform as Student

    Examples:
    |name |lastname|dni|phoneNumber|gender|birthdate|address|description|hobbies|smoker|
    |Renato |Arredondo|70874160|921879192|Male|2020-05-17|SJM|Funny|Read|1|