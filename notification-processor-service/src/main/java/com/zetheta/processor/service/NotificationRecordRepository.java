package com.zetheta.processor.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetheta.processor.entity.NotificationRecord;

public interface NotificationRecordRepository
        extends JpaRepository<NotificationRecord, Long> {

    long countByChannel(String channel);

    long countByStatus(String status);

}