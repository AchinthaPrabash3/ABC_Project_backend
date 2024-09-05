package com.example.ABC_Project.services;

import com.example.ABC_Project.OrderRepo;
import com.example.ABC_Project.models.OrdersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersService {
    @Autowired
    OrderRepo repo;

    public OrdersModel saveOrder(OrdersModel ordM){
        OrdersModel savedOrder =  repo.save(ordM);
        return savedOrder;
    }

    public List<OrdersModel> trackOrders(List<String> om){
        List<OrdersModel> allOrders = repo.findAll();
        List<OrdersModel> trackedOrders = new ArrayList<>();
        for (String orderId : om) {
            for (OrdersModel order : allOrders) {
                if (order.get_id().equals(orderId)) {
                    trackedOrders.add(order);
                }
            }
        }
       return trackedOrders;
    }
    public void cancelOrder(OrdersModel om){
        String id = om.get_id();
        repo.deleteById(id);
         boolean m = repo.findById(id).isPresent();
        System.out.println(m);
    }

}
