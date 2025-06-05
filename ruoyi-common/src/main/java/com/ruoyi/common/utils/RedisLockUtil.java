package com.ruoyi.common.utils;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisLockUtil {
    private final StringRedisTemplate redisTemplate;
    
    public RedisLockUtil(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 尝试获取分布式锁
     * @param lockKey 锁键
     * @param lockValue 锁值
     * @param expireTime 过期时间
     * @param timeUnit 时间单位
     * @return 是否获取成功
     */
    public boolean tryLock(String lockKey, String lockValue, long expireTime, TimeUnit timeUnit) {
        return Boolean.TRUE.equals(
            redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, expireTime, timeUnit)
        );
    }

    /**
     * 释放分布式锁
     * @param lockKey 锁键
     * @param lockValue 锁值（用于验证是否是当前持有者）
     * @return 是否释放成功
     */
    public boolean releaseLock(String lockKey, String lockValue) {
        String currentValue = redisTemplate.opsForValue().get(lockKey);
        if (lockValue.equals(currentValue)) {
            return Boolean.TRUE.equals(redisTemplate.delete(lockKey));
        }
        return false;
    }
} 