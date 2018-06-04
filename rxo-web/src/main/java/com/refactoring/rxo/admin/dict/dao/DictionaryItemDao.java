package com.refactoring.rxo.admin.dict.dao;


import com.refactoring.rxo.entity.dict.DictionaryItem;
import com.refactoring.rxo.mybatis.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
 * Created by FAI.YAO.
 */
@Mapper
@Repository
public interface DictionaryItemDao extends BaseDao<DictionaryItem> {


}
