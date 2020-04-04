package com.beau.graduation.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Book
 * @author beau 2020-03-28
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id  主键ID
     */
    private Long id;


    /**
     * title  图书名称
     */
    private String title;


    /**
     * author  作者
     */
    private String author;


    /**
     * sale_status  图书销售状态:[{key:sale_status,values:[{no:-1,alias:disable,remark:下架},{no:0,alias:enable,remark:在售},{no:1,alias:disable,remark:售罄}]}]
     */
    private String saleStatus;


    /**
     * review_status  图书审核状态:[{key:review_status,values:[{no:-1,alias:disable,remark:审核未通过},{no:0,alias:enable,remark:未审核},{no:1,alias:disable,remark:审核通过}]}]
     */
    private String reviewStatus;


    /**
     * publish_date  出版日期
     */
    private String publishDate;


    /**
     * publisher  出版商
     */
    private String publisher;


    /**
     * Introduction  书籍简介
     */
    private String introduction;


    /**
     * del_flag  是否删除，0-否，1-是
     */
    private String delFlag;


    /**
     * recommend_flag  编辑推荐，0-否，1-是
     */
    private String recommendFlag;


    /**
     * reserve  库存数量
     */
    private Long reserve;


    /**
     * price  商品单价
     */
    private BigDecimal price;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(String recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public Long getReserve() {
        return reserve;
    }

    public void setReserve(Long reserve) {
        this.reserve = reserve;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



}