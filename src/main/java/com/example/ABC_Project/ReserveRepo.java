package com.example.ABC_Project;

import com.example.ABC_Project.models.ReserveModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReserveRepo extends MongoRepository<ReserveModel,String> {
}
