package com.example.ABC_Project;

import com.example.ABC_Project.models.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginRepo extends MongoRepository<UserData,String> {
}
