package in.teamPlan.secure_auth_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    @JsonProperty("token")
    private String token;

    @JsonProperty("username")
    private String username;

    @JsonProperty("tokenType")
    private String tokenType = "Bearer";

    @JsonProperty("expiresIn")
    private long expiresIn = 86400; // 24 hours in seconds

    // Default constructor
    public LoginResponse() {}

    // Constructor with token and username
    public LoginResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    // Constructor with all fields
    public LoginResponse(String token, String username, String tokenType, long expiresIn) {
        this.token = token;
        this.username = username;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='[PROTECTED]'" +
                ", username='" + username + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
