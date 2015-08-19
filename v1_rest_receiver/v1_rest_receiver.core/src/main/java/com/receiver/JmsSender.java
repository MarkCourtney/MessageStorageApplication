package com.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.UUID;

@Component
public class JmsSender {
    @Autowired
    JmsTemplate template;

    public void sendJmsMessage(Map message) {
        template.convertAndSend(message);
    }
}