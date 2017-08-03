package org.robsoft.rabbitmq.sender.web;

import org.robsoft.rabbitmq.sender.service.RabbitMqSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest {

    RabbitMqSender rabbitMqSender;

    public Rest(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String sendMessage(
            @RequestParam String message) {

        for (int i = 0; i < 10; i++) {
            rabbitMqSender.sendMessage(i + " " + message);
        }

        return "Sent " + message;
    }

}
