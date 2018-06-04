package com.refactoring.rxo.admin.role.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.refactoring.rxo.admin.role.dao.RoleDao;
import com.refactoring.rxo.entity.account.Role;
import com.refactoring.rxo.admin.role.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author: fei.yao
 * @date: 2018/5/28
 * @modified by:
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Override
    public Page<Role> findPage(Page<Role> rolePage, Role roleCondition) {
        return this.selectPage(rolePage, new EntityWrapper<>(roleCondition));
    }
}
