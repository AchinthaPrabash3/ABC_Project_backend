package com.example.ABC_Project.controllers;

import com.example.ABC_Project.models.OrdersModel;
import com.example.ABC_Project.models.ProductsModel;
import com.example.ABC_Project.models.ReserveModel;
import com.example.ABC_Project.models.UserData;
import com.example.ABC_Project.services.OrdersService;
import com.example.ABC_Project.services.ProductsService;
import com.example.ABC_Project.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {
    @Autowired
    OrdersService ordersService;

    @Autowired
    ProductsService productsService;

    @Autowired
    ReserveService reserveService;

    @PostMapping("/order")
    public OrdersModel placeOrder(@RequestBody OrdersModel ordM) {
        return ordersService.saveOrder(ordM);
    }

    @PostMapping("/reserve")
    public ArrayList<String> getReserve(@RequestBody ReserveModel resM) {
        return reserveService.Reserve(resM);
    }

    @GetMapping("/products")
    public List<ProductsModel> allProd() {
        return productsService.getAllProducts();
    }

    @PostMapping("/trackorders")
    public List<OrdersModel> track(@RequestBody List<String> om) {
        return ordersService.trackOrders(om);
    }

    @PostMapping("/cancelorder")
    public void cancelOrder(@RequestBody OrdersModel om) {
        ordersService.cancelOrder(om);
    }

    @GetMapping("/locations")
    public List<String> locationInfo() {
        return ordersService.getLocations();
    }

    @PostMapping("/cancelres")
    public void cancelRes(@RequestBody ReserveModel resM) {
        reserveService.cancelReserve(resM);
    }

    @PostMapping("/getuserorders")
    public List<OrdersModel> getUserOrders(@RequestBody UserData userData) {
        return ordersService.getUserOrders(userData);
    }

    @PostMapping("/getuserres")
    public List<ReserveModel> getUserRes(@RequestBody UserData userData) {
        return reserveService.getUserRes(userData);
    }
}
