package stepDefinitions.AdminSteps;

import apiEngine.EndPoints;
import apiEngine.IRestResponse;
import apiEngine.ResponseHandler;
import apiEngine.responses.AdminResponses.ListUsersResponse;
import cucumber.DependencyInjector;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.netbeans.lib.cvsclient.response.ResponseException;
import io.restassured.response.Response;
import java.util.List;



public class GetListOfUsersSteps {

    private TestContext testContext;
    private Response response;

    public GetListOfUsersSteps() {
        this.testContext = DependencyInjector.getTestContext();
    }

    @When("I retrieve the list of users")
    public void iRetrieveTheListOfUsers() {
        EndPoints endPoints = testContext.getEndPoints();
        response = endPoints.getListOfUsers();
        testContext.getScenarioContext().setContext(Context.RESPONSE, response);
    }

    @Then("I should receive a successful response with user details")
    public void iShouldReceiveASuccessfulResponseWithUserDetails() throws ResponseException {
        Response response = testContext.getScenarioContext().getContext(Context.RESPONSE);
        IRestResponse<ListUsersResponse> restResponse = new ResponseHandler<>(ListUsersResponse.class, response);
        Assert.assertEquals(200, restResponse.getStatusCode());

        List<ListUsersResponse.UserData> usersList = restResponse.getBody().getData();
        testContext.getScenarioContext().setContext(Context.USERS_LIST, usersList);

        Assert.assertNotNull(usersList);
        Assert.assertTrue(usersList.size() > 0);
    }
}
