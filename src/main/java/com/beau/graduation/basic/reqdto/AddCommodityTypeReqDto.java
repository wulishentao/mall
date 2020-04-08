package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: AddCommodityTypeReqDto.java
 * @author: Beau
 * @create: 2020/04/05 15:02
 * @version: 1.0.0
 **/
public class AddCommodityTypeReqDto extends CommonReqDTO implements Serializable {
    /**
     * 标签名称
     */
    private String title;

    /**
     * 父级id(若为-1则为一级类别)
     */
    private Long parentId;

    /**
     * 标签备注
     */
    private String remark;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
