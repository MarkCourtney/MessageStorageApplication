package com.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JmsSender {
    @Autowired
    JmsTemplate template;

    private static final Logger logger = LoggerFactory.getLogger(JmsSender.class);

    public void sendJmsMessage(Map message) {
        try {
            template.convertAndSend(message);
        } catch (Exception e) {
            logger.error("Message could not be sent with ActiveMQ. " + e.getMessage());
        }
    }
}