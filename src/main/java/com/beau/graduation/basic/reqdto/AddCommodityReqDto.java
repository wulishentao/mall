package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @classname: AddCommodityReqDto.java
 * @author: Beau
 * @create: 2020/04/05 15:23
 * @version: 1.0.0
 **/
public class AddCommodityReqDto extends CommonReqDTO implements Serializable {
    /**
     * 书籍名称
     */
    private String title;

    /**
     * 商品标签id集合
     */
    private String typeIds;

    /**
     * 书籍作者
     */
    private String author;

    /**
     * 出版商
     */
    private String publisher;

    /**
     * 出版日期
     */
    private String publishDate;

    /**
     * 书籍简介
     */
    private String introduction;

    /**
     * 编辑推荐
     */
    private String recommendFlg;

    /**
     * 商品库存
     */
    private Long reserve;

    /**
     * 商品单价
     */
    private BigDecimal price;

    /**
     * 排序规则
     */
    private String sort;

    /**
     * 商品图片
     */
    private MultipartFile file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(String typeIds) {
        this.typeIds = typeIds;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRecommendFlg() {
        return recommendFlg;
    }

    public void setRecommendFlg(String recommendFlg) {
        this.recommendFlg = recommendFlg;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
