package com.beau.graduation.service;

import com.beau.graduation.basic.reqdto.GetBoughtBookPageReqDto;
import com.beau.graduation.basic.reqdto.GetCommentPageReqDto;
import com.beau.graduation.basic.resdto.GetBoughtBookPageResDto;
import com.beau.graduation.basic.resdto.GetCommentPageResDto;
import com.beau.graduation.model.BookOrder;
import com.beau.graduation.model.dto.BookOrderDto;

import java.util.List;

/**
 * 业务层
 * BookOrderService
 * @author beau
 * @date 2020/04/17
 */
public interface BookOrderService {

    /**
     * [新增]
     **/
    int insert(BookOrder bookOrder);

    /**
     * [批量新增]
     **/
    int batchInsert(List<BookOrderDto> list);

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
    List<BookOrderDto> selectByObj(BookOrder bookOrder);

    /**
     * [条件查询]
     **/
    List<BookOrder> selectList (BookOrder bookOrder);

    /**
     * [总量查询]
     **/
    int total(BookOrder bookOrder);

    GetBoughtBookPageResDto getBoughtBookPage(GetBoughtBookPageReqDto reqDto);

    GetCommentPageResDto getCommentPage(GetCommentPageReqDto reqDto);
}
