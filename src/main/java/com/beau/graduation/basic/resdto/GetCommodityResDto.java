package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.dto.BookDto;

import java.io.Serializable;

/**
 * @classname: GetCommodityResDto.java
 * @author: Beau
 * @create: 2020/04/04 11:31
 * @version: 1.0.0
 **/
public class GetCommodityResDto extends CommonResDTO implements Serializable {
    private Page<BookDto> bookDtoPage;

    public Page<BookDto> getBookDtoPage() {
        return bookDtoPage;
    }

    public void setBookDtoPage(Page<BookDto> bookDtoPage) {
        this.bookDtoPage = bookDtoPage;
    }
}
