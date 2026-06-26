package com.zetheta.processor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class WhatsAppNotificationService {

    @Value("${twilio.whatsapp.number}")
    private String fromNumber;

    public void sendMessage(
            String toNumber,
            String messageBody) {

        Message.creator(
                new PhoneNumber("whatsapp:" + toNumber),
                new PhoneNumber("whatsapp:" + fromNumber),
                messageBody
        ).create();

        System.out.println(
                "WhatsApp Message Sent Successfully");
    }
}