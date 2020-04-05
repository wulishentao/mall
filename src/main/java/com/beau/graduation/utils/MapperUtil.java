package com.beau.graduation.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * @classname: MapperUtil.java
 * @author: Beau
 * @create: 2020/04/04 21:47
 * @version: 1.0.0
 **/
public class MapperUtil {
    public static boolean isNotEmpty(Object obj){
        if(obj instanceof String){
            return StringUtils.isNotBlank((String)obj);
        }else if(obj instanceof Boolean){
            return (Boolean)obj;
        } else if (obj instanceof Collection) {
            return ((Collection) obj).size() > 0;
        } else{
            return obj != null;
        }
    }
}
