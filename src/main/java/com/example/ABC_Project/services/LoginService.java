package com.example.ABC_Project.services;

import com.example.ABC_Project.LoginRepo;
import com.example.ABC_Project.SignupRepo;
import com.example.ABC_Project.models.LoginModel;
import com.example.ABC_Project.models.SignupModel;
import com.example.ABC_Project.models.UserData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class LoginService {
    @Autowired
    LoginRepo repo;
    @Autowired
    SignupRepo repo1;

    public List<UserData> verify(LoginModel sModel) {
        List<SignupModel> allData = repo1.findAll();
        List<UserData> UserData = repo.findAll();
        String email = sModel.getEmail();
        String password = sModel.getPassword();
        List<UserData> verifiedData = new ArrayList<>();
        for (int i = 0; i < allData.size(); i++) {
            if (email.equals(allData.get(i).getEmail()) & password.equals(allData.get(i).getPassword())) {
                verifiedData.add(UserData.get(i));
            }
        }
        return verifiedData;
    }
}
