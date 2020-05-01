package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: GetNewBookReqDto.java
 * @author: Beau
 * @create: 2020/05/01 16:32
 * @version: 1.0.0
 **/
@Data
public class GetNewBookReqDto extends CommonReqDTO implements Serializable {
    private Integer pageNo;

    private Integer pageSize;

    private Long typeId;
}
