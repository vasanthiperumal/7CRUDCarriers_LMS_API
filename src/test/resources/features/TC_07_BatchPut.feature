
@tag
Feature: Put Batch feature
 
  @BatchPut_01
  Scenario: To update batch with valid Batch id
    Given User is on PUT Method with endpoint
    When User sends request with valid batch inputs
    Then User should receive status code and message for Put
 #	    
 #@BatchPut_02
  #Scenario: Update Batch details for a invalid Batch id
  #Given User is on PUT method with endpoint {{baseurl}}/batches/{batchId}
  #When User sends the GET request with invalid Batchid
  #Then User receives 400 Bad Request 
#
  #@BatchPut_03
  #Scenario: Update Batch details with Batch id as alphanumeric
  #Given User is on PUT method with endpoint {{baseurl}}/batches/{batchId}
  #When User sends the PUT request with alphanumeric Batchid
  #Then User receives 400 Bad Request
    #
  #@BatchPut_04
  #Scenario: Update Batch details with Batchid as blank
  #Given User is on PUT method with endpoint {{baseurl}}/batches/{batchId}
  #When User sends the request with blank Batchid
  #Then User receives 400 Bad Request
  #
  #@BatchPut_05
  #Scenario: Update Batch details with Batchid as Decimal
  #Given User is on PUT method with endpoint {{baseurl}}/batches/{batchId}
  #When User sends the PUT request with decimal Batchid
  #Then User receives 405 Method not allowed
 #
  #@BatchPut_06
  #Scenario: Update Batch details with Negative Batchid 
  #Given User is on PUT method with endpoint {{baseurl}}/batches/{batchId}
  #When User sends the PUT request with negative Batchid
  #Then User receives 400 Bad Request
  #
  #@BatchPut_07
  #Scenario: To update batch with valid Batch id and without JSON Schema
  #Given User is on PUT method with endpoint {{baseurl}}/batches/{batchId} and without valid Json Schema
  #When User sends the PUT request with valid batch id and without valid Json Schema
  #Then User receives User receives 415 Unsupported Media Type
  #//