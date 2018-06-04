package com.refactoring.rxo.admin.dict.dao;


import com.refactoring.rxo.entity.dict.DictionaryBusiness;
import com.refactoring.rxo.mybatis.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * Created by FAI.YAO.
 */
@Mapper
@Repository
public interface DictionaryBusinessDao extends BaseDao<DictionaryBusiness> {


}
