package com.zetheta.processor.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.zetheta.processor.service.NotificationProcessingService;

@Service
public class FinancialEventConsumer {

    private final
    NotificationProcessingService service;

    public FinancialEventConsumer(
            NotificationProcessingService service) {

        this.service = service;
    }

    @KafkaListener(
            topics = "financial-events",
            groupId = "notification-group")
    public void consume(
            String message) {

        System.out.println(
                "Received Event: "
                        + message);

        service.processEvent(
                message);
    }
}