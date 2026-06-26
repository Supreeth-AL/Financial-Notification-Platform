package com.zetheta.processor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.zetheta.processor.entity.InAppNotification;
import com.zetheta.processor.service.InAppNotificationService;

@RestController
@RequestMapping("/api/in-app")
public class InAppNotificationController {

    private final InAppNotificationService service;

    public InAppNotificationController(
            InAppNotificationService service) {

        this.service = service;
    }

    @GetMapping("/{userId}")
    public List<InAppNotification> getNotifications(
            @PathVariable String userId) {

        return service.getNotifications(userId);
    }

    @PostMapping("/create")
    public InAppNotification createNotification(

            @RequestParam String userId,

            @RequestParam String title,

            @RequestParam String message) {

        return service.createNotification(
                userId,
                title,
                message);
    }

    @PutMapping("/{id}/read")
    public InAppNotification markAsRead(
            @PathVariable Long id) {

        return service.markAsRead(id);
    }

}