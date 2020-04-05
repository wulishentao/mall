package com.beau.graduation.dao;

import com.beau.graduation.model.BookType;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * dao层接口
 * BookTypeDao
 * @author beau
 * @date 2020/03/28
 */
public interface BookTypeDao {

    /**
     * [新增]
     **/
    int insert(@Param("bookType") BookType bookType);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list") List<BookType> list);

    /**
     * [更新]
     **/
    int update(@Param("bookType") BookType bookType);

    /**
     * [删除]
     **/
    int delete(@Param("bookType") BookType bookType);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list") List<BookType> list);

    /**
     * [主键查询]
     **/
    BookType selectByObj(@Param("bookType") BookType bookType);

    /**
     * [条件查询]
     **/
    List<BookType> selectList(@Param("bookType") BookType bookType);

    /**
     * [分页条件查询]
     **/
    List<BookType> selectPage(@Param("bookType") BookType bookType, @Param("page") HashMap<String, Integer> page);

    /**
     * [总量查询]
     **/
    int total(@Param("bookType") BookType bookType);
}
