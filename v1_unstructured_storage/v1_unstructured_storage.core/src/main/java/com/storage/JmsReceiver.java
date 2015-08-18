package com.storage;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * Created by courtneym on 17/08/2015.
 */
@Component
@EnableAutoConfiguration
public class JmsReceiver {
    @JmsListener(destination = "message.queue")
    public void onMessage(Map message) {
        System.out.println("This message, when called " + message);
    }
}