package com.dsproject.application_service.Controller;

import com.dsproject.application_service.Model.Application;
import com.dsproject.application_service.Repository.ApplicationRepository;
import com.dsproject.application_service.Repository.ApplicationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    ApplicationRepository repository;

    @Autowired
    ApplicationsRepository repo;

    @PostMapping
    public Application add(@RequestBody Application application){
        LOGGER.info("Application add: {}",application);
//        return repository.add(application);
        return  repo.save(application);
    }

    @GetMapping
    public List<Application> findAll(){
        LOGGER.info("Applications find");
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Application> findById(@PathVariable String id){
        LOGGER.info("Application by Id: id={}",id);
        return repo.findById(id);
    }

    @GetMapping("/job/{jobId}")
    public List<Application> findByJob(@PathVariable("jobId") String jobId){
        LOGGER.info("Applications find : {jobId}",jobId);
//        return repo.findByJob(jobId);
        return repository.findByJob(jobId);
    }

    @DeleteMapping("/{applicationId}")
    public void deleteApplication(@PathVariable("applicationId") String applicationId) {
        LOGGER.info("Deleting application with applicationId: {}", applicationId);
        // Validate application exists
        Optional<Application> application = repo.findById(applicationId);
        if (application.isEmpty()) {
            LOGGER.warn("Application not found: {}", applicationId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found: " + applicationId);
        }

        try {
            repo.deleteById(applicationId);
            LOGGER.info("Application deleted successfully: {}", applicationId);
        } catch (Exception e) {
            LOGGER.error("Failed to delete application with applicationId: {}", applicationId, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete application: " + applicationId);
        }
    }
}
