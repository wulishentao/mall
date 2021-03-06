package com.beau.graduation.dao;

import com.beau.graduation.model.Book;
import com.beau.graduation.model.dto.BookDto;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
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
    int batchDelete(@Param("bookIds") List<Long> bookIds);

    /**
     * [主键查询]
     **/
    BookDto commodityDetailed(@Param("book") Book book);

    /**
     * 查询满足条件书籍总量
     * @param dto
     * @return
     */
    int totalByDto(@Param("bookDto") BookDto dto);

    BookDto selectById(Long bookId);

    /**
     * 获取满足条件的书籍
     * @param entity
     *
     * @param beginAndSize
     * @return
     */
    List<BookDto> getCommodityPage(@Param("bookDto") BookDto entity,@Param("page") HashMap<String, Integer> beginAndSize);

    List<Long> getSuchBookList(@Param("bookDto") BookDto bookDto);

    /**
     * 将书籍type_id设为空
     * @param bookIds
     */
    void updateTypeId(@Param("bookIds") List<Long> bookIds);

    List<BookDto> getBookInfoByIds(@Param("bookDto") BookDto bookDto);

    BookDto selectByBookId(@Param("bookId") Long id);

    int getNewBookCount(@Param("bookDto") BookDto entity);

    int newBookHotCount(@Param("bookDto") BookDto entity);

    List<BookDto> getBookPage(@Param("bookDto") BookDto entity,@Param("page") HashMap<String, Integer> beginAndSize);

    List<BookDto> globalSearch(@Param("bookDto") BookDto entity,@Param("page") HashMap<String, Integer> beginAndSize);

    int globalSearchCount(@Param("bookDto") BookDto bookDto);
}
