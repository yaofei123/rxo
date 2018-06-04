package com.refactoring.rxo.admin.role.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.refactoring.rxo.entity.account.Role;

/**
 * @author fai.yao
 */
public interface RoleService extends IService<Role> {


    /**
     * 分页查询列表信息
     * @param rolePage
     * @param roleCondition
     * @return
     */
    Page<Role> findPage(Page<Role> rolePage, Role roleCondition);
}
