@ProgramPost
Feature: Post Program Feature

  @ProgramPost_01
  Scenario: Check if the user able to create new Program
    Given User is on Post Method with endpoint
    When User sends request with valid inputs
    #Then User should able to create new program with system generated program id
    Then User should receive status code and message for post

  @ProgramPost_02
  Scenario: Create new Program with duplicate record
    Given User is on Post Method with endpoint
    When User sends the request with already available program name
    Then User should receive status code and message for post

  @ProgramPost_03
  Scenario: To create user record with blank fields
    Given User is on Post Method with endpoint
    When User sends the request with blank fields
    Then User should receive status code and message for post
