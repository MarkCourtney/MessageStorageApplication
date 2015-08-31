package com.storage;

import com.storage.config.MongoDbTestConfig;
import com.storage.config.repository.JsonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MongoDbTestConfig.class})
public class MongoDbTests {

    private MongoDB mongoDB;

    @Inject
    private JsonRepository jsonRepository;

    @Before
    public void setup() {
        mongoDB = new MongoDB(jsonRepository);
    }

    @Test
    public void recordsShouldBeInserted() {
        jsonRepository.deleteAll();

        Assert.isTrue(jsonRepository.count() == 0);
        insertRecords(10);
        Assert.isTrue(jsonRepository.count() == 10);
        insertRecords(10);
        Assert.isTrue(jsonRepository.count() == 20);
    }

    @Test
    public void recordsInsertShouldFail() {
        jsonRepository.deleteAll();

        Map<String, String> map = null;

        boolean insert = mongoDB.insertRecord(map);

        Assert.isTrue(jsonRepository.count() == 0);
        Assert.isTrue(!insert);
    }

    private void insertRecords(int totalRecords) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("A", "1");

        for(int i = 0; i < totalRecords; i++) {
            mongoDB.insertRecord(map);
        }
    }
}
