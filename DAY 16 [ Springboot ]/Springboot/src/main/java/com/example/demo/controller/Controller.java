package com.example.demo.controller;

import com.example.demo.model.Student;

import java.util.ArrayList;
import java.util.List;

@
public class Controller {
    
    private final List<Student> student = new ArrayList<>();
    
    public  Controller(){
        student.add(new Student(1,"Ratan", "developer"));
        student.add(new Student(2,"Soham", "developer"));
    }


    
}
