package com.example.ABC_Project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Document(collection = "users")
public class SignupModel {
    private String email;
    private String username;
    private int number;
    private String location;
    private String address;
    private String password;
}
