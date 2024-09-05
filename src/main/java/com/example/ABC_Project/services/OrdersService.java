package com.example.ABC_Project.services;

import com.example.ABC_Project.OrderRepo;
import com.example.ABC_Project.models.OrdersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrdersService {
    @Autowired
    OrderRepo repo;

    public boolean saveOrder(OrdersModel ordM){
        OrdersModel savedOrder =  repo.save(ordM);
        return repo.findById(String.valueOf(savedOrder.get_id())).isPresent();
    }
}
