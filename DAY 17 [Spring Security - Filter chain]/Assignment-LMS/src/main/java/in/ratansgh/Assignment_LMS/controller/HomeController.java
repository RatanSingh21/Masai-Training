package in.ratansgh.Assignment_LMS.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home(Authentication auth) {
        String username = (auth != null) ? auth.getName() : "anonymous";
        return Map.of("message", "Welcome to the Library Management System", "username", username);
    }

    @GetMapping("/about")
    public Map<String, String> about() {
        return Map.of("info", "About the Library Management System");
    }

    @GetMapping("/login")
    public Map<String, String> login() {
        return Map.of("message", "Please log in");
    }

    @GetMapping("/access-denied")
    public Map<String, String> accessDenied(Authentication auth) {
        String username = (auth != null) ? auth.getName() : "anonymous";
        return Map.of("error", "Access Denied", "username", username);
    }
}


