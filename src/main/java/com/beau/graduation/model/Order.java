package com.beau.graduation.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Order
 * @author beau 2020-04-13
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id  主键ID
     */
    private Long id;


    /**
     * user_id  用户id
     */
    private Long userId;


    /**
     * order_id  订单编号
     */
    private String orderId;


    /**
     * confirm_status  交易状态:[{key:confirm_status,values:[{no:0,alias:confirm,remark:未发货},{no:1,alias:payDeposit,remark:已发货},{no:2,alias:subscribe,remark:已签收}]}]
     */
    private String confirmStatus;


    /**
     * create_time  订单创建时间
     */
    private Date createTime;


    /**
     * update_time  订单更新时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
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
}