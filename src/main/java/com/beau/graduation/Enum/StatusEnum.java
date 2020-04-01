package com.beau.graduation.Enum;

/**
 * @classname: StatusEnum.java
 * @author: Beau
 * @create: 2020/03/30 18:10
 * @version: 1.0.0
 **/
public enum StatusEnum {

    /**
     * 启用
     */
    enable ("1", "启用"),

    /**
     * 禁用
     */
    disable ("0", "禁用")
    ;

    private String code;

    private String descr;

    StatusEnum(String code, String descr) {
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
