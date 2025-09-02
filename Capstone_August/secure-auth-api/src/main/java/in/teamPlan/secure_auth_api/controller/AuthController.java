package in.teamPlan.secure_auth_api.controller;


import in.teamPlan.secure_auth_api.dto.AuthResponse;
import in.teamPlan.secure_auth_api.dto.LoginRequest;
import in.teamPlan.secure_auth_api.dto.LoginResponse;
import in.teamPlan.secure_auth_api.service.TokenService;
import in.teamPlan.secure_auth_api.util.JwtUtil;
import in.teamPlan.secure_auth_api.util.RegexUtil;
import in.teamPlan.secure_auth_api.util.errorhandler.CustomExceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RegexUtil regexUtil;

    private final String VALID_USERNAME = "admin";
    private final String VALID_PASSWORD = "Admin@123";

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {

        // Additional custom validation
        if (!regexUtil.isValidUsername(loginRequest.getUsername())) {
            throw new CustomExceptions.ValidationException(regexUtil.getUsernameValidationMessage());
        }

        if (!regexUtil.isValidPassword(loginRequest.getPassword())) {
            throw new CustomExceptions.ValidationException(regexUtil.getPasswordValidationMessage());
        }

        if (VALID_USERNAME.equals(loginRequest.getUsername()) &&
                VALID_PASSWORD.equals(loginRequest.getPassword())) {

            String token = jwtUtil.generateToken(loginRequest.getUsername());
            tokenService.addToken(token);

            return ResponseEntity.ok(new LoginResponse(token, loginRequest.getUsername()));
        }

        throw new CustomExceptions.InvalidCredentialsException("Invalid username or password");
    }

    @GetMapping("/auth")
    public ResponseEntity<AuthResponse> validateToken(@RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new CustomExceptions.InvalidTokenException("Invalid authorization header format");
        }

        String token = authHeader.substring(7);

        if (!regexUtil.isValidJwtToken(token)) {
            throw new CustomExceptions.InvalidTokenException("Invalid token format");
        }

        if (!jwtUtil.validateToken(token)) {
            throw new CustomExceptions.TokenExpiredException("Token is expired or invalid");
        }

        if (!tokenService.isTokenValid(token)) {
            throw new CustomExceptions.InvalidTokenException("Token has been revoked");
        }

        String username = jwtUtil.getUsernameFromToken(token);
        return ResponseEntity.ok(new AuthResponse(username, true));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new CustomExceptions.InvalidTokenException("Invalid authorization header format");
        }

        String token = authHeader.substring(7);
        tokenService.removeToken(token);

        return ResponseEntity.ok("Logged out successfully");
    }
}
