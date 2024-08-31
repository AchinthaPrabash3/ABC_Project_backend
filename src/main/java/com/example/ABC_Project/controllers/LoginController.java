package com.example.ABC_Project.controllers;

import com.example.ABC_Project.models.LoginModel;
import com.example.ABC_Project.models.UserData;
import com.example.ABC_Project.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin (origins = "http://localhost:5173")
public class LoginController {

    @Autowired
    LoginService logs;
    @PostMapping("/users")
    public List<UserData> getAll(@RequestBody LoginModel sModel) {
       return logs.verify(sModel);
    }

}
