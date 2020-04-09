package com.beau.graduation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @classname: UploadConfig.java
 * @author: Beau
 * @create: 2020/03/23 19:26
 * @version: 1.0.0
 **/
@Component
@ConfigurationProperties(prefix = "file-service")
public class UploadConfig {
    /**
     * 上传路径
     */
    private static String profile;

    public static String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        UploadConfig.profile = profile;
    }

    /**
     * 获取上传头像路径
     * @return
     */
    public static String getAvatarPath() {
        return profile + "avatar/";
    }

    /**
     * 获取下载路径
     * @return
     */
    public static String getDownloadPath() {
        return profile + "download/";
    }

    /**
     * 获取图书封面上传路径
     * @return
     */
    public static String getBookPicUploadPath() {
        return profile + "book_pic/";
    }

    /**
     * 获取专题封面上传路径
     * @return
     */
    public static String getTopicPicUploadPath() {
        return profile + "topic_pic/";
    }
}
