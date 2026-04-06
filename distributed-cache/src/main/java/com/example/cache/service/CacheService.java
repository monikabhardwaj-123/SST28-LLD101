package com.example.cache.service;

import com.example.cache.config.CacheConfig;
import com.example.cache.database.Database;
import com.example.cache.node.CacheNode;

public class CacheService {

    private final CacheConfig config;
    private final Database database;

    public CacheService(CacheConfig config, Database database) {
        this.config = config;
        this.database = database;
    }

    public String get(String key) {
        CacheNode node = config.getNode(key);
        String value = node.get(key);

        if (value == null) {
            value = database.get(key);
            node.put(key, value);
        }

        return value;
    }

    public void put(String key, String value) {
        CacheNode node = config.getNode(key);
        node.put(key, value);
        database.put(key, value);
    }
}
