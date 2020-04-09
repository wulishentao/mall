package com.beau.graduation.service;

import com.beau.graduation.model.BookRelationTopic;

import java.util.List;

/**
 * 业务层
 * BookRelationTopicService
 * @author beau
 * @date 2020/04/09
 */
public interface BookRelationTopicService {

    /**
     * [新增]
     **/
    int insert(BookRelationTopic bookRelationTopic);

    /**
     * [批量新增]
     **/
    int batchInsert(List<BookRelationTopic> list);

    /**
     * [更新]
     **/
    int update(BookRelationTopic bookRelationTopic);

    /**
     * [删除]
     **/
    int delete(BookRelationTopic bookRelationTopic);

    /**
     * [批量删除]
     **/
    int batchDelete(List<BookRelationTopic> list);

    /**
     * [主键查询]
     **/
    BookRelationTopic selectByObj(BookRelationTopic bookRelationTopic);

    /**
     * [条件查询]
     **/
    List<BookRelationTopic> selectList (BookRelationTopic bookRelationTopic);

    /**
     * [总量查询]
     **/
    int total(BookRelationTopic bookRelationTopic);
}
