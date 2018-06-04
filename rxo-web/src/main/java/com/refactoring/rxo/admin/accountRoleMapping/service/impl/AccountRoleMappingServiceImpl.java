package com.refactoring.rxo.admin.accountRoleMapping.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.refactoring.rxo.admin.accountRoleMapping.dao.AccountRoleMappingDao;
import com.refactoring.rxo.admin.accountRoleMapping.service.AccountRoleMappingService;
import com.refactoring.rxo.entity.account.AccountRoleMapping;
import org.springframework.stereotype.Service;


@Service("accountRoleMappingService")
public class AccountRoleMappingServiceImpl extends ServiceImpl<AccountRoleMappingDao, AccountRoleMapping> implements AccountRoleMappingService {

    @Override
    public Page<AccountRoleMapping> findPage(Page<AccountRoleMapping> mappingPage, AccountRoleMapping mappingCondition) {
        return this.selectPage(mappingPage, new EntityWrapper<>(mappingCondition));
    }
}
