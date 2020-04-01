package com.beau.graduation.model.dto;

import com.beau.graduation.model.Book;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @classname: BookDto.java
 * @author: Beau
 * @create: 2020/03/31 14:07
 * @version: 1.0.0
 **/
public class BookDto extends Book implements Serializable {
    /**
     * 加入购物车的商品数量
     */
    private Long amount;

    private String imgUrl;

    private Boolean exist;

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
