package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
     * 商品标签id
     */
    private Long typeId;

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
    private List<MultipartFile> files;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}
