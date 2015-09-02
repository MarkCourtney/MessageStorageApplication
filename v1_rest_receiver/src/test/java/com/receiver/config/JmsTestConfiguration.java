package com.receiver.config;

import com.mockrunner.jms.ConfigurationManager;
import com.mockrunner.jms.DestinationManager;
import com.mockrunner.mock.jms.MockQueue;
import com.mockrunner.mock.jms.MockQueueConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsTestConfiguration {

    @Bean
    DestinationManager destinationManager() {
        return new DestinationManager();
    }

    @Bean
    ConfigurationManager configurationManager() {
        return new ConfigurationManager();
    }

    @Bean
    MockQueue mockQueue() {
        return destinationManager().createQueue("message.queue");
    }

    @Bean
    JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(mockQueueConnectionFactory());
        jmsTemplate.setDefaultDestination(mockQueue());
        return jmsTemplate;
    }

    @Bean
    MockQueueConnectionFactory mockQueueConnectionFactory() {
        return new MockQueueConnectionFactory(destinationManager(), configurationManager());
    }

//    <bean id="destinationManager" class="com.mockrunner.jms.DestinationManager"/>
//    <bean id="configurationManager" class="com.mockrunner.jms.ConfigurationManager"/>
//
//    <bean id="messageDestination" factory-bean="destinationManager" factory-method="createQueue">
//      <constructor-arg value="message.queue"/>
//    </bean>
//
//    <bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
//    <property name="connectionFactory" ref="jmsConnectionFactory"/>
//    <property name="defaultDestination" ref="messageDestination"/>
//    </bean>
//
//    <bean id="jmsConnectionFactory" class="com.mockrunner.mock.jms.MockQueueConnectionFactory" >
//      <constructor-arg ref="destinationManager" />
//      <constructor-arg ref="configurationManager" />
//    </bean>
}
