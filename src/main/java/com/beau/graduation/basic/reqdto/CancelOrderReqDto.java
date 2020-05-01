package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: CancelOrderReqDto.java
 * @author: Beau
 * @create: 2020/04/29 18:01
 * @version: 1.0.0
 **/
@Data
public class CancelOrderReqDto extends CommonReqDTO implements Serializable {
    /**
     * 订单id
     */
    private String orderId;
}
