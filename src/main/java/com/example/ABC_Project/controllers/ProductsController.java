package com.example.ABC_Project.controllers;


import com.example.ABC_Project.models.ProductsModel;
import com.example.ABC_Project.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductsController {
    @Autowired
    ProductsService pserve;

   @GetMapping("/products")
   public List<ProductsModel> allProd() {
       return pserve.getAllProducts();
   }

}
