package ds.project.assignment_service.Repository;

import ds.project.assignment_service.Model.AnswerSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnswerSubmissionRepository extends MongoRepository<AnswerSubmission, String> {
    List<AnswerSubmission> findByApplicationId(String applicationId);

    List<AnswerSubmission> findByQuestionId(String assignmentId);
}

