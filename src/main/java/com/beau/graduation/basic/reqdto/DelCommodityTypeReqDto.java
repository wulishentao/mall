package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: DelCommodityTypeReqDto.java
 * @author: Beau
 * @create: 2020/04/12 18:29
 * @version: 1.0.0
 **/
public class DelCommodityTypeReqDto extends CommonReqDTO implements Serializable {
    private Long typeId;

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
