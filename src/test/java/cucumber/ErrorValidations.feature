@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation 
  Scenario Outline: Negative Test of Submitting Order
    Given I landed on the Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name                    | password  | 
      | singamalaaaaj@gmail.com | Jathin@10 |
