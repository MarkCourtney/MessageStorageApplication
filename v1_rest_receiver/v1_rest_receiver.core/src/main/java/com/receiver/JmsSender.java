package com.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Component
public class JmsSender {
    @Autowired
    JmsTemplate template;

    public void sendJmsMessage(Map person) {
        template.convertAndSend(person);
    }
}