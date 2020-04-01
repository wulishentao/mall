package com.beau.graduation.service;

import com.beau.graduation.common.PageList;
import com.beau.graduation.model.BookRelationType;

import java.util.List;

/**
 * 业务层
 * BookRelationTypeService
 * @author beau
 * @date 2020/03/28
 */
public interface BookRelationTypeService {

    /**
     * [新增]
     **/
    int insert(BookRelationType bookRelationType);

    /**
     * [批量新增]
     **/
    int batchInsert(List<BookRelationType> list);

    /**
     * [更新]
     **/
    int update(BookRelationType bookRelationType);

    /**
     * [删除]
     **/
    int delete(BookRelationType bookRelationType);

    /**
     * [批量删除]
     **/
    int batchDelete(List<BookRelationType> list);

    /**
     * [主键查询]
     **/
    BookRelationType selectByObj(BookRelationType bookRelationType);

    /**
     * [条件查询]
     **/
    List<BookRelationType> selectList (BookRelationType bookRelationType);

    /**
     * [分页条件查询]
     **/
    PageList<BookRelationType> selectPage (BookRelationType bookRelationType, Integer page, Integer pageSize);

    /**
     * [总量查询]
     **/
    int total(BookRelationType bookRelationType);
}
