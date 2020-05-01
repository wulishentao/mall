package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: EditReqDto.java
 * @author: Beau
 * @create: 2020/04/30 13:50
 * @version: 1.0.0
 **/
@Data
public class EditReqDto extends CommonReqDTO implements Serializable {
    private Long userId;
    private String accountName;
    private String phone;
    private String address;
}
