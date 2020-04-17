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
     * operate_name  操作人员名称
     */
    private String operateName;


    /**
     * order_id  订单编号
     */
    private String orderId;


    /**
     * operate_time  操作时间
     */
    private Date operateTime;


    /**
     * remark  操作备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
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