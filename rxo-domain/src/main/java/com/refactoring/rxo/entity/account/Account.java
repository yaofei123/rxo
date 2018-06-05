package com.refactoring.rxo.entity.account;


import com.refactoring.rxo.datasource.mybatis.BaseEntity;

import java.util.Date;


/**
 * 账号
 */
public class Account extends BaseEntity<Account> {

    private Integer version;

    /**
     * 账号
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 是否启用
     */
    private Boolean inUse;


    /**
     * 用户姓名
     */
    private String name;


    /**
     * 性别
     */
    private String sexCode;

    /**
     * 身份证号
     */
    private String idCard;

    private String mobile;


    /**
     * 超级管理员
     */
    private Boolean superMaster;


    /**
     * 启用
     */
    private Boolean enable;


    /**
     * 创建时间
     */
    private Date createTime;


    /**
     * 最后更新时间
     */
    private Date updateTime;


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Boolean getSuperMaster() {
        return superMaster;
    }

    public void setSuperMaster(Boolean superMaster) {
        this.superMaster = superMaster;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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
}
