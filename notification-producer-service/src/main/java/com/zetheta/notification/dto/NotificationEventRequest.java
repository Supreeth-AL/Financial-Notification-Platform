package com.zetheta.notification.dto;

import com.zetheta.notification.enums.EventType;

public class NotificationEventRequest {

    private EventType eventType;

    private String userId;

    private String payload;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(
            EventType eventType) {
        this.eventType = eventType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(
            String userId) {
        this.userId = userId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(
            String payload) {
        this.payload = payload;
    }
}