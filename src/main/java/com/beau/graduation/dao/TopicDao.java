package com.beau.graduation.dao;

import com.beau.graduation.model.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * dao层接口
 * TopicDao
 * @author beau
 * @date 2020/04/09
 */
public interface TopicDao {

    /**
     * [新增]
     **/
    int insert(@Param("topic") Topic topic);

    /**
     * [批量新增]
     **/
    int batchInsert(@Param("list") List<Topic> list);

    /**
     * [更新]
     **/
    int update(@Param("topic") Topic topic);

    /**
     * [删除]
     **/
    int delete(@Param("topic") Topic topic);

    /**
     * [批量删除]
     **/
    int batchDelete(@Param("list") List<Topic> list);

    /**
     * [主键查询]
     **/
    Topic selectByObj(@Param("topic") Topic topic);

    /**
     * [条件查询]
     **/
    List<Topic> selectList (@Param("topic") Topic topic);

    /**
     * [总量查询]
     **/
    int total(@Param("topic") Topic topic);

    /**
     * 分页查询专题
     * @param entity
     * @param beginAndSize
     * @return
     */
    List<Topic> getTopicPage(@Param("topic") Topic entity,@Param("page") HashMap<String, Integer> beginAndSize);
}
