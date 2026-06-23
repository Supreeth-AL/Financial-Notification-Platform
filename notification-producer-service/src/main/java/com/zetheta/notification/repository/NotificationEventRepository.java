package com.zetheta.notification.repository;

import com.zetheta.notification.entity.NotificationEvent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface
NotificationEventRepository
extends JpaRepository<
        NotificationEvent,
        Long> {
}