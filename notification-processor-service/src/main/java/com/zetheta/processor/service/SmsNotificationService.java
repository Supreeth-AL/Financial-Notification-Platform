package com.zetheta.processor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsNotificationService {

    @Value("${twilio.phone.number}")
    private String fromNumber;

    public void sendSms(
            String toNumber,
            String messageBody) {

        Message.creator(
                new PhoneNumber(toNumber),
                new PhoneNumber(fromNumber),
                messageBody)
                .create();

        System.out.println(
                "SMS Sent Successfully");
    }
}