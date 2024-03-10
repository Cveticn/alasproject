@api
Feature: adding, updating and removing users scenarios

  Background: all scenarios are starting with the same baseURI
    When setting baseURI

    Scenario: user is able to create new user
      When user sends post request
      Then verify that new user is create and status code is <201>
      Then verify that user can retrieve newly created user

@api2
      Scenario: user is able to update user details
        When user sends post request
        And user sends put request to update user's details
        Then verify that status code is <200> user details are updated correctly
@api3
  Scenario: user is able to delete user
    When user sends post request
    And user sends delete request to delete user
    Then verify that status code is <204> and that user cannot retrieve previously deleted user