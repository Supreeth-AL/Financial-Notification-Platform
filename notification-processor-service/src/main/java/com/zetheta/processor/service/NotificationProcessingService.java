package com.zetheta.processor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zetheta.processor.dto.NotificationTemplateData;
import com.zetheta.processor.enums.NotificationChannel;

@Service
public class NotificationProcessingService {

    private final
    ChannelRoutingService routingService;

    private final
    TemplateEngineService templateService;

    public NotificationProcessingService(
            ChannelRoutingService routingService,
            TemplateEngineService templateService) {

        this.routingService = routingService;
        this.templateService = templateService;
    }

    public void processEvent(
            String eventType) {

        List<NotificationChannel> channels =
                routingService.getChannels(
                        eventType);

        NotificationTemplateData data =
                new NotificationTemplateData();

        data.setCustomerName(
                "Supreeth");

        data.setAmount(
                "5000");

        data.setTransactionId(
                "TXN1001");

        String content =
                templateService
                        .generateTransactionTemplate(
                                data);

        System.out.println(
                "Channels: " + channels);

        System.out.println(
                content);
    }
}