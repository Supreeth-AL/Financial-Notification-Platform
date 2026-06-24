package com.zetheta.processor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetheta.processor.entity.UserPreference;

public interface UserPreferenceRepository
        extends JpaRepository<UserPreference, Long> {

    Optional<UserPreference>
    findByUserId(String userId);
}