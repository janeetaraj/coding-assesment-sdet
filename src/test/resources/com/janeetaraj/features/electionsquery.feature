@electionsQuery
Feature: Google Civic API Elections Query

  Scenario Outline: Elections query call when <scenario>
    Given API Key is <apiKey>
    When I call Elections Query
    Then I get a response status code <statusCode>

    Examples:
      | scenario             | apiKey       | statusCode |
      | api key is valid     | "valid"      | 200        |
      | api Key is invalid   | "invalid"    | 400        |
      | api Key is null      | "null"       | 400        |
      | api Key is empty     | "empty"      | 400        |
      | no api Key is passed | "not passed" | 403        |


    #In this test case I am assuming the list of elections coming back in response should atleast have 1 element
  Scenario: Elections query call with valid API key and valid elections list in response
    Given API Key is "valid"
    When I call Elections Query
    Then I get a response status code 200
    And I get a valid list of elections
