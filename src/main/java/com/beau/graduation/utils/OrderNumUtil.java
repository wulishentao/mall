package com.beau.graduation.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @classname: OrderNumUtil.java
 * @author: Beau
 * @create: 2020/03/27 14:23
 * @version: 1.0.0
 **/
@Component
public class OrderNumUtil {
    @Autowired
    private RedisUtil redisUtil;

    public Long getOrderNum() {
        String dateTime = DateUtil.dateTime();
        if (redisUtil.hasKey(dateTime)) {
            return redisUtil.incr(dateTime);
        } else {
            String value = dateTime + "00001";
            redisUtil.set(dateTime, value, 1L, TimeUnit.DAYS);
            return Long.valueOf(value);
        }
    }
}
