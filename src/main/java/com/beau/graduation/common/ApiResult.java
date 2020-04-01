package com.beau.graduation.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Api响应类
 * ApiResult.java
 * @author beau
 * @date 2020/03/28
 */
public class ApiResult<T> implements Serializable {

    /**
     * 状态码
     */
    private String code;

    /**
     * 数据
     */
    private T data;

    /**
     * 错误信息
     */
    private String msg;

    /***
     * 时间
     */
    private String time;

    /***
     * 地址
     */
    private String path;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ApiResult(String code, T data, String msg, String path) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.path = path;
        this.time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public ApiResult() {
    }
}