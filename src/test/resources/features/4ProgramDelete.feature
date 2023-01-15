@ProgramDelete
Feature: Delete Program Feature
  I want to use this template for my feature file

Scenario: To delete existing program by id
Given User is on DELETE method with endpoint
When User sends request with existing programId  as input
Then user receive status code and message for delete

Scenario: To delete existing program by name
Given User is on DELETE method with endpoint by progname
When User sends request with existing programName  as input
Then user receive status code for existing prog name

Scenario: To delete non-existing program by id
Given User is on DELETE method with endpoint
When User sends request with nonexisting programId  as input
Then user receive status code and message for delete

Scenario: To delete non-existing program by name
Given User is on DELETE method with endpoint by progname
When User sends request with nonexisting programName  as input
Then user receive status code for existing prog name

Scenario: To delete the program without passing the program id or name
Given User is on DELETE method with endpoint
When User sends request without passing programId or programName
Then user receive status code for blank value
  