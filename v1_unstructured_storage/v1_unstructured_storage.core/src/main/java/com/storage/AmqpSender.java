package com.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
@ImportResource("amqp-status-producer.xml")
public class AmqpSender {
    @Inject
    RabbitTemplate template;

    private static final Logger logger = LoggerFactory.getLogger(AmqpSender.class);

    public void sendAmqpMessage(Boolean status) {
        try {
            template.convertAndSend(status);
        } catch (Exception e) {
            logger.error("Message could not be sent with ActiveMQ. " + e.getMessage());
        }
    }
}