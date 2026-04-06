package com.example.ratelimiter;

import com.example.ratelimiter.api.RateLimiter;
import com.example.ratelimiter.config.InMemoryConfigProvider;
import com.example.ratelimiter.model.RequestContext;
import com.example.ratelimiter.service.RateLimiterService;
import com.example.ratelimiter.strategy.fixedwindow.FixedWindowStrategy;

public class RateLimiterApplication {

    public static void main(String[] args) {
        RateLimiterService service = new RateLimiterService(
                new FixedWindowStrategy(),
                new InMemoryConfigProvider()
        );

        RateLimiter rateLimiter = new RateLimiter(service);

        RequestContext context = new RequestContext("tenant:T1");

        for (int i = 0; i < 10; i++) {
            boolean allowed = rateLimiter.allow(context);
            System.out.println("Request " + i + " allowed: " + allowed);
        }
    }
}