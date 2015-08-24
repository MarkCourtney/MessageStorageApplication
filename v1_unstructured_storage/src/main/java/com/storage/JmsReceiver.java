package com.storage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import java.util.Map;

@Component
public class JmsReceiver {

    private static Logger log = LogManager.getLogger(JmsReceiver.class.getName());

    @Inject
    private MongoDB mongoDB;

    @Inject
    private AmqpSender amqpSender;

    @Bean
    JmsListenerContainerFactory<?> jsonListener(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @JmsListener(destination = "message.queue")
    public void onMessage(Map message) {
        log.debug("Message Received. Attempting to insert record");
        Boolean inserted = mongoDB.insertRecord(message);
//        logger.info("Correctly inserted: " + inserted);
//        logger.info("Sending Jms message to AMQP for consumption");
//        amqpSender.sendAmqpMessage(inserted);
    }
}