package com.zetheta.processor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zetheta.processor.entity.NotificationCounter;

public interface NotificationCounterRepository
        extends JpaRepository<NotificationCounter, Long> {

    Optional<NotificationCounter> findByUserIdAndChannel(
            String userId,
            String channel);
}