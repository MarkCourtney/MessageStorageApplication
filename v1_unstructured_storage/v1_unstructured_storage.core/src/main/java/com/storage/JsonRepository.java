package com.storage;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

public interface JsonRepository extends MongoRepository<Map, String>{}