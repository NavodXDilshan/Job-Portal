package com.dsproject.joblisting_service.Controller;

import com.dsproject.joblisting_service.Model.Job;
import com.dsproject.joblisting_service.Repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);
    @Autowired
    private JobRepository repository;

    @PostMapping
    public Job add(@RequestBody Job job){
        LOGGER.info("Job add: {}", job);
        return repository.addJob(job);
    }
    @GetMapping
    public List<Job> findAll(){
        LOGGER.info("Jobs Find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Job findById(@PathVariable Long id){
        LOGGER.info("Job find: id = {}",id);
        return repository.findById(id);
    }
}
