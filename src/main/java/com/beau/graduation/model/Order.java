package com.beau.graduation.model;

import com.alibaba.fastjson.annotation.JSONField;

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
     * 交易状态:[{key:confirm_status,values:
     * [{no:-1,remark:订单已关闭},
     * {no:0,remark:待付款},
     * {no:1,remark:未发货},
     * {no:2,remark:已发货},
     * {no:3,remark:已签收}]}]
     */
    private String confirmStatus;


    /**
     * logistics_company  物流公司名称
     */
    private String logisticsCompany;


    /**
     * shipment_number  物流单号
     */
    private String shipmentNumber;


    /**
     * create_time  订单创建时间
     */
    @JSONField
    private Date createTime;


    /**
     * update_time  订单更新时间
     */
    @JSONField
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

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getShipmentNumber() {
        return shipmentNumber;
    }

    public void setShipmentNumber(String shipmentNumber) {
        this.shipmentNumber = shipmentNumber;
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