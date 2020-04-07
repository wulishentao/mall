package com.beau.graduation.model;

import java.io.Serializable;
import java.util.List;

/**
 * BookRelationType
 * @author beau 2020-03-28
 */
public class BookRelationType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id  主键ID
     */
    private Long id;


    /**
     * book_id  图书id
     */
    private Long bookId;


    /**
     * type_id  类别id
     */
    private Long typeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

}