package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import com.beau.graduation.common.CommonResDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: NewBookHotPageReqDto.java
 * @author: Beau
 * @create: 2020/05/01 17:02
 * @version: 1.0.0
 **/
@Data
public class NewBookHotPageReqDto extends CommonReqDTO implements Serializable {
    private Long typeId;

    private Integer pageNo;

    private Integer pageSize;
}
