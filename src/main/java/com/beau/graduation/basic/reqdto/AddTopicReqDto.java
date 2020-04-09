package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @classname: AddTopicReqDto.java
 * @author: Beau
 * @create: 2020/04/09 16:26
 * @version: 1.0.0
 **/
public class AddTopicReqDto extends CommonReqDTO implements Serializable {
    /**
     * 专题名称
     */
    private String topicTitle;
    private String topicIntroduce;
    private String beginTime;
    private String endTime;
    private String sort;
    private MultipartFile topicImag;
    private String remark;

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicIntroduce() {
        return topicIntroduce;
    }

    public void setTopicIntroduce(String topicIntroduce) {
        this.topicIntroduce = topicIntroduce;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public MultipartFile getTopicImag() {
        return topicImag;
    }

    public void setTopicImag(MultipartFile topicImag) {
        this.topicImag = topicImag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
