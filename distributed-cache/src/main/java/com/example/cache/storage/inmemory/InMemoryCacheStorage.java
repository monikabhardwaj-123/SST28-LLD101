package com.example.cache.storage.inmemory;

import com.example.cache.storage.CacheStorage;

import java.util.*;

public class InMemoryCacheStorage implements CacheStorage {

    private final int capacity;
    private final Map<String, String> map = new HashMap<>();

    public InMemoryCacheStorage(int capacity) {
        this.capacity = capacity;
    }

    public String get(String key) {
        return map.get(key);
    }

    public void put(String key, String value) {
        map.put(key, value);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public boolean isFull() {
        return map.size() >= capacity;
    }
}
