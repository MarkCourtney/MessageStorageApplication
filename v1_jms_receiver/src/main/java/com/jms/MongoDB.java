package com.jms;

import com.jms.config.repository.JsonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class MongoDB {

    private static Logger log = LogManager.getLogger(MongoDB.class.getName());

    private JsonRepository jsonRepository;

    @Inject
    public MongoDB(JsonRepository jsonRepository) {
        this.jsonRepository = jsonRepository;
    }

    public String insertRecord(Map<String, String> message) {
        // Report if there's issues with the MongoDB (timeouts, CRUD fails)
        try {
            jsonRepository.insert(message);
            log.info("INSERT Map completed");
            return String.valueOf(true);
        } catch (Exception e) {
            log.info("INSERT Map failed");
            return String.valueOf(false);
        }
    }
}
