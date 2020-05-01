package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.dto.BookDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: BookSellingPageResDto.java
 * @author: Beau
 * @create: 2020/05/01 21:10
 * @version: 1.0.0
 **/
@Data
public class BookSellingPageResDto extends CommonResDTO implements Serializable {
    private Page<BookDto> bookDtoPage;
}
