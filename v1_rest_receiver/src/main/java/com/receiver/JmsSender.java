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
            template.convertAndSend(message);
        } catch (Exception e) {
            log.debug("Message could not be sent with ActiveMQ. " + e.getMessage());
        }
    }
}