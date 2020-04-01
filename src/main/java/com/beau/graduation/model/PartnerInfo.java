package com.beau.graduation.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * PartnerInfo
 * @author beau 2020-03-28
 */
public class PartnerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id  主键ID
     */
    private Long id;


    /**
     * account_name  用户名
     */
    private String accountName;


    /**
     * account_type  账户类型:[{key:mechanism_account_type,values:[{no:0,alias:mechanism,remark:管理员},{no:1,alias:individual,remark:用户}]}]
     */
    private String accountType;


    /**
     * status  状态:[{key:mechanism_status,values:[{no:0,alias:disable,remark:停用},{no:1,alias:enable,remark:启用}]}]
     */
    private String status;


    /**
     * password  密码:MD5加密
     */
    private String password;


    /**
     * address  地址
     */
    private String address;


    /**
     * remark  备注
     */
    private String remark;


    /**
     * phone  电话号码
     */
    private String phone;


    /**
     * create_time  创建时间
     */
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;


    /**
     * update_time  修改时间
     */
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    /**
     * gender  性别: 0,男、1,女
     */
    private String gender;


    /**
     * del_flag  是否删除，0-否，1-是
     */
    private String delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}