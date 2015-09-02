package com.receiver.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JmsConfiguration {

    private final String queueName = "message.queue";

    @Bean
    ActiveMQQueue activeMqQueue() {
        return new ActiveMQQueue(queueName);
    }

    @Bean
    ActiveMQConnectionFactory activeMqConnectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean
    JmsTemplate jmsTemplate() {//ConnectionFactory connectionFactory, Destination destination) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMqConnectionFactory());
        jmsTemplate.setDefaultDestination(activeMqQueue());
        return jmsTemplate;
    }

//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, Queue amqpQueue, MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueues(amqpQueue);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }

//    <bean id="jmsTemplate" class="org.springframework.jms.core.JmsTemplate">
//    <property name="connectionFactory" ref="jmsConnectionFactory"/>
//    <property name="defaultDestination" ref="messageDestination"/>
//    </bean>

//    <bean id="messageDestination" class="org.apache.activemq.command.ActiveMQQueue">
//    <constructor-arg value="message.queue"/>
//    </bean>
//
//    <bean id="jmsConnectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
//    <property name="brokerURL" value="tcp://localhost:61616"/>
//    </bean>
}
