package com.refactoring.rxo.admin.account.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.refactoring.rxo.admin.role.service.RoleService;
import com.refactoring.rxo.admin.account.dao.AccountDao;
import com.refactoring.rxo.admin.account.service.AccountService;
import com.refactoring.rxo.admin.accountRoleMapping.service.AccountRoleMappingService;
import com.refactoring.rxo.admin.resource.service.ResourceService;
import com.refactoring.rxo.admin.roleResourceMapping.service.RoleResourceMappingService;
import com.refactoring.rxo.entity.account.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: fei.yao
 * @date: 2018/5/28
 * @modified by:
 */
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<AccountDao, Account> implements AccountService {

    @Autowired
    private RoleResourceMappingService roleResourceMappingService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private AccountRoleMappingService accountRoleMappingService;

    @Autowired
    private RoleService roleService;


    @Override
    public List<Resource> findResourceTreeForRole(List<String> roleIdList) {

        List<Resource> resourceList = new ArrayList<>();

        Set<String> resourceKeySet = new HashSet<>();

        for (String roleId : roleIdList) {
            if (resourceKeySet.contains(roleId)) {
                continue;
            }
            resourceKeySet.add(roleId);
            RoleResourceMapping roleResourceCondition = new RoleResourceMapping();
            roleResourceCondition.setRoleId(roleId);
            List<RoleResourceMapping> roleResourceMappingList = roleResourceMappingService.selectList(new EntityWrapper<>(roleResourceCondition));

            for (RoleResourceMapping roleResourceMapping : roleResourceMappingList) {
                Resource resource = resourceService.selectById(roleResourceMapping.getResourceId());
                if (resource != null) {
                    resourceList.add(resource);
                }
            }

        }

        resourceService.sortResourceList(resourceList);


        resourceService.fillResource(resourceList);


        //只获取父节点为-1的节点
        List<Resource> result = new ArrayList<>();
        for (Resource resource : resourceList) {
            if (resource.getParentId().equals(Resource.TOP_NODE_ID)) {
                result.add(resource);
            }
        }
        resourceService.sortResourceList(result);


        return result;
    }

    @Override
    public List<Resource> findSystemAccountResourceTree(String accountName) {
        Account accountCondition = new Account();
        accountCondition.setName(accountName);
        Account account = this.selectOne(new EntityWrapper<>(accountCondition));

        AccountRoleMapping accountRoleCondition = new AccountRoleMapping();
        accountRoleCondition.setAccountId(account.getId());

        List<AccountRoleMapping> roleMappings = accountRoleMappingService.selectList(new EntityWrapper<>(accountRoleCondition));

        List<String> roleIdList = new ArrayList<>();
        for (AccountRoleMapping mapping : roleMappings) {
            roleIdList.add(mapping.getRoleId());
        }

        if (CollectionUtils.isEmpty(roleIdList)) {

        }

        return this.findResourceTreeForRole(roleIdList);
    }

    @Override
    public List<Role> findRoleListByAccount(String accountName) {
        Account accountCondition = new Account();
        accountCondition.setName(accountName);
        Account account = this.selectOne(new EntityWrapper<>(accountCondition));

        AccountRoleMapping accountRoleCondition = new AccountRoleMapping();
        accountRoleCondition.setAccountId(account.getId());


        List<AccountRoleMapping> roleMappings = accountRoleMappingService.selectList(new EntityWrapper<>(accountRoleCondition));

        List<Role> roleList = new ArrayList<>();
        for (AccountRoleMapping mapping : roleMappings) {
            Role role = roleService.selectById(mapping.getRoleId());
            if (role != null) {
                roleList.add(role);
            }
        }
        return roleList;
    }

    @Override
    public Page<Account> findPage(Page<Account> accountPage, Account accountCondition) {
        return this.selectPage(accountPage, new EntityWrapper<>(accountCondition));
    }
}
