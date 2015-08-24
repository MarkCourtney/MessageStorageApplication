package com.receiver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class JmsSender {
    @Inject
    private JmsTemplate template;

    private static Logger log = LogManager.getLogger(JmsSender.class.getName());

    public void sendJmsMessage(Map message) {
        try {
            log.debug("Sending map with JMS message");
            template.convertAndSend(message);
        } catch (Exception e) {
            log.error("Message could not be sent with ActiveMQ. " + e.getMessage());
        }
    }
}