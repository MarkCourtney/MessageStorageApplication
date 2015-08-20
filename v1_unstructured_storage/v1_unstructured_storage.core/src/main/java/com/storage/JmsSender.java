package com.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsSender {
    @Autowired
    JmsTemplate template;

    private static final Logger logger = LoggerFactory.getLogger(JmsSender.class);

    public void sendJmsMessage(Boolean status) {
        try {
            template.convertAndSend(status);
        } catch (Exception e) {
            logger.error("Message could not be sent with ActiveMQ. " + e.getMessage());
        }
    }
}