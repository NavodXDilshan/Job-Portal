package com.dsproject.joblisting_service.Model;

import java.util.ArrayList;
import java.util.List;

public class Job {
    private Long id;
    private String name;
    private List<Application> application = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Job(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Job() {
    }


}
