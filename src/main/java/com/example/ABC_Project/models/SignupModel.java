package com.example.ABC_Project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@AllArgsConstructor
@Document(collection = "users")
public class SignupModel {
    private String _id;
    private String email;
    private String username;
    private Integer number;
    private String location;
    private String address;
    private String password;
    private List<String> orders;
    private List<String> reservations;
}
