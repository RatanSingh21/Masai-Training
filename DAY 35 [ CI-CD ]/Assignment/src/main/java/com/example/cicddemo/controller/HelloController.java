package com.example.cicddemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
