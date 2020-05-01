package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: SubmitOrderResDto.java
 * @author: Beau
 * @create: 2020/04/28 12:06
 * @version: 1.0.0
 **/
@Data
public class SubmitOrderResDto extends CommonResDTO implements Serializable {
    /**
     * 订单号
     */
    private String orderId;
}
