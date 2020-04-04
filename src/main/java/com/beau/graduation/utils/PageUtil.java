package com.beau.graduation.utils;

import java.util.HashMap;

/**
 * 获取分页开始索引
 * @classname: PageUtil.java
 * @author: Beau
 * @create: 2020/04/03 17:09
 * @version: 1.0.0
 **/
public class PageUtil {
    public static HashMap<String, Integer> getBeginAndSize(Integer pageNo, Integer pageSize) {
        HashMap<String, Integer> map = new HashMap<>(5);
        int beginIndex = (pageNo - 1) * pageSize;
        map.put("begin", beginIndex);
        map.put("size", pageSize);
        return map;
    }
}
