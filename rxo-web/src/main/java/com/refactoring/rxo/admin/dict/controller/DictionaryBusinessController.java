package com.refactoring.rxo.admin.dict.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.refactoring.rxo.admin.dict.service.DictionaryBusinessService;
import com.refactoring.rxo.entity.dict.DictionaryBusiness;
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
 * 管理数据字典业务
 */
@RestController
@RequestMapping(value = "/admin/dict/dictionaryBusiness")
public class DictionaryBusinessController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryBusinessController.class);


    @Autowired
    private DictionaryBusinessService dictionaryBusinessService;


    /**
     * 新增数据字典业务
     *
     * @param dictionaryBusiness
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DictionaryBusiness add(@RequestBody @Valid DictionaryBusiness dictionaryBusiness) {

        dictionaryBusinessService.insert(dictionaryBusiness);

        return dictionaryBusiness;
    }

    /**
     * 删除数据字典业务,id以逗号分隔
     *
     * @param idArray
     */
    @DeleteMapping(value = "/{idArray}")
    public void delete(@PathVariable String idArray) {

        LOGGER.debug("delete dictionaryBusiness :{}", idArray);

        String[] ids = idArray.split(",");
        dictionaryBusinessService.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 更新数据字典业务
     *
     * @param dictionaryBusiness
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    public DictionaryBusiness update(@RequestBody @Valid DictionaryBusiness dictionaryBusiness, @PathVariable String id) {
        dictionaryBusiness.setId(id);
        dictionaryBusinessService.updateById(dictionaryBusiness);

        return dictionaryBusiness;
    }

    /**
     * 根据ID查询数据字典业务
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public DictionaryBusiness get(@PathVariable String id) {

        DictionaryBusiness dictionaryBusiness = dictionaryBusinessService.selectById(id);

        return dictionaryBusiness;
    }

    /**
     * 查询数据字典业务列表
     *
     * @param pageRequest
     * @return
     */
    @PostMapping("/list")
    public Page<DictionaryBusiness> list(@RequestBody PageRequest<DictionaryBusiness> pageRequest) {


        return dictionaryBusinessService.findPage(new Page<>(pageRequest.getPage(), pageRequest.getLimit()), pageRequest.getCondition());

    }

    /**
     * 查询数据字典业务列表
     *
     * @param
     * @return
     */
    @GetMapping("/listAll")
    public List<DictionaryBusiness> list() {

        return dictionaryBusinessService.selectList(new EntityWrapper<>());

    }


}
