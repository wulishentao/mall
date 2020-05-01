package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: GlobalSearchReqDto.java
 * @author: Beau
 * @create: 2020/05/01 15:39
 * @version: 1.0.0
 **/
@Data
public class GlobalSearchReqDto extends CommonReqDTO implements Serializable {
    /**
     * 搜索内容
     */
    private String title;

    /**
     * 书籍类型
     */
    private String bookType;

    private Integer pageNo;

    private Integer pageSize;
}
