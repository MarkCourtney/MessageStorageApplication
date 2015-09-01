package com.storage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class AmqpSender {

    private static Logger log = LogManager.getLogger(AmqpSender.class.getName());

    @Inject
    private RabbitTemplate template;

    public void sendAmqpMessage(String status) {
        try {
            template.convertAndSend(status);
            log.info("Sent AMQP message");
        } catch (Exception e) {
            log.info("Message could not be sent with RabbitMQ. " + e.getMessage());
        }
    }
}