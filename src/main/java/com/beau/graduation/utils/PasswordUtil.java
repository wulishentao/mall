package com.beau.graduation.utils;

import com.alibaba.fastjson.JSON;
import com.beau.graduation.model.PartnerInfo;
import org.springframework.util.DigestUtils;

/**
 * @classname: PasswordUtil.java
 * @author: Beau
 * @create: 2020/03/15 15:18
 * @version: 1.0.0
 **/
public class PasswordUtil {
    public static String Md5Hex(PartnerInfo partnerInfo) {
        return DigestUtils.md5DigestAsHex(partnerInfo.getPassword().getBytes());
    }

    public static String Md5Hex(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    public static void main(String[] args) {
        PartnerInfo pi = new PartnerInfo();
        System.out.println(Md5Hex("123456789"));
    }
}
