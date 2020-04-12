package com.beau.graduation.service;

import com.beau.graduation.basic.reqdto.AddCommodityTypeReqDto;
import com.beau.graduation.basic.reqdto.DelCommodityTypeReqDto;
import com.beau.graduation.basic.reqdto.GetCommodityTypeReqDto;
import com.beau.graduation.basic.resdto.AddCommodityTypeResDto;
import com.beau.graduation.basic.resdto.DelCommodityTypeResDto;
import com.beau.graduation.basic.resdto.GetCommodityTypeResDto;
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
     * [总量查询]
     **/
    int total(BookType bookType);

    GetCommodityTypeResDto getCommodityTypePage(GetCommodityTypeReqDto reqDto);

    AddCommodityTypeResDto addCommodityType(AddCommodityTypeReqDto reqDto);

    DelCommodityTypeResDto delCommodityType(DelCommodityTypeReqDto reqDto);
}
