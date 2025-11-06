
package com.example.movieticketing.controller;

import com.example.movieticketing.model.User;
import com.example.movieticketing.repository.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User u) {
        // NOTE: password hashing omitted for brevity
        User saved = userRepo.save(u);
        return ResponseEntity.ok(saved);
    }
}
