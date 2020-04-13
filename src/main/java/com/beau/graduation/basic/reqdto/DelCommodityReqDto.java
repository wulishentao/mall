package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @classname: DelCommodityReqDto.java
 * @author: Beau
 * @create: 2020/04/13 14:59
 * @version: 1.0.0
 **/
public class DelCommodityReqDto extends CommonReqDTO implements Serializable {
    private List<Long> bookIds;

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}
