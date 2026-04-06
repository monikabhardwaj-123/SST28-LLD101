package com.example.cache.node;

import com.example.cache.eviction.EvictionPolicy;
import com.example.cache.storage.CacheStorage;

public class CacheNode {
    private final CacheStorage storage;
    private final EvictionPolicy evictionPolicy;

    public CacheNode(CacheStorage storage, EvictionPolicy evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public String get(String key) {
        return storage.get(key);
    }

    public void put(String key, String value) {
        if (storage.isFull()) {
            String evictKey = evictionPolicy.evict();
            storage.remove(evictKey);
        }
        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
    }
}
