@amazon
Feature: Amazon Search and Cart functionality test

 @web
  Scenario: Verify add to cart functionality with a Guest user
    Given Guest user is on amazon home screen
    When user searches for "iphone 13"
    And opens the product details page
    And Adds the product to cart
    Then User should see the product in the cart