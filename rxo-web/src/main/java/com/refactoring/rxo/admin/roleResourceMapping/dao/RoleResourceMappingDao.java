package com.refactoring.rxo.admin.roleResourceMapping.dao;

import com.refactoring.rxo.entity.account.RoleResourceMapping;
import com.refactoring.rxo.mybatis.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleResourceMappingDao extends BaseDao<RoleResourceMapping> {
}
