package com.dsproject.joblisting_service.Controller;

import com.dsproject.joblisting_service.Client.ApplicationClient;
import com.dsproject.joblisting_service.Model.Job;
import com.dsproject.joblisting_service.Repository.JobRepository;
import com.dsproject.joblisting_service.Repository.JobsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/job")
public class JobController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobController.class);
    @Autowired
    private JobRepository repository;

    @Autowired
    JobsRepository repo;

    @Autowired
    private ApplicationClient applicationClient;

    @PostMapping
    public Job add(@RequestBody Job job){
        LOGGER.info("Job add: {}", job);
        return repo.save(job);
//        return repository.addJob(job);
    }
    @GetMapping
    public List<Job> findAll(){
        LOGGER.info("Jobs Find");
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Job> findById(@PathVariable String id){
        LOGGER.info("Job find: id = {}",id);
//        return repository.findById(id);
        return repo.findById(id);
    }

    @GetMapping("/with-applicants")
    public List<Job> findAllWithApplicants(){
        LOGGER.info("Jobs Find with Applicants");
        List<Job> jobs = repo.findAll();

        jobs.forEach(job ->
                job.setApplication(applicationClient.findByJob(job.getId())));

        return jobs;
    }


}
