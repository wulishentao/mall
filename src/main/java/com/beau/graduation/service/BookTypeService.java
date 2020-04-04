package com.beau.graduation.service;

import com.beau.graduation.common.Page;
import com.beau.graduation.model.BookType;

import java.util.List;

/**
 * 业务层
 * BookTypeService
 * @author beau
 * @date 2020/03/28
 */
public interface BookTypeService {

    /**
     * [新增]
     **/
    int insert(BookType bookType);

    /**
     * [批量新增]
     **/
    int batchInsert(List<BookType> list);

    /**
     * [更新]
     **/
    int update(BookType bookType);

    /**
     * [删除]
     **/
    int delete(BookType bookType);

    /**
     * [批量删除]
     **/
    int batchDelete(List<BookType> list);

    /**
     * [主键查询]
     **/
    BookType selectByObj(BookType bookType);

    /**
     * [条件查询]
     **/
    List<BookType> selectList(BookType bookType);

    /**
     * [分页条件查询]
     **/
    Page<BookType> selectPage(BookType bookType, Integer page, Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(BookType bookType);
}
