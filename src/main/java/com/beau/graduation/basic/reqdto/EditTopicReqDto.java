package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: EditTopicReqDto.java
 * @author: Beau
 * @create: 2020/04/11 14:12
 * @version: 1.0.0
 **/
public class EditTopicReqDto extends CommonReqDTO implements Serializable {
    /**
     * 专题id
     */
    private Long topicId;

    private String status;

    private String sort;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
