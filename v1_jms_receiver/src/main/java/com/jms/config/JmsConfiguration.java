package com.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    // Equivalent XML Config
    /*
     <bean id="jsonListener" class="org.springframework.jms.config.DefaultJmsListenerContainerFactory">
        <property name="connectionFactory" ref="jmsConnectionFactory"/>
    </bean>

    <bean id="jmsConnectionFactory" class="org.apache.activemq.ActiveMQConnectionFactory">
        <property name="brokerURL" value="tcp://localhost:61616"/>
    </bean>

    <bean id="message-destination" class="org.apache.activemq.command.ActiveMQQueue">
        <constructor-arg value="message.queue"/>
    </bean>
    */
}
