package com.refactoring.rxo.admin.dict.service;

import com.refactoring.rxo.entity.dict.DictionaryType;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;


public interface DictionaryTypeService extends IService<DictionaryType>{

	Page<DictionaryType> findPage(Page<DictionaryType> dictionaryTypePage,DictionaryType dictionaryTypeCondition);


}
