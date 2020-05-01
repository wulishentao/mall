package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: ConfirmOrderReqDto.java
 * @author: Beau
 * @create: 2020/04/30 10:46
 * @version: 1.0.0
 **/
@Data
public class ConfirmOrderReqDto extends CommonReqDTO implements Serializable {
    private String orderId;
}
