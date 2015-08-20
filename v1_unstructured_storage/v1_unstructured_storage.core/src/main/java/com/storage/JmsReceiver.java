package com.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import java.util.Map;

@Component
public class JmsReceiver {

    @Autowired
    MongoDB mongoDB;

    @Autowired
    JmsSender jmsSender;

    private static final Logger logger = LoggerFactory.getLogger(JmsReceiver.class);

    @Bean
    JmsListenerContainerFactory<?> jsonListener(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @JmsListener(destination = "message.queue")
    public void onMessage(Map message) {
        logger.info("Message Received. Attempting to insert record");
        Boolean inserted = mongoDB.insertRecord(message);
        logger.info("Correctly inserted: " + inserted);
        logger.info("Sending Jms message to AMQP for consumption");
        jmsSender.sendJmsMessage(inserted);
    }
}