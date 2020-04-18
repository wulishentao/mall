package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.model.dto.OrderDto;

import java.io.Serializable;

/**
 * @classname: ViewOrderInfoResDto.java
 * @author: Beau
 * @create: 2020/04/18 16:22
 * @version: 1.0.0
 **/
public class ViewOrderInfoResDto extends CommonResDTO implements Serializable {
    private OrderDto orderDto;

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }
}
