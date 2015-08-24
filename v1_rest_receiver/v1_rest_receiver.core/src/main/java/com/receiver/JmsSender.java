package com.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class JmsSender {
    @Inject
    JmsTemplate template;

    private static final Logger logger = LoggerFactory.getLogger(JmsSender.class);

    public void sendJmsMessage(Map message) {
        try {
            template.convertAndSend(message);
            System.out.println("Sending message");
        } catch (Exception e) {
            logger.error("Message could not be sent with ActiveMQ. " + e.getMessage());
        }
    }
}