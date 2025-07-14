package com.dsproject.joblisting_service.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Jobpost")
public class Job {
    private String id;
    private String name;
    private String description;
    private List<Application> application = new ArrayList<>();
//    private List<Application> applications;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Application> getApplication() {
        return application;
    }

    public void setApplication(List<Application> application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", application=" + application +
                '}';
    }

    public Job(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Job() {
    }


//    public List<Application> getApplications() {
//        return applications;
//    }
//
//    public void setApplications(List<Application> applications) {
//        this.applications = applications != null ? applications : List.of();
//    }
}

