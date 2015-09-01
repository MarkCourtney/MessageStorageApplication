package com.storage;

import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {

    public void receiveMessage(String status) {
        //TODO postgres INSERT
    }
}