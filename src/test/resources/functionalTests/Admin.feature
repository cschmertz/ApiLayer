@Admin @Suite
Feature: Administrative features

	#User is able to Login to the application given the correct credentials - username and password.
  @CreateUser
  Scenario: Create user
    When I provide valid user details
    Then I should receive a successful response with the created user's information

     #User should be able to update User information.
  @UpdateUser
  Scenario: Update user
    When I provide valid user update details
    Then I should receive a successful response with the updated user's information

	#Deletes user
  @DeleteUser
  Scenario: Delete User
    When I delete a user by providing a user ID
    Then I should receive a successful response indicating the user has been deleted

    @ListUsers
  Scenario: Retrieve a list of users
    When I request the list of users
    Then I should receive a successful response with user details

