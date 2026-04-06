package com.example.cache.eviction;

public interface EvictionPolicy {
    void keyAccessed(String key);
    String evict();
}
