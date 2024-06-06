package stepDefinitions.AdminSteps;

import apiEngine.EndPoints;
import cucumber.DependencyInjector;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static org.junit.Assert.assertEquals;

public class DeleteUserSteps {

    private TestContext testContext;
    private Response response;

    public DeleteUserSteps() {
        this.testContext = DependencyInjector.getTestContext();
    }

    @When("I delete a user by providing a user ID")
    public void iDeleteAUserByProvidingAUserID() {
        EndPoints endPoints = testContext.getEndPoints();
        response = endPoints.deleteUser(2);
        testContext.getScenarioContext().setContext(Context.RESPONSE, response);
    }

    @Then("I should receive a successful response indicating the user has been deleted")
    public void iShouldReceiveASuccessfulResponseIndicatingTheUserHasBeenDeleted() {
        Response response = testContext.getScenarioContext().getContext(Context.RESPONSE);
        assertEquals(204, response.getStatusCode());
    }
}
