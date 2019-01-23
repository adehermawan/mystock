Feature: Testing a REST API
  Users should be able to submit GET and POST requests to a web service

Scenario: Generate token
When users input username and password
Then users get response code of 200
And users receives generated token

Scenario: Users registration
When User signup with the following details
|Robet|robet|robet@google.com|rahasia|
Then The server should handle it and return a success status