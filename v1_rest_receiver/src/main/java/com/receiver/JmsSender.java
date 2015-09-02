package com.receiver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class JmsSender {

    private JmsTemplate template;

    @Inject
    public JmsSender(JmsTemplate template) {
        this.template = template;
    }

    private static Logger log = LogManager.getLogger(JmsSender.class.getName());

    public void sendJmsMessage(Map<String, String> message) {
        try {
            template.convertAndSend(message);
            log.info("Sending JMS message");
        } catch (Exception e) {
            log.info("Message could not be sent with ActiveMQ. " + e.getMessage());
        }
    }
}