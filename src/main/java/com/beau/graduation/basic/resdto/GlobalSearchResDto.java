package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.dto.BookDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: GlobalSearchResDto.java
 * @author: Beau
 * @create: 2020/05/01 15:40
 * @version: 1.0.0
 **/
@Data
public class GlobalSearchResDto extends CommonResDTO implements Serializable {
    private Page<BookDto> bookDtoPage;
}
