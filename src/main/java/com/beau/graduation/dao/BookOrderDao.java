package com.beau.graduation.dao;

import com.beau.graduation.model.BookOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * dao层接口
 * BookOrderDao
 * @author beau
 * @date 2020/03/28
 */
public interface BookOrderDao {

    /**
     * [新增]
     **/
    int insert(@Param("bookOrder") BookOrder bookOrder);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list") List<BookOrder> list);

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
    int batchDelete(@Param("list") List<BookOrder> list);

    /**
     * [主键查询]
     **/
    BookOrder selectByObj(@Param("bookOrder") BookOrder bookOrder);

    /**
     * [条件查询]
     **/
    List<BookOrder> selectList (@Param("bookOrder") BookOrder bookOrder);

    /**
     * [分页条件查询]
     **/
    List<BookOrder> selectPage (@Param("bookOrder") BookOrder bookOrder, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(@Param("bookOrder") BookOrder bookOrder);
}
