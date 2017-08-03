package org.robsoft.rabbitmq.worker.spring;

import org.robsoft.rabbitmq.worker.service.RabbitMqConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkerRabbitMqConfig {

    public static final String QUEUE_NAME = "traalalala";

    @Bean Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean TopicExchange exchange() {
        return new TopicExchange("dummy");
    }

    @Bean Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(QUEUE_NAME);
    }

    @Bean SimpleMessageListenerContainer container(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
        container.setMessageListener(listenerAdapter);

        return container;
    }

    @Bean MessageListenerAdapter listenerAdapter(
            RabbitMqConsumer rabbitMqConsumer) {

        return new MessageListenerAdapter(rabbitMqConsumer, "receiveMessage");
    }

}
