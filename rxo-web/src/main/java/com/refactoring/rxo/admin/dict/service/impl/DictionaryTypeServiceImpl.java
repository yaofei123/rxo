package com.refactoring.rxo.admin.dict.service.impl;

import com.refactoring.rxo.entity.dict.DictionaryType;
import com.refactoring.rxo.admin.dict.dao.DictionaryTypeDao;
import com.refactoring.rxo.admin.dict.service.DictionaryTypeService;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;



@Service("dictionaryTypeService")
public class DictionaryTypeServiceImpl extends ServiceImpl<DictionaryTypeDao,DictionaryType> implements DictionaryTypeService{

    @Override
    public Page<DictionaryType> findPage(Page<DictionaryType> dictionaryTypePage, DictionaryType dictionaryTypeCondition) {
        return this.selectPage(dictionaryTypePage, new EntityWrapper<>(dictionaryTypeCondition));
    }


}
