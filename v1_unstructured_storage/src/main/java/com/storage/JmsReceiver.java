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

    private MongoDB mongoDB;
    private AmqpSender amqpSender;

    @Inject
    public JmsReceiver(MongoDB mongoDB, AmqpSender amqpSender) {
        this.mongoDB = mongoDB;
        this.amqpSender = amqpSender;
    }

    @JmsListener(destination = "message.queue")
    public void onMessage(Map message) {
        System.out.println("Hits");
        log.debug("Message Received. Attempting to insert record");
        boolean inserted = mongoDB.insertRecord(message);
//        amqpSender.sendAmqpMessage(inserted);
    }
}