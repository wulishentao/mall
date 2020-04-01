package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: AddCartReqDto.java
 * @author: Beau
 * @create: 2020/03/31 14:24
 * @version: 1.0.0
 **/
public class AddCartReqDto extends CommonReqDTO implements Serializable {
    private Long bookId;
    private Long amount;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
