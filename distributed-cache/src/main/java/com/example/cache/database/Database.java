package com.example.cache.database;

public interface Database {
    String get(String key);
    void put(String key, String value);
}
