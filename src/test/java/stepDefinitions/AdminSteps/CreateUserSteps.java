package stepDefinitions.AdminSteps;

import apiEngine.EndPoints;
import apiEngine.IRestResponse;
import apiEngine.ResponseHandler;
import apiEngine.responses.AdminResponses.CreateUserResponse;
import apiEngine.responses.LoginResponses.SadPathResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.DependencyInjector;
import cucumber.TestContext;
import enums.Context;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.netbeans.lib.cvsclient.response.ResponseException;

import static org.junit.Assert.assertEquals;

public class CreateUserSteps {

    private TestContext testContext;
    private Response response;

    public CreateUserSteps() {
        this.testContext = DependencyInjector.getTestContext();
    }

    @When("I provide valid user details")
    public void iProvideValidUserDetails(){
        EndPoints endPoints = testContext.getEndPoints();
        response = endPoints.createUser();
        testContext.getScenarioContext().setContext(Context.RESPONSE, response);
    }

    @Then("I should receive a successful response with the created user's information")
    public void iShouldReceiveASuccessfulResponseWithTheCreatedUserSInformation() throws ResponseException {
        Response response = testContext.getScenarioContext().getContext(Context.RESPONSE);
        IRestResponse<CreateUserResponse> restResponse = new ResponseHandler<>(CreateUserResponse.class, response);
        assertEquals(201, restResponse.getStatusCode());
        Assert.assertNotNull(restResponse.getBody().getId());
        Assert.assertNotNull(restResponse.getBody().getCreatedAt());
        Assert.assertEquals("morpheus", restResponse.getBody().getName());
        Assert.assertEquals("leader", restResponse.getBody().getJob());
        System.out.println("Response Body: " + response.getBody().asString());
    }

}
