package com.example.cicddemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// CI/CD Pipeline Test - This change should trigger the automated workflow
// Updated: 2024-11-06 - Testing workflow trigger
// testing comment for CI/CD
@RestController
public class HelloController {

    @GetMapping("/")
    public String home(){
       return "Hello Controller For CI/CD Pipeline";
    }

    @GetMapping("/health")
    public String health(){
        return "The Application Is Working Properly";
    }
}
