package apiEngine;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class EndPoints {

    private final RequestSpecification request;

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ACCEPT = "Accept";
    private static final String APPLICATION_JSON = "application/json";

    public EndPoints(String baseUrl) {
        this(baseUrl, null);
    }

    public EndPoints(String baseUrl, Map<String, String> additionalHeaders) {
        RestAssured.baseURI = baseUrl;
        request = RestAssured.given();
        request.header(CONTENT_TYPE, APPLICATION_JSON);
        request.header(ACCEPT, APPLICATION_JSON);

        if (additionalHeaders != null) {
            additionalHeaders.forEach(request::header);
        }
    }

    public RequestSpecification getRequest() {
        return request;
    }


}
