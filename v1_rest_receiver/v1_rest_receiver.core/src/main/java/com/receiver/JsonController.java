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

    // Endpoint /json
    // Json message will be sent here with POST request
    // Send that message onward to a JMS consumer
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public void receiveJson(@RequestBody Map person) {
        jmsSender.sendJmsMessage(person);
        System.out.println("Sending map");
    }

    /*
    // Boiler plate code for producing a map message over JMS
    // Spring boot above speeds this up
    public void run(Map map) {
        try {
            // Create a ConnectionFactory
            // Active MQ software wasn't running, thats why it wasn't working
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("jms://localhost:8080");

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("/json");

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create a map message
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setObject("PersonMap", map);
            System.out.println("Sending this object " + mapMessage);
            producer.send(mapMessage);

            // Clean up
            session.close();
            connection.close();
        }
        catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }*/
}