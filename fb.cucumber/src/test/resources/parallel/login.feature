#################################
#Author: Arun K Babu
#Date: 1/27/2021
#################################
@SmokeScenario
Feature: To validate facebook login

  Scenario: To validate facebook login fields
    Given The user is on facebook.com
    When title of the login page is facebook
    Then validate that username password and login button is displayed
    Then close the browser

  @SmokeTest
  Scenario Outline: To validate facebook login
    Given The user is on facebook.com
    When user enter username <user>
    And user enters password <pass>
    And user selects login button
    Then user homepage is displayed
    Then close the browser

    Examples: 
      | user                   | pass       |
      | arunbabu2@hotmail.com  | Darsh@1979 |
      | arunbabu23@hotmail.com | Darsh@1979 |
      | arunbabu78@hotmail.com | Darsh@1979 |
