package stepDefinitions.AdminSteps;

import apiEngine.EndPoints;
import apiEngine.IRestResponse;
import apiEngine.ResponseHandler;
import apiEngine.requests.AdminRequests.UpdateUserRequest;
import apiEngine.responses.AdminResponses.UpdateUserResponse;
import cucumber.DependencyInjector;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.netbeans.lib.cvsclient.response.ResponseException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UpdateUserSteps {

    private TestContext testContext;
    private Response response;

    public UpdateUserSteps() {
        this.testContext = DependencyInjector.getTestContext();
    }

    @When("I provide valid user update details")
    public void iProvideValidUserUpdateDetails() {
        EndPoints endPoints = testContext.getEndPoints();
        UpdateUserRequest updateUserRequest = new UpdateUserRequest("morpheus", "zion resident");
        response = endPoints.updateUser(2, updateUserRequest);
        testContext.getScenarioContext().setContext(Context.RESPONSE, response);
    }

    @Then("I should receive a successful response with the updated user's information")
    public void iShouldReceiveASuccessfulResponseWithTheUpdatedUsersInformation() throws ResponseException {
        Response response = testContext.getScenarioContext().getContext(Context.RESPONSE);
        IRestResponse<UpdateUserResponse> restResponse = new ResponseHandler<>(UpdateUserResponse.class, response);
        assertEquals(300, restResponse.getStatusCode());
        assertEquals("morpheus", restResponse.getBody().getName());
        assertEquals("zion resident", restResponse.getBody().getJob());
        assertNotNull(restResponse.getBody().getUpdatedAt());
    }

}
