package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.dto.BookDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: GetNewBookResDto.java
 * @author: Beau
 * @create: 2020/05/01 16:32
 * @version: 1.0.0
 **/
@Data
public class GetNewBookResDto extends CommonResDTO implements Serializable {
    private Page<BookDto> page;
}
