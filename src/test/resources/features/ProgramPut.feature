@ProgramPut
Feature: Put program feature


  @Put_01
  Scenario: To update program by program id
    Given User is on PUT method with endpoint Program id
    When User sends request with valid inputs 
    Then User should receive proper status code and valid reponse
    And JSON schema of response should be valid

  @Put_02
  Scenario: To update program by program name
    Given User is on PUT method with endpoint Program name
    When User sends request with valid inputs 
    ThenUser should receive proper status code and valid reponse
    And JSON schema of response should be valid
    
  @Put_03
  Scenario: To update program with already available program name
    Given User is on PUT method with endpoint Program id
    When User sends request with duplicate program name 
    Then User should receive proper status code and message
    
  @Put_04
  Scenario: To update record with alphanumeric program name, description and status
    Given User is on PUT method with endpoint Program id
    When User sends inputs with alphanumeric values in program name, description and status
    Then User should receive proper status code and valid reponse
    And JSON schema of response should be valid
    
  @Put_05
  Scenario: To update record with invalid creation time and last mod time
    Given User is on PUT method with endpoint Program id
    When User sends request with invalid creation time and last mod time 
    Then User should receive proper status code and message
    
    
  
  