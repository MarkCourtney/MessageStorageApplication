package com.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {

    private static final Logger logger = LoggerFactory.getLogger(AmqpReceiver.class);

    public void onMessage(Boolean status) {
        logger.info("Message Received. Attempting to insert record into postgres");
        logger.info("Correctly inserted: " + status);
        logger.info("Sending Jms message to AMQP for consumption");
        //amqpSender.sendAmqpMessage(data);
    }
}