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

    /**
     * 书籍图片地址
     */
    private String imgUrl;

    private Boolean exist;



    /**
     * 销量
     */
    private Long sales;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 书籍所属类型
     */
    private String bookType;


    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

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
