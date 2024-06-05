package apiEngine.requests.LoginRequests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HappyPathRequest {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    // Default constructor is necessary for Jackson
    public HappyPathRequest() {}

    // Parameterized constructor for convenience
    public HappyPathRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
