package com.example.ABC_Project.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Document(collection = "locationData")
public class LocationDataModel {
    private ObjectId _id;
    private String location;
    private Integer seatCount;
    private String password;
}
