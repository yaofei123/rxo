package com.refactoring.rxo.entity.account;

import com.refactoring.rxo.mybatis.BaseEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 角色资源映射
 */
public class RoleResourceMapping extends BaseEntity<RoleResourceMapping> {

    public static final String PROPERTY_ROLE_ID = "roleId";
    public static final String PROPERTY_RESOURCE_ID = "resourceId";


    private String id;

    private Integer version;


    /**
     * 角色ID
     */
    private String roleId;


    /**
     * 资源ID
     */
    private String resourceId;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
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
