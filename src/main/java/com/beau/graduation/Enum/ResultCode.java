package com.beau.graduation.Enum;

/***
 * 返回码
 */
public enum ResultCode {

    /**
    * 请求响应成功
    */
    SUCCESS ("1", "请求响应成功"),

    /**
    * 请求响应失败
    */
    FAILED ("0", "请求响应失败"),

    /**
     * 需登录后才能进行操作
     */
    LOGIN_REQUIRED("999","操作需登录"),

    ;

    private String code;

    private String descr;

    ResultCode(String code, String descr) {
        this.code = code;
        this.descr = descr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}