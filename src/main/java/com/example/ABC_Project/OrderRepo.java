package com.example.ABC_Project;

import com.example.ABC_Project.models.OrdersModel;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OrderRepo extends MongoRepository<OrdersModel,String> {

}
