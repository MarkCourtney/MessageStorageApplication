package com.receiver;

import com.mockrunner.mock.jms.MockMapMessage;
import com.mockrunner.mock.jms.MockQueue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.inject.Inject;
import javax.jms.JMSException;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mockApplicationContext.xml")
public class JmsSenderTests {

    private JmsSender jmsSender;

    @Inject
    private JmsTemplate jmsTemplate;

    @Inject
    private MockQueue messageDestination;

    @Before
    public void setup() {
        jmsSender = new JmsSender(jmsTemplate);
    }

    @Test
    public void sendJmsMap() throws JMSException {
        Map<String, String> map;

        map = new LinkedHashMap<String, String>();
        map.put("firstName", "Mark");
        map.put("lastName", "Courtney");
        jmsSender.sendJmsMessage(map);

        Assert.isTrue(messageDestination.getCurrentMessageList().size() == 1);
        Assert.isTrue(getQueueMap().equals(map));


        map = new LinkedHashMap<String, String>();
        map.put("firstName", "Mark");
        jmsSender.sendJmsMessage(map);

        Assert.isTrue(messageDestination.getCurrentMessageList().size() == 1);
        Assert.isTrue(getQueueMap().equals(map));


        map = new LinkedHashMap<String, String>();
        map.put("firstName", "Mark");
        Map<String, String> emptyMap = new LinkedHashMap<>();
        jmsSender.sendJmsMessage(map);
        jmsSender.sendJmsMessage(emptyMap);

        Assert.isTrue(messageDestination.getCurrentMessageList().size() == 2);
        Assert.isTrue(getQueueMap().equals(map));
        Assert.isTrue(getQueueMap().equals(emptyMap));
    }

    @Test
    public void sendJmsNullMap() throws JMSException {
        Map<String, String> map = null;
        jmsSender.sendJmsMessage(map);
        Assert.isTrue(messageDestination.isEmpty());
    }

    private Map<String, String> getQueueMap() {
        MockMapMessage mockMapMessage = (MockMapMessage) jmsSender.getTemplate().receive(messageDestination);
        return mockMapMessage.getMap();
    }
}