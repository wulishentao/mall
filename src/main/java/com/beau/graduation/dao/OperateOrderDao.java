package com.beau.graduation.dao;

import com.beau.graduation.model.OperateOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * dao层接口
 * OperateOrderDao
 * @author beau
 * @date 2020/04/17
 */
public interface OperateOrderDao {

    /**
     * [新增]
     **/
    int insert(@Param("operateOrder") OperateOrder operateOrder);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list")List<OperateOrder> list);

    /**
     * [更新]
     **/
    int update(@Param("operateOrder") OperateOrder operateOrder);

    /**
     * [删除]
     **/
    int delete(@Param("operateOrder") OperateOrder operateOrder);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list")List<OperateOrder> list);

    /**
     * [主键查询]
     **/
    OperateOrder selectByObj(@Param("operateOrder") OperateOrder operateOrder);

    /**
     * [条件查询]
     **/
    List<OperateOrder> selectList (@Param("operateOrder") OperateOrder operateOrder);


    /**
     * [总量查询]
     **/
    int total(@Param("operateOrder") OperateOrder operateOrder);
}
