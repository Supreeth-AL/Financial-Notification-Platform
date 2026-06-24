package com.zetheta.processor.service;

import org.springframework.stereotype.Service;

import com.zetheta.processor.dto.NotificationTemplateData;

@Service
public class TemplateEngineService {

    public String generateTransactionTemplate(
            NotificationTemplateData data) {

        String template = """

Dear {name},

Your transaction of ₹{amount}
has been completed successfully.

Transaction ID: {txnId}

Thank You.
""";

        return template
                .replace("{name}",
                        data.getCustomerName())
                .replace("{amount}",
                        data.getAmount())
                .replace("{txnId}",
                        data.getTransactionId());
    }
}