package com.storage;

import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class MongoDB {

    @Inject
    private JsonRepository jsonRepository;

    public boolean insertRecord(Map message) {
        // Report if there's issues with the MongoDB (timeouts, CRUD fails)
        try {
            jsonRepository.insert(message);
            System.out.println("INSERTED record : " + message);
            return true;
        } catch (Exception e) {
            System.out.println("Couldn't insert into DB. " + e.getMessage());
            return false;
        }
    }
}