package com.beau.graduation.model;

import com.beau.graduation.model.dto.BookDto;

import java.io.Serializable;
import java.util.List;

/**
 * ShoppingCart
 * @author beau 2020-03-30
 */
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 书籍列表
     */
    private List<BookDto> bookDtoList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<BookDto> getBookDtoList() {
        return bookDtoList;
    }

    public void setBookDtoList(List<BookDto> bookDtoList) {
        this.bookDtoList = bookDtoList;
    }
}