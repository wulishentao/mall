package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: EditCommodityTypeReqDto.java
 * @author: Beau
 * @create: 2020/04/13 14:41
 * @version: 1.0.0
 **/
public class EditCommodityTypeReqDto extends CommonReqDTO implements Serializable {
    private Long typeId;
    private Long parentId;
    private String typeTitle;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTypeTitle() {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle) {
        this.typeTitle = typeTitle;
    }
}
