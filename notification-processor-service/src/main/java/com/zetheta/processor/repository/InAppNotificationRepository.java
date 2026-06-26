package com.zetheta.processor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetheta.processor.entity.InAppNotification;

public interface InAppNotificationRepository
        extends JpaRepository<InAppNotification, Long> {

    List<InAppNotification> findByUserId(String userId);

}