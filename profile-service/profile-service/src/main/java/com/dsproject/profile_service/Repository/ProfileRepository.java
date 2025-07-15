package com.dsproject.profile_service.Repository;

import com.dsproject.profile_service.Model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    Optional<Profile> findByUserId(String userId);
    Optional<Profile> findByEmail(String email);
    List<Profile> findByNameContainingIgnoreCase(String name);
    boolean existsByEmail(String email);
    void deleteByUserId(String userId);
}
