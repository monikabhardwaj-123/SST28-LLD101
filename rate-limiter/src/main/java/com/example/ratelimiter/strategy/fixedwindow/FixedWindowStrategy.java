package com.example.ratelimiter.strategy.fixedwindow;

import com.example.ratelimiter.model.RateLimitConfig;
import com.example.ratelimiter.strategy.RateLimitStrategy;

import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowStrategy implements RateLimitStrategy {

    private final ConcurrentHashMap<String, Integer> counter = new ConcurrentHashMap<>();

    @Override
    public boolean allow(String key, RateLimitConfig config) {
        counter.putIfAbsent(key, 0);

        if (counter.get(key) < config.getLimit()) {
            counter.put(key, counter.get(key) + 1);
            return true;
        }
        return false;
    }
}
