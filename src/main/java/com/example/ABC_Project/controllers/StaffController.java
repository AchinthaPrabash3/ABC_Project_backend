package com.example.ABC_Project.controllers;

import com.example.ABC_Project.models.LocationDataModel;
import com.example.ABC_Project.models.OrdersModel;
import com.example.ABC_Project.models.ReserveModel;
import com.example.ABC_Project.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class StaffController {
    @Autowired
    StaffService staff;

    @PostMapping("/staff")
    public List<String> staffLogin(@RequestBody LocationDataModel ldm) {
        return staff.StaffLogin(ldm);
    }

    @PostMapping("/stafforders")
    public List<OrdersModel> getOrders(@RequestBody ArrayList<String> locM) {
        return staff.orders(locM);
    }

    @PostMapping("/update")
    public void updates(@RequestBody OrdersModel om) {
        staff.update(om);
    }

    @PostMapping("/reserves")
    public List<ReserveModel> getLocationReservations(@RequestBody LocationDataModel locM) {
        return staff.getReserves(locM);
    }
}
