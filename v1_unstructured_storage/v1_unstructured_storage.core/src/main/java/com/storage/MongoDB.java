package com.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MongoDB {

    @Autowired
    JsonRepository jsonRepository;

    private static final Logger logger = LoggerFactory.getLogger(MongoDB.class);

    public Boolean insertRecord(Map message) {
        // Report if there's issues with the MongoDB (timeouts, CRUD fails)
        try {
            jsonRepository.insert(message);
            logger.info("Inserted record : " + message);
            return true;
        } catch (Exception e) {
            logger.error("Couldn't insert into DB. " + e.getLocalizedMessage());
            return false;
        }
   }
}
