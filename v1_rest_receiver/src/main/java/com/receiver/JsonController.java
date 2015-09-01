package com.receiver;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Map;

@RestController
public class JsonController {

    private JmsSender jmsSender;

    @Inject
    public JsonController(JmsSender jmsSender) {
        this.jmsSender = jmsSender;
    }

    @RequestMapping(value = "/payload", method = RequestMethod.POST)
    public void receiveJson(@RequestBody Map person) {
        jmsSender.sendJmsMessage(person);
    }
}