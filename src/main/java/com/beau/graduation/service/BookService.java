package com.beau.graduation.service;

import com.beau.graduation.basic.reqdto.*;
import com.beau.graduation.basic.resdto.*;
import com.beau.graduation.model.Book;
import com.beau.graduation.model.dto.BookDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 业务层
 * BookService
 * @author beau
 * @date 2020/03/31
 */
public interface BookService {

    /**
     * [新增]
     **/
    int insert(Book book);

    /**
     * [批量新增]
     **/
    int batchInsert(List<Book> list);

    /**
     * [更新]
     **/
    int update(Book book);

    /**
     * [删除]
     **/
    int delete(Book book);

    /**
     * [条件查询]
     **/
    BookDto selectByObj(Book book);

    /**
     * [主键查询]
     **/
    BookDto selectById(Long bookId);

    /**
     * 添加商品进购物车
     * @param addCartReqDto
     * @return
     */
    AddCartResDto addCart(AddCartReqDto addCartReqDto, HttpServletRequest request, HttpServletResponse response);

    SyncCartResDto getShoppingCart(SyncCartReqDto syncCartReqDto, HttpServletRequest request, HttpServletResponse response);

    SyncCartResDto syncShoppingCart(SyncCartReqDto syncCartReqDto, HttpServletRequest request);

    GetCommodityResDto getCommodityPage(GetCommodityReqDto reqDto);

    AddCommodityResDto addCommodity(AddCommodityReqDto reqDto) throws Exception;

    EditCommodityResDto editCommodity(EditCommodityReqDto reqDto) throws Exception;

    CommodityDetailedResDto commodityDetailed(CommodityDetailedReqDto reqDto) throws Exception;

    DelCartResDto delShoppingCart(DelCartReqDto reqDto, HttpServletRequest request);

    List<Long> getSuchBookList(BookDto bookDto);

    void updateTypeId(List<Long> bookId);

    DelCommodityResDto delCommodity(DelCommodityReqDto reqDto);

    List<BookDto> getBookInfoByIds(BookDto bookDto);

    BookDto selectByBookId(Long id);

    GlobalSearchResDto globalSearch(GlobalSearchReqDto reqDto);

    GetNewBookResDto getNewBook(GetNewBookReqDto reqDto);

    NewBookHotPageResDto newBookHotPage(NewBookHotPageReqDto reqDto);

    BookSellingPageResDto bookSellingPage(BookSellingPageReqDto reqDto);

    RecommendResDto recommend(RecommendReqDto reqDto);
}
