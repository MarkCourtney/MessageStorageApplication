package com.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("message-producer.xml")
public class V1RestReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.run(V1RestReceiverApplication.class, args);
    }
}