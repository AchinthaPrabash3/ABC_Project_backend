package com.example.ABC_Project.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@Document(collection = "products")
public class ProductsModel {
    private String _id;
    private String name;
    private int price;
    private String description;
    private String tag;
    private String img;

}
