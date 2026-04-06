package com.example.cache.strategy;

public interface DistributionStrategy {
    int getNodeIndex(String key, int totalNodes);
}
