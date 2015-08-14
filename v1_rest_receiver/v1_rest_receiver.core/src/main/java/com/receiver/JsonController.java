package com.receiver;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Map;

/**
 * Created by courtneym on 14/08/2015.
 */
@RestController
public class JsonController {

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void getRequest(@RequestBody Map person) {

        System.out.println(person);
        // Perform some altering if required
        // person.put("id", UUID.randomUUID().toString());
        // person.put("date", new Timestamp(System.currentTimeMillis()));
    }
}