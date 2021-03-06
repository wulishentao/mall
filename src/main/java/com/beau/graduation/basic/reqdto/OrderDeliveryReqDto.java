package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import com.beau.graduation.model.OperateOrder;

import java.io.Serializable;
import java.util.List;

/**
 * @classname: OrderDeliveryReqDto.java
 * @author: Beau
 * @create: 2020/04/17 15:42
 * @version: 1.0.0
 **/
public class OrderDeliveryReqDto extends CommonReqDTO implements Serializable {
    private List<OperateOrder> operateOrders;

    public List<OperateOrder> getOperateOrders() {
        return operateOrders;
    }

    public void setOperateOrders(List<OperateOrder> operateOrders) {
        this.operateOrders = operateOrders;
    }
}
