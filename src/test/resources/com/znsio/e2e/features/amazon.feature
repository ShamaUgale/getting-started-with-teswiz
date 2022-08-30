@amazon
Feature: Amazon Search and Cart functionality test

 @web
  Scenario: Verify add to cart functionality with a Guest user
   Given user searches for "iPhone 13" product on amazon home page
   When user selects first product from search results
   And user adds product into cart
   Then added product should be visible into the cart