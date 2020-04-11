package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: GetTopicPageReqDto.java
 * @author: Beau
 * @create: 2020/04/11 13:22
 * @version: 1.0.0
 **/
public class GetTopicPageReqDto extends CommonReqDTO implements Serializable {
    /**
     * 专题名称
     */
    private String topicTitle;

    /**
     * 是否推荐
     */
    private String status;

    private Integer pageNo;

    private Integer pageSize;

    /**
     * 专题是否正在进行
     */
    private String proceedStatus;

    public String getProceedStatus() {
        return proceedStatus;
    }

    public void setProceedStatus(String proceedStatus) {
        this.proceedStatus = proceedStatus;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
