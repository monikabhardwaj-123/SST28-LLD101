package com.example.cache;

import com.example.cache.api.DistributedCache;
import com.example.cache.config.CacheConfig;
import com.example.cache.database.InMemoryDatabase;
import com.example.cache.eviction.lru.LRUEvictionPolicy;
import com.example.cache.node.CacheNode;
import com.example.cache.service.CacheService;
import com.example.cache.storage.inmemory.InMemoryCacheStorage;
import com.example.cache.strategy.modulo.ModuloStrategy;

import java.util.*;

public class CacheApplication {
    public static void main(String[] args) {
        List<CacheNode> nodes = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            nodes.add(new CacheNode(
                    new InMemoryCacheStorage(2),
                    new LRUEvictionPolicy()
            ));
        }

        CacheConfig config = new CacheConfig(new ModuloStrategy(), nodes);
        CacheService service = new CacheService(config, new InMemoryDatabase());
        DistributedCache cache = new DistributedCache(service);

        cache.put("a", "1");
        cache.put("b", "2");
        cache.put("c", "3");

        System.out.println(cache.get("a"));
        System.out.println(cache.get("b"));
        System.out.println(cache.get("c"));
    }
}
