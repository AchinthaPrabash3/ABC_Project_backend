package com.example.ABC_Project.services;

import com.example.ABC_Project.LocationDataRepo;
import com.example.ABC_Project.OrderRepo;
import com.example.ABC_Project.SignupRepo;
import com.example.ABC_Project.models.LocationDataModel;
import com.example.ABC_Project.models.OrdersModel;
import com.example.ABC_Project.models.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    LocationDataRepo locationR;
    @Autowired
    SignupRepo signupRepo;

    public OrdersModel saveOrder(OrdersModel ordM) {
        OrdersModel savedOrder = orderRepo.save(ordM);
        return savedOrder;
    }

    public List<OrdersModel> trackOrders(List<String> om) {
        List<OrdersModel> allOrders = orderRepo.findAll();
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

    public void cancelOrder(OrdersModel om) {
        String id = om.get_id();
        orderRepo.deleteById(id);
        boolean m = orderRepo.findById(id).isPresent();
        System.out.println(m);
    }

    public List<String> getLocations() {
        List<LocationDataModel> allLocations = locationR.findAll();
        List<String> locationNames = new ArrayList<>();
        for (LocationDataModel loc : allLocations) {
            locationNames.add(loc.getLocation());
        }
        return locationNames;
    }

    public List<OrdersModel> getUserOrders(UserData userData) {
        String id = userData.get_id();
        List<OrdersModel> allOrders = orderRepo.findAll();
        List<OrdersModel> userOrders = new ArrayList<>();
        if (id != null) {
            List<String> orders = signupRepo.findById(id).get().getOrders();
            for (String orderId : orders) {
                for (OrdersModel order : allOrders) {
                    if (order.get_id().equals(orderId)) {
                        userOrders.add(order);
                    }
                }
            }
        }
        return userOrders;
    }

}
