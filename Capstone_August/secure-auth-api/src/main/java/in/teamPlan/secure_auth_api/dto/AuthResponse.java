package in.teamPlan.secure_auth_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class AuthResponse {

    @JsonProperty("username")
    private String username;

    @JsonProperty("valid")
    private boolean valid;

    @JsonProperty("authenticated")
    private boolean authenticated;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @JsonProperty("message")
    private String message;

    // Default constructor
    public AuthResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // Constructor with username and valid status
    public AuthResponse(String username, boolean valid) {
        this();
        this.username = username;
        this.valid = valid;
        this.authenticated = valid;
        this.message = valid ? "Token is valid" : "Token is invalid";
    }

    // Constructor with all fields
    public AuthResponse(String username, boolean valid, boolean authenticated, String message) {
        this();
        this.username = username;
        this.valid = valid;
        this.authenticated = authenticated;
        this.message = message;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "username='" + username + '\'' +
                ", valid=" + valid +
                ", authenticated=" + authenticated +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}
