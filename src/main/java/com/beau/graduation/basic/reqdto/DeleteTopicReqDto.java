package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: DeleteTopicReqDto.java
 * @author: Beau
 * @create: 2020/04/11 17:11
 * @version: 1.0.0
 **/
public class DeleteTopicReqDto extends CommonReqDTO implements Serializable {
    private Long topicId;

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
}
