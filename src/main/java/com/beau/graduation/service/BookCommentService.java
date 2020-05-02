package com.beau.graduation.service;

import com.beau.graduation.basic.reqdto.CommentBookReqDto;
import com.beau.graduation.basic.reqdto.GetCommentPageReqDto;
import com.beau.graduation.basic.resdto.CommentBookResDto;
import com.beau.graduation.basic.resdto.GetCommentPageResDto;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.BookComment;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 业务层
 * BookCommentService
 * @author beau
 * @date 2020/03/28
 */
public interface BookCommentService {

    /**
     * [新增]
     **/
    int insert(BookComment bookComment);

    /**
     * [批量新增]
     **/
    int batchInsert(List<BookComment> list);

    /**
     * [更新]
     **/
    int update(BookComment bookComment);

    /**
     * [删除]
     **/
    int delete(BookComment bookComment);

    /**
     * [批量删除]
     **/
    int batchDelete(List<BookComment> list);

    /**
     * [主键查询]
     **/
    BookComment selectByObj(BookComment bookComment);

    /**
     * [条件查询]
     **/
    List<BookComment> selectList(BookComment bookComment);

    /**
     * [分页条件查询]
     **/
    List<BookComment> selectPage(BookComment bookComment, HashMap<String, Integer> page);

    /**
     * [总量查询]
     **/
    int total(@Param("bookComment") BookComment bookComment);

    CommentBookResDto commentBook(CommentBookReqDto reqDto);

}
