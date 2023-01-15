@ProgramGet
Feature: Get Program Feature

  @ProgramGet_01
  Scenario: To check if the user able to read all the Programs
    Given User is on GET method with endpoint
    When User sends the request with valid input
    Then User is able to read all Program details and receive status code for get

  @ProgramGet_02
  Scenario: To Check if the user able to read a specific Program
    Given User is on GET method with end point with single programId
    When User sends the request with existing programId
    Then User should see the program details for the given programId

  @ProgramGet_03
  Scenario: To Check if the user gets error message for a invalid program id
    Given User is on GET method with end point with single programId
    When User sends the request with invalid programId
    Then User should receive error status code and message

  @ProgramGet_04
  Scenario: To check if able to read record with alphanumberic programId
    Given User is on GET method with end point with single programId
    When User sends request with alphanumeric programId
    Then User should receive error status code and message

  @ProgramGet_05
  Scenario: To check if able to read record with programId as null
    Given User is on GET method with end point with single programId
    When User sends request with a null programId
    Then User should receive error status code and message

  @ProgramGet_06
  Scenario: To check if able to read record with programId as decimal
    Given User is on GET method with end point with single programId
    When User sends request with a decimal programId
    Then User should receive error status code and message

  @ProgramGet_07
  Scenario: To check if able to read record with programId as blank
    Given User is on Get Method with end point with programId as blank
    When User sends request with a blank programId
    Then User should receive error status code for get
