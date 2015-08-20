package com.storage;

import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MongoDB {

    @Autowired
    MongoRepository<Map, String> mongoRepository;

    private static final Logger logger = LoggerFactory.getLogger(MongoDB.class);

    public Boolean insertRecord(Map message) {
        // Report if there's issues with the MongoDB (timeouts, CRUD fails)
        try {
            mongoRepository.insert(message);
            logger.info("Inserted record : " + message);
            return true;
        } catch (MongoException e) {
            logger.error("Couldn't insert into DB. " + e.getLocalizedMessage());
            return false;
        }
   }
}
