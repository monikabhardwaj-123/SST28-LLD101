package com.example.ratelimiter.strategy;

import com.example.ratelimiter.model.RateLimitConfig;

public interface RateLimitStrategy {
    boolean allow(String key, RateLimitConfig config);
}
