package org.robsoft.rabbitmq.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.robsoft.rabbitmq")
public class RabbitMqSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqSpringApplication.class, args);
    }

}
