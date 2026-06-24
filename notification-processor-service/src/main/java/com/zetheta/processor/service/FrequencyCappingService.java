package com.zetheta.processor.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zetheta.processor.entity.NotificationCounter;
import com.zetheta.processor.repository.NotificationCounterRepository;

@Service
public class FrequencyCappingService {

    private final
    NotificationCounterRepository repository;

    public FrequencyCappingService(
            NotificationCounterRepository repository) {

        this.repository = repository;
    }

    public boolean canSend(
            String userId,
            String channel) {

        Optional<NotificationCounter> counter =
                repository.findByUserIdAndChannel(
                        userId,
                        channel);

        int maxLimit =
                getChannelLimit(channel);

        if (counter.isEmpty()) {

            NotificationCounter newCounter =
                    new NotificationCounter();

            newCounter.setUserId(userId);
            newCounter.setChannel(channel);
            newCounter.setCount(1);

            repository.save(newCounter);

            return true;
        }

        NotificationCounter existing =
                counter.get();

        if (existing.getCount() >= maxLimit) {

            return false;
        }

        existing.setCount(
                existing.getCount() + 1);

        repository.save(existing);

        return true;
    }

    private int getChannelLimit(
            String channel) {

        switch (channel) {

            case "SMS":
                return 10;

            case "EMAIL":
                return 50;

            case "PUSH":
                return 20;

            case "WHATSAPP":
                return 15;

            default:
                return 5;
        }
    }
}