package com.receiver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import java.util.Map;

@RestController
public class JsonController {

    private ApplicationContext context;
    private JmsSender jmsSender;

    @Inject
    public JsonController(ApplicationContext context, JmsSender jmsSender) {
        this.context = context;
        this.jmsSender = jmsSender;
    }

    @Bean
    JmsListenerContainerFactory<?> jsonListener(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    // Receives a Json message and sends onto a JMS consumer
    @RequestMapping(value = "/payload", method = RequestMethod.POST)
    public void receiveJson(@RequestBody Map person) {
        jmsSender.sendJmsMessage(person);
    }
}