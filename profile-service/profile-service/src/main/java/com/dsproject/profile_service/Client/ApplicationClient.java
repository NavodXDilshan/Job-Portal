package com.dsproject.profile_service.Client;

import com.dsproject.application_service.Model.Application;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface ApplicationClient {
    @GetExchange("/application/user/{userId}")
    List<Application> findByUserId(@PathVariable("userId") String userId);
}