package com.refactoring.rxo.admin.dict.service;

import com.refactoring.rxo.entity.dict.DictionaryItem;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;


public interface DictionaryItemService extends IService<DictionaryItem>{

	Page<DictionaryItem> findPage(Page<DictionaryItem> dictionaryItemPage,DictionaryItem dictionaryItemCondition);


}
