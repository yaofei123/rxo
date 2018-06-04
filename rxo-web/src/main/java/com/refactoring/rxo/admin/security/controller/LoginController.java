package com.refactoring.rxo.admin.security.controller;


import com.refactoring.rxo.admin.security.domain.SecurityUser;
import com.refactoring.rxo.admin.account.service.AccountService;
import com.refactoring.rxo.entity.account.Account;
import com.refactoring.rxo.entity.account.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 管理账号
 */
@RestController
@RequestMapping(value = "/security/login")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AccountService accountService;

    /**
     * 获取可用资源
     *
     * @return
     */
    @PostMapping("/account")
    public Account getAccount() {

        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetails.getAccount();
    }

    /**
     * 获取可用资源
     *
     * @return
     */
    @GetMapping("/getResource/{name}")
    public List<Resource> getResource(@PathVariable("name") String name) {

        SecurityUser userDetails = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return accountService.findSystemAccountResourceTree(username);
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login.html?logout";
    }


}
