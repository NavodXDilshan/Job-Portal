package com.dsproject.application_service.Model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="applicants")
public record Application(String id, String jobId, String name, String email, int score, String userId) {


}
