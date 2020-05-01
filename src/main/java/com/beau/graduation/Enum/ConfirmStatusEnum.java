package com.beau.graduation.Enum;

/**
 * @classname: ConfirmStatusEnum.java
 * @author: Beau
 * @create: 2020/04/17 16:33
 * @version: 1.0.0
 **/
public enum ConfirmStatusEnum {
    /**
     * 已关闭
     */
    closed ("-1", "已关闭"),

    /**
     * 订单待付款
     */
    pending_pay ("0", "待付款"),

    /**
     * 订单代发货
     */
    pending_delivered ("1", "待发货"),

    /**
     * 已发货
     */
    shipped ("2", "已发货"),

    /**
     * 已签收
     */
    received("3", "已签收"),

    /**
     * 已评价
     */
    commented("4", "已评价")
    ;

    private String code;

    private String descr;

    ConfirmStatusEnum(String code, String descr) {
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
