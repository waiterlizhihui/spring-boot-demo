package com.waiter.web.config.shiro;


import org.apache.shiro.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName ShiroRedisCache
 * @Description TOOD
 * @Author lizhihui
 * @Date 2019/11/9 17:40
 * @Version 1.0
 */
public class ShiroRedisCache<K, V> extends Cache<K, V> {
    private RedisTemplate redisTemplate;
    private String prefix = "shiro_redis";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public ShiroRedisCache(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public ShiroRedisCache(RedisTemplate redisTemplate, String prefix) {
        this(redisTemplate);
        this.prefix = prefix;
    }

    @Override
    public V get(K k) {
        if (k == null) {
            return null;
        }
        byte[] bytes = getByteKey(k);
        return (V) redisTemplate.opsForValue().get(bytes);

    }

    @Override
    public V put(K k, V v) {
        if (k == null || v == null) {
            return null;
        }

        byte[] bytes = getByteKey(k);
        redisTemplate.opsForValue().set(bytes, v);
        return v;
    }

    @Override
    public V remove(K k) {
        if (k == null) {
            return null;
        }

        byte[] bytes = getByteKey(k);
        V v = (V) redisTemplate.opsForValue().get(bytes);
        redisTemplate.delete(bytes);
        return v;
    }

    @Override
    public void clear() {
        //慎用！！！！！！
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }


    private byte[] getByteKey(K k) {
        if (k instanceof String) {
            String preKey = this.prefix + k;
        } else {
            //目前不知道这个序列化工具是什么
//            return SerializationUtil.toBytes(k);
            return null;
        }
    }
}
