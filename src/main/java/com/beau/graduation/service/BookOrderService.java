package com.beau.graduation.service;

import com.beau.graduation.common.PageList;
import com.beau.graduation.model.BookOrder;

import java.util.List;

/**
 * 业务层
 * BookOrderService
 * @author beau
 * @date 2020/03/28
 */
public interface BookOrderService {

    /**
     * [新增]
     **/
    int insert(BookOrder bookOrder);

    /**
     * [批量新增]
     **/
    int batchInsert(List<BookOrder> list);

    /**
     * [更新]
     **/
    int update(BookOrder bookOrder);

    /**
     * [删除]
     **/
    int delete(BookOrder bookOrder);

    /**
     * [批量删除]
     **/
    int batchDelete(List<BookOrder> list);

    /**
     * [主键查询]
     **/
    BookOrder selectByObj(BookOrder bookOrder);

    /**
     * [条件查询]
     **/
    List<BookOrder> selectList (BookOrder bookOrder);

    /**
     * [分页条件查询]
     **/
    PageList<BookOrder> selectPage (BookOrder bookOrder, Integer page, Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(BookOrder bookOrder);
}
