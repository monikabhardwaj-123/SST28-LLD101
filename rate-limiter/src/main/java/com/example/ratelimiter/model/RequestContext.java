package com.example.ratelimiter.model;

public class RequestContext {
    private final String key;

    public RequestContext(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
