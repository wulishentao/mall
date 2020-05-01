package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.dto.BookOrderDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: GetCommentPageResDto.java
 * @author: Beau
 * @create: 2020/05/01 13:48
 * @version: 1.0.0
 **/
@Data
public class GetCommentPageResDto extends CommonResDTO implements Serializable {
    private Page<BookOrderDto> bookOrderDtoPage;
}
