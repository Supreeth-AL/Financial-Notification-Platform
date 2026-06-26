package com.zetheta.processor.dto;

public class AnalyticsResponse {

    private long totalNotifications;

    private long successfulNotifications;

    private long failedNotifications;

    private long emailNotifications;

    private long smsNotifications;

    private long whatsappNotifications;

    private long pushNotifications;

    private long inAppNotifications;

    public AnalyticsResponse() {
    }

    public long getTotalNotifications() {
        return totalNotifications;
    }

    public void setTotalNotifications(long totalNotifications) {
        this.totalNotifications = totalNotifications;
    }

    public long getSuccessfulNotifications() {
        return successfulNotifications;
    }

    public void setSuccessfulNotifications(long successfulNotifications) {
        this.successfulNotifications = successfulNotifications;
    }

    public long getFailedNotifications() {
        return failedNotifications;
    }

    public void setFailedNotifications(long failedNotifications) {
        this.failedNotifications = failedNotifications;
    }

    public long getEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(long emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public long getSmsNotifications() {
        return smsNotifications;
    }

    public void setSmsNotifications(long smsNotifications) {
        this.smsNotifications = smsNotifications;
    }

    public long getWhatsappNotifications() {
        return whatsappNotifications;
    }

    public void setWhatsappNotifications(long whatsappNotifications) {
        this.whatsappNotifications = whatsappNotifications;
    }

    public long getPushNotifications() {
        return pushNotifications;
    }

    public void setPushNotifications(long pushNotifications) {
        this.pushNotifications = pushNotifications;
    }

    public long getInAppNotifications() {
        return inAppNotifications;
    }

    public void setInAppNotifications(long inAppNotifications) {
        this.inAppNotifications = inAppNotifications;
    }
}