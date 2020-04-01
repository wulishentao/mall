package com.beau.graduation.Enum;

/**
 * @classname: LoginTypeEnum.java
 * @author: Beau
 * @create: 2020/03/29 12:52
 * @version: 1.0.0
 **/
public enum LoginTypeEnum {
    /**
     * 普通用户
     */
    USER ("1", "普通用户"),

    /**
     * 管理员
     */
    ADMIN ("0", "管理员")
    ;

    private String code;

    private String descr;

    LoginTypeEnum(String code, String descr) {
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
