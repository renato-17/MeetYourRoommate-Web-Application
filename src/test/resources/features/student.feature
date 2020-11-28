Feature: Get Students feature

  Scenario: As a Student I want to get all the other students who look for a roommate
    Given the following students
      |firstName | lastName  | dni      | phoneNumber | gender   | birthdate  | address    | description            | hobbies     | smoker |
      |Lucia     | Hernandez | 73847562 | 988726351   | femenino | 2001-03-29 | Chorrillos | Me gustan los deportes | jugar voley | false   |
      |Jennifer  | Garcia    | 71263745 | 918273652   | femenino | 1999-01-02 | San Isidro | Estudio medicina       | La guitarra | false   |
    #When the user requests all the students
    #Then all the students are returned
