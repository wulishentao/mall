package com.beau.graduation.model.dto;

import com.beau.graduation.model.OperateOrder;
import com.beau.graduation.model.Order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @classname: OrderDto.java
 * @author: Beau
 * @create: 2020/04/14 14:12
 * @version: 1.0.0
 **/
public class OrderDto extends Order implements Serializable {
    private String beginTime;
    private String endTime;
    private List<BookOrderDto> bookOrderDtoList;
    private List<OperateOrder> operateOrders;

    /**
     * 用户名称/手机号
     */
    private String receive;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 订单总额
     */
    private BigDecimal orderTotal;


    public List<OperateOrder> getOperateOrders() {
        return operateOrders;
    }

    public void setOperateOrders(List<OperateOrder> operateOrders) {
        this.operateOrders = operateOrders;
    }

    public List<BookOrderDto> getBookOrderDtoList() {
        return bookOrderDtoList;
    }

    public void setBookOrderDtoList(List<BookOrderDto> bookOrderDtoList) {
        this.bookOrderDtoList = bookOrderDtoList;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getReceive() {
        return receive;
    }

    public void setReceive(String receive) {
        this.receive = receive;
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
}
