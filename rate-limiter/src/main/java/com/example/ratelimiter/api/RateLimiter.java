package com.example.ratelimiter.api;

import com.example.ratelimiter.model.RequestContext;
import com.example.ratelimiter.service.RateLimiterService;

public class RateLimiter {
    private final RateLimiterService service;

    public RateLimiter(RateLimiterService service) {
        this.service = service;
    }

    public boolean allow(RequestContext context) {
        return service.isAllowed(context);
    }
}
