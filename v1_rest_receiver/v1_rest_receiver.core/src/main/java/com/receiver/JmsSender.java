package com.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JmsSender {
    @Autowired
    JmsTemplate template;

    public void sendJmsMessage(Map message) {
        template.convertAndSend(message);
    }
}