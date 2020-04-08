package com.beau.graduation.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * BookOrder
 * @author beau 2020-03-28
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
     * user_id  用户id
     */
    private Long userId;


    /**
     * amount  商品数量
     */
    private Long amount;


    /**
     * order_id  订单编号
     */
    private Long orderId;


    /**
     * confirm_status  交易状态:[{key:confirm_status,values:[{no:0,alias:confirm,remark:未发货},{no:1,alias:payDeposit,remark:已发货},{no:2,alias:subscribe,remark:已签收}]}]
     */
    private Long confirmStatus;


    /**
     * create_time  订单创建时间
     */
    @JSONField
    private Date createTime;


    /**
     * update_time  订单修改时间
     */
    @JSONField
    private Date updateTime;


    /**
     * total_price  商品总价
     */
    private BigDecimal totalPrice;


    /**
     * del_flag  是否删除，0-否，1-是
     */
    private String delFlag;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Long confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}