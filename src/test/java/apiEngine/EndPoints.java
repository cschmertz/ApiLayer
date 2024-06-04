package apiEngine;

import apiEngine.requests.LoginRequests.LoginRequest;
import dataProvider.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class EndPoints {

    private final RequestSpecification request;

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ACCEPT = "Accept";
    private static final String APPLICATION_JSON = "application/json";
    public static final String BASE_URL = ConfigReader.getInstance().getBaseUrl();

    public EndPoints(String baseUrl) {
        this(baseUrl, null);
    }

    public EndPoints(String baseUrl, Map<String, String> additionalHeaders) {
        RestAssured.baseURI = baseUrl;
        request = given();
        request.header(CONTENT_TYPE, APPLICATION_JSON);
        request.header(ACCEPT, APPLICATION_JSON);

        if (additionalHeaders != null) {
            additionalHeaders.forEach(request::header);
        }
    }

    public RequestSpecification getRequest() {
        return request;
    }


    public Response login() {
        LoginRequest loginRequest = new LoginRequest(ConfigReader.getInstance().getEmail(), ConfigReader.getInstance().getPassword());
        Response response = given()
                .contentType("application/json")
                .body(loginRequest)
                .post(BASE_URL + "/login");
        System.out.println("Response: " + response.asString());
        return response;
    }

    public Response loginWithInvalidEmail() {
        String invalidEmail = ConfigReader.getInstance().getWrongEmail();
        String invalidEmailRequest = "{\"email\":\"" + invalidEmail + "\"}";
        Response response = given()
                .contentType("application/json")
                .body(invalidEmailRequest)
                .post(BASE_URL + "/login");
        return response;
    }



}
