package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: GetBoughtBookPageReqDto.java
 * @author: Beau
 * @create: 2020/04/30 15:55
 * @version: 1.0.0
 **/
@Data
public class GetBoughtBookPageReqDto extends CommonReqDTO implements Serializable {
    private Long userId;
    private Integer pageNo;
    private Integer pageSize;
}
