package com.zetheta.notification.controller;

import org.springframework.web.bind.annotation.*;

import com.zetheta.notification.dto.NotificationEventRequest;
import com.zetheta.notification.entity.NotificationEvent;
import com.zetheta.notification.service.NotificationEventService;

@RestController

@RequestMapping("/api/events")
public class NotificationEventController {

    private final
    NotificationEventService service;

    public NotificationEventController(
            NotificationEventService service) {

        this.service = service;
    }

    @PostMapping
    public NotificationEvent createEvent(
            @RequestBody
            NotificationEventRequest request) {

        return service.createEvent(
                request);
    }
}