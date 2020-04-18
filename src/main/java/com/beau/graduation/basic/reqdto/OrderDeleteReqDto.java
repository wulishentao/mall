package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @classname: OrderDeleteReqDto.java
 * @author: Beau
 * @create: 2020/04/18 15:35
 * @version: 1.0.0
 **/
public class OrderDeleteReqDto extends CommonReqDTO implements Serializable {
    private List<String> orderIds;

    public List<String> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }
}
