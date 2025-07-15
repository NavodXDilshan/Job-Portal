package com.dsproject.profile_service.Controller;

import com.dsproject.profile_service.Model.Profile;
import com.dsproject.profile_service.Repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
        LOGGER.info("Creating new profile for user: {}", profile.getUserId());

        if (profileRepository.existsByEmail(profile.getEmail())) {
            return ResponseEntity.badRequest().build();
        }

        profile.setCreatedAt(LocalDateTime.now());
        profile.setUpdatedAt(LocalDateTime.now());
        Profile savedProfile = profileRepository.save(profile);
        return ResponseEntity.ok(savedProfile);
    }

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        LOGGER.info("Fetching all profiles");
        List<Profile> profiles = profileRepository.findAll();
        return ResponseEntity.ok(profiles);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<Profile> getProfile(@PathVariable String userId) {
        LOGGER.info("Fetching profile for user: {}", userId);
        return profileRepository.findByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Profile>> searchProfiles(@RequestParam String name) {
        LOGGER.info("Searching profiles with name: {}", name);
        List<Profile> profiles = profileRepository.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok(profiles);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Profile> updateProfile(
            @PathVariable String userId,
            @RequestBody Profile updatedProfile) {
        LOGGER.info("Updating profile for user: {}", userId);

        return profileRepository.findByUserId(userId)
                .map(existingProfile -> {
                    existingProfile.setName(updatedProfile.getName());
                    existingProfile.setEmail(updatedProfile.getEmail());
                    existingProfile.setPhoneNumber(updatedProfile.getPhoneNumber());
                    existingProfile.setAddress(updatedProfile.getAddress());
                    existingProfile.setUpdatedAt(LocalDateTime.now());
                    return ResponseEntity.ok(profileRepository.save(existingProfile));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable String userId) {
        LOGGER.info("Deleting profile for user: {}", userId);

        return profileRepository.findByUserId(userId)
                .map(profile -> {
                    profileRepository.delete(profile);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
