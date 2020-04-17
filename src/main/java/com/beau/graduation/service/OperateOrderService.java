package com.beau.graduation.service;

import com.beau.graduation.model.OperateOrder;

import java.util.List;

/**
 * 业务层
 * OperateOrderService
 * @author beau
 * @date 2020/04/17
 */
public interface OperateOrderService {

    /**
     * [新增]
     **/
    int insert(OperateOrder operateOrder);

    /**
     * [批量新增]
     **/
    int batchInsert(List<OperateOrder> list);

    /**
     * [更新]
     **/
    int update(OperateOrder operateOrder);

    /**
     * [删除]
     **/
    int delete(OperateOrder operateOrder);

    /**
     * [批量删除]
     **/
    int batchDelete(List<OperateOrder> list);

    /**
     * [主键查询]
     **/
    OperateOrder selectByObj(OperateOrder operateOrder);

    /**
     * [条件查询]
     **/
    List<OperateOrder> selectList (OperateOrder operateOrder);

    /**
     * [总量查询]
     **/
    int total(OperateOrder operateOrder);
}
