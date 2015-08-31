package com.storage.config;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;

public class MongoDbTestConfig extends MongoDbConfig {

    @Override
    protected String getDatabaseName() {
        return "testdb";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new Fongo("fongoInstance").getMongo();
    }
}