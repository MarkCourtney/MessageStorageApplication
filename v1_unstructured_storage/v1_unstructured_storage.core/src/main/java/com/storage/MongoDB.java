package com.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class MongoDB {

    @Inject
    private JsonRepository jsonRepository;

    private static final Logger logger = LoggerFactory.getLogger(MongoDB.class);

    public boolean insertRecord(Map message) {
        // Report if there's issues with the MongoDB (timeouts, CRUD fails)
        try {
            jsonRepository.insert(message);
            logger.info("INSERTED record : " + message);
            return true;
        } catch (Exception e) {
            logger.error("Couldn't insert into DB. " + e.getMessage());
            return false;
        }
    }
}