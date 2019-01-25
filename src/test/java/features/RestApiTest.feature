Feature: Testing a REST API
Users should be able to submit GET and POST requests to a web service

Scenario: Users registration
When User signup with the following details
|Ade|ade|adet@google.com|rahasia|
Then The server should handle it and return a success status

Scenario: Generate token
When users input username "ade" and password "rahasia"
Then users get response code of 200
And users receives generated token

Scenario: Data retrieval from a web service
When users want to get profile information by username "ade"
Then the requested data is returned