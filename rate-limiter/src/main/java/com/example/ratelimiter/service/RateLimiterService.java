package com.example.ratelimiter.service;

import com.example.ratelimiter.config.InMemoryConfigProvider;
import com.example.ratelimiter.model.RequestContext;
import com.example.ratelimiter.model.RateLimitConfig;
import com.example.ratelimiter.strategy.RateLimitStrategy;

public class RateLimiterService {

    private final RateLimitStrategy strategy;
    private final InMemoryConfigProvider configProvider;

    public RateLimiterService(RateLimitStrategy strategy,
                              InMemoryConfigProvider configProvider) {
        this.strategy = strategy;
        this.configProvider = configProvider;
    }

    public boolean isAllowed(RequestContext context) {
        RateLimitConfig config = configProvider.getConfig(context.getKey());
        return strategy.allow(context.getKey(), config);
    }
}
