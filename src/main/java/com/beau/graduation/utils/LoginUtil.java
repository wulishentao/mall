package com.beau.graduation.utils;

import com.alibaba.fastjson.JSON;
import com.beau.graduation.basic.resdto.LoginResDto;
import com.beau.graduation.model.PartnerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @classname: LoginUtil.java
 * @author: Beau
 * @create: 2020/03/12 13:08
 * @version: 1.0.0
 **/
@Component
public class LoginUtil {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取用户缓存
     * @param token
     * @return
     */
    public PartnerInfo getUser(String token) {
        return redisUtil.get(token, PartnerInfo.class);
    }

    /**
     * 存储用户缓存
     * @param pi
     */
    public void setUser(PartnerInfo pi) {
        redisUtil.set(String.valueOf(pi.getId()), JSON.toJSONString(pi));
    }

    /**
     * 存储用户缓存同时设置过期时间
     * @param key
     * @param resDto
     * @param time
     * @param timeUnit
     */
    public void setUser(String key, LoginResDto resDto, Long time, TimeUnit timeUnit) {
        redisUtil.set(key, JSON.toJSONString(resDto), time, timeUnit);
    }

    /**
     * 存储用户缓存同时设置过期时间
     * @param key
     * @param pi
     * @param time
     * @param timeUnit
     */
    public void setUser(String key, PartnerInfo pi, Long time, TimeUnit timeUnit) {
        redisUtil.set(key, JSON.toJSONString(pi),time,timeUnit);
    }

    /**
     * 移除用户缓存
     * @param token
     */
    public void removeUser(String token) {
        redisUtil.delete(token);
    }

    /**
     * 批量移除用户缓存
     * @param tokens
     */
    public void removeUser(List<String> tokens) {
        redisUtil.delete(tokens);
    }

    /**
     * 获取用户缓存过期时间
     * @param token
     * @return
     */
    public Long getUserExpire(String token) {
        return redisUtil.getExpire(token);
    }

    public Long getUserExpire(String token,TimeUnit timeUnit) {
        return redisUtil.getExpire(token,timeUnit);
    }
}
