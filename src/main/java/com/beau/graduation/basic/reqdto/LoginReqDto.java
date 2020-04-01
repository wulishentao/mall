package com.beau.graduation.basic.reqdto;

import com.beau.graduation.common.CommonReqDTO;

import java.io.Serializable;

/**
 * @classname: LoginReqDto.java
 * @author: Beau
 * @create: 2020/03/28 17:41
 * @version: 1.0.0
 **/
public class LoginReqDto extends CommonReqDTO implements Serializable {
    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
