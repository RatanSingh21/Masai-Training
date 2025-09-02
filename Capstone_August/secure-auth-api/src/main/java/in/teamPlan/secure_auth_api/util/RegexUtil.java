package in.teamPlan.secure_auth_api.util;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class RegexUtil {

    // Username: 3-20 characters, alphanumeric and underscore
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{3,20}$";

    // Password: 8-50 characters, at least one uppercase, lowercase, digit, special char
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$";

    // Email validation
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";

    // JWT Token format
    public static final String JWT_REGEX = "^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_.+/]*$";

    private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern JWT_PATTERN = Pattern.compile(JWT_REGEX);

    public boolean isValidUsername(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    public boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean isValidJwtToken(String token) {
        return token != null && JWT_PATTERN.matcher(token).matches();
    }

    public String getUsernameValidationMessage() {
        return "Username must be 3-20 characters long and contain only letters, numbers, and underscores";
    }

    public String getPasswordValidationMessage() {
        return "Password must be 8-50 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character";
    }

    public String getEmailValidationMessage() {
        return "Please provide a valid email address";
    }
}