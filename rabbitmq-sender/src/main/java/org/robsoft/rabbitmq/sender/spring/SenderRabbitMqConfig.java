package org.robsoft.rabbitmq.sender.spring;

import org.robsoft.rabbitmq.sender.service.RabbitMqSender;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderRabbitMqConfig {

    public static final String QUEUE_NAME = "traalalala";

    @Bean Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean TopicExchange exchange() {
        return new TopicExchange("dummy");
    }

    @Bean Binding binding(
            Queue queue,
            TopicExchange exchange) {

        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME);
    }

    @Bean RabbitMqSender rabbitMqSender(RabbitTemplate rabbitTemplate){
        return new RabbitMqSender(rabbitTemplate);
    }

}
