Feature: Student Functionality

  Scenario: As a Student I want to get all the other students who look for a roommate
    Given the following students
      |firstName | lastName  | dni      | phoneNumber | gender   | birthdate  | address    | description            | hobbies     | bSmoker |
      |Lucia     | Hernandez | 73847562 | 988726351   | femenino | 2001-03-29 | Chorrillos | Me gustan los deportes | jugar voley | false   |
      |Jennifer  | Garcia    | 71263745 | 918273652   | femenino | 1999-01-02 | San Isidro | Estudio medicina       | La guitarra | false   |
    When the user requests all the students
    Then all the students are returned

  Scenario Outline: As a Person I want to subscribe as Student
    Given I enter my information like first name "<name>", last name "<lastname>", dni "<dni>", phone number "<phoneNumber>", gender "<gender>", birthdate "<birthdate>", address "<address>", description "<description>", hobbies "<hobbies>", smoker "<bSmoker>"
    When I select subscribe as Student
    Then I should be able to enter to the platform as Student

    Examples:
      |name |lastname|dni|phoneNumber|gender|birthdate|address|description|hobbies|bSmoker|
      |Renato |Arredondo|70874160|921879192|Male|2020-05-17|SJM|Funny|Read|1|