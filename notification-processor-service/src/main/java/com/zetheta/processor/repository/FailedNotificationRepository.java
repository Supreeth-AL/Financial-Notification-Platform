package com.zetheta.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetheta.processor.entity.FailedNotification;

public interface FailedNotificationRepository
        extends JpaRepository<FailedNotification, Long> {

}