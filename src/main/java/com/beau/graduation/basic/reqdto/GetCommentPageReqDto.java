package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: GetCommentPageReqDto.java
 * @author: Beau
 * @create: 2020/05/01 13:47
 * @version: 1.0.0
 **/
@Data
public class GetCommentPageReqDto extends CommonReqDTO implements Serializable {
    private Long userId;

    /**
     * 是否评论
     */
    private String ifComment;

    private Integer pageNo;

    private Integer pageSize;
}
