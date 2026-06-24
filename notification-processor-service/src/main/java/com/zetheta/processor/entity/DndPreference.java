package com.zetheta.processor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dnd_preferences")
public class DndPreference {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private boolean dndEnabled;

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

    public boolean isDndEnabled() {
        return dndEnabled;
    }

    public void setDndEnabled(
            boolean dndEnabled) {
        this.dndEnabled = dndEnabled;
    }
}