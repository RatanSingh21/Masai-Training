package com.example.SpringBootAuthProject.Controllers;

import com.example.SpringBootAuthProject.Dto.LoginRequest;
import com.example.SpringBootAuthProject.Dto.LoginResponse;
import com.example.SpringBootAuthProject.Service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class LoginController {

    @Autowired
    LoginService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid  @RequestBody LoginRequest loginRequest) {
        return service.verifyLogin(loginRequest);
    }

}
