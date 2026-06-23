package com.zetheta.notification.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.zetheta.notification.dto.NotificationEventRequest;
import com.zetheta.notification.entity.NotificationEvent;
import com.zetheta.notification.repository.NotificationEventRepository;

@Service
public class NotificationEventService {

    private final
    NotificationEventRepository repository;

    public NotificationEventService(
            NotificationEventRepository repository) {

        this.repository = repository;
    }

    public NotificationEvent createEvent(
            NotificationEventRequest request) {

        NotificationEvent event =
                new NotificationEvent();

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

        return repository.save(event);
    }
}