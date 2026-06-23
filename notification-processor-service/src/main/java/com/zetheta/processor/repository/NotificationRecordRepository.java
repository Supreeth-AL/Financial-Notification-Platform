package com.zetheta.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetheta.processor.entity.NotificationRecord;

public interface
NotificationRecordRepository
extends JpaRepository<
        NotificationRecord,
        Long> {
}