package com.refactoring.rxo.admin.role.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.refactoring.rxo.admin.role.service.RoleService;
import com.refactoring.rxo.entity.account.Role;
import com.refactoring.rxo.springmvc.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * @author: fei.yao
 * @date: 2018/5/29
 * @modified by:
 */
@RestController
@RequestMapping(value = "/admin/role")
public class RoleController {


    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);


    @Autowired
    private RoleService roleService;

    /**
     * 新增账号
     *
     * @param role
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role add(@RequestBody @Valid Role role) {

        roleService.insert(role);

        return role;
    }

    /**
     * 删除账号,id以逗号分隔
     *
     * @param idArray
     */
    @DeleteMapping(value = "/{idArray}")
    public void delete(@PathVariable String idArray) {

        LOGGER.debug("delete Role :{}", idArray);
        roleService.deleteBatchIds(Arrays.asList(idArray, String.class));
    }

    /**
     * 更新角色
     *
     * @param role
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Role update(@RequestBody @Valid Role role, @PathVariable String id) {
        role.setId(id);
        roleService.updateById(role);

        return role;
    }

    /**
     * 根据ID查询角色
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Role get(@PathVariable String id) {

        return roleService.selectById(id);
    }

    /**
     * 查询角色列表
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/list")
    public Page<Role> list(@RequestBody PageRequest<Role> pageRequest) {
        return roleService.findPage(new Page<>(pageRequest.getPage(), pageRequest.getLimit()), pageRequest.getCondition());
    }
}
