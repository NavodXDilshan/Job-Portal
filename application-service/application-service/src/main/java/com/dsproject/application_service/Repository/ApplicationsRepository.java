package com.dsproject.application_service.Repository;

import com.dsproject.application_service.Model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApplicationsRepository extends MongoRepository<Application, String> {
    List<Application> findByUserId(String userId);


}
