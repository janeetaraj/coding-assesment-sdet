Feature: Google Civic API Elections Query

  Scenario: Elections query call with valid api key
    Given A valid API Key
    When I call Elections Query
    Then I get a response status code 200
    And I get a valid list of elections

  Scenario: Elections query call with invalid api key
    Given An invalid API Key
    When I call Elections Query
    Then I get a response status code 400

  Scenario: Elections query call with invalid api key
    When I call Elections Query without api key
    Then I get a response status code 403
