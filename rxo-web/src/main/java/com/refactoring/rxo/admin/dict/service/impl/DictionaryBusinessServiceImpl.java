package com.refactoring.rxo.admin.dict.service.impl;

import com.refactoring.rxo.admin.dict.service.DictionaryBusinessService;
import com.refactoring.rxo.entity.dict.DictionaryBusiness;
import com.refactoring.rxo.admin.dict.dao.DictionaryBusinessDao;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;



@Service("dictionaryBusinessService")
public class DictionaryBusinessServiceImpl extends ServiceImpl<DictionaryBusinessDao,DictionaryBusiness> implements DictionaryBusinessService {

    @Override
    public Page<DictionaryBusiness> findPage(Page<DictionaryBusiness> dictionaryBusinessPage, DictionaryBusiness dictionaryBusinessCondition) {
        return this.selectPage(dictionaryBusinessPage, new EntityWrapper<>(dictionaryBusinessCondition));
    }


}
