package com.dsproject.application_service.Repository;

import com.dsproject.application_service.Model.Application;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ApplicationRepository {
    private List<Application> applications = new ArrayList<>();

    public Application add(Application application) {
        applications.add(application);
        return application;
    }

    public Application findById(Long id){
        return applications.stream()
                .filter(a->a.id().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Application> findByJob(Long jobId){
        return applications.stream()
                .filter(a->a.jobId().equals(jobId))
                .toList();
    }

    public List<Application> findAll() {
        return applications;
    }
}
