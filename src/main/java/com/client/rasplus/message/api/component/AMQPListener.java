package com.client.rasplus.message.api.component;

import com.client.rasplus.message.api.dto.UserRecoveryCodeDto;
import com.client.rasplus.message.api.integration.MailIntegration;
import com.client.rasplus.message.api.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AMQPListener {

    private final MailIntegration mailIntegration;

    private final MessageUtils messageUtils;

    @RabbitListener(queues = "recovery.code.email")
    public void recoveryCodeListener(UserRecoveryCodeDto dto) {
        log.info(messageUtils.get("recovery.code.title"));
        mailIntegration.sendHtmlTemplate(dto.email(), "Código de Recuperação de conta");

    }

}
