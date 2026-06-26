package com.zetheta.processor.service;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class PushNotificationService {

    public void sendNotification(
            String deviceToken,
            String title,
            String body)
            throws Exception {

        Message message =
                Message.builder()
                .setToken(deviceToken)
                .setNotification(
                        Notification.builder()
                                .setTitle(title)
                                .setBody(body)
                                .build())
                .build();

        String response =
                FirebaseMessaging
                        .getInstance()
                        .send(message);

        System.out.println(
                "Push Notification Sent: "
                        + response);
    }
}