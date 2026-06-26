package com.zetheta.processor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zetheta.processor.dto.NotificationTemplateData;
import com.zetheta.processor.entity.NotificationRecord;
import com.zetheta.processor.enums.NotificationChannel;
import com.zetheta.processor.repository.NotificationRecordRepository;

@Service
public class NotificationProcessingService {

    private final ChannelRoutingService routingService;

    private final TemplateEngineService templateService;

    private final DndComplianceService dndService;

    private final FrequencyCappingService frequencyCappingService;

    private final QuietHoursService quietHoursService;

    private final EmailNotificationService emailService;

    private final SmsNotificationService smsService;

    private final WhatsAppNotificationService whatsappService;

    private final PushNotificationService pushNotificationService;

    private final InAppNotificationService inAppNotificationService;

    private final NotificationRecordRepository notificationRecordRepository;

    public NotificationProcessingService(

            ChannelRoutingService routingService,

            TemplateEngineService templateService,

            DndComplianceService dndService,

            FrequencyCappingService frequencyCappingService,

            QuietHoursService quietHoursService,

            EmailNotificationService emailService,

            SmsNotificationService smsService,

            WhatsAppNotificationService whatsappService,

            PushNotificationService pushNotificationService,

            InAppNotificationService inAppNotificationService,

            NotificationRecordRepository notificationRecordRepository) {

        this.routingService = routingService;
        this.templateService = templateService;
        this.dndService = dndService;
        this.frequencyCappingService = frequencyCappingService;
        this.quietHoursService = quietHoursService;
        this.emailService = emailService;
        this.smsService = smsService;
        this.whatsappService = whatsappService;
        this.pushNotificationService = pushNotificationService;
        this.inAppNotificationService = inAppNotificationService;
        this.notificationRecordRepository = notificationRecordRepository;
    }

    public void processEvent(String eventType) {

        String userId = "USR1001";

        //-----------------------------------------
        // DND CHECK
        //-----------------------------------------

        if (!dndService.canSendNotification(userId,
                "TRANSACTIONAL")) {

            System.out.println(
                    "Notification Blocked By DND");

            return;
        }

        //-----------------------------------------
        // QUIET HOURS
        //-----------------------------------------

        if (!quietHoursService.canSendNotification(
                userId,
                eventType)) {

            System.out.println(
                    "Notification Blocked During Quiet Hours");

            return;
        }

        //-----------------------------------------
        // CHANNEL ROUTING
        //-----------------------------------------

        List<NotificationChannel> channels =
                routingService.getChannels(eventType);

        //-----------------------------------------
        // TEMPLATE
        //-----------------------------------------

        NotificationTemplateData data =
                new NotificationTemplateData();

        data.setCustomerName("Supreeth");
        data.setAmount("5000");
        data.setTransactionId("TXN10001");

        String content =
                templateService
                        .generateTransactionTemplate(data);

        //-----------------------------------------
        // SEND TO EACH CHANNEL
        //-----------------------------------------

        for (NotificationChannel channel : channels) {

            //-----------------------------------------
            // FREQUENCY CAPPING
            //-----------------------------------------

            boolean allowed =
                    frequencyCappingService.canSend(
                            userId,
                            channel.name());

            if (!allowed) {

                System.out.println(
                        "Blocked By Frequency Cap : "
                                + channel);

                continue;
            }

            //-----------------------------------------
            // CREATE DELIVERY RECORD
            //-----------------------------------------

            NotificationRecord record =
                    new NotificationRecord();

            record.setEventId(
                    "EVT-" + System.currentTimeMillis());

            record.setChannel(channel.name());

            record.setMessage(content);

            record.setStatus("PROCESSING");

            notificationRecordRepository.save(record);

            //-----------------------------------------
            // SEND NOTIFICATION
            //-----------------------------------------

            try {

                switch (channel) {

                    case EMAIL:

                        emailService.sendEmail(

                                "your-email@gmail.com",

                                "Transaction Successful",

                                content);

                        break;

                    case SMS:

                        smsService.sendSms(

                                "+91XXXXXXXXXX",

                                content);

                        break;

                    case WHATSAPP:

                        whatsappService.sendMessage(

                                "+91XXXXXXXXXX",

                                content);

                        break;

                    case PUSH:

                        pushNotificationService
                                .sendNotification(

                                        "DEVICE_TOKEN",

                                        "Transaction Successful",

                                        content);

                        break;

                    case IN_APP:

                        inAppNotificationService
                                .createNotification(

                                        userId,

                                        "Transaction Successful",

                                        content);

                        break;

                    default:

                        break;
                }

                //-----------------------------------------
                // SUCCESS
                //-----------------------------------------

                record.setStatus("DELIVERED");

                notificationRecordRepository.save(record);

                System.out.println(

                        channel + " Delivered Successfully");

            } catch (Exception exception) {

                //-----------------------------------------
                // FAILURE
                //-----------------------------------------

                record.setStatus("FAILED");

                record.setFailureReason(

                        exception.getMessage());

                notificationRecordRepository.save(record);

                System.out.println(

                        channel + " Delivery Failed");

                System.out.println(

                        exception.getMessage());
            }
        }
    }
}