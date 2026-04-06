package com.example.cache.database;

import java.util.*;

public class InMemoryDatabase implements Database {

    private final Map<String, String> db = new HashMap<>();

    public String get(String key) {
        return db.getOrDefault(key, "DB-" + key);
    }

    public void put(String key, String value) {
        db.put(key, value);
    }
}
