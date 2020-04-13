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
    private String begin_time;

    /**
     * 统计订单创建结束时间
     */
    private String end_time;

    /**
     * 订单状态
     */
    private String orderStatus;

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

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
