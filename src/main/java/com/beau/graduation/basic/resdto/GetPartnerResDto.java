package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;
import com.beau.graduation.common.Page;
import com.beau.graduation.model.PartnerInfo;

import java.io.Serializable;

/**
 * @classname: GetPartnerResDto.java
 * @author: Beau
 * @create: 2020/04/03 16:01
 * @version: 1.0.0
 **/
public class GetPartnerResDto extends CommonResDTO implements Serializable {
    private Page<PartnerInfo> infoPage;

    public Page<PartnerInfo> getInfoPage() {
        return infoPage;
    }

    public void setInfoPage(Page<PartnerInfo> infoPage) {
        this.infoPage = infoPage;
    }
}
