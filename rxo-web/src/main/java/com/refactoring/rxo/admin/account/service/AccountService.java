package com.refactoring.rxo.admin.account.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.refactoring.rxo.entity.account.Account;
import com.refactoring.rxo.entity.account.Resource;
import com.refactoring.rxo.entity.account.Role;

import java.util.List;


public interface AccountService extends IService<Account> {

    Page<Account> findPage(Page<Account> keywordPage, Account keywordCondition);

    List<Resource> findResourceTreeForRole(List<String> roleIdList);

    List<Resource> findSystemAccountResourceTree(String accountName);

    List<Role> findRoleListByAccount(String accountName);
}
