package com.example.cache.config;

import com.example.cache.node.CacheNode;
import com.example.cache.strategy.DistributionStrategy;

import java.util.List;

public class CacheConfig {
    private final DistributionStrategy strategy;
    private final List<CacheNode> nodes;

    public CacheConfig(DistributionStrategy strategy, List<CacheNode> nodes) {
        this.strategy = strategy;
        this.nodes = nodes;
    }

    public CacheNode getNode(String key) {
        int index = strategy.getNodeIndex(key, nodes.size());
        return nodes.get(index);
    }
}
