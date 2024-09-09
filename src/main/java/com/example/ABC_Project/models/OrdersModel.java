package com.example.ABC_Project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@AllArgsConstructor
@Document(collection = "orders")
public class OrdersModel {
    private String _id;
    private Integer total;
    private Integer delivary;
    private boolean completed;
    private String email;
    private List<OrderItemsModel> items;
    private String location;
    private String phone;
    private String address;
}
