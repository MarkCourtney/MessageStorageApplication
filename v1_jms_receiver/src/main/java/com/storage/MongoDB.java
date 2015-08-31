package com.storage;

import com.storage.config.repository.JsonRepository;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class MongoDB {

    private JsonRepository jsonRepository;

    @Inject
    public MongoDB(JsonRepository jsonRepository) {
        this.jsonRepository = jsonRepository;
    }

    public boolean insertRecord(Map<String, String> message) {
        // Report if there's issues with the MongoDB (timeouts, CRUD fails)
        try {
            jsonRepository.insert(message);
            System.out.println("INSERT Map worked");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
