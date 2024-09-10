package com.example.ABC_Project;

import com.example.ABC_Project.models.ProductsModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepo extends MongoRepository<ProductsModel,String> {
}
