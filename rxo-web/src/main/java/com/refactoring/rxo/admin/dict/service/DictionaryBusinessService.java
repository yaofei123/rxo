package com.refactoring.rxo.admin.dict.service;

import com.refactoring.rxo.entity.dict.DictionaryBusiness;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;


public interface DictionaryBusinessService extends IService<DictionaryBusiness>{

	Page<DictionaryBusiness> findPage(Page<DictionaryBusiness> dictionaryBusinessPage,DictionaryBusiness dictionaryBusinessCondition);


}
