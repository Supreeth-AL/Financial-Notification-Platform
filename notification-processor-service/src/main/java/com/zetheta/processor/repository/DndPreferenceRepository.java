package com.zetheta.processor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetheta.processor.entity.DndPreference;

public interface DndPreferenceRepository
        extends JpaRepository<DndPreference, Long> {

    Optional<DndPreference>
    findByUserId(String userId);
}