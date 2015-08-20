package com.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {
    @Autowired
    JmsTemplate template;

    public void sendJmsMessage(Boolean status) {
        template.convertAndSend(status);
    }
}