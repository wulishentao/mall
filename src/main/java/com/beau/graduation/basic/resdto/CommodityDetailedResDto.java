package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.model.dto.BookDto;

import java.io.Serializable;

/**
 * @classname: CommodityDetailedResDto.java
 * @author: Beau
 * @create: 2020/04/09 18:34
 * @version: 1.0.0
 **/
public class CommodityDetailedResDto extends CommonResDTO implements Serializable {
    private BookDto bookDto;

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }
}
