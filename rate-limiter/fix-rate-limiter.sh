
# RateLimiterApplication
cat > src/main/java/com/example/ratelimiter/RateLimiterApplication.java << 'EOL'
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
            System.out.println(rateLimiter.allow(context));
        }
    }
}
EOL

# RateLimiter
cat > src/main/java/com/example/ratelimiter/api/RateLimiter.java << 'EOL'
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
EOL

# RequestContext
cat > src/main/java/com/example/ratelimiter/model/RequestContext.java << 'EOL'
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
EOL

# RateLimitConfig
cat > src/main/java/com/example/ratelimiter/model/RateLimitConfig.java << 'EOL'
package com.example.ratelimiter.model;

public class RateLimitConfig {
    private final int limit;
    private final long windowSizeInMillis;

    public RateLimitConfig(int limit, long windowSizeInMillis) {
        this.limit = limit;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    public int getLimit() {
        return limit;
    }

    public long getWindowSizeInMillis() {
        return windowSizeInMillis;
    }
}
EOL

# Strategy Interface
cat > src/main/java/com/example/ratelimiter/strategy/RateLimitStrategy.java << 'EOL'
package com.example.ratelimiter.strategy;

import com.example.ratelimiter.model.RateLimitConfig;

public interface RateLimitStrategy {
    boolean allow(String key, RateLimitConfig config);
}
EOL

# FixedWindowStrategy
cat > src/main/java/com/example/ratelimiter/strategy/fixedwindow/FixedWindowStrategy.java << 'EOL'
package com.example.ratelimiter.strategy.fixedwindow;

import com.example.ratelimiter.model.RateLimitConfig;
import com.example.ratelimiter.strategy.RateLimitStrategy;

import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowStrategy implements RateLimitStrategy {

    private final ConcurrentHashMap<String, Integer> counter = new ConcurrentHashMap<>();

    @Override
    public boolean allow(String key, RateLimitConfig config) {
        counter.putIfAbsent(key, 0);

        if (counter.get(key) < config.getLimit()) {
            counter.put(key, counter.get(key) + 1);
            return true;
        }
        return false;
    }
}
EOL

# ConfigProvider
cat > src/main/java/com/example/ratelimiter/config/InMemoryConfigProvider.java << 'EOL'
package com.example.ratelimiter.config;

import com.example.ratelimiter.model.RateLimitConfig;

public class InMemoryConfigProvider {
    public RateLimitConfig getConfig(String key) {
        return new RateLimitConfig(5, 60000);
    }
}
EOL

# Service
cat > src/main/java/com/example/ratelimiter/service/RateLimiterService.java << 'EOL'
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
EOL

echo "✅ Files fixed!"

