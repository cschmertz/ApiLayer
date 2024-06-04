package stepDefinitions.LoginSteps;

import apiEngine.EndPoints;
import apiEngine.IRestResponse;
import apiEngine.ResponseHandler;
import apiEngine.responses.LoginResponses.HappyPathResponse;
import cucumber.DependencyInjector;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.netbeans.lib.cvsclient.response.ResponseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HappyPathSteps {

    private TestContext testContext;
    private Response response;

    public HappyPathSteps() {
        this.testContext = DependencyInjector.getTestContext();
    }

    @When("I provide valid login details")
    public void iProvideValidLoginDetails() {
        EndPoints endPoints = testContext.getEndPoints();
        response = endPoints.login();
        testContext.getScenarioContext().setContext(Context.RESPONSE, response);
    }

    @Then("I should receive a successful login response with a token")
    public void i_should_receive_a_successful_login_response_with_a_token() throws ResponseException {
        Response response = testContext.getScenarioContext().getContext(Context.RESPONSE);
        IRestResponse<HappyPathResponse> loginResponse = new ResponseHandler<>(HappyPathResponse.class, response);
        assertEquals(200, loginResponse.getStatusCode());
        assertNotNull(loginResponse.getBody().getToken());
        testContext.getScenarioContext().setContext(Context.TOKEN, loginResponse.getBody().getToken());
    }
}
