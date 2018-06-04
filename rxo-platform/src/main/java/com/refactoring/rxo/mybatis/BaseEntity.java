package com.refactoring.rxo.mybatis;


import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * @author: fei.yao
 * @date: 2018/5/28
 * @modified by:
 */
public class BaseEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID
     */
    private String id;

//    private String tenantId;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getTenantId() {
//        return tenantId;
//    }
//    public void setTenantId(String tenantId) {
//        this.tenantId = tenantId;
//    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
