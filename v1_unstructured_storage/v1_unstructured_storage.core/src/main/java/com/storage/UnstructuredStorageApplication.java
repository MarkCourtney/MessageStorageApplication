package com.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("consumer.xml")
public class UnstructuredStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnstructuredStorageApplication.class, args);
    }
}