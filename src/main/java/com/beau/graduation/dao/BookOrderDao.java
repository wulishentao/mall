package com.beau.graduation.dao;

import com.beau.graduation.model.BookOrder;
import com.beau.graduation.model.dto.BookOrderDto;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * dao层接口
 * BookOrderDao
 * @author beau
 * @date 2020/04/17
 */
public interface BookOrderDao {

    /**
     * [新增]
     **/
    int insert(@Param("bookOrder") BookOrder bookOrder);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list")List<BookOrderDto> list);

    /**
     * [更新]
     **/
    int update(@Param("bookOrder") BookOrder bookOrder);

    /**
     * [删除]
     **/
    int delete(@Param("bookOrder") BookOrder bookOrder);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list")List<BookOrder> list);

    /**
     * [主键查询]
     **/
    List<BookOrderDto> getBookByOrderId(@Param("bookOrder") BookOrder bookOrder);

    /**
     * [条件查询]
     **/
    List<BookOrder> selectList (@Param("bookOrder") BookOrder bookOrder);


    /**
     * [总量查询]
     **/
    int total(@Param("bookOrder") BookOrder bookOrder);

    int getBoughtBookTotal(@Param("bookOrderDto") BookOrderDto bookOrderDto);

    List<BookOrderDto> getBoughtBookPage(@Param("bookOrderDto") BookOrderDto bookOrderDto,@Param("page") HashMap<String,Integer> page);

    List<BookOrder> selectPage(@Param("bookOrderDto") BookOrderDto entity,@Param("page") HashMap<String, Integer> beginAndSize);
}
