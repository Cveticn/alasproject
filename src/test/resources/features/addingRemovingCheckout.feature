@wip
Feature: Adding, removing items from the shopping cart and checkout

  Background: for the scenarios in this feature file, user is expected to be logged in to the website
    When user is on the login page
    And user enters valid username
    And user enters valid password
    And user clicks on login button
    Then verify that user is successfully logged in

@wip2
Scenario: user is able to add items to the cart
  When user adds and item from the list to the cart
  Then verify that cart badge is updated correctly
  And user open another item's details page
  And user adds the item to the cart
  And user opens the cart
  Then verify that the correct items are present

  Scenario: user is able to remove item from the shopping cart
    When user adds and item from the list to the cart
    Then verify that cart badge is updated correctly
    And user open another item's details page
    And user adds the item to the cart
    And user opens the cart
    Then verify that the correct items are present
    And user removes first item from the shopping cart
    Then verify that 2nd added item is present in the shopping cart

    Scenario: user is able to complete the order
      When user adds and item from the list to the cart
      Then verify that cart badge is updated correctly
      And user open another item's details page
      And user adds the item to the cart
      And user opens the cart
      Then verify that the correct items are present
      And user removes first item from the shopping cart
      Then verify that 2nd added item is present in the shopping cart
      And user clicks checkout button
      And user enters first name
      And user enters last name
      And user enters postal code
      And user clicks on continue button
      And user clicks on finish button
      Then verify that order is completed successfully and message is displayed correctly

