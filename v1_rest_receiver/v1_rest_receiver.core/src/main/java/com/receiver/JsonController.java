package com.receiver;

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
import java.util.Map;

/**
 * Created by courtneym on 14/08/2015.
 */
@RestController
public class JsonController {

    @Autowired
    ConfigurableApplicationContext context;

    // Setup a producer to send a message with JMS
    @Bean
    JmsListenerContainerFactory<?> jsonListener(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    // Endpoint /json
    // Json message will be sent here with POST request
    // Send that message onward to a JMS consumer
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void sendRequest(@RequestBody Map person) {
        sendJmsMap("jms-receiver", person);
    }

    private void sendJmsMap(String destination, Map map) {
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        jmsTemplate.convertAndSend(destination, map);
    }
}