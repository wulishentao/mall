package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.dto.BookDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: NewBookHotPageResDto.java
 * @author: Beau
 * @create: 2020/05/01 17:02
 * @version: 1.0.0
 **/
@Data
public class NewBookHotPageResDto extends CommonResDTO implements Serializable {
    private Page<BookDto> bookDtoPage;
}
