package com.qgsg.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public final class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param key
     * @param value
     * @return
     */
    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }
}
