package com.beau.graduation.dao;

import com.beau.graduation.model.BookRelationTopic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * dao层接口
 * BookRelationTopicDao
 * @author beau
 * @date 2020/04/09
 */
public interface BookRelationTopicDao {

    /**
     * [新增]
     **/
    int insert(@Param("bookRelationTopic") BookRelationTopic bookRelationTopic);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list") List<BookRelationTopic> list);

    /**
     * [更新]
     **/
    int update(@Param("bookRelationTopic") BookRelationTopic bookRelationTopic);

    /**
     * [删除]
     **/
    int delete(@Param("bookRelationTopic") BookRelationTopic bookRelationTopic);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list") List<BookRelationTopic> list);

    /**
     * [主键查询]
     **/
    BookRelationTopic selectByObj(@Param("bookRelationTopic") BookRelationTopic bookRelationTopic);

    /**
     * [条件查询]
     **/
    List<BookRelationTopic> selectList (@Param("bookRelationTopic") BookRelationTopic bookRelationTopic);

    /**
     * [总量查询]
     **/
    int total(@Param("bookRelationTopic") BookRelationTopic bookRelationTopic);
}
