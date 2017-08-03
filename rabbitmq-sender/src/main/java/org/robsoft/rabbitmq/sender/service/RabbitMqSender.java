package org.robsoft.rabbitmq.sender.service;

import org.robsoft.rabbitmq.sender.spring.SenderRabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqSender {

    RabbitTemplate rabbitTemplate;

    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        System.out.println("sending -> " + message);
        this.rabbitTemplate.convertAndSend(SenderRabbitMqConfig.QUEUE_NAME, message);
    }

}
