package com.receiver;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JsonControllerTests {

    @Inject
    private WebApplicationContext webApplicationContext;

    @Inject
    private JmsSender jmsSender;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        JsonController jsonController = new JsonController(jmsSender);
        mockMvc = MockMvcBuilders.standaloneSetup(jsonController).build();
    }

    @Test
    public void requestWithCorrectData() throws Exception {
        JSONObject jsonObject;

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
        postJson("{\'firstName\':\'John\'}");

        postJson("{firstName:John}");

        postJson("");
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