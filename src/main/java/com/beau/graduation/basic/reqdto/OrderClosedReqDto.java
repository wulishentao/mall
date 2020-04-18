package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import com.beau.graduation.model.OperateOrder;

import java.io.Serializable;
import java.util.List;

/**
 * @classname: OrderClosedReqDto.java
 * @author: Beau
 * @create: 2020/04/18 14:43
 * @version: 1.0.0
 **/
public class OrderClosedReqDto extends CommonReqDTO implements Serializable {
    private List<OperateOrder> operateOrders;

    public List<OperateOrder> getOperateOrders() {
        return operateOrders;
    }

    public void setOperateOrders(List<OperateOrder> operateOrders) {
        this.operateOrders = operateOrders;
    }
}
