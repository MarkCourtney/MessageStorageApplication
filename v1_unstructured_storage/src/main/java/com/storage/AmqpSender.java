package com.storage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@ImportResource("amqp-status-producer.xml")
public class AmqpSender {

    private static Logger log = LogManager.getLogger(AmqpSender.class.getName());

    @Inject
    private RabbitTemplate template;

    public void sendAmqpMessage(Boolean status) {
        try {
            template.convertAndSend(status);
        } catch (Exception e) {
            log.error("Message could not be sent with ActiveMQ. " + e.getMessage());
        }
    }
}