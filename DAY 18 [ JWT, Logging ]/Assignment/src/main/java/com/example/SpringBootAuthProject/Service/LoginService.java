package com.example.SpringBootAuthProject.Service;


import com.example.SpringBootAuthProject.Dto.LoginRequest;
import com.example.SpringBootAuthProject.Dto.LoginResponse;
import com.example.SpringBootAuthProject.Entity.Member;
import com.example.SpringBootAuthProject.Entity.MemberDetails;
import com.example.SpringBootAuthProject.Exceptions.InvalidCredentialsException;
import com.example.SpringBootAuthProject.Repository.MemberRepo;
import com.example.SpringBootAuthProject.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    MemberRepo repo;

    public ResponseEntity<LoginResponse> verifyLogin(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserId(),
                            loginRequest.getPassword()
                    )

            );
            MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(memberDetails);
            LoginResponse loginResponse = new LoginResponse(token,"Login Successfull");
            return ResponseEntity.status(HttpStatus.OK).body(loginResponse);

        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Username or Password are incorrect");
        }
    }

}
