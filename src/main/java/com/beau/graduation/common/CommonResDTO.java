package com.beau.graduation.common;

/**
 * response基础类
 * CommonResDTO.java
 * @author beau
 * @date 2020/03/28
 */
public class CommonResDTO {

    /**
     * 状态码
     */
    private String code;


    /**
     * 错误信息
     */
    private String msg;

    /***
     * 时间
     */
    private String time;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

}