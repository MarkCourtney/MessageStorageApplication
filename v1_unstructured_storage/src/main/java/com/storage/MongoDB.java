package com.storage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class MongoDB {

    private static Logger log = LogManager.getLogger(MongoDB.class.getName());

    private JsonRepository jsonRepository;

    public boolean insertRecord(Map message) {
        // Report if there's issues with the MongoDB (timeouts, CRUD fails)
        try {
            jsonRepository.insert(message);
            log.debug("INSERTED record : " + message);
            return true;
        } catch (Exception e) {
            log.debug("Couldn't insert into DB. " + e.getMessage());
            return false;
        }
    }
}