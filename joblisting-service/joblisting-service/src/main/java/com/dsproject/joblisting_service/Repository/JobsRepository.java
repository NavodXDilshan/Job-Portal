package com.dsproject.joblisting_service.Repository;

import com.dsproject.joblisting_service.Model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobsRepository extends MongoRepository<Job, String> {

}
