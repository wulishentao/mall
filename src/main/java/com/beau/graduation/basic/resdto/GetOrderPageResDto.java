package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.dto.OrderDto;

import java.io.Serializable;

/**
 * @classname: GetOrderPageResDto.java
 * @author: Beau
 * @create: 2020/04/13 17:22
 * @version: 1.0.0
 **/
public class GetOrderPageResDto extends CommonResDTO implements Serializable {
    private Page<OrderDto> page;

    public Page<OrderDto> getPage() {
        return page;
    }

    public void setPage(Page<OrderDto> page) {
        this.page = page;
    }
}
