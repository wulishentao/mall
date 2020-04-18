package com.beau.graduation.model;

import java.io.Serializable;
import java.util.Date;

/**
 * OperateOrder
 * @author beau 2020-04-17
 */
public class OperateOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id  主键ID
     */
    private Long id;


    /**
     * operator  操作人员名称
     */
    private String operator;


    /**
     * order_id  订单编号
     */
    private String orderId;

    /**
     * confirm_status 操作后的订单状态
     */
    private String confirmStatus;

    /**
     * operate_time  操作时间
     */
    private Date operateTime;


    /**
     * remark  操作备注
     */
    private String remark;

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}