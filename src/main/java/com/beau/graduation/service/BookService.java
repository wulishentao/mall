package com.beau.graduation.service;

import com.beau.graduation.basic.reqdto.AddCartReqDto;
import com.beau.graduation.basic.resdto.AddCartResDto;
import com.beau.graduation.common.PageList;
import com.beau.graduation.model.Book;
import com.beau.graduation.model.dto.BookDto;

import javax.servlet.http.HttpServletRequest;
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
     * [批量删除]
     **/
    int batchDelete(List<Book> list);

    /**
     * [条件查询]
     **/
    BookDto selectByObj(Book book);

    /**
     * [主键查询]
     **/
    BookDto selectById(Long bookId);

    /**
     * [条件查询]
     **/
    List<Book> selectList (Book book);

    /**
     * [分页条件查询]
     **/
    PageList<Book> selectPage (Book book, Integer page, Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(Book book);

    /**
     * 添加商品进购物车
     * @param addCartReqDto
     * @return
     */
    AddCartResDto addCart(AddCartReqDto addCartReqDto, HttpServletRequest request);
}
