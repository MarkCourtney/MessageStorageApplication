package com.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("applicationContext.xml")
public class JmsStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(JmsStorageApplication.class, args);
    }
}