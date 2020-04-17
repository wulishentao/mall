package com.beau.graduation.dao;

import com.beau.graduation.model.Order;
import com.beau.graduation.model.dto.OrderDto;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * dao层接口
 * OrderDao
 * @author beau
 * @date 2020/04/13
 */
public interface OrderDao {

    /**
     * [新增]
     **/
    int insert(@Param("order") Order order);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list")List<Order> list);

    /**
     * [更新]
     **/
    int update(@Param("dto") OrderDto dto);

    /**
     * [删除]
     **/
    int delete(@Param("order") Order order);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list")List<Order> list);

    /**
     * [主键查询]
     *
     * @return*/
    OrderDto selectByOrderId(@Param("orderId") String orderId);

    /**
     * [条件查询]
     **/
    List<Order> selectList (@Param("order") Order order);

    /**
     * [总量查询]
     **/
    int total(@Param("dto") OrderDto dto);

    /**
     * 获取订单列表
     * @param dto
     * @param beginAndSize
     * @return
     */
    List<OrderDto> getOrderPage(@Param("dto") OrderDto dto,@Param("page") HashMap<String, Integer> beginAndSize);
}
