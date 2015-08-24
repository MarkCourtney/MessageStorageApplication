package com.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("amqp-status-consumer.xml")
public class StructuredStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StructuredStorageApplication.class, args);
    }
}