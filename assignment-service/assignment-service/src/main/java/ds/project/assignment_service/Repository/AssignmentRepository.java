package ds.project.assignment_service.Repository;

import ds.project.assignment_service.Model.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {
    List<Assignment> findByJobId(String jobId);
}
