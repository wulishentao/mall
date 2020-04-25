package com.beau.graduation.service;

import com.beau.graduation.basic.reqdto.*;
import com.beau.graduation.basic.resdto.*;
import com.beau.graduation.model.Order;
import com.beau.graduation.model.dto.OrderDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 业务层
 * OrderService
 * @author beau
 * @date 2020/04/13
 */
public interface OrderService {

    /**
     * [新增]
     **/
    int insert(Order order);

    /**
     * [批量新增]
     **/
    int batchInsert(List<Order> list);

    /**
     * [更新]
     **/
    int update(OrderDto orderDto);

    /**
     * [删除]
     **/
    int delete(Order order);

    /**
     * [批量删除]
     **/
    int batchDelete(List<Order> list);

    /**
     * [根据订单号查找]
     *
     * @param orderId
     * @return*/
    OrderDto selectByOrderId(String orderId);

    /**
     * [总量查询]
     **/
    int total(OrderDto orderDto);

    GetOrderPageResDto getOrderPage(GetOrderPageReqDto reqDto);

    OrderDeliveryResDto orderDelivery(OrderDeliveryReqDto reqDto, HttpServletRequest request);

    OrderClosedResDto orderClosed(OrderClosedReqDto reqDto, HttpServletRequest request);

    OrderCancelResDto orderCancel(OrderCancelReqDto reqDto, HttpServletRequest request);

    OrderDeleteResDto orderDelete(OrderDeleteReqDto reqDto);

    ViewOrderInfoResDto viewOrderInfo(ViewOrderInfoReqDto reqDto);

    ConfirmOrderInfoResDto confirmOrderInfo(ConfirmOrderInfoReqDto reqDto, HttpServletRequest request);
}
