package com.zetheta.processor.service;

import org.springframework.stereotype.Service;

import com.zetheta.processor.dto.AnalyticsResponse;
import com.zetheta.processor.repository.FailedNotificationRepository;
import com.zetheta.processor.repository.InAppNotificationRepository;
import com.zetheta.processor.repository.NotificationRecordRepository;

@Service
public class AnalyticsService {

    private final NotificationRecordRepository notificationRepository;

    private final FailedNotificationRepository failedRepository;

    private final InAppNotificationRepository inAppRepository;

    public AnalyticsService(

            NotificationRecordRepository notificationRepository,

            FailedNotificationRepository failedRepository,

            InAppNotificationRepository inAppRepository) {

        this.notificationRepository = notificationRepository;

        this.failedRepository = failedRepository;

        this.inAppRepository = inAppRepository;
    }

    public AnalyticsResponse getAnalytics() {

        AnalyticsResponse response =
                new AnalyticsResponse();

        response.setTotalNotifications(
                notificationRepository.count());

        response.setSuccessfulNotifications(
                notificationRepository
                        .countByStatus("DELIVERED"));

        response.setFailedNotifications(
                failedRepository.count());

        response.setEmailNotifications(
                notificationRepository
                        .countByChannel("EMAIL"));

        response.setSmsNotifications(
                notificationRepository
                        .countByChannel("SMS"));

        response.setWhatsappNotifications(
                notificationRepository
                        .countByChannel("WHATSAPP"));

        response.setPushNotifications(
                notificationRepository
                        .countByChannel("PUSH"));

        response.setInAppNotifications(
                inAppRepository.count());

        return response;
    }

}