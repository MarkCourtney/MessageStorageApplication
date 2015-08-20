package com.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.ConnectionFactory;
import java.util.Map;

@RestController
public class JsonController {

    @Autowired
    ApplicationContext context;

    @Autowired
    JmsSender jmsSender;

    @Bean
    JmsListenerContainerFactory<?> jsonListener(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    // Receives a Json message and sends onto a JMS consumer
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void receiveJson(@RequestBody Map person) {
        jmsSender.sendJmsMessage(person);
    }
}