package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @classname: DelCartReqDto.java
 * @author: Beau
 * @create: 2020/04/12 14:55
 * @version: 1.0.0
 **/
public class DelCartReqDto extends CommonReqDTO implements Serializable {
    private List<Long> bookIds;

    public List<Long> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Long> bookIds) {
        this.bookIds = bookIds;
    }
}
