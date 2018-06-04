package com.refactoring.rxo.admin.resource.service;

import com.baomidou.mybatisplus.service.IService;
import com.refactoring.rxo.entity.account.Resource;

import java.util.List;

public interface ResourceService  extends IService<Resource> {

    void sortResourceList(List<Resource> resourceList);
    void fillResource(List<Resource> resourceList);

    List<Resource> selectByName(String name);
}
