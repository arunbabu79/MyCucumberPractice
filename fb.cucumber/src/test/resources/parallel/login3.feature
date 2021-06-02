#################################
#Author: Arun K Babu
#Date: 1/27/2021
#################################

Feature: To validate facebook login3

  Scenario: To validate facebook login fields3
    Given The user is on facebook.com
    When title of the login page is facebook
    Then validate that username password and login button is displayed
    Then close the browser


  Scenario Outline: validate facebook login3
    Given The user is on facebook.com
    When user enter username <user>
    And user enters password <pass>
    And user selects login button
    Then user homepage is displayed
    Then close the browser

    Examples: 
      | user                   | pass       |
      | arunbabu2@hotmail.com  | Darsh@1979 |
     
     
     
      