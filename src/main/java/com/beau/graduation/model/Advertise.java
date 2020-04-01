package com.beau.graduation.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Advertise
 * @author beau 2020-03-28
 */
public class Advertise implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id  主键ID
     */
    private Long id;


    /**
     * ad_title  广告名称
     */
    private String adTitle;


    /**
     * begin_time  广告开始时间
     */
    private Date beginTime;


    /**
     * end_time  广告结束时间
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
     * del_flag  是否删除，0-否，1-是
     */
    private String delFlag;


    /**
     * ad_url  广告链接
     */
    private String adUrl;


    /**
     * ad_img  广告图片
     */
    private String adImg;


    /**
     * remark  广告备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getAdImg() {
        return adImg;
    }

    public void setAdImg(String adImg) {
        this.adImg = adImg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}