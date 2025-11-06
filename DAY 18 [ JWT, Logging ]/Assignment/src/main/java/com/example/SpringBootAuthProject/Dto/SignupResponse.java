package com.example.SpringBootAuthProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupResponse {

    private int status;
    private String message;
    private List<String> suggestion;

}
