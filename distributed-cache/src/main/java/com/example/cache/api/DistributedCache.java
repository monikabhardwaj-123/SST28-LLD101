package com.example.cache.api;

import com.example.cache.service.CacheService;

public class DistributedCache {
    private final CacheService service;

    public DistributedCache(CacheService service) {
        this.service = service;
    }

    public String get(String key) {
        return service.get(key);
    }

    public void put(String key, String value) {
        service.put(key, value);
    }
}
