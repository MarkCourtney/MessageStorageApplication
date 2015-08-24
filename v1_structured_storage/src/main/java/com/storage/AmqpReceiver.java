package com.storage;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {

    private static org.apache.logging.log4j.Logger log = LogManager.getLogger(AmqpReceiver.class.getName());

    public void onMessage(Boolean status) {
        log.debug("Message Received. Attempting to insert record into postgres");
        log.debug("Correctly inserted: " + status);
        log.debug("Sending Jms message to AMQP for consumption");
        //amqpSender.sendAmqpMessage(data);
    }
}