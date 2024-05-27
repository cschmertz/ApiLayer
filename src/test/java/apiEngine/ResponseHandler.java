package apiEngine;

import dataProvider.ConfigReader;
import io.restassured.response.Response;
import org.netbeans.lib.cvsclient.response.ResponseException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            this.data = t.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new ResponseException(e, "Failed to create instance of class " + t.getName());
        }
    }

       @Override
       public T getBody() {
           try {
               data = (T) response.getBody().as(data.getClass());
           } catch (Exception e) {
               this.e = e;
               LOGGER.log(Level.SEVERE, "Error retrieving response body: " + e.getMessage(), e);
               throw new RuntimeException("Error retrieving response body", e);
           }
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
            return Arrays.asList(200, 201, 202, 203, 204, 205).contains(code);
        }

        @Override
        public String getStatusDescription () {
            return response.getStatusLine();
        }

        @Override
        public Response getResponse () {
            return response;
        }

        @Override
        public Exception getException () {
            return e;
        }

        @Override
        public Map<String, String> getHeaders() {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            headers.put("Accept", "application/json");
            LOGGER.log(Level.INFO, "Headers: " + headers);
            return headers;
        }
}

