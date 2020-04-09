package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: CommodityDetailedReqDto.java
 * @author: Beau
 * @create: 2020/04/09 18:34
 * @version: 1.0.0
 **/
public class CommodityDetailedReqDto extends CommonReqDTO implements Serializable {
    private Long bookId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
