package com.beau.graduation.Enum;

/**
 * @classname: ReviewStatusEnum.java
 * @author: Beau
 * @create: 2020/04/07 14:18
 * @version: 1.0.0
 **/
public enum ReviewStatusEnum {
    /**
     * 审核未通过
     */
    failed ("-1", "审核未通过"),

    /**
     * 未审核
     */
    unreviewed("0", "未审核"),

    /**
     * 审核通过
     */
    passed ("1", "审核通过")
    ;

    private String code;

    private String descr;

    ReviewStatusEnum(String code, String descr) {
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
