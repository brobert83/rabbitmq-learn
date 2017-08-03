package org.robsoft.rabbitmq.worker.service;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {

    @SuppressWarnings("unused")
    public void receiveMessage(
            @Payload String message) {

        System.out.println( "Received -> " + message);
    }

}