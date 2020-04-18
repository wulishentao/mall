package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: ViewOrderInfoReqDto.java
 * @author: Beau
 * @create: 2020/04/18 16:22
 * @version: 1.0.0
 **/
public class ViewOrderInfoReqDto extends CommonReqDTO implements Serializable {
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
