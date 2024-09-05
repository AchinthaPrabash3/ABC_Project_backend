package com.example.ABC_Project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderItemsModel {
    private int amount;
    private String name;
    private int price;
    private String description;
    private String tag;
    private String img;
}
