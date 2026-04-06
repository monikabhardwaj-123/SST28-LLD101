package com.example.ratelimiter.config;

import com.example.ratelimiter.model.RateLimitConfig;

public class InMemoryConfigProvider {
    public RateLimitConfig getConfig(String key) {
        return new RateLimitConfig(5, 60000);
    }
}
