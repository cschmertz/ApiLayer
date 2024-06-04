package apiEngine;

import dataProvider.ConfigReader;
import io.restassured.response.Response;
import org.netbeans.lib.cvsclient.response.ResponseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ResponseHandler<T> implements IRestResponse<T> {

    private T data;
    private Response response;
    private Exception e;

    private static final Logger LOGGER = Logger.getLogger(ConfigReader.class.getName());

    public ResponseHandler(Class<T> t, Response response) throws ResponseException {
        if (response == null) {
            throw new NullPointerException("Response object cannot be null");
        }
        this.response = response;
        try {
            if (t == null) {
                throw new NullPointerException("Class object cannot be null");
            }
            String contentType = response.getHeader("Content-Type");
            if (contentType != null && contentType.contains("application/json")) {
                ObjectMapper objectMapper = new ObjectMapper();
                this.data = objectMapper.readValue(response.getBody().asString(), t);
            } else {
                this.data = null;
            }
        } catch (Exception e) {
            throw new ResponseException(e, "Failed to create instance of class " + t.getName());
        }
    }

    @Override
    public T getBody() {
        return data;
    }

    @Override
    public String getContent() {
        return response.getBody().asString();
    }

    @Override
    public int getStatusCode() {
        return response.getStatusCode();
    }

    @Override
    public boolean isSuccessful() {
        int code = response.getStatusCode();
        return code >= 200 && code < 300;
    }

    @Override
    public String getStatusDescription() {
        return response.getStatusLine();
    }

    @Override
    public Response getResponse() {
        return response;
    }

    @Override
    public Exception getException() {
        return e;
    }

    @Override
    public Map<String, String> getHeaders() {
        return response.getHeaders().asList().stream().collect(Collectors.toMap(Header::getName, Header::getValue));
    }

}

