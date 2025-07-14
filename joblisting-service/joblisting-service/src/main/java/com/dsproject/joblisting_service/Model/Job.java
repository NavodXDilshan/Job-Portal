package com.dsproject.joblisting_service.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Jobpost")
public class Job {
    private String id;
    private String name;
    private List<Application> application = new ArrayList<>();

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

    public Job(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Job() {
    }


}
