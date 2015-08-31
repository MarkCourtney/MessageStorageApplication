package com.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("applicationContext.xml")
public class RestReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestReceiverApplication.class, args);
    }
}