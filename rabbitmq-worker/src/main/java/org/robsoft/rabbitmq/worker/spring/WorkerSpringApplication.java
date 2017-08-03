package org.robsoft.rabbitmq.worker.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.robsoft.rabbitmq.worker")
public class WorkerSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkerSpringApplication.class, args);
    }

}
