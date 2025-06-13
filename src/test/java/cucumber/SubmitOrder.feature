@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

   Background:
   Given I landed on the Ecommerce Page
   
  @Regression
  Scenario Outline: Positive Test of Submitting Order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                 | password  | productName     |
      | singamalaj@gmail.com | Jathin@10 | ZARA COAT 3     |
      | xyz123abc@gmail.com  | Abcd@1234 | ADIDAS ORIGINAL |
