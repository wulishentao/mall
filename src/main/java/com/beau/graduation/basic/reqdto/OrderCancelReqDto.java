package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: OrderCancelReqDto.java
 * @author: Beau
 * @create: 2020/04/18 14:55
 * @version: 1.0.0
 **/
public class OrderCancelReqDto extends CommonReqDTO implements Serializable {
    private String orderId;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
