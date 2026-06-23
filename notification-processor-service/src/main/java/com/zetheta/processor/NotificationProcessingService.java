package com.zetheta.processor.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationProcessingService {

    public void processEvent(
            String eventId) {

        System.out.println(
                "Processing Event: "
                        + eventId);
    }
}