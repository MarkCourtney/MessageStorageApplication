package com.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmqpStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmqpStorageApplication.class, args);
    }
}