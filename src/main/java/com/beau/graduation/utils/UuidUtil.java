package com.beau.graduation.utils;


import com.alibaba.fastjson.JSON;

import java.util.Date;
import java.util.UUID;

/**
 * @classname: UuidUtil.java
 * @author: Beau
 * @create: 2020/03/23 19:47
 * @version: 1.0.0
 **/
public class UuidUtil {
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(getUuid());
        System.out.println(DateUtil.getTime());
    }
}
