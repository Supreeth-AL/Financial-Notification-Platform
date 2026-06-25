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
    private final QuietHoursService quietHoursService;
    private final EmailNotificationService emailService;

    public NotificationProcessingService(
            ChannelRoutingService routingService,
            TemplateEngineService templateService,
            DndComplianceService dndService,
            FrequencyCappingService frequencyCappingService,
            QuietHoursService quietHoursService,
            EmailNotificationService emailService) {

        this.routingService = routingService;
        this.templateService = templateService;
        this.dndService = dndService;
        this.frequencyCappingService = frequencyCappingService;
        this.quietHoursService = quietHoursService;
        this.emailService = emailService;
    }

    public void processEvent(String eventType) {

        String userId = "USR1001";

        // Step 1 - Route Notification
        List<NotificationChannel> channels =
                routingService.getChannels(eventType);

        System.out.println("Selected Channels: " + channels);

        // Step 2 - DND Check
        boolean dndAllowed =
                dndService.canSendNotification(
                        userId,
                        "TRANSACTIONAL");

        if (!dndAllowed) {

            System.out.println("Blocked By DND");
            return;
        }

        // Step 3 - Frequency Cap Check
        boolean frequencyAllowed =
                frequencyCappingService.canSend(
                        userId,
                        "EMAIL");

        if (!frequencyAllowed) {

            System.out.println("Blocked By Frequency Cap");
            return;
        }

        // Step 4 - Quiet Hours Check
        boolean quietHoursAllowed =
                quietHoursService.canSendNotification(
                        userId,
                        eventType);

        if (!quietHoursAllowed) {

            System.out.println("Blocked By Quiet Hours");
            return;
        }

        // Step 5 - Generate Template
        NotificationTemplateData data =
                new NotificationTemplateData();

        data.setCustomerName("Supreeth");
        data.setAmount("5000");
        data.setTransactionId("TXN1001");

        String content =
                templateService.generateTransactionTemplate(data);

        System.out.println("\nNotification Content:\n");
        System.out.println(content);

        // Step 6 - Send Email
        emailService.sendEmail(
                "supreethal.dev@gmail.com",
                "Transaction Successful",
                content);

        System.out.println("\nEmail Sent Successfully");

        System.out.println("\nNotification Ready For Delivery");
    }
}