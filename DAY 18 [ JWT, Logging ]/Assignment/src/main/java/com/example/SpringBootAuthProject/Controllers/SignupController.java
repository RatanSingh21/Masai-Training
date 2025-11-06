package com.example.SpringBootAuthProject.Controllers;


import com.example.SpringBootAuthProject.Dto.SignupRequest;
import com.example.SpringBootAuthProject.Dto.SignupResponse;
import com.example.SpringBootAuthProject.Service.SignupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest signupRequest)
    {
      SignupResponse sr =  signupService.signupUser(signupRequest);
      if(sr.getStatus()==409)
      {
          return ResponseEntity.status(HttpStatus.CONFLICT).body(sr);
      }

        return ResponseEntity.status(HttpStatus.OK).body(sr);
    }

}
