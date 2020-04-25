package com.beau.graduation.service;

import com.beau.graduation.basic.reqdto.*;
import com.beau.graduation.basic.resdto.*;
import com.beau.graduation.model.Topic;

import java.io.IOException;
import java.util.List;

/**
 * 业务层
 * TopicService
 * @author beau
 * @date 2020/04/09
 */
public interface TopicService {

    /**
     * [新增]
     **/
    int insert(Topic topic);

    /**
     * [批量新增]
     **/
    int batchInsert(List<Topic> list);

    /**
     * [更新]
     **/
    int update(Topic topic);

    /**
     * [删除]
     **/
    int delete(Topic topic);

    /**
     * [批量删除]
     **/
    int batchDelete(List<Topic> list);

    /**
     * [主键查询]
     **/
    Topic selectByObj(Topic topic);

    /**
     * [条件查询]
     **/
    List<Topic> selectList (Topic topic);

    /**
     * [总量查询]
     **/
    int total(Topic topic);

    AddTopicResDto addTopic(AddTopicReqDto reqDto) throws IOException;

    GetTopicPageResDto getTopicPage(GetTopicPageReqDto reqDto);

    /**
     * 编辑专题活动
     * @param reqDto
     * @return
     */
    EditTopicResDto editTopic(EditTopicReqDto reqDto) throws Exception;

    DeleteTopicResDto deleteTopic(DeleteTopicReqDto reqDto);

    GetRelatableBooksResDto getRelatableBooks(GetRelatableBooksReqDto reqDto);
}
