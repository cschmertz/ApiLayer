Feature: Create a user

	#User is able to Login to the application given the correct credentials - username and password.
  @TEST_EL-30
  Scenario: Create user
    When I provide valid user details,
    Then I should receive a successful response with the created user's information.