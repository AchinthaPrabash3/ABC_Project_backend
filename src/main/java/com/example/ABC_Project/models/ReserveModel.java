package com.example.ABC_Project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Document(collection = "reservations")
public class ReserveModel {
    private ObjectId _id;
    private String email;
    private String name;
    private String location;
    private String phone;
    private String date;
    private String time;
    private Integer peopleCount;
    private String request;
}
