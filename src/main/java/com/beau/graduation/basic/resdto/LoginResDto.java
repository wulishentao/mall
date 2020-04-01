package com.beau.graduation.basic.resdto;

import com.beau.graduation.common.CommonResDTO;

import java.io.Serializable;

/**
 * @classname: LoginResDto.java
 * @author: Beau
 * @create: 2020/03/28 17:43
 * @version: 1.0.0
 **/
public class LoginResDto extends CommonResDTO implements Serializable {
    private String id;
    private String accountName;
    private String accountType;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
