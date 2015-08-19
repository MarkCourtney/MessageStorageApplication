package com.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class JmsReceiver {

    @Autowired
    JsonRepository jsonRepository;

    private String messageId = "";

    @JmsListener(destination = "message.queue")
    public void onMessage(Map message) {
        jsonRepository.deleteAll();
        messageId = UUID.randomUUID().toString();
        message.put("_id", messageId);
        System.out.println("Inserting a record: " + message);
        System.out.println("Id generated: " + messageId);
        jsonRepository.insert(message);
        System.out.println("Found all the inserted records: " + jsonRepository.findAll());
        confirmInsert(messageId);
    }

    public void confirmInsert(String messageId) {
        //jsonRepository.findOne(messageId);
        try {
            System.out.println("Does the insert work: " + jsonRepository.exists(messageId));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception thrown");
        }
    }
}