package apiEngine.requests.LoginRequests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnsuccessfulLoginRequest {

    @JsonProperty("wrongEmail")
    private String wrongEmail;

    // Default constructor is necessary for Jackson
    public UnsuccessfulLoginRequest() {}

    // Parameterized constructor for convenience
    public UnsuccessfulLoginRequest(String wrongEmail) {
        this.wrongEmail = wrongEmail;
    }

    // Getters and Setters
    public String getWrongEmailEmail() {
        return wrongEmail;
    }

    public void setWrongEmail(String email) {
        this.wrongEmail = wrongEmail;
    }

}
