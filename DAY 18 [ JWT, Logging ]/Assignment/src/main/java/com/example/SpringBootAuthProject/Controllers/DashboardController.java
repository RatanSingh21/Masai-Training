package com.example.SpringBootAuthProject.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboardPage()
    {
        return ResponseEntity.status(HttpStatus.OK).body("Welcome to Dashboard with auth");
    }

}
