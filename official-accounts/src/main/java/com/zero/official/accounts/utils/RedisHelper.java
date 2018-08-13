package com.zero.official.accounts.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 * 
 * @author yezhaoxing
 * @date 2017/7/17
 */
@Configuration
public class RedisHelper {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, String value, long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime);
    }

    public void expire(String key, long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void clear() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    public Long size() {
        return redisTemplate.getConnectionFactory().getConnection().dbSize();
    }
}
