
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Negative Test Login validation
    Given I landed on Ecamerce page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  									| password 			|
      | sdhadavai.qa@gmail.com 	| JavaSelen4$  	| 
 	