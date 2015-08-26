package com.receiver;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:jms-message-producer.xml")
public class JsonControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JmsSender jmsSender;

    private JsonController jsonController;

    private MockMvc mockMvc;

    private JSONObject jsonObject;
    private String jsonString;

    @Before
    public void setup() {
        jsonController = new JsonController(webApplicationContext, jmsSender);
        mockMvc = MockMvcBuilders.standaloneSetup(jsonController).build();
    }

    @Test
    public void requestWithCorrectData() throws Exception {
        jsonObject = new JSONObject();
        jsonObject.put("firstName", "Mark");
        postJson(jsonObject);

        jsonObject = new JSONObject();
        jsonObject.put("firstName", "Mark");
        jsonObject.put("lastName", "Courtney");
        jsonObject.put("firstName", "Mark");
        postJson(jsonObject);

        jsonObject = new JSONObject();
        postJson(jsonObject);
    }

    @Test
    public void requestWithMalformedData() throws Exception {
        jsonString = new String("{\'firstName\':\'John\'}");
        postJson(jsonString);

        jsonString = new String("{firstName:John}");
        postJson(jsonString);
    }

    public void postJson(JSONObject jsonObject) throws Exception {
        mockMvc.perform(post("/payload")
                .content(jsonObject.toJSONString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public void postJson(String jsonString) throws Exception {
        mockMvc.perform(post("/payload")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }
}