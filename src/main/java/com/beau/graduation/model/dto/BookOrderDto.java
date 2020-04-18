package com.beau.graduation.model.dto;

import com.beau.graduation.model.BookOrder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @classname: BookOrderDto.java
 * @author: Beau
 * @create: 2020/04/18 20:01
 * @version: 1.0.0
 **/
public class BookOrderDto extends BookOrder implements Serializable {
    private String imgUrl;
    private String bookTitle;
    private BigDecimal unitPrice;
    private String topicName;
    private String typeName;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
