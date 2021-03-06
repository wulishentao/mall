package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: RecommendReqDto.java
 * @author: Beau
 * @create: 2020/05/01 21:21
 * @version: 1.0.0
 **/
@Data
public class RecommendReqDto extends CommonReqDTO implements Serializable {
    private Long typeId;

    private Integer pageNo;

    private Integer pageSize;
}
