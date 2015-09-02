package com.amqp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AmqpReceiver {

    private static Logger log = LogManager.getLogger(AmqpReceiver.class.getName());

    public void receiveMessage(String status) {
        //TODO postgres INSERT
        log.info("Status to be INSERTED into Postgres database:" + status);
    }
}