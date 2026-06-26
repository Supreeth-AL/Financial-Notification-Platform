package com.zetheta.processor.service;

import org.springframework.stereotype.Service;

@Service
public class RetryService {

    private static final int MAX_RETRY = 3;

    private final DeadLetterQueueService deadLetterQueueService;

    public RetryService(
            DeadLetterQueueService deadLetterQueueService) {

        this.deadLetterQueueService = deadLetterQueueService;
    }

    public void retryNotification(
            String userId,
            String channel,
            String message,
            Runnable notificationTask) {

        int retryCount = 0;

        while (retryCount < MAX_RETRY) {

            try {

                notificationTask.run();

                System.out.println(
                        "Notification Sent Successfully");

                return;

            } catch (Exception e) {

                retryCount++;

                System.out.println(
                        "Retry Attempt: " + retryCount);

            }
        }

        deadLetterQueueService.moveToDeadLetterQueue(
                userId,
                channel,
                message,
                retryCount);
    }
}