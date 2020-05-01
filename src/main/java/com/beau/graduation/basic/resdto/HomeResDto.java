package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.model.PartnerInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: HomeResDto.java
 * @author: Beau
 * @create: 2020/04/30 13:29
 * @version: 1.0.0
 **/
@Data
public class HomeResDto extends CommonResDTO implements Serializable {
    private PartnerInfo partnerInfo;
}
