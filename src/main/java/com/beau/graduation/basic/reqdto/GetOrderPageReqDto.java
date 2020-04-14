package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: GetOrderPageReqDto.java
 * @author: Beau
 * @create: 2020/04/13 17:21
 * @version: 1.0.0
 **/
public class GetOrderPageReqDto extends CommonReqDTO implements Serializable {
    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 收货人(用户名/手机号码)
     */
    private String receiver;

    /**
     * 统计订单创建开始时间
     */
    private String beginTime;

    /**
     * 统计订单创建结束时间
     */
    private String endTime;

    /**
     * 订单状态
     */
    private String orderStatus;

    private Integer pageNo;

    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
