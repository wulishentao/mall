package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.model.PartnerInfo;
import com.beau.graduation.model.dto.BookDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @classname: ConfirmOrderInfoResDto.java
 * @author: Beau
 * @create: 2020/04/25 14:26
 * @version: 1.0.0
 **/
@Data
public class ConfirmOrderInfoResDto extends CommonResDTO implements Serializable {
    /**
     * 收货用户信息
     */
    private PartnerInfo user;

    /**
     * 确认订单中的书籍
     */
    private List<BookDto> bookDtoList;

    /**
     * 订单来源(0为商品详情页面直接购买,1为购物车页面结算)
     */
    private String origin;
}
