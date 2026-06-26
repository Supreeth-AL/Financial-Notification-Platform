package com.zetheta.processor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zetheta.processor.enums.NotificationChannel;

@Service
public class ChannelRoutingService {

    public List<NotificationChannel> getChannels(
            String eventType) {

        List<NotificationChannel> channels =
                new ArrayList<>();

        switch (eventType) {

            case "TRANSACTION_SUCCESS":

                channels.add(
                        NotificationChannel.EMAIL);

                channels.add(
                        NotificationChannel.SMS);

                break;

            case "MARGIN_CALL":

                channels.add(
                        NotificationChannel.EMAIL);

                channels.add(
                        NotificationChannel.SMS);

                channels.add(
                        NotificationChannel.WHATSAPP);

                channels.add(
                        NotificationChannel.PUSH);

                break;

            case "PASSWORD_CHANGED":

                channels.add(
                        NotificationChannel.EMAIL);

                channels.add(
                        NotificationChannel.IN_APP);

                break;

                

            default:

                channels.add(
                        NotificationChannel.EMAIL);
        }

        return channels;
    }
}