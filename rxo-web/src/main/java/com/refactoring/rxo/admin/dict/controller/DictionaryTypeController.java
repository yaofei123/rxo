package com.refactoring.rxo.admin.dict.controller;



import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.refactoring.rxo.admin.dict.service.DictionaryTypeService;
import com.refactoring.rxo.entity.dict.DictionaryType;
import com.refactoring.rxo.springmvc.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * 管理数据字典类型
 */
@RestController
@RequestMapping(value = "/admin/dict/dictionaryType")
public class DictionaryTypeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryTypeController.class);



	@Autowired
	private DictionaryTypeService dictionaryTypeService;


	/**
	 * 新增数据字典类型
	 * @param dictionaryType
	 * @return
	 */
	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public DictionaryType add(@RequestBody @Valid DictionaryType dictionaryType){

		dictionaryTypeService.insert(dictionaryType);

		return  dictionaryType;
	}

	/**
	 * 删除数据字典类型,id以逗号分隔
	 * @param idArray
	 */
	@DeleteMapping(value="/{idArray}")
	public void delete(@PathVariable String idArray){

	    LOGGER.debug("delete dictionaryType :{}", idArray);

	    String[] ids = idArray.split(",");
	    dictionaryTypeService.deleteBatchIds(Arrays.asList(ids));
	}

	/**
	 * 更新数据字典类型
	 * @param dictionaryType
	 * @param id
	 * @return
	 */
	@PutMapping(value="/{id}")
	public	DictionaryType update(@RequestBody @Valid DictionaryType dictionaryType, @PathVariable String id){
		dictionaryType.setId(id);
		dictionaryTypeService.updateById(dictionaryType);

		return  dictionaryType;
	}

	/**
	 * 根据ID查询数据字典类型
	 * @param id
	 * @return
	 */
	@GetMapping(value="/{id}")
	public  DictionaryType get(@PathVariable String id) {

		DictionaryType dictionaryType = dictionaryTypeService.selectById(id);

		return dictionaryType;
	}

	/**
	 * 查询数据字典类型列表
	 * @param pageRequest
	 * @return
	 */
	@PostMapping("/list")
	public Page<DictionaryType> list(@RequestBody PageRequest<DictionaryType> pageRequest){


        return dictionaryTypeService.findPage(new Page<>(pageRequest.getPage(), pageRequest.getLimit()), pageRequest.getCondition());

	}

	/**
	 * 查询数据字典业务列表
	 *
	 * @param
	 * @return
	 */
	@GetMapping("/listAll/{businessId}")
	public List<DictionaryType> list(@PathVariable String businessId) {

		return dictionaryTypeService.selectList(new EntityWrapper<>(new DictionaryType(businessId)));

	}

}
