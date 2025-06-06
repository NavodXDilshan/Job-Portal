package com.dsproject.joblisting_service.Repository;

import com.dsproject.joblisting_service.Model.Job;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JobRepository {
    private List<Job> jobs = new ArrayList<>();

    public Job addJob(Job job){
        jobs.add(job);
        return job;
    }

    public Job findById(Long id){
        return jobs.stream()
                .filter(job -> job.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public List<Job> findAll(){
        return jobs;
    }
}
