package com.beau.graduation.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * BookOrder
 * @author beau 2020-04-17
 */
public class BookOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id  主键ID
     */
    private Long id;


    /**
     * book_id  图书id
     */
    private Long bookId;


    /**
     * order_id  订单编号
     */
    private String orderId;


    /**
     * create_time  关联时间
     */
    @JSONField
    private Date createTime;


    /**
     * amount  书籍数量
     */
    private Long amount;


    /**
     * total_price  商品小结
     */
    private BigDecimal totalPrice;

    private String ifComment;

    public String getIfComment() {
        return ifComment;
    }

    public void setIfComment(String ifComment) {
        this.ifComment = ifComment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}