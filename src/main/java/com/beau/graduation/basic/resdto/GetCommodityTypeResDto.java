package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.BookType;

import java.io.Serializable;

/**
 * @classname: GetCommodityTypeResDto.java
 * @author: Beau
 * @create: 2020/04/05 14:34
 * @version: 1.0.0
 **/
public class GetCommodityTypeResDto extends CommonResDTO implements Serializable {
    private Page<BookType> page;

    public Page<BookType> getPage() {
        return page;
    }

    public void setPage(Page<BookType> page) {
        this.page = page;
    }
}
