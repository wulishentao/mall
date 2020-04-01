package com.beau.graduation.dao;

import com.beau.graduation.model.BookImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * dao层接口
 * BookImageDao
 * @author beau
 * @date 2020/03/28
 */
public interface BookImageDao {

    /**
     * [新增]
     **/
    int insert(@Param("bookImage") BookImage bookImage);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list") List<BookImage> list);

    /**
     * [更新]
     **/
    int update(@Param("bookImage") BookImage bookImage);

    /**
     * [删除]
     **/
    int delete(@Param("bookImage") BookImage bookImage);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list") List<BookImage> list);

    /**
     * [主键查询]
     **/
    BookImage selectByObj(@Param("bookImage") BookImage bookImage);

    /**
     * [条件查询]
     **/
    List<BookImage> selectList(@Param("bookImage") BookImage bookImage);

    /**
     * [分页条件查询]
     **/
    List<BookImage> selectPage(@Param("bookImage") BookImage bookImage, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(@Param("bookImage") BookImage bookImage);
}
