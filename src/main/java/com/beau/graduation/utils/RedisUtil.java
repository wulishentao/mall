package com.beau.graduation.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @classname: RedisUtil.java
 * @author: Beau
 * @create: 2020/03/12 12:35
 * @version: 1.0.0
 **/
@Component
public class RedisUtil {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 存储信息
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储信息且设置过期时间
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     */
    public void set(String key, String value, Long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    /**
     * 删除key
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除
     * @param keys
     */
    public void delete(List<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 重命名key
     * @param oldKey
     * @param newKey
     */
    public void reName(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 获取
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> T get(String key,Class<T> tClass) {
        return JSON.parseObject(redisTemplate.opsForValue().get(key), tClass);
    }

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取过期时间
     * @param key
     * @return
     */
    public Long getExpire(String key,TimeUnit timeUnit) {
        return redisTemplate.getExpire(key,timeUnit);
    }

    /**
     * 给key设置新值value且返回旧值
     * @param key
     * @param value
     * @return
     */
    public String getAndSet(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 是否存在key
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 增加(自增长), 负数则为自减
     */
    public Long incrBy(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 自增长1
     */
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    /**
     * 增加(自增长), 负数则为自减  浮点数
     */
    public Double incrByFloat(String key, double increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }
}
