package com.zetheta.processor.service;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zetheta.processor.entity.QuietHoursPreference;
import com.zetheta.processor.repository.QuietHoursPreferenceRepository;

@Service
public class QuietHoursService {

    private final
    QuietHoursPreferenceRepository repository;

    public QuietHoursService(
            QuietHoursPreferenceRepository repository) {

        this.repository = repository;
    }

    public boolean canSendNotification(
            String userId,
            String eventType) {

        // Emergency notifications bypass quiet hours

        if ("SECURITY_ALERT".equals(eventType)
                || "OTP".equals(eventType)
                || "REGULATORY_NOTICE".equals(eventType)) {

            return true;
        }

        Optional<QuietHoursPreference> pref =
                repository.findByUserId(userId);

        if (pref.isEmpty()) {
            return true;
        }

        QuietHoursPreference quiet =
                pref.get();

        if (!quiet.isEnabled()) {
            return true;
        }

        int currentHour =
                LocalTime.now().getHour();

        int start =
                quiet.getStartHour();

        int end =
                quiet.getEndHour();

        // Example: 22 → 7

        if (start > end) {

            return !(currentHour >= start
                    || currentHour < end);
        }

        return !(currentHour >= start
                && currentHour < end);
    }
}