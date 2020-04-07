package com.beau.graduation.Enum;

/**
 * @classname: SaleStatusEnum.java
 * @author: Beau
 * @create: 2020/04/07 14:14
 * @version: 1.0.0
 **/
public enum SaleStatusEnum {
    /**
     * 已下架
     */
    removed ("-1", "已下架"),

    /**
     * 在售
     */
    in_stock ("0", "在售"),

    /**
     * 售罄
     */
    sold_out ("1", "售罄")
    ;

    private String code;

    private String descr;

    SaleStatusEnum(String code, String descr) {
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
