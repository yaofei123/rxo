package com.refactoring.rxo.admin.dict.service.impl;

import com.refactoring.rxo.entity.dict.DictionaryItem;
import com.refactoring.rxo.admin.dict.dao.DictionaryItemDao;
import com.refactoring.rxo.admin.dict.service.DictionaryItemService;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;



@Service("dictionaryItemService")
public class DictionaryItemServiceImpl extends ServiceImpl<DictionaryItemDao,DictionaryItem> implements DictionaryItemService{

    @Override
    public Page<DictionaryItem> findPage(Page<DictionaryItem> dictionaryItemPage, DictionaryItem dictionaryItemCondition) {
        return this.selectPage(dictionaryItemPage, new EntityWrapper<>(dictionaryItemCondition));
    }


}
