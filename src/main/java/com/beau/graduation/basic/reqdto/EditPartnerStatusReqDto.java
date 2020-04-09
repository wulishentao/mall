package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: EditPartnerStatusReqDto.java
 * @author: Beau
 * @create: 2020/04/09 17:55
 * @version: 1.0.0
 **/
public class EditPartnerStatusReqDto extends CommonReqDTO implements Serializable {
    private Long userId;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
