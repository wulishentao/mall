package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import com.beau.graduation.model.dto.BookDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @classname: SubmitOrderReqDto.java
 * @author: Beau
 * @create: 2020/04/28 12:06
 * @version: 1.0.0
 **/
@Data
public class SubmitOrderReqDto extends CommonReqDTO implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单中的全部书籍
     */
    private List<BookDto> bookDtoList;

    /**
     * 订单来源
     */
    private String origin;
}
