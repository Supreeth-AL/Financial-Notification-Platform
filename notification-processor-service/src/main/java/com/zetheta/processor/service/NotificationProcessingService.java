package com.zetheta.processor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zetheta.processor.dto.NotificationTemplateData;
import com.zetheta.processor.enums.NotificationChannel;

@Service
public class NotificationProcessingService {

        private final ChannelRoutingService routingService;

        private final TemplateEngineService templateService;

        private final DndComplianceService dndService;

        private final FrequencyCappingService frequencyCappingService;

        public NotificationProcessingService(
                        ChannelRoutingService routingService,
                        TemplateEngineService templateService,
                        DndComplianceService dndService,
                        FrequencyCappingService frequencyCappingService) {

                this.routingService = routingService;
                this.templateService = templateService;
                this.dndService = dndService;
                this.frequencyCappingService = frequencyCappingService;
        }

        public void processEvent(String eventType) {

                String userId = "USR1001";

                // DND CHECK

                boolean allowedByDnd = dndService.canSendNotification(
                                userId,
                                "TRANSACTIONAL");

                if (!allowedByDnd) {

                        System.out.println(
                                        "Blocked By DND");

                        return;
                }

                // CHANNEL ROUTING

                List<NotificationChannel> channels = routingService.getChannels(
                                eventType);

                // TEMPLATE DATA

                NotificationTemplateData data = new NotificationTemplateData();

                data.setCustomerName(
                                "Supreeth");

                data.setAmount(
                                "5000");

                data.setTransactionId(
                                "TXN1001");

                String content = templateService
                                .generateTransactionTemplate(
                                                data);

                // PROCESS EACH CHANNEL

                for (NotificationChannel channel : channels) {

                        boolean allowedByCap = frequencyCappingService.canSend(
                                        userId,
                                        channel.name());

                        if (!allowedByCap) {

                                System.out.println(
                                                "Blocked By Frequency Cap : "
                                                                + channel);

                                continue;
                        }

                        System.out.println(
                                        "Sending via : "
                                                        + channel);

                        System.out.println(content);

                        System.out.println(
                                        "================================");
                }
        }
}