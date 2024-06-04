Feature: User Login - Happy Path

	#User is able to Login to the application given the correct credentials - username and password.
	@TEST_EL-29 @Login
	Scenario: User Login - Happy Path
		When I provide valid login details
		Then I should receive a successful login response with a token
		
