package com.refactoring.rxo.admin.roleResourceMapping.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.refactoring.rxo.admin.roleResourceMapping.dao.RoleResourceMappingDao;
import com.refactoring.rxo.admin.roleResourceMapping.service.RoleResourceMappingService;
import com.refactoring.rxo.entity.account.RoleResourceMapping;
import org.springframework.stereotype.Service;

@Service("roleResourceMappingService")
public class RoleResourceMappingServiceImpl  extends ServiceImpl<RoleResourceMappingDao, RoleResourceMapping> implements RoleResourceMappingService {
}
