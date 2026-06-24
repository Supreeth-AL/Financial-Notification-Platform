package com.zetheta.processor.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zetheta.processor.entity.DndPreference;
import com.zetheta.processor.repository.DndPreferenceRepository;

@Service
public class DndComplianceService {

    private final
    DndPreferenceRepository repository;

    public DndComplianceService(
            DndPreferenceRepository repository) {

        this.repository = repository;
    }

    public boolean canSendNotification(
            String userId,
            String notificationType) {

        Optional<DndPreference> preference =
                repository.findByUserId(userId);

        if (preference.isEmpty()) {
            return true;
        }

        if (!preference.get()
                .isDndEnabled()) {

            return true;
        }

        return notificationType.equals(
                "TRANSACTIONAL");
    }
}