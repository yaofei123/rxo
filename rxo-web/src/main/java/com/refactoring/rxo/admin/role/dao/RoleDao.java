package com.refactoring.rxo.admin.role.dao;

import com.refactoring.rxo.entity.account.Role;
import com.refactoring.rxo.datasource.mybatis.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleDao extends BaseDao<Role> {
}
