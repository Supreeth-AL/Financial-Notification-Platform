package com.zetheta.processor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetheta.processor.entity.QuietHoursPreference;

public interface QuietHoursPreferenceRepository
        extends JpaRepository<
        QuietHoursPreference,
        Long> {

    Optional<QuietHoursPreference>
    findByUserId(String userId);
}