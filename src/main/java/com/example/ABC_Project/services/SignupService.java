package com.example.ABC_Project.services;

import com.example.ABC_Project.SignupRepo;
import com.example.ABC_Project.models.SignupModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SignupService {
    @Autowired
    private SignupRepo sign;

    private List<SignupModel> getUsers() {
        return sign.findAll();
    }

    public boolean signup(SignupModel sModel) {
        List<SignupModel> list = getUsers();
        String email = sModel.getEmail();
        System.out.println(email);
        boolean isTrue = false;
        boolean isMatching = list.stream().anyMatch(d -> email.equals(d.getEmail()));
        if (list.isEmpty()) {
            sign.save(sModel);
        } else {
            if (!isMatching) {
                sign.save(sModel);
                isTrue = true ;
            } else {
                isTrue = false;
            }
        }
        return isTrue;

    }
}
