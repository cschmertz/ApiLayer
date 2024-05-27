package apiEngine;

import io.restassured.response.Response;

import java.util.Map;

public interface IRestResponse<T> {
    public T getBody();

    public String getContent();

    public int getStatusCode();

    public boolean isSuccessful();

    public String getStatusDescription();

    public Response getResponse();

    public Exception getException();

    public Map<String, String> getHeaders();

}
