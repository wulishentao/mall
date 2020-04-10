package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @classname: EditCommodityReqDto.java
 * @author: Beau
 * @create: 2020/04/09 18:25
 * @version: 1.0.0
 **/
public class EditCommodityReqDto extends AddCommodityReqDto implements Serializable {
    private Long bookId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
