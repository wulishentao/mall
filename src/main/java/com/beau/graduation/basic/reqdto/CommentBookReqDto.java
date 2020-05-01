package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: CommentBookReqDto.java
 * @author: Beau
 * @create: 2020/04/30 10:57
 * @version: 1.0.0
 **/
@Data
public class CommentBookReqDto extends CommonReqDTO implements Serializable {
    /**
     * 订单号
     */
    private String orderId;

    private Long bookId;

    /**
     * 评论内容
     */
    private String remark;
}
