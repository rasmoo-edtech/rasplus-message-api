package com.client.rasplus.message.api.integration;

public interface MailIntegration {

    void send(String mailTo, String message, String subject);

    void sendHtmlTemplate(String mailTo, String subject);
}
