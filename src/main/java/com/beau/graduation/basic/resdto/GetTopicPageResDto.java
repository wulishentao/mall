package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.Topic;

import java.io.Serializable;

/**
 * @classname: GetTopicPageResDto.java
 * @author: Beau
 * @create: 2020/04/11 13:22
 * @version: 1.0.0
 **/
public class GetTopicPageResDto extends CommonResDTO implements Serializable {
    private Page<Topic> topicPage;

    public Page<Topic> getTopicPage() {
        return topicPage;
    }

    public void setTopicPage(Page<Topic> topicPage) {
        this.topicPage = topicPage;
    }
}
