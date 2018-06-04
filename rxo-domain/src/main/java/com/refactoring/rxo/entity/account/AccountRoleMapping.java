package com.refactoring.rxo.entity.account;

import com.refactoring.rxo.mybatis.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 用户角色映射
 */
public class AccountRoleMapping extends BaseEntity<AccountRoleMapping> {

    //@Version
    private Integer version;


    /**
     * 账号ID
     */
    private String accountId;


    /**
     * 角色ID
     */
    private String roleId;


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
