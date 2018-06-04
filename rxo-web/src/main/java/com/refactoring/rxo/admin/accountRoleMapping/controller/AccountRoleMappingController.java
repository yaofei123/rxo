package com.refactoring.rxo.admin.accountRoleMapping.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.refactoring.rxo.entity.account.AccountRoleMapping;
import com.refactoring.rxo.admin.accountRoleMapping.service.AccountRoleMappingService;
import com.refactoring.rxo.admin.account.service.AccountService;
import com.refactoring.rxo.springmvc.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 管理用户角色映射
 */
@RestController
@RequestMapping(value = "/admin/accountRoleMapping")
public class AccountRoleMappingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRoleMappingController.class);


    @Autowired
    private AccountRoleMappingService accountRoleMappingService;

    @Autowired
    private AccountService accountService;


    /**
     * 新增用户角色映射
     *
     * @param accountRoleMapping
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountRoleMapping add(@RequestBody @Valid AccountRoleMapping accountRoleMapping) {

        accountRoleMappingService.insert(accountRoleMapping);

        return accountRoleMapping;
    }

    /**
     * 删除用户角色映射,id以逗号分隔
     *
     * @param idArray
     */
    @DeleteMapping(value = "/{idArray}")
    public void delete(@PathVariable String idArray) {

        LOGGER.debug("delete accountRoleMapping :{}", idArray);

        String[] ids = idArray.split(",");
        accountRoleMappingService.deleteBatchIds(Arrays.asList(ids, String.class));
    }

    /**
     * 更新用户角色映射
     *
     * @param accountRoleMapping
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public AccountRoleMapping update(@RequestBody @Valid AccountRoleMapping accountRoleMapping, @PathVariable String id) {
        accountRoleMapping.setId(id);
        accountRoleMappingService.updateById(accountRoleMapping);

        return accountRoleMapping;
    }

    /**
     * 根据ID查询用户角色映射
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public AccountRoleMapping get(@PathVariable String id) {

        return accountRoleMappingService.selectById(id);
    }

    /**
     * 查询用户角色映射列表
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/list")
    public Page<AccountRoleMapping> list(@RequestBody PageRequest<AccountRoleMapping> pageRequest) {
        return accountRoleMappingService.findPage(new Page<>(pageRequest.getPage(), pageRequest.getLimit()), pageRequest.getCondition());
    }
}
