package com.zetheta.processor.service;

import org.springframework.stereotype.Service;

import com.zetheta.processor.entity.FailedNotification;
import com.zetheta.processor.repository.FailedNotificationRepository;

@Service
public class DeadLetterQueueService {

    private final FailedNotificationRepository repository;

    public DeadLetterQueueService(
            FailedNotificationRepository repository) {

        this.repository = repository;
    }

    public void moveToDeadLetterQueue(
            String userId,
            String channel,
            String message,
            int retryCount) {

        FailedNotification failed =
                new FailedNotification();

        failed.setUserId(userId);
        failed.setChannel(channel);
        failed.setMessage(message);
        failed.setRetryCount(retryCount);
        failed.setStatus("DEAD_LETTER");

        repository.save(failed);

        System.out.println(
                "Notification moved to Dead Letter Queue");
    }
}