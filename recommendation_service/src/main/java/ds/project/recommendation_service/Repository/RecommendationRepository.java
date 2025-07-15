package ds.project.recommendation_service.Repository;

import ds.project.recommendation_service.Model.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecommendationRepository extends MongoRepository<Recommendation, String> {
    List<Recommendation> findByUserId(String userId);
}