package com.example.ABC_Project;

import com.example.ABC_Project.models.SignupModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SignupRepo extends MongoRepository<SignupModel,String> {
}
