package com.example.ABC_Project.controllers;

import com.example.ABC_Project.models.SignupModel;
import com.example.ABC_Project.services.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class SignupController {

    @Autowired
    SignupService service;

    @PostMapping("/signup")
    public boolean adduser(@RequestBody SignupModel model) {
       return service.signup(model);
    }
}
