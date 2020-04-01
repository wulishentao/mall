package com.beau.graduation.dao;

import com.beau.graduation.model.Book;
import com.beau.graduation.model.dto.BookDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * dao层接口
 * BookDao
 * @author beau
 * @date 2020/03/31
 */
public interface BookDao {

    /**
     * [新增]
     **/
    int insert(@Param("book") Book book);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list") List<Book> list);

    /**
     * [更新]
     **/
    int update(@Param("book") Book book);

    /**
     * [删除]
     **/
    int delete(@Param("book") Book book);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list") List<Book> list);

    /**
     * [主键查询]
     **/
    BookDto selectByObj(@Param("book") Book book);

    /**
     * [条件查询]
     **/
    List<Book> selectList (@Param("book") Book book);

    /**
     * [分页条件查询]
     **/
    List<Book> selectPage (@Param("book") Book book, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(@Param("book") Book book);

    BookDto selectById(Long bookId);
}
