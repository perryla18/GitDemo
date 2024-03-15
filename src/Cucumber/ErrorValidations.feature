@tag
Feature: Error validation
  I want to use this template for my feature file

  @Error
  Scenario Outline: Fail to log in
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | name              | password    |
      | anshika@gmail.com | Iamking@0 |