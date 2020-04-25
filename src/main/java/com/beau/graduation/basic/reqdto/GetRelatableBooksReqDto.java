package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: GetRelatableBooksReqDto.java
 * @author: Beau
 * @create: 2020/04/24 15:52
 * @version: 1.0.0
 **/
@Data
public class GetRelatableBooksReqDto extends CommonReqDTO implements Serializable {
    /**
     * 专题id
     */
    private Long topicId;

    /**
     * 书籍名称/出版社/作者
     */
    private String title;

    /**
     * 书籍类型
     */
    private String typeId;
    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 页面大小
     */
    private Integer pageSize;
}
