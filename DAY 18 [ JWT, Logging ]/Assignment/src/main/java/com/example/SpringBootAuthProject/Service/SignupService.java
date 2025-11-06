package com.example.SpringBootAuthProject.Service;

import com.example.SpringBootAuthProject.Dto.SignupRequest;
import com.example.SpringBootAuthProject.Dto.SignupResponse;
import com.example.SpringBootAuthProject.Entity.Member;
import com.example.SpringBootAuthProject.Entity.MemberDetails;
import com.example.SpringBootAuthProject.Exceptions.UserAlreadyExistsException;
import com.example.SpringBootAuthProject.Repository.MemberRepo;
import com.example.SpringBootAuthProject.Util.JwtUtil;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class SignupService {

    @Autowired
    private MemberRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;

    private final Random random = new Random();

    public SignupResponse signupUser(SignupRequest signupRequest){
        Set<String> existingUserIds = new HashSet<>(repo.findAllUserIds());

        Set<String> suggestions = new HashSet<>();

        while(suggestions.size()<3){

            String suggestion = signupRequest.getFirstName().toLowerCase() + (100 + random.nextInt(900));

            if (!existingUserIds.contains(suggestion)) {
                suggestions.add(suggestion);
            }

        }

        if (repo.existsByUserId(signupRequest.getUserId())) {
            throw new UserAlreadyExistsException("User ID already taken", suggestions);
        }


                Member user = new Member();
                user.setFirstName(signupRequest.getFirstName());
                user.setLastName(signupRequest.getLastName());
                user.setEmail(signupRequest.getEmail());
                user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
                user.setUserId(signupRequest.getUserId());
                user.setDob(java.sql.Date.valueOf(signupRequest.getDob()));

                repo.save(user);


        return new SignupResponse(200, "User registered successfully", null);

    }

}
