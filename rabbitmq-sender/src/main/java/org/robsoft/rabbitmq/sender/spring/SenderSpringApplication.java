package org.robsoft.rabbitmq.sender.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.robsoft.rabbitmq.sender")
public class SenderSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SenderSpringApplication.class, args);
    }

}
