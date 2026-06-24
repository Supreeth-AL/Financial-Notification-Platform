package com.zetheta.processor.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zetheta.processor.entity.UserPreference;
import com.zetheta.processor.repository.UserPreferenceRepository;

@Service
public class UserPreferenceService {

    private final
    UserPreferenceRepository repository;

    public UserPreferenceService(
            UserPreferenceRepository repository) {

        this.repository = repository;
    }

    public UserPreference savePreference(
            UserPreference preference) {

        return repository.save(preference);
    }

    public Optional<UserPreference>
    getPreference(String userId) {

        return repository.findByUserId(
                userId);
    }
}