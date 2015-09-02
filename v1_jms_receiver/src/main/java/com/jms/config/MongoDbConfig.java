package com.jms.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoDbConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "db";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.jms";
    }
}