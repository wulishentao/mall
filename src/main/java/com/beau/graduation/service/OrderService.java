package com.beau.graduation.service;

import com.beau.graduation.basic.reqdto.GetOrderPageReqDto;
import com.beau.graduation.basic.resdto.GetOrderPageResDto;
import com.beau.graduation.model.Order;
import com.beau.graduation.model.dto.OrderDto;

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
    int update(Order order);

    /**
     * [删除]
     **/
    int delete(Order order);

    /**
     * [批量删除]
     **/
    int batchDelete(List<Order> list);

    /**
     * [主键查询]
     **/
    Order selectByObj(Order order);

    /**
     * [总量查询]
     **/
    int total(OrderDto orderDto);

    GetOrderPageResDto getOrderPage(GetOrderPageReqDto reqDto);

}
