package com.example.cache.eviction.lru;

import com.example.cache.eviction.EvictionPolicy;

import java.util.*;

public class LRUEvictionPolicy implements EvictionPolicy {

    private final LinkedHashSet<String> set = new LinkedHashSet<>();

    public void keyAccessed(String key) {
        set.remove(key);
        set.add(key);
    }

    public String evict() {
        String first = set.iterator().next();
        set.remove(first);
        return first;
    }
}
