package com.refactoring.rxo.admin.account.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.refactoring.rxo.admin.account.service.AccountService;
import com.refactoring.rxo.entity.account.Account;
import com.refactoring.rxo.springmvc.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 管理账号
 */
@RestController
@RequestMapping(value = "/admin/account")
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    private AccountService accountService;

    /**
     * 新增账号
     *
     * @param account
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account add(@RequestBody @Valid Account account) {

        accountService.insert(account);

        return account;
    }

    /**
     * 删除账号,id以逗号分隔
     *
     * @param idArray
     */
    @DeleteMapping(value = "/{idArray}")
    public void delete(@PathVariable String idArray) {

        LOGGER.debug("delete account :{}", idArray);
        accountService.deleteBatchIds(Arrays.asList(idArray, String.class));
    }

    /**
     * 更新账号
     *
     * @param account
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Account update(@RequestBody @Valid Account account, @PathVariable String id) {
        account.setId(id);
        accountService.updateById(account);

        return account;
    }

    /**
     * 修改密码
     *
     * @param password
     * @param id
     * @return
     */
    @PutMapping(value = "/changePwd/{id}")
    public Account changePwd(@RequestBody @Valid String password, @PathVariable String id) {

//        accountService.changePwd(id, password);

        Account account = new Account();
        return account;
    }

    /**
     * 根据ID查询账号
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Account get(@PathVariable String id) {

        Account account = accountService.selectById(id);

        return account;
    }

    /**
     * 查询账号列表
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/list")
    public Page<Account> list(@RequestBody PageRequest<Account> pageRequest) {
        return accountService.findPage(new Page<>(pageRequest.getPage(), pageRequest.getLimit()), pageRequest.getCondition());
    }
}
