package in.teamPlan.secure_auth_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class LoginRequest {

    @NotBlank(message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$",
            message = "Username must be 3-20 characters long and contain only letters, numbers, and underscores")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$",
            message = "Password must be 8-50 characters with at least one uppercase, lowercase, digit, and special character")
    private String password;

    // Default constructor
    public LoginRequest() {}

    // Parameterized constructor
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}