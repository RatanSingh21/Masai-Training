package in.ratansgh.resilience.controller;

import in.ratansgh.resilience.entities.User;
import in.ratansgh.resilience.service.CircuitBreakerTesting;
import in.ratansgh.resilience.service.UserService;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

@RestController
// for logs using lombok
@Slf4j
@Tag(name = "Rate Limiter Controller", description = "Endpoints for Employee Rate limiter and circuit breaker")
public class MyController {

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CircuitBreakerTesting circuitBreakerTesting;

    @PostMapping("/Register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User user) {

        logger.info("Registering user");

        Map<String, Object > response;

            User registeredUser = userService.registerUser(user);

            response = new LinkedHashMap<>();
            response.put("message", "User registered successfully");
            response.put("user", registeredUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/users")
    @RateLimiter(name = "GetUsers")
    public ResponseEntity<Map<String, Object>> getAllUsers() {

        logger.info("Fetching all users");
        Map<String, Object> response = new LinkedHashMap<>();

        List<User> users = userService.getAllUsers();

        if (users == null || users.isEmpty()) {
            response.put("message", "No users found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        response.put("message", "User found");
        response.put("users", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/circuitbreaker")
    @CircuitBreaker(name = "MyCircuitBreaker", fallbackMethod = "circuitBreakerFallback")
    public ResponseEntity<Map<String, Object>> testCircuitBreaker() {

        logger.info("Testing circuit breaker");
        Map<String, Object> response = new LinkedHashMap<>();

        String result = circuitBreakerTesting.callExternalService();

        response.put("message", "External service call result");
        response.put("result", result);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> circuitBreakerFallback(CallNotPermittedException ex) {

        logger.error("Circuit breaker fallback executed: " + ex.getMessage());
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("message", "Circuit breaker is open. Fallback method executed.");
        response.put("error", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
