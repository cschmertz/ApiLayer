package apiEngine;

import apiEngine.requests.AdminRequests.CreateUserRequest;
import apiEngine.requests.AdminRequests.UpdateUserRequest;
import apiEngine.requests.LoginRequests.HappyPathRequest;
import apiEngine.requests.LoginRequests.SadPathRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataProvider.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class EndPoints {

    private final RequestSpecification request;

    public EndPoints(String baseUrl) {
        this(baseUrl, null);
    }

    public EndPoints(String baseUrl, Map<String, String> additionalHeaders) {
        request = given()
                .baseUri(baseUrl)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        if (additionalHeaders != null) {
            additionalHeaders.forEach(request::header);
        }
    }

    public RequestSpecification getRequest() {
        return request;
    }

    public Response login() {
        HappyPathRequest happyPathRequest = new HappyPathRequest(
                ConfigReader.getInstance().getEmail(),
                ConfigReader.getInstance().getPassword()
        );
        
        return request
                .body(happyPathRequest)
                .post("/login");
    }

    public Response loginWithInvalidEmail() {
        SadPathRequest invalidLoginRequest = new SadPathRequest(ConfigReader.getInstance().getWrongEmail());
        
        return request
                .body(invalidLoginRequest)
                .post("/login");
    }

    public Response createUser() {
        CreateUserRequest createUserRequest = new CreateUserRequest(
                ConfigReader.getInstance().getName(),
                ConfigReader.getInstance().getJob()
        );

        return request
                .body(createUserRequest)
                .post("/users");
    }

    public Response updateUser(int userId, UpdateUserRequest updateUserRequest) {
        return request
                .body(updateUserRequest)
                .put("/users/" + userId);
    }

    public Response deleteUser(int userId) {
        return request
                .delete("/users/" + userId);
    }

    public Response getListOfUsers() {
        return request
                .get("/users");
    }
}

