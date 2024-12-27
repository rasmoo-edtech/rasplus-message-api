package com.client.rasplus.message.api.integration.impl;

import com.client.rasplus.message.api.integration.MailIntegration;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailIntegrationImpl implements MailIntegration {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(String mailTo, String message, String subject) {
        log.info("Enviando email");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        javaMailSender.send(simpleMailMessage);
        log.info("Email enviado com sucesso");
    }

    @Override
    public void sendHtmlTemplate(String mailTo, String subject) {
        log.info("Enviando email");
        String content = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Account Recovery</title>
                    <!-- Bootstrap CSS -->
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            background-color: #f9f9f9;
                            margin: 0;
                            padding: 0;
                        }
                        .email-container {
                            max-width: 600px;
                            margin: 20px auto;
                            background: #ffffff;
                            border: 1px solid #ddd;
                            border-radius: 8px;
                            overflow: hidden;
                        }
                        .email-header {
                            background-color: #e31c79;
                            color: #ffffff;
                            padding: 20px;
                            text-align: center;
                        }
                        .email-body {
                            padding: 20px;
                            color: #333333;
                            line-height: 1.6;
                        }
                        .email-footer {
                            background-color: #f1f1f1;
                            padding: 15px;
                            text-align: center;
                            font-size: 14px;
                            color: #666666;
                        }
                        .recovery-code {
                            display: block;
                            font-size: 24px;
                            font-weight: bold;
                            text-align: center;
                            margin: 20px 0;
                            padding: 10px;
                            background-color: #e9ecef;
                            border: 1px dashed #6c757d;
                            border-radius: 4px;
                            color: #e31c79;
                        }
                    </style>
                </head>
                <body>
                                
                <div class="email-container">
                    <div class="email-header">
                        <h1>Account Recovery</h1>
                    </div>
                    <div class="email-body">
                        <p>Hello,</p>
                        <p>You recently requested to recover your account. Use the code below to proceed:</p>
                        <span class="recovery-code">123456</span>
                        <p>If you didn't request this, please ignore this email or contact support if you have concerns.</p>
                    </div>
                    <div class="email-footer">
                        <p>&copy; 2024 Rasmoo Plus. All rights reserved.</p>
                    </div>
                </div>
                                
                <!-- Bootstrap JS (Optional) -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
                </body>
                </html>
                                
                """;
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(mailTo);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException m) {
            throw new RuntimeException(m.getMessage());
        }
        log.info("Email enviado com sucesso");
    }
}
