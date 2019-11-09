package com.waiter.web.config.shiro;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName ShiroRedisCacheManager
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/11/9 17:37
 * @Version 1.0
 */
public class ShiroRedisCacheManager extends AbstractCacheManager {
    private RedisTemplate<byte[], byte[]> redisTemplate;

    public ShiroRedisCacheManager(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected Cache createCache(String name) {
        return new
    }
}
