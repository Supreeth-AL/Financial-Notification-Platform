package com.zetheta.processor.entity;

import jakarta.persistence.*;

@Entity
@Table(name="notification_records")
public class NotificationRecord {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    private String eventId;

    private String channel;

    private String status;

    public Long getId() {
        return id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(
            String eventId) {
        this.eventId = eventId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(
            String channel) {
        this.channel = channel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status) {
        this.status = status;
    }
}