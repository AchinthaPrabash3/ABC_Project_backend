package com.example.ABC_Project.controllers;

import com.example.ABC_Project.models.LoginModel;
import com.example.ABC_Project.models.UserData;
import com.example.ABC_Project.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {

    @Autowired
    private LoginService logs;

    @PostMapping("/users")
    public List<UserData> verify(@RequestBody LoginModel sModel) {
        return logs.verify(sModel);
    }
    @PostMapping("/upuserprod")
    public boolean updateUserProd( @RequestBody List<String> data){
        return logs.updateUserDetails(data);
    }
    @PostMapping("/upuserres")
    public boolean updateUserRes( @RequestBody List<String> data){
        return logs.updateUserReservations(data);
    }

}
