#!/bin/bash

# CacheApplication
cat > src/main/java/com/example/cache/CacheApplication.java << 'EOF'
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
EOF

# DistributedCache
cat > src/main/java/com/example/cache/api/DistributedCache.java << 'EOF'
package com.example.cache.api;

import com.example.cache.service.CacheService;

public class DistributedCache {
    private final CacheService service;

    public DistributedCache(CacheService service) {
        this.service = service;
    }

    public String get(String key) {
        return service.get(key);
    }

    public void put(String key, String value) {
        service.put(key, value);
    }
}
EOF

# CacheNode
cat > src/main/java/com/example/cache/node/CacheNode.java << 'EOF'
package com.example.cache.node;

import com.example.cache.eviction.EvictionPolicy;
import com.example.cache.storage.CacheStorage;

public class CacheNode {
    private final CacheStorage storage;
    private final EvictionPolicy evictionPolicy;

    public CacheNode(CacheStorage storage, EvictionPolicy evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public String get(String key) {
        return storage.get(key);
    }

    public void put(String key, String value) {
        if (storage.isFull()) {
            String evictKey = evictionPolicy.evict();
            storage.remove(evictKey);
        }
        storage.put(key, value);
        evictionPolicy.keyAccessed(key);
    }
}
EOF

# CacheService
cat > src/main/java/com/example/cache/service/CacheService.java << 'EOF'
package com.example.cache.service;

import com.example.cache.config.CacheConfig;
import com.example.cache.database.Database;
import com.example.cache.node.CacheNode;

public class CacheService {

    private final CacheConfig config;
    private final Database database;

    public CacheService(CacheConfig config, Database database) {
        this.config = config;
        this.database = database;
    }

    public String get(String key) {
        CacheNode node = config.getNode(key);
        String value = node.get(key);

        if (value == null) {
            value = database.get(key);
            node.put(key, value);
        }

        return value;
    }

    public void put(String key, String value) {
        CacheNode node = config.getNode(key);
        node.put(key, value);
        database.put(key, value);
    }
}
EOF

# CacheConfig
cat > src/main/java/com/example/cache/config/CacheConfig.java << 'EOF'
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
EOF

# ModuloStrategy
cat > src/main/java/com/example/cache/strategy/modulo/ModuloStrategy.java << 'EOF'
package com.example.cache.strategy.modulo;

import com.example.cache.strategy.DistributionStrategy;

public class ModuloStrategy implements DistributionStrategy {
    public int getNodeIndex(String key, int totalNodes) {
        return Math.abs(key.hashCode()) % totalNodes;
    }
}
EOF

# DistributionStrategy
cat > src/main/java/com/example/cache/strategy/DistributionStrategy.java << 'EOF'
package com.example.cache.strategy;

public interface DistributionStrategy {
    int getNodeIndex(String key, int totalNodes);
}
EOF

# Storage
cat > src/main/java/com/example/cache/storage/CacheStorage.java << 'EOF'
package com.example.cache.storage;

public interface CacheStorage {
    String get(String key);
    void put(String key, String value);
    void remove(String key);
    boolean isFull();
}
EOF

# InMemoryStorage
cat > src/main/java/com/example/cache/storage/inmemory/InMemoryCacheStorage.java << 'EOF'
package com.example.cache.storage.inmemory;

import com.example.cache.storage.CacheStorage;

import java.util.*;

public class InMemoryCacheStorage implements CacheStorage {

    private final int capacity;
    private final Map<String, String> map = new HashMap<>();

    public InMemoryCacheStorage(int capacity) {
        this.capacity = capacity;
    }

    public String get(String key) {
        return map.get(key);
    }

    public void put(String key, String value) {
        map.put(key, value);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public boolean isFull() {
        return map.size() >= capacity;
    }
}
EOF

# EvictionPolicy
cat > src/main/java/com/example/cache/eviction/EvictionPolicy.java << 'EOF'
package com.example.cache.eviction;

public interface EvictionPolicy {
    void keyAccessed(String key);
    String evict();
}
EOF

# LRU
cat > src/main/java/com/example/cache/eviction/lru/LRUEvictionPolicy.java << 'EOF'
package com.example.cache.eviction.lru;

import com.example.cache.eviction.EvictionPolicy;

import java.util.*;

public class LRUEvictionPolicy implements EvictionPolicy {

    private final LinkedHashSet<String> set = new LinkedHashSet<>();

    public void keyAccessed(String key) {
        set.remove(key);
        set.add(key);
    }

    public String evict() {
        String first = set.iterator().next();
        set.remove(first);
        return first;
    }
}
EOF

# Database
cat > src/main/java/com/example/cache/database/Database.java << 'EOF'
package com.example.cache.database;

public interface Database {
    String get(String key);
    void put(String key, String value);
}
EOF

cat > src/main/java/com/example/cache/database/InMemoryDatabase.java << 'EOF'
package com.example.cache.database;

import java.util.*;

public class InMemoryDatabase implements Database {

    private final Map<String, String> db = new HashMap<>();

    public String get(String key) {
        return db.getOrDefault(key, "DB-" + key);
    }

    public void put(String key, String value) {
        db.put(key, value);
    }
}
EOF

echo "✅ Distributed Cache fixed!"
