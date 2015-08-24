package com.project;

import com.receiver.RestReceiverApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(RestReceiverApplication.class, args);
    }
}