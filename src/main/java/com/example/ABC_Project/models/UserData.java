package com.example.ABC_Project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "users")
public class UserData {
    private ObjectId _id;
    private String email;
    private String username;
    private Integer number;
    private String address;
    private String location;
    private List<String> orders;
}
