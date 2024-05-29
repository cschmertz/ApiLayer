package apiEngine;

import apiEngine.requests.LoginRequest;
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

    private String baseUrl;

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
                .post(baseUrl + "/login");
        System.out.println("Response: " + response.asString());
        return response;
    }


}
