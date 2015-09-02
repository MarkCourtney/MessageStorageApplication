package com.jms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
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
        String inserted = mongoDB.insertRecord(message);
        amqpSender.sendAmqpMessage(inserted);
    }
}