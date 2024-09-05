package com.example.ABC_Project.controllers;

import com.example.ABC_Project.models.LocationDataModel;
import com.example.ABC_Project.models.LoginModel;
import com.example.ABC_Project.models.OrdersModel;
import com.example.ABC_Project.models.ReserveModel;
import com.example.ABC_Project.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin (origins = "http://localhost:5173")
public class AdminController {
    @Autowired
    AdminService adminS;

    @PostMapping("/adminlogin")
    public boolean login( @RequestBody LoginModel logM){
        return adminS.adminLogon(logM);
    }
    @GetMapping("/locdata")
    public List<LocationDataModel> allLocationData(){
        return adminS.allLocationData();
    }

    @GetMapping("/allorders")
    public List<OrdersModel> allOrders() {
        return  adminS.allOrderData();
    }
    @GetMapping("/allreservations")
    public List<ReserveModel> allReservations(){
        return  adminS.allReservations();
    }

    @GetMapping("/newreservations")
    public List<ReserveModel> allReserves() {
        return adminS.newReservations();
    }
    @GetMapping("/oldreservations")
    public List<ReserveModel> oldReservations() {
        return adminS.oldReservations();
    }

    @PostMapping("/addlocation")
    public boolean addLocation(@RequestBody LocationDataModel locM){
        return adminS.addLocation(locM);
    }

    @PostMapping("/deleteLoc")
    public boolean deleteLocation(@RequestBody LocationDataModel locM){
        return adminS.deleteLocation(locM);
    }

    @PostMapping("/updatelocname")
    public boolean updateLocationName(@RequestBody LocationDataModel locM){
        return adminS.updateLocationName(locM);
    }

    @PostMapping("/updatelocseats")
    public boolean updateLocationSeatCount(@RequestBody LocationDataModel locM){
       return adminS.updateSeatCount(locM);
    }
    @PostMapping("/updatelocpass")
    public boolean updatePassword(@RequestBody LocationDataModel locM){
         return adminS.updatePass(locM);
    }
}
