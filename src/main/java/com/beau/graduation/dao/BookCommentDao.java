package com.beau.graduation.dao;

import com.beau.graduation.model.BookComment;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * dao层接口
 * BookCommentDao
 * @author beau
 * @date 2020/03/28
 */
public interface BookCommentDao {

    /**
     * [新增]
     **/
    int insert(@Param("bookComment") BookComment bookComment);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list") List<BookComment> list);

    /**
     * [更新]
     **/
    int update(@Param("bookComment") BookComment bookComment);

    /**
     * [删除]
     **/
    int delete(@Param("bookComment") BookComment bookComment);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list") List<BookComment> list);

    /**
     * [主键查询]
     **/
    BookComment selectByObj(@Param("bookComment") BookComment bookComment);

    /**
     * [条件查询]
     **/
    List<BookComment> selectList (@Param("bookComment") BookComment bookComment);

    /**
     * [分页条件查询]
     **/
    List<BookComment> selectPage (@Param("bookComment") BookComment bookComment,@Param("page") HashMap<String,Integer> page);

    /**
     * [总量查询]
     **/
    int total(@Param("bookComment") BookComment bookComment);
}
