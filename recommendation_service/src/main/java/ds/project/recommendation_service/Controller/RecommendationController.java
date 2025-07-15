package ds.project.recommendation_service.Controller;

import ds.project.recommendation_service.Model.Recommendation;
import ds.project.recommendation_service.Repository.RecommendationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationController.class);
    @Autowired
    RecommendationRepository repo;

    // Post a new recommendation
    @PostMapping
    public ResponseEntity<Recommendation> postingRecommendation(@RequestBody Recommendation recommend) {
        LOGGER.info("Adding recommendation: {}", recommend);
        Recommendation savedRecommendation = repo.save(recommend);
        return new ResponseEntity<>(savedRecommendation, HttpStatus.CREATED);
    }

    // Retrieve a recommendation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Recommendation> getRecommendation(@PathVariable String id) {
        LOGGER.info("Retrieving recommendation with ID: {}", id);
        Optional<Recommendation> recommendation = repo.findById(id);
        if (recommendation.isPresent()) {
            return new ResponseEntity<>(recommendation.get(), HttpStatus.OK);
        } else {
            LOGGER.warn("Recommendation with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Retrieve all recommendations for a given userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsByUserId(@PathVariable String userId) {
        LOGGER.info("Retrieving recommendations for userId: {}", userId);
        List<Recommendation> recommendations = repo.findByUserId(userId);
        if (!recommendations.isEmpty()) {
            return new ResponseEntity<>(recommendations, HttpStatus.OK);
        } else {
            LOGGER.warn("No recommendations found for userId: {}", userId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing recommendation by ID
    @PutMapping("/{id}")
    public ResponseEntity<Recommendation> updateRecommendation(@PathVariable String id, @RequestBody Recommendation updatedRecommendation) {
        LOGGER.info("Updating recommendation with ID: {}", id);
        Optional<Recommendation> existingRecommendation = repo.findById(id);
        if (existingRecommendation.isPresent()) {
            Recommendation recommendation = existingRecommendation.get();
            // Update fields if provided
            if (updatedRecommendation.getName() != null) {
                recommendation.setName(updatedRecommendation.getName());
            }
            if (updatedRecommendation.getEmail() != null) {
                recommendation.setEmail(updatedRecommendation.getEmail());
            }
            if (updatedRecommendation.getQualifications() != null) {
                recommendation.setQualifications(updatedRecommendation.getQualifications());
            }
            if (updatedRecommendation.getDescription() != null) {
                recommendation.setDescription(updatedRecommendation.getDescription());
            }
            Recommendation savedRecommendation = repo.save(recommendation);
            LOGGER.info("Recommendation updated: {}", savedRecommendation);
            return new ResponseEntity<>(savedRecommendation, HttpStatus.OK);
        } else {
            LOGGER.warn("Recommendation with ID {} not found for update", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a recommendation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable String id) {
        LOGGER.info("Deleting recommendation with ID: {}", id);
        if (repo.existsById(id)) {
            repo.deleteById(id);
            LOGGER.info("Recommendation with ID {} deleted", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            LOGGER.warn("Recommendation with ID {} not found for deletion", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}