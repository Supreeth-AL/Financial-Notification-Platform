package com.zetheta.processor.dto;

public class NotificationTemplateData {

    private String customerName;

    private String amount;

    private String transactionId;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(
            String customerName) {
        this.customerName = customerName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(
            String amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(
            String transactionId) {
        this.transactionId = transactionId;
    }
}