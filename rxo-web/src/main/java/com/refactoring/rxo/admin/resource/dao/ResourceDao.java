package com.refactoring.rxo.admin.resource.dao;


import com.refactoring.rxo.entity.account.Resource;
import com.refactoring.rxo.mybatis.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by fai.yao.
 */
@Mapper
@Repository
public interface ResourceDao extends BaseDao<Resource> {

    List<Resource> selectByName(String name);
}
