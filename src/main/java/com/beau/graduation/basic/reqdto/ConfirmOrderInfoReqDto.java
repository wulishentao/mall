package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import com.beau.graduation.model.dto.BookDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @classname: ConfirmOrderInfoReqDto.java
 * @author: Beau
 * @create: 2020/04/25 14:25
 * @version: 1.0.0
 **/
@Data
public class ConfirmOrderInfoReqDto extends CommonReqDTO implements Serializable {

    /**
     * 书籍订单集合
     */
    private List<BookDto> bookDtoList;

    /**
     * 订单来源(0为直接购买,1为购物车结算)
     */
    private String origin;
}
