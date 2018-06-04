package com.refactoring.rxo.admin.accountRoleMapping.dao;


import com.refactoring.rxo.entity.account.AccountRoleMapping;
import com.refactoring.rxo.mybatis.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * Created by fai.yao.
 */
@Mapper
@Repository
public interface AccountRoleMappingDao extends BaseDao<AccountRoleMapping> {
}
