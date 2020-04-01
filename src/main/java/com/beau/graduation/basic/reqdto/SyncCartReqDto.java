package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: SyncCartReqDto.java
 * @author: Beau
 * @create: 2020/04/01 15:13
 * @version: 1.0.0
 **/
public class SyncCartReqDto extends CommonReqDTO implements Serializable {
    private Long bookId;
    private Long amount;
    private String delFlag;

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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
