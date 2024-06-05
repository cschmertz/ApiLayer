@Login
Feature: User Login

	#User is able to Login to the application given the correct credentials - username and password.
	@HappyPath
	Scenario: User Login - Happy Path
		When I provide valid login details
		Then I should receive a successful login response with a token

   #User should not be able to log in when providing incorrect credentials.
	@SadPath
	Scenario: User Login - Sad Path
		When I provide invalid login details
		Then I should receive an unsuccessful response
		
