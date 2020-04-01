package com.beau.graduation.service;

import com.beau.graduation.common.PageList;
import com.beau.graduation.model.BookImage;

import java.util.List;

/**
 * 业务层
 * BookImageService
 * @author beau
 * @date 2020/03/28
 */
public interface BookImageService {

    /**
     * [新增]
     **/
    int insert(BookImage bookImage);

    /**
     * [批量新增]
     **/
    int batchInsert(List<BookImage> list);

    /**
     * [更新]
     **/
    int update(BookImage bookImage);

    /**
     * [删除]
     **/
    int delete(BookImage bookImage);

    /**
     * [批量删除]
     **/
    int batchDelete(List<BookImage> list);

    /**
     * [主键查询]
     **/
    BookImage selectByObj(BookImage bookImage);

    /**
     * [条件查询]
     **/
    List<BookImage> selectList (BookImage bookImage);

    /**
     * [分页条件查询]
     **/
    PageList<BookImage> selectPage (BookImage bookImage, Integer page, Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(BookImage bookImage);
}
