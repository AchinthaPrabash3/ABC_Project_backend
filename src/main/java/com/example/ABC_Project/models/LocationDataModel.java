package com.example.ABC_Project.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
@Document(collection = "locationData")
public class LocationDataModel {
    private String location;
    private Integer seatCount;
}
