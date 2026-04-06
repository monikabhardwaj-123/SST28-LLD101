package com.example.cache.storage;

public interface CacheStorage {
    String get(String key);
    void put(String key, String value);
    void remove(String key);
    boolean isFull();
}
