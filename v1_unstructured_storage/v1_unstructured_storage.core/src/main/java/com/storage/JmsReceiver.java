package com.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JmsReceiver {

    @Autowired
    MongoDB mongoDB;

    private static final Logger logger = LoggerFactory.getLogger(JmsReceiver.class);

    @JmsListener(destination = "message.queue")
    public void onMessage(Map message) {
        logger.info("Message Received. Attempting to insert record");
        Boolean inserted = mongoDB.insertRecord(message);
        logger.info("Correctly inserted: " + inserted);
    }
}