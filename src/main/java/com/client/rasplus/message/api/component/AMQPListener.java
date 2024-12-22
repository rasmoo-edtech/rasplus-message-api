package com.client.rasplus.message.api.component;

import com.client.rasplus.message.api.dto.UserRecoveryCodeDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AMQPListener {

    @RabbitListener(queues = "recovery.code.email")
    public void recoveryCodeListener(UserRecoveryCodeDto dto) {
        System.out.println("processando mensagem - "+ Instant.now());
        if("rabbit@rabbit.com".equals(dto.email())) {
            throw new RuntimeException("Não foi possível processar ", null);
        }

        System.out.println("Mensagem processada com sucesso");
    }

}
