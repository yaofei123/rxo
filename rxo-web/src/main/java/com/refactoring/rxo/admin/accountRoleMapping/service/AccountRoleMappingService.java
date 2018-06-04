package com.refactoring.rxo.admin.accountRoleMapping.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.refactoring.rxo.entity.account.AccountRoleMapping;

public interface AccountRoleMappingService extends IService<AccountRoleMapping> {


    Page<AccountRoleMapping> findPage(Page<AccountRoleMapping> keywordPage, AccountRoleMapping keywordCondition);
}
