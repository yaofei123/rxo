package com.refactoring.rxo.admin.account.dao;


import com.refactoring.rxo.entity.account.Account;
import com.refactoring.rxo.datasource.mybatis.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * Created by fai.yao.
 */
@Mapper
@Repository
public interface AccountDao extends BaseDao<Account> {

}
