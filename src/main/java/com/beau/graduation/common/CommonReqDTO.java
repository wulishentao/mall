package com.beau.graduation.common;

/**
 * request基础类
 * CommonReqDTO.java
 * @author beau
 * @date 2020/03/28
 */
public class CommonReqDTO {

    /**
     * 用户id
     */
    private String userId;

    /***
     * token
     */
    private String token;

    /**
     * 账号类型
     */
    private String accountType;



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}