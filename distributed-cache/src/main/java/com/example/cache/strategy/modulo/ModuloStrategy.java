package com.example.cache.strategy.modulo;

import com.example.cache.strategy.DistributionStrategy;

public class ModuloStrategy implements DistributionStrategy {
    public int getNodeIndex(String key, int totalNodes) {
        return Math.abs(key.hashCode()) % totalNodes;
    }
}
