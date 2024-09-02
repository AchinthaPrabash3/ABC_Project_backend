package com.example.ABC_Project.controllers;

import com.example.ABC_Project.models.ReserveModel;
import com.example.ABC_Project.services.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ReserveController {

    @Autowired
    ReserveService res;

    @PostMapping("/reserve")
    public ArrayList<String> getReserve(@RequestBody ReserveModel resM) {
        return res.Reserve(resM);
    }
}
