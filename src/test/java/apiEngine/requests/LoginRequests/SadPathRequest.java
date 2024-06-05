package apiEngine.requests.LoginRequests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SadPathRequest {


    private String email;

    // Default constructor is necessary for Jackson
    public SadPathRequest() {}

    // Parameterized constructor for convenience
    public SadPathRequest(String email) {
        this.email = email;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
