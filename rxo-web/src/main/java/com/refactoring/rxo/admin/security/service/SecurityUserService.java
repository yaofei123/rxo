package com.refactoring.rxo.admin.security.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.refactoring.rxo.admin.security.domain.SecurityUser;
import com.refactoring.rxo.admin.account.service.AccountService;
import com.refactoring.rxo.entity.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by fai.yao on 2017/1/10.
 */
@Service
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SecurityUser securityUser = loadUserBySystemUsername(username);

        if (securityUser != null) {
            return securityUser;
        }
        throw new UsernameNotFoundException("用户名不存在");

    }

    private SecurityUser loadUserBySystemUsername(String username) {
        Account accountCondition = new Account();
        accountCondition.setName(username);
        Account account = accountService.selectOne(new EntityWrapper<>(accountCondition));

        if (account == null) {
            return null;
        }

        return new SecurityUser(account, accountService.findRoleListByAccount(username));
    }

}
