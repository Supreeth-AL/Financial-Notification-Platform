package com.zetheta.processor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notification_counters")
public class NotificationCounter {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String channel;

    private int count;

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(
            String userId) {
        this.userId = userId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(
            String channel) {
        this.channel = channel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(
            int count) {
        this.count = count;
    }
}