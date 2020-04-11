package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @classname: RelatedBookReqDto.java
 * @author: Beau
 * @create: 2020/04/11 17:35
 * @version: 1.0.0
 **/
public class RelatedBookReqDto extends CommonReqDTO implements Serializable {
    /**
     * 专题id
     */
    private Long topicId;

    /**
     * 图书id集合
     */
    private List<Long> bookIds;

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}
