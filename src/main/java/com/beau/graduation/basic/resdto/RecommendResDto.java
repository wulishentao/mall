package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.dto.BookDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @classname: RecommendResDto.java
 * @author: Beau
 * @create: 2020/05/01 21:22
 * @version: 1.0.0
 **/
@Data
public class RecommendResDto extends CommonResDTO implements Serializable {
    private Page<BookDto> bookDtoPage;
}
