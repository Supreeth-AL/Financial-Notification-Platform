package com.zetheta.processor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zetheta.processor.entity.InAppNotification;
import com.zetheta.processor.repository.InAppNotificationRepository;

@Service
public class InAppNotificationService {

    private final InAppNotificationRepository repository;

    public InAppNotificationService(
            InAppNotificationRepository repository) {

        this.repository = repository;
    }

    public InAppNotification createNotification(
            String userId,
            String title,
            String message) {

        InAppNotification notification =
                new InAppNotification();

        notification.setUserId(userId);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setRead(false);

        return repository.save(notification);
    }

    public List<InAppNotification> getNotifications(
            String userId) {

        return repository.findByUserId(userId);
    }

    public InAppNotification markAsRead(Long id) {

        InAppNotification notification =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found"));

        notification.setRead(true);

        return repository.save(notification);
    }
}