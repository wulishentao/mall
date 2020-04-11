package com.beau.graduation.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * Topic
 * @author beau 2020-04-09
 */
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id  主键ID
     */
    private Long id;


    /**
     * topic_title  专题名称
     */
    private String topicTitle;


    /**
     * topic_introduce  专题介绍
     */
    private String topicIntroduce;


    /**
     * begin_time  专题开始时间
     */
    private Date beginTime;


    /**
     * end_time  专题结束时间
     */
    private Date endTime;


    /**
     * status  是否启用，0-否，1-是
     */
    private String status;


    /**
     * sort  曝光程度(0~9),级别越高最先最优放送
     */
    private String sort;


    /**
     * topic_img  专题宣传图片地址
     */
    private String topicImg;

    /**
     * 专题是否正在进行
     */
    private String proceedStatus;

    /**
     * 当前时间
     */
    private Date dateNow;

    public Date getDateNow() {
        return dateNow;
    }

    @JSONField
    public void setDateNow(Date dateNow) {
        this.dateNow = dateNow;
    }

    public String getProceedStatus() {
        return proceedStatus;
    }

    public void setProceedStatus(String proceedStatus) {
        this.proceedStatus = proceedStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }
}