package com.example.ABC_Project;

import com.example.ABC_Project.models.LocationDataModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationDataRepo extends MongoRepository<LocationDataModel,String> {
}
