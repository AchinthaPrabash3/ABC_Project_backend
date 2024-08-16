package com.example.ABC_Project.services;

import com.example.ABC_Project.Productsrepo;
import com.example.ABC_Project.models.ProductsModel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
public class ProductsService {
    @Autowired
    Productsrepo prepo;


    public List<ProductsModel> getAllProducts(){
        return  prepo.findAll();
    }
}
