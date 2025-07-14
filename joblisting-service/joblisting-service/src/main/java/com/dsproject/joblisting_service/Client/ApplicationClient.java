package com.dsproject.joblisting_service.Client;

import com.dsproject.joblisting_service.Model.Application;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface ApplicationClient {
    @GetExchange("application/job/{jobId}")
    public List<Application> findByJob(@PathVariable("jobId") String jobId);
}
