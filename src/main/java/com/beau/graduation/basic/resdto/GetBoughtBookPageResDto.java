package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.dto.BookOrderDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: GetBoughtBookPageResDto.java
 * @author: Beau
 * @create: 2020/04/30 15:56
 * @version: 1.0.0
 **/
@Data
public class GetBoughtBookPageResDto extends CommonResDTO implements Serializable {
    private Page<BookOrderDto> page;
}
