package com.zetheta.notification.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.zetheta.notification.dto.NotificationEventRequest;
import com.zetheta.notification.entity.NotificationEvent;
import com.zetheta.notification.repository.NotificationEventRepository;

@Service
public class NotificationEventService {

    private final NotificationEventRepository repository;
    private final KafkaProducerService kafkaProducerService;

    public NotificationEventService(
            NotificationEventRepository repository,
            KafkaProducerService kafkaProducerService) {

        this.repository = repository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public NotificationEvent createEvent(
            NotificationEventRequest request) {

        NotificationEvent event = new NotificationEvent();

        event.setEventId(
                UUID.randomUUID().toString());

        event.setEventType(
                request.getEventType());

        event.setUserId(
                request.getUserId());

        event.setPayload(
                request.getPayload());

        event.setStatus(
                "RECEIVED");

        NotificationEvent savedEvent =
                repository.save(event);

        kafkaProducerService.publishEvent(
                savedEvent.getEventId());

        return savedEvent;
    }
}