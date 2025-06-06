package com.dsproject.application_service.Controller;

import com.dsproject.application_service.Model.Application;
import com.dsproject.application_service.Repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    ApplicationRepository repository;

    @PostMapping
    public Application add(@RequestBody Application application){
        LOGGER.info("Application add: {}",application);
        return repository.add(application);
    }

    @GetMapping
    public List<Application> findAll(){
        LOGGER.info("Applications find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Application findById(@PathVariable Long id){
        LOGGER.info("Application by Id: id={}",id);
        return repository.findById(id);
    }

    @GetMapping("/job/{jobId}")
    public List<Application> findByJob(@PathVariable("jobId") Long jobId){
        LOGGER.info("Applications find : {jobId}",jobId);
        return repository.findByJob(jobId);
    }
}
