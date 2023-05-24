Feature: Validating place APIs

Scenario Outline: Verify if place is being added successfully using Add place API
Given Add place payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with "POST" http request
Then API call got success with status code 200
And "status" in response body is "OK" 
And "scope" in response body is "APP"

Examples: 
	| name 		 | language | address  |
	| John     | French   | London   |
	| peter    | English  | Paris    |
	| Harry    | Hindi    | India    |